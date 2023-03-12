/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.ViewMarket;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Categorie;
import Service.CategorieService;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class ViewAjoutCategorieController implements Initializable {

    CategorieService categorieservice =new CategorieService() ;
    @FXML
    private TextField NomCat;
    @FXML
    private TextField DescCat;
    private TextArea listeCat;

    /**
     * Initializes the controller class.
     * 
     */
     private String[] choix ={"Pieces de rechange","mode"} ;
    @FXML
    private ChoiceBox<String> choicefx;
    private ChoiceBox<Categorie> essaye1;
    @FXML
    private Button AddBtn;
    private BorderPane borderPane;

    
            public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choicefx.getItems().addAll(choix);
    }    

    @FXML
    private void AjouterCat(ActionEvent event) {
        
         if (NomCat.getText().length() == 0||DescCat.getText().length() == 0||choicefx.getValue().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Veuillez remplir tous les champs"+ "");
            alert.show();

        } else if(NomCat.getText().matches("\\d*")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Le nom de catégorie doit etre une chaine"+ "");
            alert.show(); 
    }
         else{
       
             Categorie c =new Categorie();
            c.setNom_categorie(NomCat.getText());
            c.setDescription_categorie(DescCat.getText());
            c.setType_categorie(choicefx.getValue());
            categorieservice.insert(c);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes");
            alert.setHeaderText("Categorie Ajoutée avec succes");
            alert.setContentText("Une nouvelle categorie a été ajouté"+ "");
            alert.show();
          
           
         }
            
           
    }

    private void afficherCat(ActionEvent event) {
        List <Categorie> listecategorie = FXCollections.observableArrayList(categorieservice.readAll());
      //  aa.setItems((ObservableList<listecategorie>));
       listeCat.setText(categorieservice.readAll().toString());

       
    }

    @FXML
    private void consulterliste(ActionEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./GestionCategorie.fxml"));
            
            Parent view_2=loader.load();
            GestionCategorieController suppcategorie=loader.getController();
            
             
                
            suppcategorie.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
        } catch (IOException ex) {
            Logger.getLogger(ViewAjoutCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
