/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Event;
import services.Services_event;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ModifierEventController implements Initializable {

    @FXML
    private TextField Nom;
    @FXML
    private TextField lieu;
    @FXML
    private TextField type;
    @FXML
    private TextField prix;
    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker datef;
    @FXML
    private Button ModifierEvent;

       Event e;
    Services_event Services_event=new Services_event();
    @FXML
    private Button home;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
void getEvent(Event e){
    Nom.setText(e.getNom_event());
    lieu.setText(e.getLieu_event());
    type.setText(e.getType_event());
    dated.setValue(e.getDate_debut().toLocalDate());
 datef.setValue(e.getDate_fin().toLocalDate());
    prix.setText(e.getPrix().toString());
}    
    @FXML
    private void ModifierEvent(ActionEvent event) {
         if (Nom.getText().length() == 0||lieu.getText().length() == 0||type.getText().length() == 0||prix.getText().length() == 0||dated.getValue()== null||datef.getValue()== null){

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
        try {
            e.setNom_event(Nom.getText());
            e.setLieu_event(lieu.getText());
            e.setType_event(type.getText());
            
            LocalDate d=dated.getValue();
            
            e.setDate_debut(java.sql.Date.valueOf(d));
            
            LocalDate f=datef.getValue();
            e.setDate_fin(java.sql.Date.valueOf(f));
            
            e.setPrix(Double.parseDouble(prix.getText()));
            
            
            Services_event.update(e);
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./AfficherEvent.fxml"));
            Parent view_2=loader.load();
            Scene scene = new Scene(view_2);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 

    @FXML
    private void home(ActionEvent event) {
            try {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./Acceuil.fxml"));
        Parent view_2=loader.load();
        Scene scene = new Scene(view_2);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AfficherEventUserController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    }
    
    

