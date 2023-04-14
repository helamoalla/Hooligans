<?php

namespace App\Controller;

use App\Entity\Garagec;
use App\Entity\Maintenance;
use App\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Form\MaintenanceFormType;
use App\Repository\MaintenanceRepository;
use App\Repository\GaragecRepository;
use App\Repository\UserRepository;
use DateTime;
use Doctrine\Persistence\ManagerRegistry;
use phpDocumentor\Reflection\Types\Null_;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;

class MaintenanceController extends AbstractController
{
    #[Route('/maintenance', name: 'app_maintenance')]
    public function index(MaintenanceRepository $r, ManagerRegistry $doctrine,GaragecRepository $r1): Response
    {
        $maintenances = $r->findMaintenanceByIdUser(26);
        $test = false;
        $dateAujourdhui = new DateTime();
        $garageC=$r1->orderById();
        foreach ($maintenances as $maintenance) {
            $diff =$dateAujourdhui->diff( $maintenance->getDateMaintenance())->days;
            if ($diff > 2) {
                $test = false;
               
            }
        }
         //$dateLimite = $dateAujourdhui|date_modify('-2 days')|date('m/d/Y') ;
       
        return $this->render('front.html.twig', [
            't'=>$test,'g'=>$garageC
        ]);
    }
    #[Route('/afficheM', name: 'app_afficheM')]
    public function afficheM(MaintenanceRepository $r): Response
    {
        $maintenance=$r->orderById();
        return $this->render('maintenance/afficheM.html.twig', [
            'm'=>$maintenance,
        ]);
    }
    #[Route('/detailGCU/{id}', name: 'detailGCU')]
    public function detailGCU($id): Response
    {
        $garageC=$this->getDoctrine()->getRepository(Garagec::class)->find($id);
        return $this->render('garage_c/detailGCU.html.twig', [
            'g'=>$garageC,
        ]);
    }
    #[Route('/afficheMU', name: 'app_afficheMU')]
    public function afficheMU(MaintenanceRepository $r, ManagerRegistry $doctrine): Response
    {   
        $maintenance=$r->findMaintenanceByIdUser(25);
        return $this->render('maintenance/afficheMU.html.twig', [
            'm'=>$maintenance,
        ]);
    }
    #[Route('/supprimerM/{id}', name: 'supprimerM')]
    public function supprimerM($id,MaintenanceRepository $r, ManagerRegistry $doctrine): Response
    {   ///////recuperer la classroom a supp//////////
        $maintenance=$r->find($id);
         ////////supprimer/////////
        $em=$doctrine->getManager();/////persist()
        $em->remove($maintenance);/////remove()
        $em->flush(); ////////flush()
        return $this->redirectToRoute('app_afficheM',);
    }
    #[Route('/ajouterM', name: 'ajouterM')]
    public function ajouterM(ManagerRegistry $doctrine,Request $request): Response
    {
        $maintenance=new Maintenance();
        $form=$this->createForm(MaintenanceFormType::class,$maintenance);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid ()){
            $em =$doctrine->getManager() ;
            $user=$this->getDoctrine()->getRepository(User::class)->find(25);
            $maintenance->setUser($user);
            $maintenance->setDateMaintenance(new \DateTime('now'));
            $em->persist($maintenance);
            $em->flush();
            return $this->redirectToRoute('app_afficheMU',);}
            
           return $this->renderForm("maintenance/ajoutMaintenance.html.twig",
            array("f"=>$form));
        }

        #[Route('/modifierM/{id}', name: 'modifierM')]
        public function modifierM($id,MaintenanceRepository $r,ManagerRegistry $doctrine,Request $request)
                
              { //récupérer la classroom à modifier
                $maintenance=$r->find($id);
            $form=$this->createForm(MaintenanceFormType::class,$maintenance);
             $form->handleRequest($request);
             if($form->isSubmitted()){
            $em =$doctrine->getManager() ;
                 $em->flush();
          return $this->redirectToRoute("app_afficheMU");}
            
           return $this->renderForm("maintenance/ajoutMaintenance.html.twig",
                                       array("f"=>$form));
                                
                            }

}
