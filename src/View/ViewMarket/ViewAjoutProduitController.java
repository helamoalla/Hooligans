
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.ViewMarket;

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
import Model.Categorie;
import Model.Produit;
import Service.CategorieService;
import Service.ProduitService;
import javafx.scene.layout.BorderPane;
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
    private BorderPane borderPane;

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

            public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;}
    @FXML
    private void ajoutproduit(ActionEvent event) {
       
        Produit p =new Produit();
        try {
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
           else if(!quantiteprod.getText().matches("\\d*")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("la quantite doit etre un nombre positive"+ "");
            alert.show(); 
    } 
            
               double prix=Double.parseDouble(prixprod.getText());
        
           if(prix<=0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("le prix  doit etre un nombre positive"+ "");
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
    }catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le prix doit être un nombre");
        alert.showAndWait();
        
    }
            
            
            
    }

    @FXML
    private void afficherproduits(ActionEvent event) {
        
             try {
                 FXMLLoader loader= new FXMLLoader(getClass().getResource("./GestionProduit.fxml"));
                 Parent view_2=loader.load();
                 GestionProduitController afficheproduit=loader.getController();
                 
             
                
            afficheproduit.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
             } catch (IOException ex) {
                 Logger.getLogger(ViewAjoutProduitController.class.getName()).log(Level.SEVERE, null, ex);
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
