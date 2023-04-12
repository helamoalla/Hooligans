<?php

namespace App\Controller;

use App\Entity\Garagec;
use App\Entity\Maintenance;
use App\Form\GarageCFormType;
use App\Repository\GaragecRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;




class GarageCController extends AbstractController
{
    #[Route('/garage/c', name: 'app_garage_c')]
    public function index(): Response
    {
        return $this->render('garage_c/index.html.twig', [
            'controller_name' => 'GarageCController',
        ]);
    }
    #[Route('/afficheGC', name: 'app_afficheGC')]
    public function afficheGC(): Response
    {
        $garageC=$this->getDoctrine()->getRepository(Garagec::class)->findAll();
        return $this->render('garage_c/afficheGC.html.twig', [
            'g'=>$garageC,
        ]);
    }
    #[Route('/detailGC/{id}', name: 'detailGC')]
    public function detailGC($id): Response
    {
        $garageC=$this->getDoctrine()->getRepository(Garagec::class)->find($id);
        return $this->render('garage_c/detailGC.html.twig', [
            'g'=>$garageC,
        ]);
    }
    #[Route('/index', name: 'app_index')]
    public function afficheGCA(): Response
    {
        $garageC=$this->getDoctrine()->getRepository(Garagec::class)->findAll();
        $maintenance=$this->getDoctrine()->getRepository(Maintenance::class)->findAll();
        return $this->render('index.html.twig', [
            'g'=>$garageC, 'm'=> $maintenance
        ]);
    }
    #[Route('/supprimerGC/{id}', name: 'supprimerGC')]
    public function supprimerGC($id,GaragecRepository $r, ManagerRegistry $doctrine): Response
    {   ///////recuperer garage//////////
         $garageC=$r->find($id);
         ////////supprimer/////////
        $em=$doctrine->getManager();/////persist()
        $em->remove($garageC);/////remove()
        $em->flush(); ////////flush()
        return $this->redirectToRoute('app_afficheGC',);
    }

    #[Route('/ajouterGC', name: 'ajouterGC')]
    public function ajouterGC(ManagerRegistry $doctrine,Request $request): Response
    {
        $garageC=new Garagec();
        $form=$this->createForm(GarageCFormType::class,$garageC);
        $form->handleRequest($request);
       
        if($form->isSubmitted() && $form->isValid()){
            $em =$doctrine->getManager() ;
            $imageFile = $form->get('image')->getData();

            if ($imageFile) {
                $imagesDirectory = 'C:/xampp/htdocs/images';
                $originalFilename = $imageFile->getClientOriginalName();
                $filenameWithoutSpaces = str_replace(' ', '_', $originalFilename);

                try {
                   
                   $imageFile->move($imagesDirectory, $filenameWithoutSpaces);
                } catch (FileException $e) {
                    // Handle exception
                }
                
                $garageC->setImage($filenameWithoutSpaces);
               }
               $em->persist($garageC);
               $em->flush();
               return $this->redirectToRoute('app_afficheGC',);
         }

            

           return $this->renderForm("garage_c/ajoutGarageC.html.twig",
            array("f"=>$form,"g"=>$garageC));
        }

        #[Route('/modifierGC/{id}', name: 'modifierGC')]
        public function modifierGC($id,GaragecRepository $r,ManagerRegistry $doctrine,Request $request)
                
              { 
                $garageC=$r->find($id);
               
            $form=$this->createForm(GarageCFormType::class,$garageC);
             $form->handleRequest($request);
           
             
             if($form->isSubmitted() && $form->isValid()){
            $em =$doctrine->getManager() ;
            $imageFile = $form->get('image')->getData();
            if ($imageFile) {
                $imagesDirectory = 'C:/xampp/htdocs/images';
                $originalFilename = $imageFile->getClientOriginalName();
                $filenameWithoutSpaces = str_replace(' ', '_', $originalFilename);

                try {
                   
                   $imageFile->move($imagesDirectory, $filenameWithoutSpaces);
                } catch (FileException $e) {
                    // Handle exception
                }
                
                $garageC->setImage($filenameWithoutSpaces);
                }
                $em->flush();
                return $this->redirectToRoute("app_afficheGC");
            }
                              return $this->renderForm("garage_c/ajoutGarageC.html.twig",
                                       array("f"=>$form,"g"=>$garageC)
                                    );
                                
                            }
    
}
