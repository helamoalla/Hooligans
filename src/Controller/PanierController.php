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
use App\Repository\CommandeRepository;
use App\Repository\ProduitRepository;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use Doctrine\Persistence\ManagerRegistry;
use App\Form\CommandeFormType;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use Symfony\Component\Mime\Attachment;



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
        $nbp = 0;
        foreach ($produits as $lignePanier) {
            $quantite = $lignePanier->getQuantite();
            $prixUnitaire = $lignePanier->getPrix();
            $total += $quantite * $prixUnitaire;
            $nbp += 1;
        }

       
        
    return $this->render('panier/afficherpanier.html.twig', [
        'produits' => $produits,
        'total' => $total,
        'panier' => $panier
    ]);
    }

     //Fonction qui retourne le nombre de produits dans le panier d'un user
     #[Route('/nbprod', name: 'app_nbprod')]
     public function nbprod(PanierRepository $panierRep,UserRepository $ur,LignepanierRepository $rep):Response
     {
         $user = $ur->find(22);
         $panier = $panierRep->findOneBy(['user' => $user]);
         $idPanier=$panier->getId();
         $produits = $rep->findBy(['panier' => $idPanier]);    
         $nbp = 0;
         foreach ($produits as $l) {
             $nbp += 1;
         }

       // Retourner une réponse JSON avec le nombre de produits
       return $this->json(['nbp' => $nbp]);
     }


    //Fonction qui met a jours la qantité +1 d'un produit dans un panier
    #[Route('/qtPlusUn/{id}', name: 'plus1')]
    public function updateLignePanierQuantitePlusUn(int $id, ManagerRegistry $doctrine,LignepanierRepository $rep)
{
    $entityManager = $doctrine->getManager();
    $lignePanier = $rep->find($id);
    $lignePanier->setQuantite($lignePanier->getQuantite() + 1);

    $entityManager->persist($lignePanier);
    $entityManager->flush();

    // Rediriger vers la page d'affichage du panier
    return $this->redirectToRoute("app_Affichepanier");

       // Récupérer la nouvelle quantité
       //$quantite = $lignePanier->getQuantite();

       // Retourner une réponse JSON avec la nouvelle quantité
      // return $this->json(['quantite' => $quantite]);
}


    
    //Fonction qui met a jours la qantité -1 d'un produit dans un panier
    #[Route('/qtMoinsUn/{id}', name: 'moins1')]
    public function updateLignePanierQuantiteMoinsUn(int $id, ManagerRegistry $doctrine,LignepanierRepository $rep)
{
    $entityManager = $doctrine->getManager();
    $lignePanier = $rep->find($id);
    $lignePanier->setQuantite($lignePanier->getQuantite() - 1);

    $entityManager->persist($lignePanier);
    $entityManager->flush();

   // Rediriger vers la page d'affichage du panier
   return $this->redirectToRoute("app_Affichepanier");

       // Récupérer la nouvelle quantité
       $quantite = $lignePanier->getQuantite();
       $prix = $lignePanier->getPrix();

       // Retourner une réponse JSON avec la nouvelle quantité
       //return $this->json(['quantite' => $quantite,
       //'prix' => $prix
    //]);
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


#[Route('/imprimerfacture/{idCommande}', name: 'app_facture')]
public function imprimerFacture(int $idCommande,LignepanierRepository $lr,PanierRepository $pr,UserRepository $ur,CommandeRepository $cr,MailerInterface $mailer ): Response
{
    $user = $ur->find(22);
    $panier = $pr->findOneBy(['user' => $user]);
    $id=$panier->getId();
    $produits_par_panier=$lr->findBy(['panier' => $panier]);
    $commande = $cr->find($idCommande);
    $publicPath = $this->getParameter('kernel.project_dir') . '/public';
    $pdfOptions = new Options();
    $pdfOptions->set('defaultFont', 'Arial');
    $pdfOptions->set('isRemoteEnabled', true);
    $pdf = new Dompdf($pdfOptions);
//Contenu du Pdf
    $html = '<html><body>';
    //$html .= '<h1>Facture</h1>';
    // $html .= '<img src="' . $publicPath . '/en_tete.png" />';
    $html .= '<img src="public/images/en_tete.png" />';
    //$html .= '<img src="data:image/png;base64,'.base64_encode(file_get_contents($publicPath.'/en_tete.png')).'" />';
    $html .= '<p>Vendeur : Drift$Race</p>';
    $html .= '<p>________________________________________________</p>';
    $html .= '<p>Client : '.$user->getNom().' '.$user->getPrenom().'</p>';
    $html .= '<p>Commande n°'.$commande->getId().'</p>';
    $html .= '<p>Date de facturation '.$commande->getDateCommande()->format('d/m/Y').'</p>';
    $html .= '<p>________________________________________________</p>';
    $html .= '<h3>Détails de la commande</h3>';
    $html .= '<table border="1" cellspacing="0">';
    $html .= '<tr><th>Image</th><th>Produit</th><th>Prix unitaire</th><th>Quantité</th><th>Sous Montant</th></tr>';
    foreach ($produits_par_panier as $p) {
        $html .= '<tr><td>'.$p->getProduit()->getImage().'</td><td>'.$p->getProduit()->getNomProd().'</td><td>'.$p->getProduit()->getPrixProd().' DT</td><td>'.$p->getQuantite().'</td><td>'.$p->getProduit()->getPrixProd()*$p->getQuantite().' DT</td></tr>';
    }
    $html .= '<tr><td colspan="4">Montant Total à payer</td><td>'.$commande->getMontant().' DT</td></tr>';
    $html .= '</table>';
   // $html .= '<img src="data:image/png;base64,'.base64_encode(file_get_contents($publicPath.'/bas_page.png')).'" />';
    //$html .= '<img src="' . $publicPath . '/bas_page.png" />';
    $html .= '<img src="public/images/bas_page.png" />';
    $html .= '</body></html>';
    $pdf->loadHtml($html);
    $pdf->setPaper('A4', 'portrait');
     // Render the PDF as a string
     $pdf->render();

     // Retourner le pdf
     $output = $pdf->output();
     file_put_contents('downloads/facture.pdf', $output);
     // Rendre la template Twig en HTML
   $contenu = $this->renderView('mail.html.twig');

      // Créer l'email
    $email = (new Email())
    ->from('asma.choueibi@gmail.com')
    ->to('chouaibiasma15@gmail.com')
    ->subject('Facture')
    ->html($contenu)
    ->attach($output, 'facture.pdf','downloads/facture.pdf');

   // Envoyer l'email
   $mailer->send($email);

     return $this->redirectToRoute("vider_panier", ['id' => $id]);
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
        $idCommande = $commande->getId();

        return $this->redirectToRoute("app_facture", ['idCommande' => $idCommande]);
    }

    return $this->renderForm("commande/passercommande.html.twig",
        array("f"=>$form));
}
}
