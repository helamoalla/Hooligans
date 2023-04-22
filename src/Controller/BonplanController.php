<?php

namespace App\Controller;

use App\Entity\Bonplan;
use App\Entity\Feedback;
use App\Form\BonplanFormType;
use App\Form\FeedbackFormType;
use App\Repository\BonplanRepository;
use App\Repository\FeedbackRepository;
use App\Repository\UserRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\SerializerInterface;

class BonplanController extends AbstractController
{
    #[Route('/allBonplan', name: 'all_bonplan')]
    public function allBonplan(BonplanRepository $bonplanRep,Request $request,SerializerInterface $serializer): Response
    {
        $query = $request->get('query');
        $allBonplan = $bonplanRep->getAllBonPlanWithFeedbacks();

        if ($request->get('ajax')) {
             $allBonplan = $bonplanRep->findAll();
             $view=$this->renderView('bonplan/search.html.twig', [
                'allBonplan' => $allBonplan,
             ]);

             $response = new JsonResponse($serializer->serialize($allBonplan, 'json'));
             $response->headers->set('Content-Type', 'application/json');
             return $response;
        }
        return $this->render('bonplan/allBonplan.html.twig', [
            'allBonplan' => $allBonplan,
        ]);
    }
    #[Route('/bonplan/{id}', name: 'detail_bonplan')]
    public function detailBonPlan(ManagerRegistry $doctrine,Request $request,UserRepository $userRep,BonplanRepository $bonplanRep,$id,FeedbackRepository $feedRep): Response
    {
        $bonplan = $bonplanRep->find($id);
        $bonplanDetail=$bonplanRep->getBonPlanWithFeedbacks($bonplan);
        $feeds=$feedRep->getFeedbackByBonPlan($bonplan);
        $user=$userRep->find(22);
        $feedback=new Feedback();
        $form=$this->createForm(FeedbackFormType::class,$feedback);

        if($bonplanRep->checkIfAlreadyReported($bonplan,$user)>0){
            $form->add('report', null, [
                'disabled' => true,
            ]);
            $feedback->setReport(false);

        }
        if($bonplanRep->checkIfAlreadyRated($bonplan,$user)>0){
            $form->add('rate', null, [
                'disabled' => true,
                'attr' => [
                    'style' => 'display:none',
                ],
            ]);
            $feedback->setRate(-1);
        }
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()){
            $em =$doctrine->getManager() ;
            $feedback->setUser($user);
            $feedback->setBonplan($bonplan);

            $em->persist($feedback);
            $em->flush();

            // bonplan reporting
            $reportLines=$bonplanRep->count_reports();
            foreach ($reportLines as $line){
                $bonplanReported = $line[0]->getBonplan();
                $reportCount = $line['report_count'];
                if($reportCount >=5 ){
                    $em->remove($bonplanReported);
                    $em->flush();
                    return $this->redirectToRoute("all_bonplan");
                }
            }
            
            return $this->redirectToRoute("detail_bonplan",["id"=>$id]);}

        return $this->renderForm("bonplan/detailBonplan.html.twig",["f" => $form,
        'bonplan' => $bonplanDetail,
        'feeds'=>$feeds,
        ]);
    }
    
    

    
    // ajouter bonPlan

    #[Route('/addBonplan', name: 'addBonplan')]
    public function addBonplan(ManagerRegistry $doctrine,Request $request,UserRepository $userRep): Response
    {
        $user=$userRep->find(26);
        
        $bonplan=new Bonplan();
        $form=$this->createForm(BonplanFormType::class,$bonplan);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em =$doctrine->getManager() ;
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
            return $this->redirectToRoute("all_bonplan");}

        return $this->renderForm("bonplan/addBonplan.html.twig",
        array("f"=>$form));
    }

    #[Route('/updateBonplan/{id}', name: 'updateBonplanUser')]
    public function updatBonPlan( $id,ManagerRegistry $doctrine, Request $request, BonplanRepository $bonplanRep): Response
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
            "bonplan/updateBonplan.html.twig",["f" => $form,
            "b"=>$bonplan,
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
    

    
}
