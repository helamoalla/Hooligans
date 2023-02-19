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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizh
 */
public class UpdatebonplanController implements Initializable {

    @FXML
    private TextField updatenom;
    @FXML
    private TextField updateadresse;
    @FXML
    private Button update_Btn;
    
    BonPlan b;
    BonPlanService bonPlanService=new BonPlanService();
    @FXML
    private Button retour_btn;
    @FXML
    private ChoiceBox<String> typeBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeBtn.getItems().add("Garage");
        typeBtn.getItems().add("Circuit");
    }
void getBonPlan(BonPlan b){
    updatenom.setText(b.getNom_bonplan());
        updateadresse.setText(b.getAdresse());
        typeBtn.setValue(b.getType());
}    
    

    

    @FXML
    private void updateBonPlan(ActionEvent event) {
        
        
        try {
            b.setNom_bonplan(updatenom.getText());
            b.setAdresse(updateadresse.getText());
            b.setType(typeBtn.getValue());            
            
            bonPlanService.update(b);
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./BonPlan.fxml"));
            Parent view_2=loader.load();
            Scene scene = new Scene(view_2);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UpdatebonplanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour(ActionEvent event) {
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
