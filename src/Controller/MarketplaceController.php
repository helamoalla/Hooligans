<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\ProduitRepository;
use App\Repository\UserRepository;
use App\Repository\PanierRepository;
use App\Entity\Panier;
use App\Entity\Produit;
use App\Entity\Lignepanier;
use App\Repository\LignepanierRepository;

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
    #[Route('/marketplace/{id}', name: 'app_marketplace')]
    public function afficherProduits(int $id,ProduitRepository $rep,UserRepository $ur,PanierRepository $panierRep): Response
    {
        $user = $ur->find($id);
        $panier = $panierRep->findOneBy(['user' => $user]);
        $produits=$rep->findAll();

         return $this->render('marketplace/marketplace.html.twig', [
            'panier' => $panier,
            'produits' => $produits,
        ]);
    }

    //Méthode qui ajoute un produit au panier 
    #[Route('/ajouter-produit-au-panier/{panier}/{produit}', name:'ajouter_produit_panier')]
    public function ajouterProduitDansPanier(Panier $panier, Produit $produit, LignepanierRepository $lr): Response
{
    $produits_par_panier=$lr->findBy(['panier' => $panier]);
    //$lignePanier = null;
    $id =$panier->getId();
    $verif = true;

    foreach ($produits_par_panier as $p) {
        
        if ($p->getProduit() == $produit) {
            //le produit existe dans le panier --> mettre à jours sa quantité 
            $p->setQuantite($p->getQuantite() + 1);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($p);
            $entityManager->flush();
            return $this->redirectToRoute("app_marketplace", ['id' => $id]);
        } 
        else {
            $verif=false;
        }

            if ($verif==false){
            // Le produit n'est pas encore dans le panier, on crée une nouvelle ligne panier
            $lignePanier = new Lignepanier();
            $lignePanier->setProduit($produit);
            $lignePanier->setPanier($panier);
            $lignePanier->setQuantite(1);
            $lignePanier->setPrix($produit->getPrixProd());
            $lignePanier->setNomProduit($produit->getNomProd());
            $lignePanier->setDescriptionProd($produit->getDescriptionProd());
            $lignePanier->setImage($produit->getImage());
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($lignePanier);
            $entityManager->flush();
            }
    }
     // Rediriger vers la page d'affichage des produits en passant l'ID du panier
   return $this->redirectToRoute("app_marketplace", ['id' => $id]);
}
}
