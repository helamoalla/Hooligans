<?php

namespace App\Controller;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use App\Entity\Formation;
use App\Entity\User;
use App\Entity\Role;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;


class AdminController extends AbstractController
{



    #[Route('/admin/modify/{id}', name: 'app_admin_modify')]
    public function modify(Request $request, $id,EntityManagerInterface $entityManager): Response
    {
        $this->denyAccessUnlessGranted('ROLE_ADMIN');
        $user = $this->getDoctrine()->getRepository(User::class)->find($id);

        $selectedOption = $request->request->get('my_select');

        $role = $entityManager->getRepository(Role::class)->findOneBy(['idRole' => $selectedOption]);

       $user->setIdRole($role);
       $entityManager->flush();
       return $this->redirectToRoute("app_admin_gererusers",[]);

   
        
    }


    #[Route('/admin/user/delete/{idUser}', name: 'app_user_delete')]
    public function deleteUser(User $user): Response
    {
        $this->denyAccessUnlessGranted('ROLE_ADMIN');
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($user);
        $entityManager->flush();

        return $this->redirectToRoute('app_admin_gererusers');
    }
    #[Route('/admin/formation',name: 'app_admin_gererformation')]
    public function gererFormation(EntityManagerInterface $em) {
        $this->denyAccessUnlessGranted('ROLE_ADMIN');
        $formations = $em->getRepository(Formation::class)->findAll();
        return $this->render('admin/FormationBack.html.twig',[
            'formations' => $formations
        ]);

    }
    #[Route('/admin/users_list', name: 'app_admin_gererusers')]
    public function gererUsers(EntityManagerInterface $entityManager, Request $request)
    {
        $this->denyAccessUnlessGranted('ROLE_ADMIN');
        $users = $entityManager->getRepository(User::class)->findAll();
        $user = $this->getUser();
        $form = $this->createFormBuilder($user)
            ->add('idRole', ChoiceType::class, [
                'choices' => [
                    'Admin' => 1,
                    'User' => 2,
                    'Recruteur' => 3
                ],
                'expanded' => true,
                'multiple' => false,
                'label' => 'Role'
            ])
            ->add('Modifier', SubmitType::class)
            ->getForm();
    
        $form->handleRequest($request);
    
        if ($form->isSubmitted() && $form->isValid()) {
            $user->setIdUser($form->get("idRole")->getData());
            $entityManager->flush();
            return $this->redirectToRoute('app_admin_gererusers');
        }
    
        return $this->render('admin/usersBack.html.twig', [
            'form' => $form->createView(), // pass the form view instead of the form itself
            'users' => $users
        ]);
    }
}    
