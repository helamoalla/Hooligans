<?php

namespace App\Controller;
use App\Entity\Reponse;
use App\Entity\Commentaire;
use App\Entity\Publication;
use App\Entity\User;
use App\Form\PublicationType;
use App\Form\CommentaireType;
use App\Form\ReponseType;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mailer\Mailer;
use Symfony\Component\Mailer\Transport;
use Symfony\Component\Mime\Email;
use Symfony\Component\Mime\Address;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\JsonResponse;

#[Route('/publication')]
class PublicationController extends AbstractController
{
    #[Route('/', name: 'app_publication_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $publications = $entityManager
            ->getRepository(Publication::class)
            ->findAll();

        return $this->render('publication/index.html.twig', [
            'publications' => $publications,
        ]);
    }
    
    #[Route('/front/recSearch', name: 'recSearch', methods: ['GET','POST'])]
    public function search(Request $request, EntityManagerInterface $entityManager)
    {
        // Get search parameters from request
        $searchType = $request->query->get('searchType');
        $searchValue = $request->query->get('searchValue');
        
        // Query the database with search parameters using DQL
        $query = $entityManager->createQuery("SELECT t FROM App\Entity\Publication t WHERE t.$searchType LIKE :searchValue")
            ->setParameter('searchValue', '%' . $searchValue . '%');
        $publications = $query->getResult();
       
         // Manually serialize entities to avoid circular references
         $serializedRecs = [];
         foreach ($publications as $publication) {
             $serializedRecs[] = [
                
                'idpost' => $publication->getIdpost(),
                 'description' => $publication->getDescription(),
                 'nblikes' => $publication->getNblikes(),
                 'nbdislike' => $publication->getNbdislike(),
                 'nbcomments' => $publication->getNbcomments(),
                 'idUser' => $publication->getIdUser()->getNom(),
                 
             ];
         }
            // Create JSON response
        $response = new JsonResponse();
        $response->setData(['publications' => $serializedRecs]);
        return $response;
    }

    #[Route('/listPub', name: 'listPub', methods: ['GET'])]
    public function listPub(EntityManagerInterface $entityManager): Response
    {
        $idUser=1;
        $publications = $entityManager
            ->getRepository(Publication::class)
            ->findAll();

        return $this->render('publication/listePubFront.html.twig', [
            'publications' => $publications,
            'idUser'=>$idUser,
        ]);
    }

    #[Route('/pubDetails/{id}', name: 'pubComFront', methods: ['GET','POST'])]
    public function pubComFront($id,Request $request,EntityManagerInterface $entityManager): Response
    {
        $idUser=1;
        
        $publication = $entityManager
            ->getRepository(Publication::class)
            ->find($id);
            $user = $entityManager
            ->getRepository(User::class)
            ->find(1);
            $commentaires = $entityManager
            ->getRepository(Commentaire::class)
            ->findBy(['idPublication' => $publication]);
            $commentaire = new Commentaire();
            $reponse = new Reponse();
            $form = $this->createForm(CommentaireType::class, $commentaire);
            $formRep = $this->createForm(ReponseType::class, $reponse);
            $form->handleRequest($request);
            $formRep->handleRequest($request);
            if ($formRep->isSubmitted() ) {
                
          
                $reponse->setIdUser($user);
                $reponse->setIdCommentaire($entityManager
                ->getRepository(Commentaire::class)
                ->find($reponse->getIdCom()));
                $reponse->setDate(new \DateTime());
                
                $entityManager->persist($reponse);
                $entityManager->flush();
            }
            if ($form->isSubmitted() ) {
                $commentaire->setIdUser($user);
                $commentaire->setIdPublication($publication);
                $commentaire->setDate(new \DateTime());
                $containsBadWord = $this->checkBadWord($commentaire->getDescription());

        if ($containsBadWord) {            

            $transport = Transport::fromDsn("smtp://pidev.hooligans@gmail.com:nrciivksrjwhneso@smtp.gmail.com:587?encryption=tls");
            $mailer = new Mailer($transport);
           $emailTo = "pidev.hooligans@gmail.com";
            $email = (new Email())
       
            ->from('pidev.hooligans@gmail.com')
            ->to($emailTo)
            ->subject('Bad Word Notification!')
            ->text('Sending emails is fun again!')
            ->html('
            
                <h1>Bad Word Notification</h1>
                <p>Dear'. $user->getNom() .',</p>
                <p>We have detected that you used a bad word in your message on our website. This is not allowed as per our community guidelines.</p>
                <p>Please refrain from using inappropriate language in your future messages.</p>
                <p>Thank you for your cooperation.</p>
                <p>Sincerely,<br>Hooligans Team</p>
           ');   
        

$headers = $email->getHeaders();

$mailer->send($email);

        } else {
                $entityManager->persist($commentaire);
                $entityManager->flush();
            }
                return $this->redirectToRoute('pubComFront', ['id'=>$id], Response::HTTP_SEE_OTHER);
            }
        return $this->render('publication/pubComFront.html.twig', [
            'publication' => $publication,
            'commentaires' => $commentaires,
            'idUser'=>$idUser,
            'form' => $form->createView(),
            'formRep' => $formRep->createView(),
            
        ]);
    }

    #[Route('/new', name: 'app_publication_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $publication = new Publication();
        $form = $this->createForm(PublicationType::class, $publication);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $publication->setDate(new \DateTime())  ;
            $entityManager->persist($publication);
            $entityManager->flush();

            return $this->redirectToRoute('listPub', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('publication/new.html.twig', [
            'publication' => $publication,
            'form' => $form,
        ]);
    }

    #[Route('/{idpost}', name: 'app_publication_show', methods: ['GET'])]
    public function show(Publication $publication): Response
    {
        return $this->render('publication/show.html.twig', [
            'publication' => $publication,
        ]);
    }

    #[Route('/likepub/{idpost}', name: 'likepub', methods: ['GET'])]
    public function likepub($idpost,Publication $publication ,EntityManagerInterface $entityManager): Response
    {
       $publication->setNblikes($publication->getNblikes()+1);
       $entityManager->persist($publication);
            $entityManager->flush();

            return $this->redirectToRoute('pubComFront', ['id'=>$idpost], Response::HTTP_SEE_OTHER);
      
    }
    #[Route('/dislike/{idpost}', name: 'dislike', methods: ['GET'])]
    public function dislike($idpost,Publication $publication ,EntityManagerInterface $entityManager): Response
    {
       $publication->setNbdislike($publication->getNbdislike()+1);
       $entityManager->persist($publication);
            $entityManager->flush();

            return $this->redirectToRoute('pubComFront', ['id'=>$idpost], Response::HTTP_SEE_OTHER);
      
    }

    #[Route('/updatePubFront/{idpost}', name: 'updatePubFront', methods: ['GET', 'POST'])]
    public function updatePubFront($idpost ,Request $request,  EntityManagerInterface $entityManager): Response
    {
        $publication = $entityManager
            ->getRepository(Publication::class)
            ->find($idpost);
        $form = $this->createForm(PublicationType::class, $publication);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('pubComFront', ['id'=>$idpost], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('publication/updateFront.html.twig', [
            'publication' => $publication,
            'form' => $form,
        ]);
    }

    #[Route('/{idpost}/edit', name: 'app_publication_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Publication $publication, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(PublicationType::class, $publication);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_publication_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('publication/edit.html.twig', [
            'publication' => $publication,
            'form' => $form,
        ]);
    }

    #[Route('/{idpost}', name: 'app_publication_delete', methods: ['POST'])]
    public function delete(Request $request, Publication $publication, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$publication->getIdpost(), $request->request->get('_token'))) {
            $entityManager->remove($publication);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_publication_index', [], Response::HTTP_SEE_OTHER);
    }

    public function checkBadWord(string $word): bool
    {
        $badWords = ['Badword1', 'Badword2', 'Badword3']; 

        foreach ($badWords as $badWord) {
            if (stripos($word, $badWord) !== false) {
                return true;
            }
        }

        return false;
    }
}
