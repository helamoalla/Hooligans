<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\ProduitRepository;

class MarketplaceController extends AbstractController
{
    #[Route('/marketplace', name: 'app_marketplace')]
    public function index(): Response
    {
        return $this->render('marketplace/index.html.twig', [
            'controller_name' => 'MarketplaceController',
        ]);
    }


    //Fonction qui affiche tous les produits depuis la base
    #[Route('/marketplace', name: 'app_marketplace')]
    public function afficherProduits(ProduitRepository $rep): Response
    {
        $produits=$rep->findAll();
        return $this->render('marketplace/marketplace.html.twig',
         ['produits'=>$produits]);
    }
}
