<?php

namespace App\Controller;

use App\Entity\Panier;
use App\Entity\Role;
use App\Entity\User;
use App\Form\RegistrationFormType;
use App\Security\LoginAuthenticator;
use App\Service\StripeService;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Http\Authentication\UserAuthenticatorInterface;
use Symfony\Contracts\Translation\TranslatorInterface;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;

class RegistrationController extends AbstractController
{
    #[Route('/register/{idrole}', name: 'app_register')]
    public function register(Request $request, UserPasswordHasherInterface $userPasswordHasher, UserAuthenticatorInterface $userAuthenticator, LoginAuthenticator $authenticator, EntityManagerInterface $entityManager,int $idrole): Response
    {


        $user = new User();
        $panier= new Panier();
        $userRole = $entityManager->getRepository(Role::class)->find($idrole);
        $user ->setIdRole($userRole);
        $user->setEtatUser(0);
        

        $form = $this->createForm(RegistrationFormType::class, $user);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $user->setMdp(
                $userPasswordHasher->hashPassword(
                    $user,
                    $form->get('plainPassword')->getData()
                )
                
            );
            $cvFile = $form->get('cv')->getData();
            if ($cvFile) {
                $originalFilename = pathinfo($cvFile->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = $originalFilename.'-'.uniqid().'.'.$cvFile->guessExtension();
        
                try {
                    $cvFile->move(
                        "%kernel.project_dir%/public/cv_directory",
                        $newFilename
                    );
                } catch (FileException $e) {
                    // handle exception if something happens during file upload
                }
        
                $user->setCv($newFilename);
              
            }
            $user->setImg("hela.jpg");
            $entityManager->persist($user);
            $entityManager->flush();

            $panier->setUser($user);
            $panier->setId($user->getIdUser());
           
            
            $entityManager->persist($panier);
            $entityManager->flush();
           
            return $userAuthenticator->authenticateUser(
                $user,
                $authenticator,
                $request
            );
     

  

        }
return $this->render('registration/register.html.twig', [
    'registrationForm' => $form->createView(),
    'idrole' => $idrole]);
}}