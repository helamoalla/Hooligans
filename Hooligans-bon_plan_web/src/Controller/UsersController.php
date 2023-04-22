<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\ProduitRepository ;
use App\Entity\Produit;
use App\Repository\CategorieRepository ;
use App\Entity\Categorie;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\HttpFoundation\JsonResponse;

class UsersController extends AbstractController
{
    #[Route('/users', name: 'app_users')]
    public function index(): Response
    {
        return $this->render('homeuser.html.twig', [
            'controller_name' => 'UsersController',
        ]);
    }

                 
    //Afficher Produit
    #[Route('/userafficheproduit', name: 'affichep')]
    public function affichep(ProduitRepository $Rep,CategorieRepository $Rep1,PaginatorInterface $paginator , Request $request): Response
    { 
       
        $Categorie=$Rep1->findAll();
        //on recupere le filtre
        $filters= $request->get("categories");
        
       //Je recupere el produit belfonction eli aamaltha 
        $Produit=$Rep->findByCategoryId($filters);
        $Produit = $paginator->paginate(
            $Produit, /* query NOT result */
            $request->query->getInt('page', 1)/*page number*/,

            12/*limit per page*/,
           
        );
       
        $pageNumber = $Produit->getCurrentPageNumber(); 
        // On vérifie si on a une requête Ajax
        if($request->get('ajax')){
            return new JsonResponse([
                'content' => $this->renderView('produit/content.html.twig', [
                    'p'=>$Produit  ,
                    'c'=>$Categorie  ,
                    'pagenum'=>$pageNumber ,
            
                    ])
            ]);
        }
      
        return $this->render('produit/afficherProduitUser.html.twig', [
        'p'=>$Produit  ,
        'c'=>$Categorie  ,
        'pagenum'=>$pageNumber ,
        

        ]);
    }

    //Afficherdetail
    #[Route('/useraffichedetailproduit/{id}', name: 'affichedetail')]
    public function affichedetail(ProduitRepository $Rep, int $id ): Response
    { $Produit=$Rep->find($id);
     $Produits=$Rep->findAll();
        
        return $this->render('produit/afficherDetailProduitUser.html.twig', [
        'p'=>$Produit  ,
        's'=>$Produits  ,
        
        

        ]);
}

}