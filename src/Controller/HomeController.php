<?php

namespace App\Controller;

use App\Entity\Event;
use App\Entity\Ticket;
use App\Entity\User;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use App\Form\EventType;
use App\Form\QuantiteType;
use App\Repository\EventRepository;
use App\Repository\TicketRepository;
use InvalidArgumentException;
use PHPUnit\Framework\Constraint\FileExists;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


class HomeController extends AbstractController
{
    public function index(): Response
    {
        return $this->render('admin.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
    #[Route('/add', name: 'add')]
    public function ajouter(ManagerRegistry $doctrine,Request $request): Response
    {
        $Event=new Event();
        
        $form=$this->createForm(EventType::class,$Event);
        $form->handleRequest($request);
        if($form->isSubmitted()&& $form->isValid()){
            $em =$doctrine->getManager() ;
            $imageFile = $form->get('image_event')->getData();
            
            if ($imageFile) {
                $imagesDirectory = 'C:/xampp/htdocs/images';
                $originalFilename = $imageFile->getClientOriginalName();
                $filenameWithoutSpaces = str_replace(' ', '_', $originalFilename);

                
                    
                    $imageFile->move($imagesDirectory,$filenameWithoutSpaces);
           
                $Event->setImageEvent($filenameWithoutSpaces);
            }
            

            //$bonplan->setImage("img.jpg");
         
            $em->persist($Event);
            $em->flush();
            return $this->redirectToRoute('app_get');}

           return $this->renderForm("Event/addEvent.html.twig",
           
            array("f"=>$form));
        }
        #[Route('/get', name: 'app_get')]
    public function affiche(EventRepository $r): Response
        {
        $Event=$r->orderById();
        return $this->render('Event/getallEvent.html.twig', [
            'e'=>$Event,
        ]);
    }
    #[Route('/resume', name: 'resume')]
    public function resume(): Response
    {
        return $this->render('resume.html.twig', [
            
        ]);
    }
    #[Route('/supprimer/{id}', name: 'supprimer')]
    public function supprimer($id,EventRepository $r, ManagerRegistry $doctrine): Response
    {   ///////recuperer la classroom a supp//////////
         $Event=$r->find($id);
         ////////supprimer/////////
        $em=$doctrine->getManager();/////persist()
        $em->remove($Event);/////remove()
        $em->flush(); ////////flush()
        return $this->redirectToRoute('app_get',);
    }

    #[Route('/modifier/{id}', name: 'modifier')]
        public function modifier($id,EventRepository $r,ManagerRegistry $doctrine,Request $request)
                
              { //récupérer Event à modifier
 
                
                                
                    $Event=$r->find($id);
                    $form=$this->createForm(EventType::class,$Event);
                     $form->handleRequest($request);
        
                     
                     $imageFile = $form->get('image_event')->getData();
                    
                  
                     if($form->isSubmitted() && $form->isValid()){
                    $em =$doctrine->getManager() ;
                    $imageFile = $form->get('image_event')->getData();
        
                    if ($imageFile) {
                        
                        $imagesDirectory = 'C:/xampp/htdocs/images';
                        $originalFilename = $imageFile->getClientOriginalName();
                        $filenameWithoutSpaces = str_replace(' ', '_', $originalFilename);
        
                        
                           
                           $imageFile->move($imagesDirectory, $filenameWithoutSpaces);
                      
                        
                        $Event->setImageEvent($filenameWithoutSpaces);
                        }
                        $em->flush();
                        return $this->redirectToRoute("app_get");
                    }
                                                         
                
                    return $this->renderForm("Event/addEvent.html.twig",
                                               array("f"=>$form)); 
                                               

                
         
                                
                            }
                            #[Route('/getU', name: 'app_getU')]
                            public function afficheU(EventRepository $r): Response
                                {
                                    $Event=$r->orderById();
                                return $this->render('Event/getallEventUser.html.twig', [
                                    'e'=>$Event,
                                ]);
                            }
                            #[Route('/detailE/{id}', name: 'app_detail')]
                            public function detailE($id,Request $request,ManagerRegistry $doctrine): Response
                                {
                                    $form=$this->createForm(QuantiteType::class);
                                    $form->handleRequest($request);
                                $Event=$this->getDoctrine()->getRepository(Event::class)->find($id);
                                if($form->isSubmitted() && $form->isValid()){
                                   
                                    $quantite= $form->get('quantity')->getData();
                        
                                    for ($i = 0; $i < $quantite; $i++) {
                                        $Event=$this->getDoctrine()->getRepository(Event::class)->find($id);
                                        $ticket=new Ticket();
                                        $user=$this->getDoctrine()->getRepository(User::class)->find(25);
                                        $ticket->setUser($user);
                                        $ticket->setEvent($Event);
                                        $ticket->setNumTicket(123);
                                        $ticket->setImageQr("dvvd");
                                        $em =$doctrine->getManager() ;

                                          $em->persist($ticket);
                                          
                                          $em->flush();
                              }
                                        
                              return $this->redirectToRoute("app_getT");}

                                return $this->renderForm("Event/detailEvent.html.twig",
                                array("f"=>$form,"e"=>$Event));
                            }
                        
                            #[Route('/getT', name: 'app_getT')]
                            public function afficheT(TicketRepository $r): Response
                                {
                                $Ticket=$r->findAll();
                                return $this->render('ticket/getallticket.html.twig', [
                                    't'=>$Ticket,
                                ]);
                            }
                            #[Route('/getTA', name: 'app_getTA')]
                            public function afficheTA(TicketRepository $r): Response
                                {
                                $Ticket=$r->findAll();
                                return $this->render('ticket/getallticketAdmin.html.twig', [
                                    't'=>$Ticket,
                                ]);
                            }
                            #[Route('/supprimerTA/{id}', name: 'supprimerTA')]
                            public function supprimerTA($id,TicketRepository $r, ManagerRegistry $doctrine): Response
                            {   ///////recuperer la classroom a supp//////////
                                 $Ticket=$r->find($id);
                                 ////////supprimer/////////
                                $em=$doctrine->getManager();/////persist()
                                $em->remove($Ticket);/////remove()
                                $em->flush(); ////////flush()
                                return $this->redirectToRoute('app_getTA',);
                            }
                        
                            
}
