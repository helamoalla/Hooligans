<?php

namespace App\Controller;
use Symfony\Component\Mailer\Transport\TransportInterface;
use App\Entity\Bonplan;
use App\Entity\Feedback;
use App\Form\BonplanFormType;
use App\Form\FeedbackFormType;
use App\Form\SearchType;
use App\Repository\BonplanRepository;
use App\Repository\FeedbackRepository;
use App\Repository\UserRepository;
use Doctrine\Persistence\ManagerRegistry;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use Symfony\Component\Mime\Attachment;



class BonplanController extends AbstractController
{
    #[Route('/allBonplan', name: 'all_bonplan')]
    public function allBonplan(BonplanRepository $bonplanRep, Request $request,PaginatorInterface $paginator,UserRepository $userRep ): Response
    {
        $user=$this->getUser();
        $recentBonplan = $bonplanRep->getRecentWithFeedbacks();

        if ($request->isMethod("POST")) {
            $name = $request->get('search');
            $allBonplan = $bonplanRep->search($name);
        } else {
            $allBonplan = $bonplanRep->getAllBonPlanWithFeedbacks();
        }

        $allBonplan = $paginator->paginate(
            $allBonplan, /* query NOT result */
            $request->query->getInt('page', 1)/*page number*/,

            6/*limit per page*/,
           
        );
       
        $pageNumber = $allBonplan->getCurrentPageNumber(); 

        return $this->render('bonplan/allBonplan.html.twig', [
            'allBonplan' => $allBonplan,
            'recent'=>$recentBonplan,
            'pagenum'=>$pageNumber ,
            'user'=>$user
        ]);
    }
    #[Route('/bonplan/{id}', name: 'detail_bonplan')]
    public function detailBonPlan(ManagerRegistry $doctrine, Request $request, UserRepository $userRep, BonplanRepository $bonplanRep, $id, FeedbackRepository $feedRep,TransportInterface $mailer): Response
    {

        $bonplan = $bonplanRep->find($id);
        $bonplanDetail = $bonplanRep->getBonPlanWithFeedbacks($bonplan);
        $feeds = $feedRep->getFeedbackByBonPlan($bonplan);
        $user=$this->getUser();
        $feedback = new Feedback();
        $form = $this->createForm(FeedbackFormType::class, $feedback);

        if ($bonplanRep->checkIfAlreadyReported($bonplan, $user) > 0) {
            $form->add('report', null, [
                'disabled' => true,
            ]);
            $feedback->setReport(false);
        }
        if ($bonplanRep->checkIfAlreadyRated($bonplan, $user) > 0) {
            $form->add('rate', null, [
                'disabled' => true,
                'attr' => [
                    'style' => 'display:none',
                ],
            ]);
            $feedback->setRate(-1);
        }
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $doctrine->getManager();
            $feedback->setUser($user);
            $feedback->setBonplan($bonplan);

            $em->persist($feedback);
            $em->flush();

            // bonplan reporting
            $reportLines = $bonplanRep->count_reports();
            foreach ($reportLines as $line) {
                $bonplanReported = $line[0]->getBonplan();
                $reportCount = $line['report_count'];
                if ($reportCount >= 5) {
                    $email = (new Email())
                        ->from('helamoalla91@gmail.com')
                        ->to($bonplanReported->getUser()->getEmail())
                        ->subject('Be careful !')
                        ->text("Your bonplan ". $bonplanReported->getNomBonplan() ." has been deleted due to mutliple reports  !");
                        //->html('<p>See Twig integration for better HTML integration!</p>');

                    $mailer->send($email);
                    $em->remove($bonplanReported);
                    $em->flush();
                    return $this->redirectToRoute("all_bonplan");
                }
            }

            return $this->redirectToRoute("detail_bonplan", ["id" => $id]);
        }

        return $this->renderForm("bonplan/detailBonplan.html.twig", [
            "f" => $form,
            'bonplan' => $bonplanDetail,
            'feeds' => $feeds,
            'user'=>$user
        ]);
    }




    // ajouter bonPlan

    #[Route('/addBonplan', name: 'addBonplan')]
    public function addBonplan(ManagerRegistry $doctrine, Request $request, UserRepository $userRep): Response
    {
        $user=$this->getUser();

        $bonplan = new Bonplan();
        $form = $this->createForm(BonplanFormType::class, $bonplan);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $doctrine->getManager();
            $bonplan->setUser($user);
            $imageFile = $form->get('imageFile')->getData();

            if ($imageFile) {
                $imagesDirectory = 'C:/xampp/htdocs/images';
                $originalFilename = $imageFile->getClientOriginalName();
                $filenameWithoutSpaces = str_replace(' ', '_', $originalFilename);

                try {
                    $imageFile->move($imagesDirectory, $filenameWithoutSpaces);
                } catch (FileException $e) {
                    // Handle exception
                }

                $bonplan->setImage($filenameWithoutSpaces);
            }



            //$bonplan->setImage("img.jpg");
            $bonplan->setEtat('en attente');
            $em->persist($bonplan);
            $em->flush();
            return $this->redirectToRoute("all_bonplan");
        }

        return $this->renderForm(
            "bonplan/addBonplan.html.twig",
            array("f" => $form)
        );
    }

    #[Route('/updateBonplan/{id}', name: 'updateBonplanUser')]
    public function updatBonPlan($id, ManagerRegistry $doctrine, Request $request, BonplanRepository $bonplanRep): Response
    {
        $bonplan = $bonplanRep->find($id);
        $form = $this->createForm(BonplanFormType::class, $bonplan);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $em = $doctrine->getManager();
            $imageFile = $form->get('imageFile')->getData();

            if ($imageFile) {
                $imagesDirectory = 'C:/xampp/htdocs/images';
                $originalFilename = $imageFile->getClientOriginalName();
                $filenameWithoutSpaces = str_replace(' ', '_', $originalFilename);

                try {
                    $imageFile->move($imagesDirectory, $filenameWithoutSpaces);
                } catch (FileException $e) {
                    // Handle exception
                }

                $bonplan->setImage($filenameWithoutSpaces);
            }

            $em->flush();

            return $this->redirectToRoute('all_bonplan');
        }

        return $this->renderForm(
            "bonplan/updateBonplan.html.twig",
            [
                "f" => $form,
                "b" => $bonplan,
            ]
            // array("f" => $form)
        );
    }
    #[Route('/deleteBonplan/{id}', name: 'delete_Bonplan')]
    public function supprimerC($id, BonplanRepository $bonplanRep, ManagerRegistry $doctrine): Response
    {
        // methide de revuperation finAll() find($id) findBy()
        $bonplan = $bonplanRep->find($id);
        // Action supprision
        // methode de persistance : persist() remove() flush()
        $em = $doctrine->getManager();
        $em->remove($bonplan);
        // flush heya el commit
        $em->flush();
        return $this->redirectToRoute('all_bonplan');
    }


    /////////////////////////admiiiinnnn/////////////////


    #[Route('/admin', name: 'app_admin')]
    public function index(UserRepository $userRep): Response
    {
        $user=$this->getUser();
        return $this->render('admin/indexx.html.twig', [
            'user' => $user,
        ]);
    }
    #[Route('/admAllBonplan', name: 'admin_all_bonplan')]
    public function adminAllBonplan(BonplanRepository $bonplanRep ,UserRepository $userRep): Response
    {
        $allBonplan = $bonplanRep->orderById();
        $user=$this->getUser();
        return $this->render('admin/allBonplan.html.twig', [
            'allBonplan' => $allBonplan,
            'user' => $user,
        ]);
    }
    // ajouter bonPlan

    #[Route('/admAddBonplan', name: 'admin_add_Bonplan')]
    public function addBonplanA(ManagerRegistry $doctrine, Request $request, UserRepository $userRep): Response
    {
        $user=$this->getUser();
        $admin=$userRep->find(22);
        $bonplan = new Bonplan();
        $form = $this->createForm(BonplanFormType::class, $bonplan);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $em = $doctrine->getManager();
            $bonplan->setUser($user);
            $imageFile = $form->get('imageFile')->getData();

            if ($imageFile) {
                $imagesDirectory = 'C:/xampp/htdocs/images';
                $originalFilename = $imageFile->getClientOriginalName();
                $filenameWithoutSpaces = str_replace(' ', '_', $originalFilename);

                try {
                    $imageFile->move($imagesDirectory, $filenameWithoutSpaces);
                } catch (FileException $e) {
                    // Handle exception
                }

                $bonplan->setImage($filenameWithoutSpaces);
            }



            $bonplan->setEtat("accepté");
            $em->persist($bonplan);
            $em->flush();
            return $this->redirectToRoute('admin_all_bonplan');
        }

        return $this->renderForm(
            "admin/addBonPlan.html.twig",
            array("f" => $form,'user' => $admin)
        );
    }

    // supprimer bonPlan
    #[Route('/admDeleteBonplan/{id}', name: 'admin_delete_Bonplan')]
    public function supprimerCA($id, BonplanRepository $bonplanRep, ManagerRegistry $doctrine): Response
    {
        // methide de revuperation finAll() find($id) findBy()
        $bonplan = $bonplanRep->find($id);
        
        // Action supprision
        // methode de persistance : persist() remove() flush()
        $em = $doctrine->getManager();
        $em->remove($bonplan);
        // flush heya el commit
        $em->flush();
        return $this->redirectToRoute('admin_all_bonplan');
    }

    // validate bonPlan
    #[Route('/admValidateBonplan/{id}', name: 'admin_validate_Bonplan')]
    public function validateBonplan($id, BonplanRepository $bonplanRep, ManagerRegistry $doctrine): Response
    {
        // methide de revuperation finAll() find($id) findBy()
        $bonplan = $bonplanRep->find($id);
        
        // Action supprision
        // methode de persistance : persist() remove() flush()
        $em = $doctrine->getManager();
        $bonplanRep->validateBonplan($bonplan);
        // flush heya el commit
        $em->flush();
        return $this->redirectToRoute('admin_all_bonplan');
    }
    // ajouter bonPlan

    #[Route('/admUpdateBonplan/{id}', name: 'updateBonplan')]
    public function updatBonPlanA( $id,ManagerRegistry $doctrine, Request $request, BonplanRepository $bonplanRep,UserRepository $userRep): Response
    {
        $bonplan = $bonplanRep->find($id);
        $user=$this->getUser();
        $form = $this->createForm(BonplanFormType::class, $bonplan);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $em = $doctrine->getManager();
            $imageFile = $form->get('imageFile')->getData();

            if ($imageFile) {
                $imagesDirectory = 'C:/xampp/htdocs/images';
                $originalFilename = $imageFile->getClientOriginalName();
                $filenameWithoutSpaces = str_replace(' ', '_', $originalFilename);

                try {
                    $imageFile->move($imagesDirectory, $filenameWithoutSpaces);
                } catch (FileException $e) {
                    // Handle exception
                }

                $bonplan->setImage($filenameWithoutSpaces);
            }

            $em->flush();

            return $this->redirectToRoute('admin_all_bonplan');
        }

        return $this->renderForm(
            "admin/updateBonplan.html.twig",["f" => $form,
            "b"=>$bonplan,
            'user' => $user,
            ]
            // array("f" => $form)
        );
    }

    //all feedback 

    #[Route('/admAllFeeds', name: 'admin_all_Feeds')]
    public function adminAllFeed(FeedbackRepository $feedRep,UserRepository $userRep): Response
    {
        $allFeeds = $feedRep->orderById();
        $user=$this->getUser();
        return $this->render('admin/allFeeds.html.twig', [
            'allFeeds' => $allFeeds,
            'user' => $user
        ]);
    }

    // supprimer Feedback
    #[Route('/admDeleteFeedback/{id}', name: 'admin_delete_Feed')]
    public function adminDeleteFeedback($id, FeedbackRepository $feedRep, ManagerRegistry $doctrine): Response
    {
        // methide de revuperation finAll() find($id) findBy()
        $feedback = $feedRep->find($id);
        // Action supprision
        // methode de persistance : persist() remove() flush()
        $em = $doctrine->getManager();
        $em->remove($feedback);
        // flush heya el commit
        $em->flush();
        return $this->redirectToRoute('admin_all_Feeds');
    }
}
