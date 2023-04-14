<?php

namespace App\Controller;

use App\Entity\Bonplan;
use App\Form\BonplanFormType;
use App\Repository\BonplanRepository;
use App\Repository\FeedbackRepository;
use App\Repository\UserRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class AdminController extends AbstractController
{
    #[Route('/admin', name: 'app_admin')]
    public function index(UserRepository $userRep): Response
    {
        $admin=$userRep->find(22);
        return $this->render('admin/index.html.twig', [
            'user' => $admin,
        ]);
    }
    #[Route('/adminAllBonplan', name: 'admin_all_bonplan')]
    public function adminAllBonplan(BonplanRepository $bonplanRep ,UserRepository $userRep): Response
    {
        $allBonplan = $bonplanRep->findAll();
        $admin=$userRep->find(22);
        return $this->render('admin/allBonplan.html.twig', [
            'allBonplan' => $allBonplan,
            'user' => $admin,
        ]);
    }
    // ajouter bonPlan

    #[Route('/adminAddBonplan', name: 'admin_add_Bonplan')]
    public function addBonplan(ManagerRegistry $doctrine, Request $request, UserRepository $userRep): Response
    {
        $user = $userRep->find(26);
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
    #[Route('/adminDeleteBonplan/{id}', name: 'admin_delete_Bonplan')]
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
        return $this->redirectToRoute('admin_all_bonplan');
    }

    // validate bonPlan
    #[Route('/adminValidateBonplan/{id}', name: 'admin_validate_Bonplan')]
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

    #[Route('/adminUpdateBonplan/{id}', name: 'updateBonplan')]
    public function updatBonPlan( $id,ManagerRegistry $doctrine, Request $request, BonplanRepository $bonplanRep,UserRepository $userRep): Response
    {
        $bonplan = $bonplanRep->find($id);
        $admin=$userRep->find(22);
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
            'user' => $admin,
            ]
            // array("f" => $form)
        );
    }

    //all feedback 

    #[Route('/adminAllFeeds', name: 'admin_all_Feeds')]
    public function adminAllFeed(FeedbackRepository $feedRep,UserRepository $userRep): Response
    {
        $allFeeds = $feedRep->findAll();
        $admin=$userRep->find(22);
        return $this->render('admin/allFeeds.html.twig', [
            'allFeeds' => $allFeeds,
            'user' => $admin
        ]);
    }

    // supprimer Feedback
    #[Route('/adminDeleteFeedback/{id}', name: 'admin_delete_Feed')]
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
