<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\LignepanierRepository;
use App\Entity\Lignepanier;
use App\Entity\Panier;
use App\Entity\Commande;
use App\Repository\PanierRepository;
use App\Repository\ProduitRepository;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Persistence\ManagerRegistry;
use App\Form\CommandeFormType;



class PanierController extends AbstractController
{
    #[Route('/panier', name: 'app_panier')]
    public function index(): Response
    {
        return $this->render('panier/index.html.twig', [
            'controller_name' => 'PanierController',
        ]);
    }

    //Fonction qui retourne les produits du panier d'un user ainsi que le montant total de son panier
    #[Route('/affichepanier', name: 'app_Affichepanier')]
    public function ProduitsParPanier(PanierRepository $panierRep,UserRepository $ur,LignepanierRepository $rep):Response
    {
        $user = $ur->find(22);
        $panier = $panierRep->findOneBy(['user' => $user]);
        $idPanier=$panier->getId();
        $produits = $rep->findBy(['panier' => $idPanier]);

        $total = 0;
        foreach ($produits as $lignePanier) {
            $quantite = $lignePanier->getQuantite();
            $prixUnitaire = $lignePanier->getPrix();
            $total += $quantite * $prixUnitaire;
        }
        
    return $this->render('panier/afficherpanier.html.twig', [
        'produits' => $produits,
        'total' => $total,
        'panier' => $panier
    ]);
    }

    //Fonction quit met a jours la qantité +1 d'un produit dans un panier
    #[Route('/qtPlusUn/{id}', name: 'plus1')]
    public function updateLignePanierQuantitePlusUn(int $id, ManagerRegistry $doctrine,LignepanierRepository $rep)
{
    $entityManager = $doctrine->getManager();
    $lignePanier = $rep->find($id);
    $lignePanier->setQuantite($lignePanier->getQuantite() + 1);

    $entityManager->persist($lignePanier);
    $entityManager->flush();

    // Récupérer l'ID du panier
    $idPanier = $lignePanier->getPanier()->getId();

    // Rediriger vers la page d'affichage du panier en passant l'ID du panier
    return $this->redirectToRoute("app_Affichepanier", ['idPanier' => $idPanier]);
}


    
    //Fonction quit met a jours la qantité -1 d'un produit dans un panier
    #[Route('/qtMoinsUn/{id}', name: 'moins1')]
    public function updateLignePanierQuantiteMoinsUn(int $id, ManagerRegistry $doctrine,LignepanierRepository $rep)
{
    $entityManager = $doctrine->getManager();
    $lignePanier = $rep->find($id);
    $lignePanier->setQuantite($lignePanier->getQuantite() - 1);

    $entityManager->persist($lignePanier);
    $entityManager->flush();
   // Récupérer l'ID du panier
   $idPanier = $lignePanier->getPanier()->getId();

   // Rediriger vers la page d'affichage du panier en passant l'ID du panier
   return $this->redirectToRoute("app_Affichepanier", ['idPanier' => $idPanier]);
}

   //Fonction qui supprime un produit du panier 
   #[Route('SupprimerProduit/{id}', name: 'supprimer_ligne_panier')]
   public function deleteLignePanier(int $id,ManagerRegistry $doctrine,LignepanierRepository $rep): Response
{
    $entityManager = $doctrine->getManager();
    $lignePanier = $rep->find($id);
       // Récupérer l'ID du panier
   $idPanier = $lignePanier->getPanier()->getId();
    //Supprimer la ligne panier
    $entityManager->remove($lignePanier);
    $entityManager->flush();
   // Rediriger vers la page d'affichage du panier en passant l'ID du panier
   return $this->redirectToRoute("app_Affichepanier", ['idPanier' => $idPanier]);
}

  //Fonction qui vide le panier d'un user 
  #[Route('/ViderPanier/{id}', name: 'vider_panier')]
  public function ViderPanier(int $id,ManagerRegistry $doctrine,LignepanierRepository $rep): Response
{
    $entityManager = $doctrine->getManager();
    $lignesPanier = $rep->findBy(['panier' => $id]);
        

    foreach ($lignesPanier as $lignePanier) {
        $entityManager->remove($lignePanier);
        // Récupérer l'ID du panier
   $idPanier = $lignePanier->getPanier()->getId();
    }

    $entityManager->flush();
   // Rediriger vers la page d'affichage du panier en passant l'ID du panier
   return $this->redirectToRoute("app_Affichepanier", ['idPanier' => $idPanier]);
}



#[Route('/passercommande/{idPanier}/{total}', name: 'passer_commande')]
public function passerCommande(int $idPanier, float $total,ManagerRegistry $doctrine,Request $request): Response
{
    // Récupérer le panier
    $entityManager = $doctrine->getManager();
    $panier = $entityManager->getRepository(Panier::class)->find($idPanier);
    
    $commande = new Commande();
    $form=$this->createForm(CommandeFormType::class,$commande);
    $form->handleRequest($request);
    $dateCommande = new \DateTime();


    if ($form->isSubmitted() && $form->isValid()) {
        $commande->setPanier($panier);
        $commande->setMontant($total);
        $commande->setEtatCommande("En cours de traitement");
        $commande->setDateCommande($dateCommande);
        $entityManager = $doctrine->getManager() ;
        $entityManager->persist($commande);
        $entityManager->flush();

        return $this->redirectToRoute("app_Affichepanier", ['idPanier' => $idPanier]);
    }

    return $this->renderForm("commande/passercommande.html.twig",
        array("f"=>$form));
}
}
