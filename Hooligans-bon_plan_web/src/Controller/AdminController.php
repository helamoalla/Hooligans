<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use App\Repository\CategorieRepository ;
use App\Repository\ProduitRepository ;
use Doctrine\Persistence\ManagerRegistry ;
use App\Entity\Categorie;
use App\Entity\Produit;
use App\Form\CategoryFormType ;
use App\Form\ProduitFormType ;

class AdminController extends AbstractController
{
    #[Route('/admin', name: 'app_admin')]
    public function index(CategorieRepository $Rep, ProduitRepository $Rep1): Response
    {  $Categorie=$Rep->findAll();
     $Produit=$Rep1->findAll();

        return $this->render('homeadmin.html.twig', [
            'controller_name' => 'AdminController',
            'c'=>$Categorie ,
            'p'=>$Produit ,
        ]);
    }
    
 
    

    //Ajouter categorie

        #[Route('/adminaddcategory', name: 'addcategory')]
        public function addCategorie(ManagerRegistry $doctrine,Request $request)
                {
                $Categorie= new Categorie();
                $form=$this->createForm(CategoryFormType::class,$Categorie);
                    $form->handleRequest($request);
                    if($form->isSubmitted()){
                        $em =$doctrine->getManager() ;
                        $em->persist($Categorie);
                        $em->flush();
                        return $this->redirectToRoute("addcategory");}
                return $this->renderForm("Categorie/addCategoryAdmin.html.twig",
                        array("f"=>$form));
                    }
                    
    //Afficher Categorie
        #[Route('/adminaffichecategorie', name: 'affichecategorie')]
        public function afficheCategorie(CategorieRepository $Rep): Response
        { $Categorie=$Rep->findAll();
        
            return $this->render('Categorie/afficherCategorieAdmin.html.twig', [
            's'=>$Categorie  ,
            

            ]);
        }
      

    

    //Supprimer Categorie
    #[Route('/adminsupprimecategorie/{id}', name: 'supprimec')]
    public function supprimec($id,CategorieRepository $r, ManagerRegistry $doctrine): Response
    {   //recuperer la categorie a supprimer
        $Categorie=$r->find($id);
        //action supprmer
        $em=$doctrine->getManager();
        $em->remove($Categorie);
        $em->flush();
        return $this->redirectToRoute('affichecategorie',); 
    }

    //Update Categorie

    #[Route('/adminupdatecategorie/{id}', name: 'updatec')]
    public function modifierCategorie(ManagerRegistry $doctrine,Request $request,$id,CategorieRepository $r)
                           {
          { //récupérer la categorieà modifier
            $Categorie=$r->find($id);
        $form=$this->createForm(CategoryFormType::class,$Categorie);
         $form->handleRequest($request);
         if($form->isSubmitted()){
        $em =$doctrine->getManager() ;
             $em->flush();
            return $this->redirectToRoute("affichecategorie");}
             return $this->renderForm("Categorie/addCategoryAdmin.html.twig",
                array("f"=>$form));
                            }
                        }

                    ////////////////////

             //Afficher Produit
        #[Route('/adminafficheproduit', name: 'afficheproduit')]
        public function afficheProduit(ProduitRepository $Rep): Response
        { $Produit=$Rep->findAll();
        
            return $this->render('produit/afficherProduitAdmin.html.twig', [
            's'=>$Produit  ,
            

            ]);
        }   

    
        //Ajouter produit

        #[Route('/adminaddproduit', name: 'addproduit')]
        public function addProduit(ManagerRegistry $doctrine,Request $request)
                {
                $Produit= new Produit();
                $form=$this->createForm(ProduitFormType::class,$Produit);
                    $form->handleRequest($request);
                    if($form->isSubmitted()&& $form->isValid()){
                        $em =$doctrine->getManager() ;
                        $imageFile = $form->get('image')->getData();
                        
                        if ($imageFile) {
                            $imagesDirectory = 'C:/xampp/htdocs/images';
                            $originalFilename = $imageFile->getClientOriginalName();
                            $filenameWithoutSpaces = str_replace(' ', '_', $originalFilename);
            
                            try {
                                
                                $imageFile->move($imagesDirectory,$filenameWithoutSpaces);
                                
                            } catch (FileException $e) {
                                // Handle exception
                            }
                            $Produit->setImage($filenameWithoutSpaces);
                        }
                        
            
                        //$bonplan->setImage("img.jpg");
                     
                        $em->persist($Produit);
                        $em->flush();
                        return $this->redirectToRoute('afficheproduit');}
            
                       return $this->renderForm("produit/addProduitAdmin.html.twig",
                       
                        array("f"=>$form));
                    }

                 
                    

   //Supprimer Produit
   #[Route('/adminsupprimeproduit/{id}', name: 'supprimep')]
   public function supprimep($id,ProduitRepository $r, ManagerRegistry $doctrine): Response
   {   //recuperer le produit a supprimer
       $Produit=$r->find($id);
       //action supprimer
       $em=$doctrine->getManager();
       $em->remove($Produit);
       $em->flush();
       return $this->redirectToRoute('afficheproduit',); 
   }
     
   
   //UpdateProduit

   #[Route('/adminupdateproduit/{id}', name: 'updatep')]
   public function modifierProduit(ManagerRegistry $doctrine,Request $request,$id,ProduitRepository $r)
                          {
         { //récupérer le produit a modifier
           $Produit=$r->find($id);
       $form=$this->createForm(ProduitFormType::class,$Produit);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
       $em =$doctrine->getManager() ;
       $imageFile = $form->get('image')->getData();
          
       if ($imageFile) {
           $imagesDirectory = 'C:/xampp/htdocs/images';
           $originalFilename = $imageFile->getClientOriginalName();
           $filenameWithoutSpaces = str_replace(' ', '_', $originalFilename);

           try {
               
               $imageFile->move($imagesDirectory,$filenameWithoutSpaces);
               
           } catch (FileException $e) {
               // Handle exception
           }
           $Produit->setImage($filenameWithoutSpaces);
       }
       

       //$bonplan->setImage("img.jpg");
    
       $em->persist($Produit);
       $em->flush();
       return $this->redirectToRoute('afficheproduit');}

      return $this->renderForm("produit/addProduitAdmin.html.twig",
       array("f"=>$form));
   }}



}
