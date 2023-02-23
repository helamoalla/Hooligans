/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.BonPlan;
import Model.Feedback;
import Service.FeedbackService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizh
 */
public class FeedbackController implements Initializable {

    @FXML
    private ListView<Feedback> list_feedback;
    
    FeedbackService fs=new FeedbackService();

    /**
     * Initializes the controller class.
     */
    
    void getAllFeddbacks(){
        ObservableList<Feedback> feedbacks = FXCollections.observableArrayList(fs.readAll());
        list_feedback.setItems(feedbacks);
         
         
          list_feedback.setCellFactory(param -> new ListCell<Feedback>() {
            private final ImageView imageView = new ImageView();
            private final Text nom = new Text();
            private final Text adresse = new Text();
            private final Text commentaire = new Text();
            private final Text rate = new Text();
            
            private final HBox hbox = new HBox(68,imageView,nom,adresse,commentaire,rate);
            //private final HBox hbox2 = new HBox(200,imageView,nom,adresse,type,etat);
            
            {
                imageView.setFitWidth(75);
                imageView.setFitHeight(75);
                hbox.setAlignment(Pos.CENTER_LEFT);
                hbox.setPrefWidth(500);
                nom.setWrappingWidth(100);
                adresse.setWrappingWidth(90);
                commentaire.setWrappingWidth(195);
                rate.setWrappingWidth(30);
            }

            @Override
            protected void updateItem(Feedback item, boolean empty) {
                URL imageUrl;
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        nom.setText(item.getBonPlan().getNom_bonplan());
                        adresse.setText(item.getBonPlan().getAdresse());
                        commentaire.setText(item.getCommentaire());
                        rate.setText(Integer.toString(item.getRate()));
                        imageUrl = new URL("http://localhost/images/"+item.getBonPlan().getImage());
                        Image images = new Image(imageUrl.toString());
                        imageView.setImage(images);
                        setText(null);
                        setGraphic(hbox);
                    } catch (MalformedURLException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getAllFeddbacks();
    }    
    

    @FXML
    private void AjouterFeedback(ActionEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./AjouterFeedBack.fxml"));
            Parent view_2=loader.load();
            Scene scene = new Scene(view_2);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterBonPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void AfficherFeedback(ActionEvent event) {
                getAllFeddbacks();

    }

    @FXML
    private void deleteFeedback(ActionEvent event) {
        Feedback selectedFeedBack= list_feedback.getSelectionModel().getSelectedItem();
        
        
        try {
            if(selectedFeedBack== null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("champs non selectionné !");
            alert.setContentText("Please Selectionner le champs à Supprimer"+ "");
            alert.show();
        }
        else{
           fs.delete(selectedFeedBack.getId_feedback());
                AfficherFeedback(event);
        }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void ModifierFeedback(ActionEvent event) {
        Feedback selectedFeedback= list_feedback.getSelectionModel().getSelectedItem();
        
        try {
            if(selectedFeedback== null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("champs non selectionné !");
            alert.setContentText("Please Selectionner le champs à modifier"+ "");
            alert.show();
        }
            //bonPlanService.update(bonPlanService.readById(selectedId));
            else{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./UpdateFeedback.fxml"));
            Parent view_2=loader.load();
            UpdateFeedbackController updatefeedbackController=loader.getController();
            updatefeedbackController.getFeedback(selectedFeedback);
            updatefeedbackController.f=selectedFeedback;
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
