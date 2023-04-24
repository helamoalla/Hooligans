<?php

namespace App\Controller;

use App\Entity\Devis;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Garagec;
use App\Entity\Maintenance;
use App\Entity\User;
use App\Repository\MaintenanceRepository;
use App\Repository\GaragecRepository;
use App\Repository\UserRepository;
use Symfony\Component\Validator\Constraints\Length;

class DevisController extends AbstractController
{
    #[Route('/devis', name: 'app_devis')]
    public function index(): Response
    {
        return $this->render('devis/index.html.twig', [
            'controller_name' => 'DevisController',
        ]);
    }
    
    #[Route('/deviss/{id}', name: 'deviss')]
    public function devis($id,MaintenanceRepository $r,GaragecRepository $r1,ManagerRegistry $doctrine,Request $request): Response
    {  
        $m=$r->find($id);
        $devis = array(); // un tableau pour stocker les devis générés
        $TTC = array(); 
        $TVA = 19;
        $g = $r1->findAll();
        
        for($i=0; $i<count($g); $i++){
            $devis[$i]= new Devis();
            $somme=0;
        
            if ($m->isFeuDEclairage()) {
                $somme = $somme + $g[$i]->getFeuDEclairage();
            }
            if ($m->isAmortisseur()) {
                $somme = $somme + $g[$i]->getAmortisseur();
            }
            if ($m->isBatterie()) {
                $somme = $somme + $g[$i]->getBatterie();
            }
            if ($m->isDuride()) {
                $somme = $somme + $g[$i]->getDuride();
            }
            if ($m->isEssuieGlace()) {
                $somme = $somme + $g[$i]->getEssuieGlace();
            }
            if ($m->isFiltre()) {
                $somme = $somme + $g[$i]->getFiltre();
            }
            if ($m->isFreinMain()) {
                $somme = $somme + $g[$i]->getFreinMain();
            }
            if ($m->isFuiteDHuile()) {
                $somme = $somme + $g[$i]->getFuiteDHuile();
            }
            if ($m->isPanneMoteur()) {
                $somme = $somme + $g[$i]->getPanneMoteur();
            }
            if ($m->isPatin()) {
                $somme = $somme + $g[$i]->getPatin();
            }
            if ($m->isPompeAEau()) {
                $somme = $somme + $g[$i]->getPompeAEau();
            }
            if ($m->isRadiateur()) {
                $somme = $somme + $g[$i]->getRadiateur();
            }
            if ($m->isVentilateur()) {
                $somme = $somme + $g[$i]->getVentilateur();
            }
            if ($m->isVidange()) {
                $somme = $somme + $g[$i]->getVidange();
            }
            
            $devis[$i]->setGarage($g[$i]);
            $devis[$i]->setMaintenance($m);
            $devis[$i]->setUser($m->getUser());  
            $devis[$i]->setTVA($TVA);
            $T = ($somme * $TVA) / 100;
            $TTC[$i] = ($T + $somme);
            $Red = $TTC[$i] - ($TTC[$i] * $g[$i]->getTauxDeReduction()) / 100;
            $devis[$i]->setTotal($Red);
        
        $em =$doctrine->getManager() ;
        $em->persist($devis[$i]);
        $em->flush();
    }
        return $this->render('devis/index.html.twig', [
            'd' => $devis,'TTC' => $TTC,
        ]);
    }
}
