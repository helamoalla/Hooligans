<?php

namespace App\Controller;

use App\Entity\Bonplan;
use App\Form\BonplanFormType;
use App\Repository\UserRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class BonplanController extends AbstractController
{
    #[Route('/bonplan', name: 'app_bonplan')]
    public function index(): Response
    {
        return $this->render('bonplan/index.html.twig', [
            'controller_name' => 'BonplanController',
        ]);
    }

    
    // ajouter bonPlan

    #[Route('/addBonplan', name: 'addBonplan')]
    public function addBonplan(ManagerRegistry $doctrine,Request $request,UserRepository $userRep): Response
    {
        $user=$userRep->find(26);
        $bonplan=new Bonplan();
        $form=$this->createForm(BonplanFormType::class,$bonplan);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em =$doctrine->getManager() ;
            $bonplan->setUser($user);
            $imageFile = $form->get('imageFile')->getData();

            if ($imageFile) {
                $imagesDirectory = 'C:/xampp/htdocs/images';
                $originalFilename = $imageFile->getClientOriginalName();
                $filenameWithoutSpaces = str_replace(' ', '_', $originalFilename);

                try {
                    $imageFile->move($imagesDirectory, $filenameWithoutSpaces);
                } catch (FileException $e) {
                    // Handle exception
                }

                $bonplan->setImage($filenameWithoutSpaces);
            }



            //$bonplan->setImage("img.jpg");
            $em->persist($bonplan);
            $em->flush();
            return $this->render('index.html.twig', [
                
            ]);}

        return $this->renderForm("bonplan/addBonplan.html.twig",
        array("f"=>$form));
    }
}
