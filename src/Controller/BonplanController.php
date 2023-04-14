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
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class BonplanController extends AbstractController
{
    #[Route('/allBonplan', name: 'all_bonplan')]
    public function allBonplan(BonplanRepository $bonplanRep): Response
    {
        $allBonplan = $bonplanRep->getAllBonPlanWithFeedbacks();
        return $this->render('bonplan/allBonplan.html.twig', [
            'allBonplan' => $allBonplan,
        ]);
    }
    #[Route('/bonplan/{id}', name: 'detail_bonplan')]
    public function detailBonPlan(ManagerRegistry $doctrine,Request $request,UserRepository $userRep,BonplanRepository $bonplanRep,$id,FeedbackRepository $feedRep): Response
    {
        $bonplan = $bonplanRep->find($id);
        $feeds=$feedRep->getFeedbackByBonPlan($bonplan);
        $user=$userRep->find(22);
        $feedback=new Feedback();
        $form=$this->createForm(FeedbackFormType::class,$feedback);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()){
            $em =$doctrine->getManager() ;
            $feedback->setUser($user);
            $feedback->setBonplan($bonplan);

            $em->persist($feedback);
            $em->flush();
            return $this->redirectToRoute("detail_bonplan",["id"=>$id]);}

        return $this->renderForm("bonplan/detailBonplan.html.twig",["f" => $form,
        'bonplan' => $bonplan,
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
