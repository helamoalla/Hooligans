/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package drift.race;

import entity.role;
import entity.user;
import service.RoleService;
import service.UserService;
import utils.DataSource;

/**
 *
 * @author 21694
 */
public class DriftRace {
    private static role id_role;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    
  DataSource ds1 = DataSource.getInstance();

//     
//   
//    
//    
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
      role r = new role(5,"pilote");
      user u1 = new user ("aziz","hajali","ma123","aziz@gmail.com", "12/5/22", 85154154, 0, r);

      usr.readAll().forEach(System.out::println);

//r2.setId_role(2);
//r2.setType("user0");
//rol.update(r2);


//RoleService r1 = new RoleService();
//role r = new role(5,"pilote");
//r1.insert(r);
//UserService usr = new UserService();
//usr.insert(u1);



}

    
}