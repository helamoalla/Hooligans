
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Categorie;
import models.Produit;
import services.CategorieService;
import services.ProduitService;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class ViewAjoutProduitController implements Initializable {
         CategorieService catser = new CategorieService();
    
    @FXML
    private ChoiceBox<String> choixCP;
    @FXML
    private TextField nomprod;
    @FXML
    private TextField prixprod;
    @FXML
    private TextArea descprod;
    @FXML
    private TextField quantiteprod;

    /**
     * Initializes the controller class.
     */
      ProduitService prodser= new ProduitService();
    @FXML
    private ImageView image_view;
    @FXML
    private Text image_label;
    
    File selectedFile;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         CategorieService catser = new CategorieService();
      ArrayList <Categorie> liste = catser.readAll();
      
    /*for (Categorie c :liste){
       choixCP.getItems().add(c);  
    } */ 
     ObservableList<Categorie> categories =FXCollections.observableArrayList(catser.readAll());
    //choixCP.setItems(categories);
           for (Categorie c :categories){
           choixCP.getItems().add(c.getNom_categorie());
           
           }
    
}

    @FXML
    private void ajoutproduit(ActionEvent event) {
       
        Produit p =new Produit();
           if(nomprod.getText().length() == 0||prixprod.getText().length() == 0||descprod.getText().length() == 0||quantiteprod.getText().length() == 0|| choixCP.valueProperty() == null || image_view.getImage() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Veuillez remplir tous les champs"+ "");
            alert.show(); 
           }else if(nomprod.getText().matches("\\d*")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Le nom de produit doit etre une chaine"+ "");
            alert.show(); 
    }
           else if(!prixprod.getText().matches("\\d*")||!quantiteprod.getText().matches("\\d*")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Le prix de produit ou la quantite doit etre un nombre"+ "");
            alert.show(); 
    }
           else{
                p.setNom_prod(nomprod.getText());
            p.setPrix_prod(Double.parseDouble(prixprod.getText()));
            p.setDescription_prod(descprod.getText());
            p.setQuantite(Integer.parseInt(quantiteprod.getText()));
            //p.setCategorie(choixCP.getSelectionModel().getSelectedItem());
            Categorie c1 =catser.RetournerT(choixCP.getSelectionModel().getSelectedItem());
            p.setCategorie(c1);
            p.setImage(image_label.getText());
             String htdocsPath = "C:/xampp/htdocs/images/";
                 File destinationFile = new File(htdocsPath + image_label.getText().replaceAll("\\s+", ""));
            if(selectedFile!=null){
                try (InputStream in = new FileInputStream(selectedFile);
                 OutputStream out = new FileOutputStream(destinationFile)) {
                byte[] buf = new byte[8192];
                int length;
                while ((length = in.read(buf)) > 0) {
                    out.write(buf, 0, length);
                }
            prodser.insert(p);
         
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            }else{
                System.out.println("selected file is null "+selectedFile);
            }
            
           }
            
            
            
    }

    @FXML
    private void afficherproduits(ActionEvent event) {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./ViewSuppProduit.fxml"));
        
         try {
             Parent view_2=loader.load();
             ViewSuppProduitController afficheproduit=loader.getController();
             
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
         } catch (IOException ex) {
             Logger.getLogger(ViewSuppCategorieController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.JPG", "*.gif"));
          fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
         if (selectedFile != null) {
                image_label.setText(selectedFile.getName().replaceAll("\\s+", ""));
                 try {
                Image images = new Image("file:"+selectedFile.getPath().toString());
                image_view.setImage(images);
                System.out.println(selectedFile.getPath().toString());
        } catch (Exception ex) {
                     System.out.println(ex);
        }
                
            }
    }
}
