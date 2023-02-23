/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.PanierService;

/**
 * FXML Controller class
 *
 * @author choua
 */
public class InterfaceSuppPanierController implements Initializable {

    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_supp;
    @FXML
    private TextField tfidpanier;
    
    PanierService ps = new PanierService();
    
    @FXML
    public void SupprimerPanier(ActionEvent event)throws IOException {
    if (tfidpanier.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Veuillez indiquer l'id du panier");
            alert.show();

        }
    else {
        int id = Integer.parseInt(tfidpanier.getText());
        ps.delete(id);
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Suppression");
            alert.setHeaderText("");
            alert.setContentText("Panier supprimé avec succés");
            alert.show();
    }
    
}
    
    @FXML
    public void retour(ActionEvent event) {
    
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./mainInterface.fxml"));
            Parent view_2=loader.load();
            Scene scene = new Scene(view_2);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);     
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceajoutPanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
  }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SupprimerPanier(MouseEvent event) {
    }

 

    
}
