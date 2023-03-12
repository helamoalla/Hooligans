/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package View.View;

import Model.Event;
import Model.Ticket;
import Service.Service_ticket;
import Service.Services_event;
import Util.Data;
import com.google.zxing.WriterException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author azizh
 */
public class MesTicketsController implements Initializable {

    private BorderPane borderPane;
    List<Ticket> le=new ArrayList<>(); 
    @FXML
    private VBox Scenepanee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //getAll();
    }  
    public void setBorderPane(  BorderPane borderPane){
        this.borderPane=borderPane;
        getAll();
    }
    public void getAll(){
              Service_ticket st = new Service_ticket();
               le.addAll(st.readAllMesTickets(Data.getId_user()));
               System.out.println(le);
        // TODO
     //  Scenepanee.getChildren().clear();
       
        
        Node [] nodes = new  Node[15];
        
        
        
        for(int i = 0; i<le.size() ; i++)
        {
             
                
             
                  try {
                       FXMLLoader fxmlLoader = new FXMLLoader();
                 fxmlLoader.setLocation(getClass().getResource("./ticket.fxml"));
                 AnchorPane abc = fxmlLoader.load();
                 TicketController itemcontroller = fxmlLoader.getController();
                 
                 itemcontroller.getTicket(le.get(i));
                itemcontroller.setBorderPane(borderPane);
                    borderPane.setCenter(null);
//             borderPane.setCenter(abc);
                 Scenepanee.getChildren().add(abc);
                 borderPane.setCenter(Scenepanee);
                  } catch (IOException ex) {
                      Logger.getLogger(MesTicketsController.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (WriterException ex) {
                      Logger.getLogger(MesTicketsController.class.getName()).log(Level.SEVERE, null, ex);
                  }
           
             
                
             
           
        }
    }
    
}
