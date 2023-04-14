<?php

namespace App\Controller;

use App\Entity\Bonplan;
use App\Repository\BonplanRepository;
use App\Repository\FeedbackRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class HomeController extends AbstractController
{
  
    #[Route('/', name: 'app_home')]
    public function index(BonplanRepository $bonplanRep,FeedbackRepository $feedRep): Response
    {
       
        $allBonplan = $bonplanRep->getAllBonPlanWithFeedbacks();
        $allFeeds=$feedRep->findAll();
        return $this->render('index.html.twig', [
            'allBonplan' => $allBonplan,
            'allFeeds'=>$allFeeds,
              
        ]);
    }
    #[Route('/resume', name: 'resume')]
    public function resume(): Response
    {
        return $this->render('resume.html.twig', [
            
        ]);
    }
}
