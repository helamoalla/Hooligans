/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.ViewMarket;

import Interface.MyListenerC;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Model.Commande;
import javafx.scene.layout.BorderPane;

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
    private BorderPane borderPane;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
            public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;}
    
    
        public void setData(Commande commande,MyListenerC myListenerc){
             this.commande = commande;
             System.out.println(commande);
             this.myListenerc = myListenerc;
             num_commande.setText(String.valueOf(commande.getId_commande()));
//             LocalDate ld=commande.getDate_commande().toLocalDate() ;
//
//                   date.setText(String.valueOf(ld));
           date.setText(String.valueOf(commande.getDate_commande().toLocalDate()));
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
