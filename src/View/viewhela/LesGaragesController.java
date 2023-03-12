/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.viewhela;

import Interface.InterfaceCRUD;
import Model.GarageC;
import Service.ServiceGarageC;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class LesGaragesController implements Initializable {
InterfaceCRUD sg =new ServiceGarageC();
    @FXML
    private Label nom_garage;
    @FXML
    private Label adresse;
    @FXML
    private Label numero;
    @FXML
    private ImageView id_image;
GarageC g;
URL imageUrl;
    @FXML
    private Label id_garage;
    @FXML
    private Button id_modifier;
    @FXML
    private Button id_suppr;
    private BorderPane borderPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
      public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }    
  
    public void setData(GarageC g)
    {   try {
        this.g =g;
        imageUrl = new URL("http://localhost/images/"+g.getImage());
        Image images = new Image(imageUrl.toString());
        id_image.setImage(images);
        id_garage.setText(String.valueOf(g.getId_garage()));
        //id_image.setImage(images);
        nom_garage.setText(g.getNom_garage());
        adresse.setText(g.getAdresse());
        numero.setText(String.valueOf(g.getNumero()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(LesGaragesController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void modifier_garage(ActionEvent event) {
         try {
        
        
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./modifier_Garage.fxml"));
        Parent view_2=loader.load();
        Modifier_GarageController Modifier_GarageController=loader.getController();
        Modifier_GarageController.getGarage((GarageC) sg.readById(Integer.valueOf(id_garage.getText())));
        Modifier_GarageController.g=(GarageC) sg.readById(Integer.valueOf(id_garage.getText()));
                   
            Modifier_GarageController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
    } catch (IOException ex) {
        Logger.getLogger(GarageController.class.getName()).log(Level.SEVERE, null, ex);
    }
   
    }

    @FXML
    private void supprimer_garage(ActionEvent event) {
  
    try {
        sg.delete(Integer.valueOf(id_garage.getText()));
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./affichageGarage.fxml"));
        Parent view_2=loader.load();
        
                    AffichageGarageController affichageGarageController = loader.getController();
            affichageGarageController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
        // afficher_garage(event);
    } catch (IOException ex) {
        Logger.getLogger(LesGaragesController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

   }
