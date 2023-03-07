/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Model.Event;
import Service.Services_event;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AjouterController implements Initializable {

    @FXML
    private Button AjouterEvent;
    @FXML
    private TextField nom;
    @FXML
    private TextField type;
    @FXML
    private TextField lieu;
    @FXML
    private TextField prix;
    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker datef;
    @FXML
    private Button importerimage;
    @FXML
    private ImageView imageview;
    @FXML
    private TextField nom_image;
    private File selectedFile;
    Services_event Service_event =new Services_event();
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(LocalDate.now());
    }    
       public void setBorderPane(  BorderPane borderPane){
        this.borderPane=borderPane;
    }

    @FXML
    private void gestioneventadmin(ActionEvent event) {
                try {
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./test1.fxml"));
             Parent view_2=loader.load();
             
             Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             Scene scene = new Scene(view_2);
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(Test1Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void AjouterEvent(ActionEvent event) {
          if (nom.getText().length() == 0||lieu.getText().length() == 0||type.getText().length() == 0||prix.getText().length() == 0||nom_image.getText().length()==0||dated.getValue()== null||datef.getValue()== null){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Please remplir tous les champs"+ "");
            alert.show();
            return;
    }
        // Vérifier que le prix est un nombre valide
    float prixf;
        try {
        prixf = Float.parseFloat(prix.getText());
        if (prixf <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le prix doit être supérieur à zéro.");
            alert.showAndWait();
            return;
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le prix doit être un nombre");
        alert.showAndWait();
        return;
    }
        // Ajouter un écouteur sur le champ de saisie de la date de fin
    // Vérifier si la date de fin est postérieure ou égale à la date de début
    if (datef.getValue().isBefore(dated.getValue())) {
        // Afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("La date de fin doit être postérieure ou égale à la date de début.");
        alert.showAndWait();

        // Réinitialiser la valeur du champ de saisie de la date de fin
        datef.setValue(null);
        return;
    }
    if (dated.getValue().isBefore(LocalDate.now())) {
        // Afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("La date de debut doit être postérieure ou égale à la date d'auhourd'hui.");
        alert.showAndWait();

        // Réinitialiser la valeur du champ de saisie de la date de fin
        datef.setValue(null);
        return;
    }

    
             
                  Event e=new Event();
                 e.setNom_event(nom.getText());
                 e.setLieu_event(lieu.getText());
                 e.setType_event(type.getText());
                 
                 LocalDate d=dated.getValue();
                 
                 e.setDate_debut(java.sql.Date.valueOf(d));
                 
                 LocalDate f=datef.getValue();
                 e.setDate_fin(java.sql.Date.valueOf(f));
                 
                 e.setPrix(Double.parseDouble(prix.getText()));
          
              e.setImage(nom_image.getText());
  
       
                 
                 
                 /*FXMLLoader loader= new FXMLLoader(getClass().getResource("./AfficherEvent.fxml"));
                 Parent view_2=loader.load();
                 
                 Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                 Scene scene = new Scene(view_2);
                 stage.setScene(scene);
                 stage.show();*/
                 // Copy the selected file to the htdocs directory
                 String htdocsPath = "C:/xampp/htdocs/images/";
                 File destinationFile = new File(htdocsPath + nom_image.getText());
            if(selectedFile!=null){
                try (InputStream in = new FileInputStream(selectedFile);
                 OutputStream out = new FileOutputStream(destinationFile)) {
                byte[] buf = new byte[8192];
                int length;
                while ((length = in.read(buf)) > 0) {
                    out.write(buf, 0, length);
                }
            
            Service_event.insert(e);
            // return to the main 
//                FXMLLoader loader= new FXMLLoader(getClass().getResource("./test1.fxml"));
//                 Parent view_2=loader.load();     
//                 Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//                 Scene scene = new Scene(view_2);
//                 stage.setScene(scene);
//                 stage.show();
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("./test1.fxml"));
            Parent profile = loader.load();  
             Test1Controller test1 = loader.getController();
                test1.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(profile);
            
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            }else{
                System.out.println("selected file is null "+selectedFile);
            }
            
        
         
    }

    @FXML
    private void importerimage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.JPG", "*.gif"));
          fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
         if (selectedFile != null) {
                nom_image.setText(selectedFile.getName().replaceAll("\\s+", ""));
                 try {
                Image images = new Image("file:"+selectedFile.getPath().toString());
                imageview.setImage(images);
                System.out.println(selectedFile.getPath().toString());
        } catch (Exception ex) {
                     System.out.println(ex);
        }
    }
    
}

    @FXML
    private void gestioneventuser(ActionEvent event) {
         try {
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./eventuser.fxml"));
             Parent view_2=loader.load();
             
             Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             Scene scene = new Scene(view_2);
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(Test1Controller.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
