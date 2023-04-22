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
use CMEN\GoogleChartsBundle\CMENGoogleChartsBundle\Barchart ;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart ;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\LineChart ;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\BareChart ;
use CMEN\GoogleChartsBundle\GoogleCharts;


class AdminController extends AbstractController
{
    #[Route('/admin', name: 'app_admin')]
    public function index(CategorieRepository $Rep, ProduitRepository $Rep1): Response
    {  $Categorie=$Rep->findAll();
     $Produit=$Rep1->findAll();
    //    $chart = new \CMEN\GoogleChartsBundle\GoogleCharts\Charts\BarChart();
    //     $chart->getData()->setArrayToDataTable([
    //         ['Year', 'Sales', 'Expenses'],
    //         ['2014', 1000, 400],
    //         ['2015', 1170, 460],
    //         ['2016', 660, 1120],
    //         ['2017', 1030, 540]
    //     ]);

// Récupérer les données de votre base de données
$em = $this->getDoctrine()->getManager();
$query = $em->createQuery('SELECT p.nom_prod, p.quantite_prod FROM App\Entity\Produit p');
$results = $query->getResult();

// Convertir les données en un format de données pris en charge par PieChart
$data = [['Product', 'Quantity']];
foreach ($results as $result) {
    $data[] = [$result['nom_prod'], (int) $result['quantite_prod']];
}

        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable($data);
        $pieChart->getOptions()->setTitle(' Quantité en stock');
        $pieChart->getOptions()->setHeight(400);
        $pieChart->getOptions()->setWidth(700);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#bde0ff');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);
        $pieChart->getOptions()->setColors(['#bde0ff', '#c8f4d5', '#e3f1cb', '#d4e9da', '#d7ffe4', '#dde3e3','#d7e5db','#afeeee','#e6f6c6','#fdf1b8']);
        $pieChart->getOptions()->setIs3D(true);
        $pieChart->getOptions()->getLegend()->getTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getLegend()->getTextStyle()->setFontSize(14);
        $pieChart->getOptions()->getLegend()->getTextStyle()->setColor('#666666');
        $pieChart->getOptions()->getPieSliceTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getPieSliceTextStyle()->setFontSize(14);
        $pieChart->getOptions()->setBackgroundColor('#f5f5f5');
        $pieChart->getOptions()->getLegend()->setPosition('left');
        $pieChart->getOptions()->setPieSliceText('label');

///////2EME CHART///////
     $query1 = $em->createQuery('SELECT p.nom_prod, p.prix_prod FROM App\Entity\Produit p');
    $result1 = $query1->getResult();

// Convertir les données en un format de données pris en charge par PieChart
$data1 = [['Product', 'Price']];
foreach ($result1 as $result) {
    $data1[] = [$result['nom_prod'], (float) $result['prix_prod']];}

    $line = new LineChart();
    $line->getData()->setArrayToDataTable($data1);
    $line->getOptions()->setTitle('Variation des prix des produits');
    $line->getOptions()->setCurveType('function');
    $line->getOptions()->setLineWidth(4);
    $line->getOptions()->getLegend()->setPosition('none');
    $line->getOptions()->setHeight(400);
    $line->getOptions()->setWidth(700);
    $line->getOptions()->setPointShape('point');
    $line->getOptions()->getTitleTextStyle()->setBold(true);
    $line->getOptions()->getTitleTextStyle()->setColor('#bde0ff');
    $line->getOptions()->setBackgroundColor('#f5f5f5');
    $line->getOptions()->getHAxis()->setTitle('Produits');
    $line->getOptions()->getVAxis()->setTitle('Price');
    $line->getOptions()->getTooltip()->setIsHtml(true);
    $line->getOptions()->setColors(['#bde0ff', '#DC3912', '#FF9900']);
    $line->getOptions()->getLegend()->setPosition('top');
    $line->getOptions()->getAnimation()->setDuration(1000);
    $line->getOptions()->setPointSize(7);


//////3EME CHART/////
$query2 = $em->createQuery('SELECT c.id, COUNT(p.id) AS nb_produits FROM App\Entity\Categorie c , App\Entity\Produit p Where c.id=p.id GROUP BY c.id');
$result2 = $query2->getResult();

// Convertir les données en un format de données pris en charge par PieChart
$data2 = [['Categorie', 'Nbre produit']];
foreach ($result2 as $result) {
    $data2[] = (int)[$result['id_categorie'], (int) $result['nb_produits']];}
  
    $bar = new \CMEN\GoogleChartsBundle\GoogleCharts\Charts\BarChart();
    $bar->getData()->setArrayToDataTable($data2);
    $bar->getOptions()->setTitle('Population of Largest U.S. Cities');

    
        return $this->render('homeadmin.html.twig', [
            'controller_name' => 'AdminController',
            'c'=>$Categorie ,
            'p'=>$Produit ,
           // 'charts'=>$chart ,
            'pieCharts'=>$pieChart ,
            'lines'=>$line,
            'bars'=>$bar ,
          
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
                        return $this->redirectToRoute("affichecategorie");}
                return $this->renderForm("Categorie/addCategoryAdmin.html.twig",
                        array("f"=>$form));
                    }
                    
    //Afficher Categorie
        #[Route('/adminaffichecategorie', name: 'affichecategorie')]
        public function afficheCategorie(CategorieRepository $Rep): Response
        { $Categorie=$Rep->orderById();
        
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
        { $Produit=$Rep->orderById();
        
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

                // Vérifier si le produit existe déjà en base de données
               $existingProduit = $em->getRepository(Produit::class)->findOneBy([
             'nom_prod' => $Produit->getNomProd(),
              
                  ]);
                  $nomprod = $form->get('nom_prod')->getData();
                  if ($existingProduit) {
                    // Si le produit existe déjà, mettre à jour la quantité
                    $existingProduit->setQuantiteProd($existingProduit->getQuantiteProd() + $Produit->getQuantiteProd());
                    $this->addFlash('error1', 'La quantité du produit '. $nomprod .'  est modifiée avec succes.');
                } else {
                    // Sinon, ajouter le nouveau produit à la base de données 


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
                        
            
                     
                        $em->persist($Produit);
                       
                        $this->addFlash('error', 'Le produit '. $nomprod .' est ajoutée avec succes.');
                }
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



       //Afficherdetail
       #[Route('/adminaffichedetailproduit/{id}', name: 'affichedetailp')]
       public function affichedetailp(ProduitRepository $Rep, int $id ): Response
       { $Produit=$Rep->find($id);
       
           
           return $this->render('produit/afficherDetailProduitAdmin.html.twig', [
           'p'=>$Produit  ,

   
           ]);
   }
   

}
