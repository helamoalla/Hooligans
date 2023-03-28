<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\ProduitRepository ;
use App\Entity\Produit;
use App\Repository\CategorieRepository ;
use App\Entity\Categorie;

class UsersController extends AbstractController
{
    #[Route('/users', name: 'app_users')]
    public function index(): Response
    {
        return $this->render('homeuser.html.twig', [
            'controller_name' => 'UsersController',
        ]);
    }

                 
    //Afficher Produit
    #[Route('/userafficheproduit', name: 'affichep')]
    public function affichep(ProduitRepository $Rep,CategorieRepository $Rep1 ): Response
    { $Produit=$Rep->findAll();
        $Categorie=$Rep1->findAll();
    
        return $this->render('produit/afficherProduitUser.html.twig', [
        'p'=>$Produit  ,
        'c'=>$Categorie  ,
        

        ]);
    }

    //Afficherdetail
    #[Route('/useraffichedetailproduit/{id}', name: 'affichedetail')]
    public function affichedetail(ProduitRepository $Rep, int $id ): Response
    { $Produit=$Rep->find($id);
     $Produits=$Rep->findAll();
        
        return $this->render('produit/afficherDetailProduitUser.html.twig', [
        'p'=>$Produit  ,
        's'=>$Produits  ,
        
        

        ]);

}
}