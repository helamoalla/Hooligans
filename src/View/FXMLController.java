/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.BonPlan;
import Service.BonPlanService;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.event.MouseEvent;
import javafx.event.EventHandler;



/**
 * FXML Controller class
 *
 * @author azizh
 */
public class FXMLController implements Initializable {
    
    BonPlanService bonPlanService=new BonPlanService();

    @FXML
    private Button Add_Id;
    private Text afficher_Id;
    @FXML
    private Button ReadAll_Id;
    @FXML
    private ListView<BonPlan> list_bonplan;
    @FXML
    private Button delete_btn;
    @FXML
    private Button update_bonplan;



    /**
     * Initializes the controller class.
     */

    public void initialize(URL url, ResourceBundle rb) {
      getAllBonPlans();
      
    } 
    
    @FXML
    private void AjouterBonPlan(ActionEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./AjouterBonPlan.fxml"));
            Parent view_2=loader.load();
            Scene scene = new Scene(view_2);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void getAllBonPlans(){
        ObservableList<BonPlan> bonplans = FXCollections.observableArrayList(bonPlanService.readAll());
        list_bonplan.setItems(bonplans);
         
            list_bonplan.setStyle("-fx-background-color: transparent;");
          list_bonplan.setCellFactory(param -> new ListCell<BonPlan>() {
            private final ImageView imageView = new ImageView();
            private final Text nom = new Text();
            private final Text adresse = new Text();
            private final Text type = new Text();
            private final Text etat = new Text();
            
            private final HBox hbox = new HBox(68,imageView,nom,adresse,type,etat);
            
            
            {
                imageView.setFitWidth(75);
                imageView.setFitHeight(75);
                hbox.setAlignment(Pos.CENTER_LEFT);
                hbox.setPrefWidth(500);
                nom.setWrappingWidth(100);
                adresse.setWrappingWidth(90);
                type.setWrappingWidth(70);
                etat.setWrappingWidth(80);
                hbox.setStyle("-fx-background-color: transparent;");
            }

            @Override
            protected void updateItem(BonPlan item, boolean empty) {
                URL imageUrl;
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        nom.setText(item.getNom_bonplan());
                        adresse.setText(item.getAdresse());
                        type.setText(item.getType());
                        etat.setText(item.getEtat());
                        imageUrl = new URL("http://localhost/images/"+item.getImage());
                        Image images = new Image(imageUrl.toString());
                        imageView.setImage(images);
                        setText(null);
                        //hbox.setStyle("-fx-background-color: transparent;");
                        setGraphic(hbox);
                    } catch (MalformedURLException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
    }
  

    @FXML
    private void AfficherBonPlans(ActionEvent event) {
       
    getAllBonPlans();
                    
    }

    @FXML
    private void deleteBonPlan(ActionEvent event) {
        
        BonPlan selectedBonPlan= list_bonplan.getSelectionModel().getSelectedItem();
        
        
        try {
            if(selectedBonPlan== null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("champs non selectionné !");
            alert.setContentText("Please Selectionner le champs à Supprimer"+ "");
            alert.show();
        }
        else{
           bonPlanService.delete(selectedBonPlan.getId_bonplan());
                AfficherBonPlans(event);
        }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
    }

    @FXML
    private void ModifierBonPlan(ActionEvent event) {
        BonPlan selectedBonPlan= list_bonplan.getSelectionModel().getSelectedItem();
        
        try {
            if(selectedBonPlan== null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("champs non selectionné !");
            alert.setContentText("Please Selectionner le champs à modifier"+ "");
            alert.show();
        }
            //bonPlanService.update(bonPlanService.readById(selectedId));
            else{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./updatebonplan.fxml"));
            Parent view_2=loader.load();
            UpdatebonplanController updatebonplanController=loader.getController();
            updatebonplanController.getBonPlan(selectedBonPlan);
            updatebonplanController.b=selectedBonPlan;
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void ajouterFeedBack(ActionEvent event) {
         BonPlan selectedBonPlan= list_bonplan.getSelectionModel().getSelectedItem();
        
        try {
            if(selectedBonPlan== null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("champs non selectionné !");
            alert.setContentText("Please Selectionner le bon plan au quel tu veux ajouter un feedback"+ "");
            alert.show();
        }
            //bonPlanService.update(bonPlanService.readById(selectedId));
            else{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./AjouterFeedBack.fxml"));
            Parent view_2=loader.load();
            AjouterFeedBackController ajouterFeedBackController=loader.getController();
            //AjouterFeedBackController.getBonPlan(selectedBonPlan);
            ajouterFeedBackController.b=selectedBonPlan;
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void home(ActionEvent event) {
         try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./Home.fxml"));
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
