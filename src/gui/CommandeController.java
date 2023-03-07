/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import interfaces.MyListenerC;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Commande;

/**
 * FXML Controller class
 *
 * @author choua
 */
public class CommandeController implements Initializable {

    @FXML
    private Label lbnom_prod;
    @FXML
    private Label lbdescription_prod;
    private Commande commande;
    @FXML
    private Label num_commande;
    @FXML
    private Label date;
    @FXML
    private Label montant;
    @FXML
    private Label rue;
    @FXML
    private Label ville;
    @FXML
    private Label gouvernorat;
    private MyListenerC myListenerc;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    
        public void setData(Commande commande,MyListenerC myListenerc){
             this.commande = commande;
             this.myListenerc = myListenerc;
             num_commande.setText(String.valueOf(commande.getId_commande()));
             date.setText(String.valueOf(commande.getDate_commande()));
             montant.setText(String.valueOf(commande.getMontant()));
             rue.setText(commande.getRue());
             ville.setText(commande.getVille());
             gouvernorat.setText(commande.getGouvernorat());
        
    }

    @FXML
    private void click(MouseEvent event) {
            myListenerc.onClickListener(commande);
    }
    
}
