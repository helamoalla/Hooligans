/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.View;

import com.google.zxing.WriterException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Model.Event;
import Model.Ticket;
import Model.User;
import Service.Service_ticket;
import Service.Services_event;
import Service.UserService;
import Util.Data;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DetaileventController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label dated;
    @FXML
    private Label datef;
    @FXML
    private Label lieu;
    @FXML
    private Label type;
    @FXML
    private Label prix;
    @FXML
    private Button Payer;
    @FXML
    private ImageView image;
    @FXML
    private Label id_e;
Services_event se=new Services_event();
Service_ticket st=new Service_ticket();
UserService us =new UserService();
    private BorderPane borderPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
       public void setBorderPane(  BorderPane borderPane){
        this.borderPane=borderPane;
    }
    public void getEvent(Event e){
        
        try {
            nom.setText(e.getNom_event());
           
            URL imageUrl;
         
            imageUrl = new URL("http://localhost/images/"+e.getImage());
            Image images = new Image(imageUrl.toString());
            image.setImage(images);
            
            //this.projet= P;
            LocalDate d=e.getDate_debut().toLocalDate();
            
            dated.setText(String.valueOf(d));
            LocalDate f=e.getDate_fin().toLocalDate();
            
            datef.setText(String.valueOf(f));
            lieu.setText(e.getLieu_event());
            type.setText(e.getType_event());
            prix.setText(String.valueOf(e.getPrix()));
            id_e.setText(String.valueOf(e.getId_e()));
          
            
            //System.out.println(projet);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ItemtestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }

    @FXML
    private void Payer(ActionEvent event) throws IOException, WriterException {
           if (us.readById(Data.getId_user()).getQuota()-Double.parseDouble(prix.getText())<=0){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Solde insuffisant !");
            alert.setContentText("Alimenter votre solde"+ "");
            alert.show();
            return;
    }
         Ticket t=new Ticket();
         t.setEvent((Event) se.readById(Integer.valueOf(id_e.getText())));
         //t.setId_spectateur(1);
         t.setUser(us.readById(Data.getId_user()));
         t.setImage("dsv");
         t.setNum_ticket(123);
         Event e1=(Event) se.readById(Integer.valueOf(id_e.getText()));
         User u1=us.readById(Data.getId_user());
         u1.setQuota(u1.getQuota()-e1.getPrix());
         us.update(u1);
         st.insert(t);
         
            // aziz badlha
                          
//            FXMLLoader loader= new FXMLLoader(getClass().getResource("./ticket.fxml"));
//            Parent view_2=loader.load();
//            TicketController itemcontroller = loader.getController();
//                 itemcontroller.getTicket(t);
//                 itemcontroller.setBorderPane(borderPane);
//            borderPane.setCenter(null);
//            borderPane.setCenter(view_2);
FXMLLoader loader = new FXMLLoader(getClass().getResource("../../View/SideBar.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();

          
        
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
         try {
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./eventuser.fxml"));
             Parent view_2=loader.load();
             
             Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             Scene scene = new Scene(view_2);
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(Test1Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
