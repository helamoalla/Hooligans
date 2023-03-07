/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.ViewMarket;

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
import javafx.scene.input.MouseEvent;
import Model.Categorie;
import Model.Produit;
import Interface.MyListener;
import Interface.MyListener1;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class CategorieController implements Initializable {
    Categorie categorie ;
     private MyListener1 myListener;
    @FXML
    private Label nom;
 
    @FXML
    private ImageView img;
    @FXML
    private Label description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

      public void setData(Categorie categorie,MyListener1 myListener){
    
         
          this.categorie = categorie;
          this.myListener = myListener;
          nom.setText(categorie.getNom_categorie());
          
          description.setText(categorie.getDescription_categorie());
    }

    @FXML
    private void click(javafx.scene.input.MouseEvent event) {
        myListener.onClickListener(categorie);
    }
    
    
}
