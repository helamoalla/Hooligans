<?php

namespace App\Controller;

use App\Entity\User;
use App\Entity\Publication;
use App\Entity\Commentaire;
use App\Form\CommentaireType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/commentaire')]
class CommentaireController extends AbstractController
{
    #[Route('/', name: 'app_commentaire_index', methods: ['GET'])]
    public function index(EntityManagerInterface $entityManager): Response
    {
        $commentaires = $entityManager
            ->getRepository(Commentaire::class)
            ->findAll();

        return $this->render('commentaire/index.html.twig', [
            'commentaires' => $commentaires,
        ]);
    }

    #[Route('/new/{idPub}', name: 'app_commentaire_new', methods: ['GET', 'POST'])]
    public function new($idPub,Request $request, EntityManagerInterface $entityManager): Response
    {
        $pub = $entityManager
            ->getRepository(Publication::class)
            ->find($idPub);
            $user = $entityManager
            ->getRepository(User::class)
            ->find(1);
        $commentaire = new Commentaire();
        $form = $this->createForm(CommentaireType::class, $commentaire);
        $form->handleRequest($request);
      
        if ($form->isSubmitted() && $form->isValid()) {
            $commentaire->setIdUser($user);
            $commentaire->setIdPublication($pub);
            $commentaire->setDate(new \DateTime());
            $entityManager->persist($commentaire);
            $entityManager->flush();

            return $this->redirectToRoute('app_commentaire_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('commentaire/new.html.twig', [
            'commentaire' => $commentaire,
            'form' => $form,
        ]);
    }

    #[Route('/{idComment}', name: 'app_commentaire_show', methods: ['GET'])]
    public function show(Commentaire $commentaire): Response
    {
        return $this->render('commentaire/show.html.twig', [
            'commentaire' => $commentaire,
        ]);
    }

    #[Route('/{idComment}/edit', name: 'app_commentaire_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Commentaire $commentaire, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(CommentaireType::class, $commentaire);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_commentaire_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('commentaire/edit.html.twig', [
            'commentaire' => $commentaire,
            'form' => $form,
        ]);
    }

    #[Route('/{idComment}', name: 'app_commentaire_delete', methods: ['POST'])]
    public function delete(Request $request, Commentaire $commentaire, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$commentaire->getIdComment(), $request->request->get('_token'))) {
            $entityManager->remove($commentaire);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_commentaire_index', [], Response::HTTP_SEE_OTHER);
    }

    #[Route('/deleteFront/{idComment}', name: 'deleteFront', methods: ['GET','POST'])]
    public function deleteFront($idComment,Request $request, Commentaire $commentaire, EntityManagerInterface $entityManager): Response
    {
        $commentaire = $entityManager
            ->getRepository(Commentaire::class)
            ->find($idComment);
            $entityManager->remove($commentaire);
            $entityManager->flush();
        

        return $this->redirectToRoute('listPub', [], Response::HTTP_SEE_OTHER);
    }

    #[Route('/updateCommentFront/{idComment}', name: 'updateCommentFront', methods: ['GET','POST'])]
    public function updateCommentFront($idComment,Request $request, Commentaire $commentaire, EntityManagerInterface $entityManager): Response
    {
        $commentaire = $entityManager
            ->getRepository(Commentaire::class)
            ->find($idComment);
            $form = $this->createForm(CommentaireType::class, $commentaire);
            $form->handleRequest($request);
    
            if ($form->isSubmitted() && $form->isValid()) {
                $entityManager->flush();
    
                return $this->redirectToRoute('listPub', [], Response::HTTP_SEE_OTHER);
            }
    
            return $this->renderForm('commentaire/editFront.html.twig', [
                'commentaire' => $commentaire,
                'form' => $form,
            ]);
        

    }
}
