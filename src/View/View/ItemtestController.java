/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.View;

import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Model.Event;
import Service.Services_event;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ItemtestController implements Initializable {

    @FXML
    private ImageView image;
    private Label dated;
    @FXML
    private Label nom;
    @FXML
    private AnchorPane abc;
    @FXML
    private Label dated_Month;
    @FXML
    private Label dated_day;
    @FXML
    private Label dated_year;
    @FXML
    private Label datef_Month;
    @FXML
    private Label datef_day;
    @FXML
    private Label datef_year;
    @FXML
    private Label id_e;
    Services_event se=new Services_event();
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
            id_e.setText(String.valueOf(e.getId_e()));
            //Description.setText(P.getDescription());
          //  String htdocsPath = "C:/xampp/htdocs/img/";
            URL imageUrl;
          
            imageUrl = new URL("http://localhost/images/"+e.getImage());
            Image images = new Image(imageUrl.toString());
            image.setImage(images);
            
            //this.projet= P;
            LocalDate d=e.getDate_debut().toLocalDate();
            
            dated_day.setText(String.valueOf(d.getDayOfMonth()));
            dated_Month.setText(String.valueOf(d.getMonth()));
            dated_year.setText(String.valueOf(d.getYear()));
            LocalDate f=e.getDate_fin().toLocalDate();
            
            datef_day.setText(String.valueOf(f.getDayOfMonth()));
            datef_Month.setText(String.valueOf(f.getMonth()));
            datef_year.setText(String.valueOf(f.getYear()));
            
            //System.out.println(projet);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ItemtestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }

    @FXML
    private void detail(ActionEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./detailevent.fxml"));
            Parent view_2=loader.load();
            DetaileventController DetaileventController=loader.getController();
            DetaileventController.getEvent(se.readById(Integer.valueOf(id_e.getText())));
            DetaileventController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
        } catch (IOException ex) {
            Logger.getLogger(ItemtestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void delete(ActionEvent event) {
        try {
            se.delete(Integer.valueOf(id_e.getText()));
            //Test1Controller itemcontroller = new Test1Controller();
            //itemcontroller.getAll();
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./test1.fxml"));
            Parent view_2=loader.load();
             Test1Controller test1Controller = loader.getController();
            test1Controller.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
            
            
            
            
            //se.delete(4);
        } catch (IOException ex) {
            Logger.getLogger(ItemtestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
          try {
        Event selectedEvent=se.readById(Integer.valueOf(id_e.getText()));
        
        
        //bonPlanService.update(bonPlanService.readById(selectedId));
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./Modifier.fxml"));
        Parent view_2=loader.load();
        ModifierController ModifierController=loader.getController();
        ModifierController.getEvent(selectedEvent);
        ModifierController.e=selectedEvent;
        ModifierController.setBorderPane(borderPane);
        borderPane.setCenter(null);
        borderPane.setCenter(view_2);
    } catch (IOException ex) {
        Logger.getLogger(Test1Controller.class.getName()).log(Level.SEVERE, null, ex);
    } 
    }
}
