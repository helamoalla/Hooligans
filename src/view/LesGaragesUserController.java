/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Interfaces.InterfaceCRUD;
import Models.GarageC;
import Services.ServiceGarageC;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class LesGaragesUserController implements Initializable {

    @FXML
    private ImageView id_image;
    @FXML
    private Label id_garage;
    @FXML
    private Label nom_garage;
    @FXML
    private Label adresse;
    @FXML
    private Label numero;
GarageC g;
URL imageUrl;
InterfaceCRUD sg =new ServiceGarageC();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
}
