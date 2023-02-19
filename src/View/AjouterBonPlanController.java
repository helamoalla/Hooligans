/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.BonPlan;
import Service.BonPlanService;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizh
 */
public class AjouterBonPlanController implements Initializable {
    
    BonPlanService bonPlanService=new BonPlanService();

    @FXML
    private TextField adresseBonPlan;
    @FXML
    private TextField nomBonPlan;
    @FXML
    private TextField idUser;
    @FXML
    private ChoiceBox<String> typeBonPlan;
    @FXML
    private Button Add_btn;
    @FXML
    private Button retour_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeBonPlan.getItems().add("Garage");
        typeBonPlan.getItems().add("Circuit");
    }    

    @FXML
    private void ajouterBonPlan(ActionEvent event) {
        if (nomBonPlan.getText().length() == 0||adresseBonPlan.getText().length() == 0||typeBonPlan.getValue() == null||idUser.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Please remplir tous les champs"+ "");
            alert.show();

        } else {
            try {
                BonPlan b=new BonPlan();
                b.setNom_bonplan(nomBonPlan.getText());
                b.setAdresse(adresseBonPlan.getText());
                b.setType(typeBonPlan.getValue());
                b.setId_user(Integer.parseInt(idUser.getText()));
                bonPlanService.insert(b);
                FXMLLoader loader= new FXMLLoader(getClass().getResource("./BonPlan.fxml"));
                Parent view_2=loader.load();
                Scene scene = new Scene(view_2);
                Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterBonPlanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void backToMain(ActionEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./BonPlan.fxml"));
            Parent view_2=loader.load();
            Scene scene = new Scene(view_2);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterBonPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
