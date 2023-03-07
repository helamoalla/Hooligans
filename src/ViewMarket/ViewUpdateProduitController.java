/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewMarket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
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
import models.Categorie;
import models.Produit;
import services.CategorieService;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class ViewUpdateProduitController implements Initializable {
    ProduitService produitservice=new ProduitService() ;
     CategorieService catser = new CategorieService();

    Produit p ;
    @FXML
    private TextField modifnomprod;
    @FXML
    private TextField modifprixprod;
    @FXML
    private TextArea modifdescprod;
    @FXML
    private TextField modifquantiteprod;
    @FXML
    private ChoiceBox<String> choixP;
    @FXML
    private ImageView image_view;
    @FXML
    private Text image_label;
    
    File selectedFile;

    /**
     * Initializes the controller class.
     */
    
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CategorieService catser = new CategorieService();
      //ArrayList <Categorie> categories = catser.readAll();
      
      
    /*for (Categorie c :liste){
       choixCP.getItems().add(c);  
    } */ 
     ObservableList<Categorie> categories =FXCollections.observableArrayList(catser.readAll());
    //choixP.setItems(categories);
    for (Categorie c :categories){
           choixP.getItems().add(c.getNom_categorie());}  }  
    
    void getProduit(Produit p){
    URL imageUrl;
        
    try {
        modifnomprod.setText(p.getNom_prod());
        modifprixprod.setText(p.getPrix_prod().toString());
        modifdescprod.setText(p.getDescription_prod());
        modifquantiteprod.setText(Integer.toString(p.getQuantite()));
        choixP.setValue(p.getCategorie().getNom_categorie());

        image_label.setText(p.getImage());
        imageUrl = new URL("http://localhost/images/"+p.getImage());
        Image images = new Image(imageUrl.toString());
        image_view.setImage(images);
} catch (MalformedURLException ex) {
         System.out.println(ex);
                    }
}    

    @FXML
    private void modifproduit(ActionEvent event) {
        if (modifnomprod.getText().length() == 0||modifdescprod.getText().length() == 0||modifprixprod.getText().length() == 0||modifquantiteprod.getText().length() == 0|| choixP.valueProperty() ==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Veuillez remplir tous les champs"+ "");
            alert.show();

        }else if(modifnomprod.getText().matches("\\d*")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Le nom de produit doit etre une chaine"+ "");
            alert.show(); 
    }
           else if(!modifprixprod.getText().matches("\\d*")||!modifquantiteprod.getText().matches("\\d*")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Le prix de produit ou la quantite doit etre un nombre"+ "");
            alert.show(); 
    }
        else {
         
            p.setNom_prod(modifnomprod.getText());
            p.setPrix_prod(Double.parseDouble(modifprixprod.getText()));
            p.setDescription_prod(modifdescprod.getText());
            p.setQuantite(Integer.parseInt(modifquantiteprod.getText()));
            //p.setCategorie(choixP.getSelectionModel().getSelectedItem());
            Categorie c1 =catser.RetournerT(choixP.getSelectionModel().getSelectedItem());
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
            produitservice.update(p);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            }else{
                produitservice.update(p);
                System.out.println("selected file is null "+selectedFile);
            }
            
           
 
        
    }
    }

    @FXML
    private void afficherproduits(ActionEvent event) {
              try {
                FXMLLoader loader= new FXMLLoader(getClass().getResource("./GestionProduit.fxml"));
                Parent view_2=loader.load();
                Scene scene = new Scene(view_2);
                Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ViewUpdateProduitController.class.getName()).log(Level.SEVERE, null, ex);
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
