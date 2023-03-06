/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
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
import model.Event;
import services.Services_event;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ModifierController implements Initializable {

    @FXML
    private Button Modifier;
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
    File selectedFile;
    URL imageUrl;
Event e;
Services_event se=new Services_event();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
void getEvent(Event e){
        try {
            nom.setText(e.getNom_event());
            lieu.setText(e.getLieu_event());
            type.setText(e.getType_event());
            dated.setValue(e.getDate_debut().toLocalDate());
            datef.setValue(e.getDate_fin().toLocalDate());
            prix.setText(e.getPrix().toString());
            nom_image.setText(e.getImage());
            imageUrl = new URL("http://localhost/img/"+e.getImage());
            Image images = new Image(imageUrl.toString());
            imageview.setImage(images);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}   
    @FXML
    private void modifier(ActionEvent event) {
         if (nom.getText().length() == 0||lieu.getText().length() == 0||type.getText().length() == 0||prix.getText().length() == 0||dated.getValue()== null||datef.getValue()== null){

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
        
            e.setNom_event(nom.getText());
            e.setLieu_event(lieu.getText());
            e.setType_event(type.getText());
            
            LocalDate d=dated.getValue();
            
            e.setDate_debut(java.sql.Date.valueOf(d));
            
            LocalDate f=datef.getValue();
            e.setDate_fin(java.sql.Date.valueOf(f));
            
            e.setPrix(Double.parseDouble(prix.getText()));
            e.setImage(nom_image.getText());
        String htdocsPath = "C:/xampp/htdocs/img/";
        File destinationFile = new File(htdocsPath + nom_image.getText());
        if(selectedFile!=null){
          
                try (InputStream in = new FileInputStream(selectedFile);
                        OutputStream out = new FileOutputStream(destinationFile)) {
                    byte[] buf = new byte[8192];
                    int length;
                    while ((length = in.read(buf)) > 0) {
                        out.write(buf, 0, length);
                    }
            
            se.update(e);
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./test1.fxml"));
            Parent view_2=loader.load();
            Scene scene = new Scene(view_2);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
        else{
            try {
                se.update(e);
                FXMLLoader loader= new FXMLLoader(getClass().getResource("./test1.fxml"));
                Parent view_2=loader.load();
                Scene scene = new Scene(view_2);
                Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                System.out.println("selected file is null "+selectedFile);
            } catch (IOException ex) {
                Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
    
}
    
}}

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
    
