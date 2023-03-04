/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package holigans;

import Model.BonPlan;
import Model.Feedback;
import Model.Role;
import Model.User;
import Service.BonPlanService;
import Service.FeedbackService;
import Service.RoleService;
import Service.UserService;
import Util.Data;

/**
 *
 * @author azizh
 */
public class Holigans {

    public static void main(String[] args) {
        BonPlanService bonPlanService=new BonPlanService();
        FeedbackService feedbackService= new FeedbackService();
        
        // créer Bon Plan 
        //BonPlan b1= new BonPlan("Chez lasaad", "Mnihla", "Garage", 12);
        //BonPlan b2= new BonPlan("Chez Azouz", "Ariana", "Circuit", 15);
        //BonPlan b3= new BonPlan("Amigo", "Borj louzir", "Garage", 6);
        //BonPlan b4= new BonPlan("Mecanique", "Nikhlat", "Garage", 6);
        //BonPlan b=new BonPlan("Nadia","azi", "garage", "img.jpg",Data.id_user);
        
        // Ajouter un bonPlan
        //b2.setEtat("accepté");
        //b.setId_bonplan(34);
        //bonPlanService.insert(b);

        
        
        System.out.println(feedbackService.RatingAvg(bonPlanService.readById(70)));
        // afficher tous les bonplans
        
       // System.out.println(bonPlanService.readAll());
        
        // afficher bonplan by id
        
        //System.out.println(bonPlanService.readById(2));
        
        // Update bonPlan
        //b.setId_bonplan(48);
        //b.setAdresse("Aouina");
        //bonPlanService.update(b);
        //System.out.println(b);
        System.out.println(bonPlanService.readAll());
        
        
        // read by id
        //System.out.println( bonPlanService.readById(9));
        
        // trier bonPlan
        //System.out.println(bonPlanService.sortBy("id_user", "desc"));
        
        // delete bonPlan
        //bonPlanService.delete(9);
        //System.out.println(bonPlanService.readAll());
        
        
        
        // Créer un feedback 
        //Feedback f1= new Feedback(10, "mal endroit", 12, bonPlanService.readById(50));
        //Feedback f3= new Feedback(11,10, "Trés bon  endroit", 13, bonPlanService.readById(34));
        
        //f1.setReport(true);
        //feedbackService.insert(f1);
        //feedbackService.reportBonPlan(f1);
        //System.out.println(feedbackService.checkIfReported(f1));
        
        
        
        // Ajouter un feedback
        //feedbackService.insert(f1);
        //feedbackService.insert(f2);
        //feedbackService.insert(f3);
        
        // update feedback 
        
        //f1.setId_feedback(70);
        //f1.setCommentaire("Good oneee");
        //f1.setBonPlan(bonPlanService.readAll().get(2));
        //feedbackService.update(f1);
        
        //Afficher tous les feedbacks
        
        
        //System.out.println(feedbackService.readAll());
        //System.out.println(feedbackService.checkIfReported(f3));
        //feedbackService.countReports();
        //feedbackService.reportBonPlan(f1);
        //System.out.println(feedbackService.checkIfReported(f1));

        
        // Afficher feedback by id 
        
        //System.out.println(feedbackService.readById(1));
        
        // Sort feedback by rate
        
        //System.out.println(feedbackService.sortBy("rate", "desc"));
        
        // Delete Feedback 
        //feedbackService.delete(1);
        
        
        //User u1=new User()
        Role r1=new Role(1, "admin");
        Role r2=new Role(2, "spectateur");
        Role r3=new Role(1, "pilote");
         RoleService rs=new RoleService();
         //rs.insert(r1);
         //rs.insert(r2);
         //rs.insert(r3);
         //System.out.println(rs.readAll());
         
         /*User u1=new User("Aziz", "Hajjem", "aziz", "aziz@gmail.com", 20223666, 111222333, 0, 2);
         User u2=new User("Ayoub", "Barnat", "aziz", "aziz@gmail.com", 20223666, 111222333, 0, 2);
         User u3=new User("Aziz", "Haj", "aziz", "aziz@gmail.com", 20223666, 111222333, 0, 2);
         User u4=new User("Nadia", "karboul", "aziz", "aziz@gmail.com", 20223666, 111222333, 0, 2);
         User u5=new User("Hela", "Moala", "aziz", "aziz@gmail.com", 20223666, 111222333, 0, 2);*/
         
         UserService us=new UserService();
         //us.insert(u1);
         //us.insert(u2);
         //us.insert(u3);
         //us.insert(u4);
         //us.insert(u5);
         
         //System.out.println(us.readAll());
         

           
           
    }
}
