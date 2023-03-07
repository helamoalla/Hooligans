/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.View;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Model.Event;
import Service.Services_event;
import View.MenuItemController;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EventuserController implements Initializable {

    @FXML
    private VBox Scenepanee;
    BorderPane borderPane;
List<Event> le=new ArrayList<>(); 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        if(borderPane!=null){
            System.out.println("Im here");
        }
    }    
    public void setBorderPane(  BorderPane borderPane){
        this.borderPane=borderPane;
        getAll();
    }
     public void getAll(){
              Services_event services_event = new Services_event();
        le.addAll(services_event.readAll());
        // TODO
     //  Scenepanee.getChildren().clear();
       
        
        Node [] nodes = new  Node[15];
        
        
        
        for(int i = 0; i<le.size() ; i++)
        {
             
                
             try {
                 FXMLLoader fxmlLoader = new FXMLLoader();
                 fxmlLoader.setLocation(getClass().getResource("../View/itemuser.fxml"));
                 AnchorPane abc = fxmlLoader.load();
                 ItemuserController itemcontroller = fxmlLoader.getController();
                 
                 itemcontroller.getEvent(le.get(i));
                itemcontroller.setBorderPane(borderPane);
                    borderPane.setCenter(null);
//             borderPane.setCenter(abc);
                 Scenepanee.getChildren().add(abc);
                 borderPane.setCenter(Scenepanee);
             } catch (IOException ex) {
                 Logger.getLogger(Test1Controller.class.getName()).log(Level.SEVERE, null, ex);
             }
             
                
             
           
        }
    }

    @FXML
    private void gestioneventadmin(ActionEvent event) {
        try {
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./test1.fxml"));
             Parent view_2=loader.load();
             
             Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             Scene scene = new Scene(view_2);
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(Test1Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void gestioneventuser(ActionEvent event) {
//        try {
//             FXMLLoader loader= new FXMLLoader(getClass().getResource("./eventuser.fxml"));
//             Parent view_2=loader.load();
//             
//             Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//             Scene scene = new Scene(view_2);
//             stage.setScene(scene);
//             stage.show();
//         } catch (IOException ex) {
//             Logger.getLogger(Test1Controller.class.getName()).log(Level.SEVERE, null, ex);
//         }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./eventuser.fxml"));
            Parent profile = loader.load();            
            borderPane.setCenter(null);
            borderPane.setCenter(profile);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
