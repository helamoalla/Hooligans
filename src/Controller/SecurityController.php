<?php

namespace App\Controller;

use App\Service\QrCodeGenerator;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Symfony\Component\HttpFoundation\Request;


class SecurityController extends AbstractController
{
    #[Route('/login',name:'app_login')]
    public function login(AuthenticationUtils $authenticationUtils,QrCodeGenerator $qrCodeGenerator,Request $request): Response
    {
         if ($this->getUser()) {
             return $this->redirectToRoute('app_home');
         }

        // get the login error if there is one
        $error = $authenticationUtils->getLastAuthenticationError();
        // last username entered by the user
        $lastUsername = $authenticationUtils->getLastUsername();
$qrCode=$qrCodeGenerator->createQrCode($request);
         $session = $request->getSession();
         $info = $session->get('info');
         
        return $this->render('user/login.html.twig', ['last_username' => $lastUsername, 'error' => $error,'qrCode'=>$qrCode->getDataUri()
            ,'info' => $info]);
    }

    #[Route(path: '/logout', name: 'app_logout')]
    public function logout(): Response
    {
        // vide la session
        $this->get('session')->invalidate();
        
        // redirection vers la page de connexion
        return $this->redirectToRoute('app_login');
    }
    


}
