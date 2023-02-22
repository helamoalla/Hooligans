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
    }
    
    

