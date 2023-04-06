<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use App\Entity\Commande;
use App\Entity\Panier;
use App\Repository\CommandeRepository;
use App\Repository\PanierRepository;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Persistence\ManagerRegistry;

class CommandeController extends AbstractController
{
    #[Route('/commande', name: 'app_commande')]
    public function index(): Response
    {
        return $this->render('commande/index.html.twig', [
            'controller_name' => 'CommandeController',
        ]);
    }

    #[Route('/commandesAffiche/{idPanier}', name: 'afficher_commandes')]
public function afficherCommandesParPanier($idPanier)
{
    return $this->redirectToRoute("interface_affichage_commandes", ['idPanier' => $idPanier]);
}


#[Route('/commandes/{idPanier}', name: 'interface_affichage_commandes')]
public function afficherCommandes(int $idPanier): Response
{
    $commandes = $this->getDoctrine()->getRepository(Commande::class)->findBy(['panier' => $idPanier]);

    return $this->render('commande/affichercommandes.html.twig', [
        'commandes' => $commandes,
    ]);
}

#[Route('/suppCommande/{id}', name: 'suppC')]
    public function suppClassroom($id,CommandeRepository $com,
    ManagerRegistry $doctrine): Response
    { 
    
    //récupérer la commande à supprimer
    $commande=$com->find($id);
    $idPanier = $commande->getPanier()->getId();    
    //Action suppression
     $em =$doctrine->getManager();
     $em->remove($commande);
     $em->flush();
return $this->redirectToRoute('interface_affichage_commandes', ['idPanier' => $idPanier]);
}


}
