<?php

namespace App\Controller;

use App\Entity\Garagec;
use App\Entity\User;
use App\Entity\Maintenance;
use App\Form\GarageCFormType;
use App\Repository\GaragecRepository;
use App\Repository\MaintenanceRepository;
use App\Repository\UserRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Notifier\Bridge\Twilio\TwilioTransport;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Notifier\Message\SmsMessage;
use Symfony\Component\Notifier\TexterInterface;
use Symfony\Component\Validator\Constraints\Length;

class GarageCController extends AbstractController
{
    #[Route('/garage/c', name: 'app_garage_c')]
    public function index(): Response
    {
        return $this->render('garage_c/index.html.twig', [
            'controller_name' => 'GarageCController',
        ]);
    }
    #[Route('/afficheG', name: 'afficheG')]
    public function afficheG(Request $request,GaragecRepository $r)
{ $garageC=$r->orderById();
    $lat = $request->query->get('lat');
    $lng = $request->query->get('lng');
    $adresse = $request->query->get('adresse');

    return $this->render('garage_c/afficheGC.html.twig', [
        'lat' => $lat,
        'lng' => $lng,
        'g'=>$garageC,
        'adresse' => $adresse
    ]);
}
    #[Route('/afficheGC', name: 'app_afficheGC')]
    public function afficheGC(GaragecRepository $r): Response
    {
        $garageC=$r->orderById();
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
    public function afficheGCA(GaragecRepository $r,MaintenanceRepository $r1): Response
    {
        $garageC=$r->orderById();
        $maintenance=$r1->orderById();
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
    public function ajouterGC(ManagerRegistry $doctrine,Request $request,TwilioTransport $twilio): Response
    { 
        $garageC=new Garagec();
        $form=$this->createForm(GarageCFormType::class,$garageC);
        $form->handleRequest($request);
       
        if($form->isSubmitted() && $form->isValid()){
            $em =$doctrine->getManager() ;
            $imageFile = $form->get('image')->getData();
            $adresse = $request->get('adresse');
            if ($adresse=='') {
                // Affichage d'une erreur
           
                $this->addFlash('error', 'Le champ adresse ne peut pas être vide.');
        
                // Redirection vers la page du formulaire
                return $this->renderForm("garage_c/ajoutGarageC.html.twig",
                array("f"=>$form,"g"=>$garageC));
            }
           $garageC->setAdresse($adresse);
            $numero= $form->get('numero')->getData();
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
               $users = $this->getDoctrine()->getRepository(User::class)->findAll();
               foreach($users as $user){
                $numero = $user->getNumTel(); 
                $nomg=$garageC->getNomGarage();
                $nom = $user->getNom(); 
                $prenom=$user->getPrenom();
                $message = 'M/Mme '.$nom.' '.$prenom.', on a ajouté un nouveau garage : '.$nomg.' !';
                    $sms = new SmsMessage('+216'.$numero, $message);
                $twilio->send($sms);
            }

            
               return $this->redirectToRoute('afficheG',);
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
            //$adresse=$garageC->getAdresse();
            $adresse = $request->get('adresse');
            if ($adresse=='') {
                // Affichage d'une erreur
           
                $this->addFlash('error', 'Le champ adresse ne peut pas être vide.');
        
                // Redirection vers la page du formulaire
                return $this->renderForm("garage_c/ajoutGarageC.html.twig",
                array("f"=>$form,"g"=>$garageC));
            }
            $garageC->setAdresse($adresse);
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
