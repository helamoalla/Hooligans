<?php

namespace App\Controller;

use App\Entity\Feedback;
use App\Form\FeedbackFormType;
use App\Repository\BonplanRepository;
use App\Repository\FeedbackRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class FeedbackController extends AbstractController
{
    #[Route('/feedback', name: 'app_feedback')]
    public function index(): Response
    {
        return $this->render('feedback/index.html.twig', [
            'controller_name' => 'FeedbackController',
        ]);
    }
    #[Route('/deleteFeedback/{id}', name: 'delete_feedback')]
    public function deleteFeedback($id, FeedbackRepository $feedRep, ManagerRegistry $doctrine): Response
    {
        // methide de revuperation finAll() find($id) findBy()
        $feedback = $feedRep->find($id);
        // Action supprision
        // methode de persistance : persist() remove() flush()
        $em = $doctrine->getManager();
        $em->remove($feedback);
        // flush heya el commit
        $em->flush();
        return $this->redirectToRoute('detail_bonplan',["id" => $feedback->getBonplan()->getId()]);
    }
    #[Route('/updateFeedback/{id}', name: 'update_feedback')]
    public function updateFeedback($id, FeedbackRepository $feedRep, Request $request,BonplanRepository $bonplanRep, ManagerRegistry $doctrine): Response
    {
        $feedback = $feedRep->find($id);
        $bonplan = $bonplanRep->find($feedback->getBonplan()->getId());
        $feeds=$feedRep->getFeedbackByBonPlan($bonplan);
        
        $form = $this->createForm(FeedbackFormType::class, $feedback);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $em = $doctrine->getManager();
            

            
            $em->flush();

            return $this->redirectToRoute('detail_bonplan',["id"=>$bonplan->getId()]);
        }

        return $this->renderForm("bonplan/detailBonplan.html.twig",["f" => $form,
        'bonplan' => $bonplan,
        'feeds'=>$feeds,
        ]);
    }
    

}
