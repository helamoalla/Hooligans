/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.ViewMarket;

import Interface.MyListenerP;
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
import Model.LignePanier;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class LignePanierController implements Initializable {

  
    @FXML
    private Label lbnom_prod;
    @FXML
    private Label lbdescription_prod;
    @FXML
    private Label lbquantite;
    @FXML
    private Label lbsous_montant;
    
    private LignePanier lp ; 
    @FXML
    private ImageView image_prod;
    
     private MyListenerP myListenerP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void setData(LignePanier lp,MyListenerP myListenerP){
    
        try {
            this.lp = lp;
            this.myListenerP = myListenerP;
            lbnom_prod.setText(lp.getProduit().getNom_prod()); 
           URL imageUrl;
             
             imageUrl = new URL("http://localhost/images/"+lp.getProduit().getImage());
             Image images = new Image(imageUrl.toString());
            image_prod.setImage(images);
            lbdescription_prod.setText(lp.getProduit().getDescription_prod());
            lbquantite.setText(String.valueOf(lp.getQuantite()));
            lbsous_montant.setText(String.valueOf(lp.getProduit().getPrix_prod()*lp.getQuantite()));
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(LignePanierController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
     

    @FXML
    private void click(javafx.scene.input.MouseEvent event) {
         myListenerP.onClickListener(lp);
    }

    
}
