/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.BonPlan;
import Model.Feedback;
import Service.BonPlanService;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

public class AjouterFeedBackController implements Initializable {
    BonPlan b;
    FeedbackService feedbackService=new FeedbackService();
    @FXML
    private TextField rate;
    @FXML
    private TextArea commentaire;
    @FXML
    private TextField idUser;
    @FXML
    private Button Add_btn;
    @FXML
    private Button retour_btn;
    @FXML
    private ListView<BonPlan> liste_bonplan;
    
    BonPlanService bonPlanService = new BonPlanService();

    /**
     * Initializes the controller class.
     */
    public void getAllBonPlans(){
        ObservableList<BonPlan> bonplans = FXCollections.observableArrayList(bonPlanService.readAll());
        liste_bonplan.setItems(bonplans);
         
         
          liste_bonplan.setCellFactory(param -> new ListCell<BonPlan>() {
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
        getAllBonPlans();
    }    

    @FXML
    private void ajouterFeedback(ActionEvent event) {
        BonPlan selectedBonPlan= liste_bonplan.getSelectionModel().getSelectedItem();
         if (commentaire.getText().length() == 0||rate.getText().length() == 0||idUser.getText().length() == 0||selectedBonPlan ==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Please remplir tous les champs"+ "");
            alert.show();

        }else if(!rate.getText().matches("\\d*")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("The Rate must be a number !!"+ "");
            alert.show();
            
        } 
         else if(!idUser.getText().matches("\\d*")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("The id of user must be a number !!"+ "");
            alert.show();
            
        }  
         else {
            try {
                Feedback f=new Feedback();
                f.setCommentaire(commentaire.getText());
                f.setRate(Integer.parseInt(rate.getText()));
                f.setId_user(Integer.parseInt(idUser.getText()));
                f.setBonPlan(selectedBonPlan);
                feedbackService.insert(f);
                FXMLLoader loader= new FXMLLoader(getClass().getResource("./Feedback.fxml"));
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
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./Feedback.fxml"));
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
