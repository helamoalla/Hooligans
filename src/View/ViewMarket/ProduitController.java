/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.ViewMarket;

import java.awt.event.MouseEvent;
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
import Model.Produit;
import Interface.MyListener;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class ProduitController implements Initializable {

    private Produit produit;
    private MyListener myListener;
    @FXML
    private Label nom;
    @FXML
    private Label prix;
    @FXML
    private ImageView img;
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
    //private void click(MouseEvent mouseEvent) {
      //  myListener.onClickListener(produit);
    //}
    
    public void setData(Produit produit,MyListener myListener){
    
         
         try {
             this.produit = produit;
             this.myListener = myListener;
             nom.setText(produit.getNom_prod());
             prix.setText(produit.getPrix_prod().toString()+"DT");
             
       
             URL imageUrl;
             
             imageUrl = new URL("http://localhost/images/"+produit.getImage());
             Image images = new Image(imageUrl.toString());
             img.setImage(images);
         } catch (MalformedURLException ex) {
             Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void click(javafx.scene.input.MouseEvent event) {
        myListener.onClickListener(produit);
    }
    }

