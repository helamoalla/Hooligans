<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\ProduitRepository;
use App\Repository\UserRepository;
use Symfony\Component\HttpFoundation\Session\Flash\FlashBagInterface;
use App\Repository\PanierRepository;
use App\Repository\CategorieRepository;
use Doctrine\Persistence\ManagerRegistry;
use App\Entity\Panier;
use App\Entity\Produit;
use App\Entity\Lignepanier;
use App\Repository\LignepanierRepository;
use App\Form\LignePanierFormType;

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
    public function afficherProduits(ProduitRepository $rep,UserRepository $ur,PanierRepository $panierRep,CategorieRepository $Rep1 ): Response
    {
        $user = $ur->find(22);
       
        $panier = $panierRep->findOneBy(['user' => $user]);
        $produits=$rep->findAll();
        $Categorie=$Rep1->findAll();
         return $this->render('marketplace/marketplace.html.twig', [
            'panier' => $panier,
            'p'=>$produits,
            'c'=>$Categorie,
        ]);
    }


    //Afficherdetail d'un produit et ajputer le produit au panier
    #[Route('/useraffichedetailproduit/{id}', name: 'affichedetail')]
    public function affichedetail(int $id, LignepanierRepository $lr, PanierRepository $pr, UserRepository $ur, ProduitRepository $repproduit,ManagerRegistry $doctrine, Request $request): Response
       { 
        $produit=$repproduit->find($id);
        $stock = $produit->getQuantiteProd();
        $Produits=$repproduit->findAll();
        $user = $ur->find(22);
        $panier = $pr->findOneBy(['user' => $user]);
        $em = $doctrine->getManager() ;
        $produits_par_panier=$lr->findBy(['panier' => $panier]);
        $verif = true;
      
       foreach ($produits_par_panier as $p) {  
            if ($p->getProduit() == $produit) {
                //le produit existe dans le panier --> mettre à jours sa quantité 
                $form=$this->createForm(LignePanierFormType::class,$p);
                $form->handleRequest($request);
                if ($form->isSubmitted() && $form->isValid()) {
                $quantite = $form->get('quantite')->getData();
                if ($quantite <= $stock) {
                $p->setQuantite($p->getQuantite()+$quantite);
                $em->persist($p);
                $em->flush();
                  // Rediriger vers la page d'affichage des produits 
                return $this->redirectToRoute("app_marketplace");
                }
                else {
                    // Quantité ajoutée supérieure à la quantité en stock, afficher un message d'erreur
                   //hethi baad tetbadel ki yabda aana session 
                   // $this->addFlash('error', 'La quantité ajoutée est supérieure à la quantité en stock.');
                   $response = new Response();
                   $response->setContent('La quantité ajoutée est supérieure à la quantité en stock.');
                   $response->setStatusCode(Response::HTTP_BAD_REQUEST);
                   return $response;
                }
            } 
            else {
               $verif=false;
         }
        }
     }
            if ($verif==false){
                // Le produit n'est pas encore dans le panier, on crée une nouvelle ligne panier
                $lignePanier = new Lignepanier();
                $form=$this->createForm(LignePanierFormType::class,$lignePanier);
                $form->handleRequest($request);
                
                  if ($form->isSubmitted() && $form->isValid()) {
                    $quantite = $form->get('quantite')->getData();
                     if ($quantite <= $stock) {
                  $lignePanier->setProduit($produit);
                  $lignePanier->setPanier($panier);
                  $lignePanier->setPrix($produit->getPrixProd());
                  $lignePanier->setNomProduit($produit->getNomProd());
                  $lignePanier->setDescriptionProd($produit->getDescriptionProd());
                  $lignePanier->setImage($produit->getImage());
                  $em->persist($lignePanier);
                  $em->flush();
                    // Rediriger vers la page d'affichage des produits 
                  return $this->redirectToRoute("app_marketplace");
                  } 
                 else {
                    // Quantité ajoutée supérieure à la quantité en stock, afficher un message d'erreur
                    //hethi baad tetbadel ki yabda aana session 
                    //$this->addFlash('error', 'La quantité ajoutée est supérieure à la quantité en stock.');
                    $response = new Response();
                    $response->setContent('La quantité ajoutée est supérieure à la quantité en stock.');
                    $response->setStatusCode(Response::HTTP_BAD_REQUEST);
                    return $response;
                }
            }
        }
        return $this->render('marketplace/afficherDetailProduitUser.html.twig', [
            'p'=>$produit  ,
            's'=>$Produits  , 
            'f' => $form->createView()
            ]);
       }
}
