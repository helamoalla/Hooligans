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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizh
 */
public class FXMLController implements Initializable {
    
    BonPlanService bonPlanService=new BonPlanService();

    @FXML
    private Button Add_Id;
    @FXML
    private TextField NomBonPlan_id;
    @FXML
    private TextField user_Id;
    @FXML
    private TextField Adresse_Id;
    @FXML
    private TextField Type_Id;
    private Text afficher_Id;
    @FXML
    private Label label_id;
    @FXML
    private Label label_id1;
    @FXML
    private Label label_id2;
    @FXML
    private Label label_id4;
    @FXML
    private Label label_id21;
    @FXML
    private Button ReadAll_Id;
    @FXML
    private Label label_id22;
    @FXML
    private Label label_id211;
    @FXML
    private Label label_id41;
    @FXML
    private Label label_id42;
    @FXML
    private ListView<BonPlan> list_bonplan;
    @FXML
    private Button delete_btn;
    @FXML
    private Button update_bonplan;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void AjouterBonPlan(ActionEvent event) {
         if (NomBonPlan_id.getText().length() == 0||Adresse_Id.getText().length() == 0||Type_Id.getText().length() == 0||user_Id.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Please remplir tous les champs"+ "");
            alert.show();

        } else {
            BonPlan b=new BonPlan();
            b.setNom_bonplan(NomBonPlan_id.getText());
            b.setAdresse(Adresse_Id.getText());
            b.setType(Type_Id.getText());
            b.setId_user(Integer.parseInt(user_Id.getText()));
            bonPlanService.insert(b);
            //afficher_Id.setText(bonPlanService.readAll().toString());
        }
    }

    @FXML
    private void AfficherBonPlans(ActionEvent event) {
       ObservableList<BonPlan> bonplans =FXCollections.observableArrayList(bonPlanService.readAll());
       list_bonplan.setItems(bonplans);
                    
    }

    @FXML
    private void deleteBonPlan(ActionEvent event) {
        int selectedId= list_bonplan.getSelectionModel().getSelectedItem().getId_bonplan();
        bonPlanService.delete(selectedId);
        //list_bonplan.getItems().remove(selectedId);
    }

    @FXML
    private void ModifierBonPlan(ActionEvent event) {
        BonPlan selectedBonPlan= list_bonplan.getSelectionModel().getSelectedItem();
        try {
            //bonPlanService.update(bonPlanService.readById(selectedId));
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./updatebonplan.fxml"));
            Parent view_2=loader.load();
            UpdatebonplanController updatebonplanController=loader.getController();
            updatebonplanController.getBonPlan(selectedBonPlan);
            updatebonplanController.b=selectedBonPlan;
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
