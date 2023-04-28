<?php

namespace App\Controller;
use Endroid\QrCodeBundle\Response\QrCodeResponse;
use Endroid\QrCode\Generator\QrCodeGeneratorInterface;
use Dompdf\Dompdf;
use Symfony\Component\Mime\Email;
use Symfony\Component\Mailer\Transport\TransportInterface;
use Endroid\QrCode\QrCode;
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
use App\Service\SagendaAPI;
use BaconQrCode\Common\ErrorCorrectionLevel;
use InvalidArgumentException;
use PHPUnit\Framework\Constraint\FileExists;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Endroid\QrCode\Builder\BuilderInterface;
use Endroid\QrCodeBundle\QrCodeGenerator;
use Endroid\QrCode\Color\Color;
use Endroid\QrCode\Encoding\Encoding;
use Endroid\QrCode\ErrorCorrectionLevel\ErrorCorrectionLevelLow;
use Endroid\QrCode\Label\Label;
use Endroid\QrCode\Logo\Logo;
use Endroid\QrCode\Writer\PngWriter;
use Endroid\QrCode\Label\Font\NotoSans;
use App\Repository\UserRepository;
class HomeController extends AbstractController
{
    public function index(): Response
    {
        return $this->render('admin.html.twig', [
            'controller_name' => 'HomeController',
        ]);
    }
  
    #[Route('/qrcode', name: 'qrcode')]
    public function index1(): Response
    {
        $writer = new PngWriter();
        $qrCode = QrCode::create('nom event')
            ->setEncoding(new Encoding('UTF-8'))
            ->setErrorCorrectionLevel(new ErrorCorrectionLevelLow())
            ->setSize(120)
            ->setMargin(0)
            ->setForegroundColor(new Color(0, 0, 0))
            ->setBackgroundColor(new Color(255, 255, 255));
        $logo = Logo::create('images/logo.png')
            ->setResizeToWidth(60);
        $label = Label::create('')->setFont(new NotoSans(8));
 
        $qrCodes = [];
        $qrCodes['img'] = $writer->write($qrCode, $logo)->getDataUri();
        $qrCodes['simple'] = $writer->write(
                                $qrCode,
                                null,
                                $label->setText('Simple')
                            )->getDataUri();
 
        $qrCode->setForegroundColor(new Color(255, 0, 0));
        $qrCodes['changeColor'] = $writer->write(
            $qrCode,
            null,
            $label->setText('Color Change')
        )->getDataUri();
 
        $qrCode->setForegroundColor(new Color(0, 0, 0))->setBackgroundColor(new Color(255, 0, 0));
        $qrCodes['changeBgColor'] = $writer->write(
            $qrCode,
            null,
            $label->setText('Background Color Change')
        )->getDataUri();
 
        $qrCode->setSize(200)->setForegroundColor(new Color(0, 0, 0))->setBackgroundColor(new Color(255, 255, 255));
        $qrCodes['withImage'] = $writer->write(
            $qrCode,
            $logo,
            $label->setText('With Image')->setFont(new NotoSans(20))
        )->getDataUri();
 
        return $this->render('qrcode.html.twig', $qrCodes);
    

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
        public function modifier($id,EventRepository $r,ManagerRegistry $doctrine,Request $request,TransportInterface $mailer,UserRepository $ur,TicketRepository $tr )
                
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
                        // Créer l'email
                      
                        $tickets = $tr->findBy([
                            'event' => $id
                        ]);

                        foreach ($tickets as $ticket){
                            $email = (new Email())
                            ->from('ayoub.bbarnat@gmail.com')
                            ->to($ticket->getUser()->getEmail())
                            ->subject('Reprogrammation de l evenement')
                            ->text('La date de l\'événement a été modifiée. L\'événement sera programmé pour le ' . $Event->getDateDebut()->Format('d/m/Y') . ' Et se termine le  ' . $Event->getDateFin()->Format('d/m/Y') );
                        
                        // Envoyer l'email
                        $mailer->send($email);
                        }

       
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
                                        $writer = new PngWriter();
                                       
                                        $qrCode = QrCode::create( $Event->getNomEvent())
                                            ->setEncoding(new Encoding('UTF-8'))
                                            ->setErrorCorrectionLevel(new ErrorCorrectionLevelLow())
                                            ->setSize(120)
                                            ->setMargin(0)
                                            ->setForegroundColor(new Color(0, 0, 0))
                                            ->setBackgroundColor(new Color(255, 255, 255));
                                 
                                        
                                 
                                        $qrCodes = [];
                                      
                                        $qrCodes['simple'] = $writer->write(
                                                                $qrCode,
                                                                null,
                                                          
                                                            )->getDataUri();
                                       
                                    
                                        $chemin ='C:/xampp/htdocs/images/';

//Générer un nom unique pour l'image
$nomImage = uniqid('qr_', true) . '.png';

//Enregistrer l'image sur le disque
file_put_contents($chemin . $nomImage, base64_decode(substr($qrCodes['simple'], strpos($qrCodes['simple'], ',') + 1)));

//Enregistrer le nom de l'image dans la base de données
$ticket->setImageQr($nomImage);
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
                        
                            #[Route('/agenda', name: 'agenda')]

                            public function Agenda(EventRepository $r): Response
                            {
                               
                                $Event=$r->orderById();
                                return $this->render('Event/programme.html.twig', [
                                    'events' => $Event,
                                ]);
                            }
                            public function downloadTicketPdf($id)
{
    // Récupérer le ticket correspondant à l'ID
    $ticket = $this->getDoctrine()->getRepository(Ticket::class)->find($id);

    // Générer le contenu du fichier PDF à télécharger (par exemple, le texte du ticket)
    $content = $this->renderView('ticket/pdf.html.twig', ['ticket' => $ticket]);

    // Générer le PDF avec Dompdf
    $dompdf = new Dompdf();
    $dompdf->loadHtml($content);
    $dompdf->setPaper('A4', 'portrait');
    $dompdf->render();

    // Créer une réponse de type 'Response' avec le contenu PDF et les en-têtes appropriés
    $response = new Response($dompdf->output());
    $response->headers->set('Content-Type', 'application/pdf');
    $response->headers->set('Content-Disposition', 'attachment; filename="ticket.pdf"');

    return $response;
}
                            }
                            
                            

