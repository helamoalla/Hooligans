/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pidev;
import interfaces.InterfaceCRUD;


import model.Event;
import model.Ticket;
import model.role;
import model.user;
import services.RoleService;
import services.Service_ticket;
import services.Services_event;
import services.UserService;

/**
 *
 * @author User
 */
public class Pidev {

    public static void main(String[] args) {
        
    RoleService rol = new RoleService();
     UserService usr=new UserService();
//        user u = new user();
//      //  u=usr.readById(14);
//       // System.out.println(u);
//     // usr.update(u5);
//      //rol.insert(r1);
//     //  rol.delete(r1);
//         // usr.insert(u7);
      // usr.delete(u5);
      role r = new role(1,"vd");
      user u1 = new user ("aziz","hajali","ma123","aziz@gmail.com", 54219377, 85154154, 1.0, r.getId_role());
        System.out.println(u1.getNom());
      usr.insert(u1);

//        InterfaceCRUD se = new Services_event();
//        
//        initialisation d'un garage
//        Event e=new Event();
//        e.setId_e(5);
//        e.setNom_event("Garage Auto Mohsen");
//        e.setDate_debut(java.sql.Date.valueOf("2013-09-04"));
//        e.setDate_fin(java.sql.Date.valueOf("2013-09-04"));
//        e.setLieu_event("dfvdf");
//        e.setType_event("vrrv");
//        e.setImage("ayoub");
//        e.setPrix(50.00);
//        InterfaceCRUD st = new Service_ticket();
//              initialisation d'un ticket
//        Ticket  t=new Ticket();
//t.setId_ticket(2);
//t.setNum_ticket(100);
//t.setId_spectateur(1);
//t.setEvent(e);
//t.setImage("gh");
//
//
//        
//
//       ajout Event 
//      se.insert(e);
//       int id_e=1;
//        se.delete(3);
//        e.setNom_event("ayoub");
//        e.setId_e(7);
//       se.update(e);
//       System.out.println(se.readAll());
//    System.out.println(se.readById(4));
//       System.out.println(se.sortBy("nom_event","DESC"));
//       System.out.println(se.trie("type_event"));
//       st.insert(t);
//       st.delete(1);
//       st.update(t);
//       System.out.println(st.readAll());
//       System.out.println(st.readById(2));
//      System.out.println(st.sortBy("num_ticket", "Asc"));
//       
//    }
//} 
    }}