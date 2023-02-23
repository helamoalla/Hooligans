/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Feedback;
import Service.FeedbackService;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizh
 */
public class UpdateFeedbackController implements Initializable {

    @FXML
    private TextField rate;
    @FXML
    private Button retour_btn;
    @FXML
    private TextArea commentaire;
    
    Feedback f = new Feedback();
    FeedbackService fs=new FeedbackService();
    
    public void getFeedback(Feedback f){
        rate.setText(Integer.toString(f.getRate()));
        commentaire.setText(f.getCommentaire());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateFeedback(ActionEvent event) {
        if(!rate.getText().matches("\\d*")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("The Rate must be a number !!"+ "");
            alert.show();
            
        } 
        else{
            try {
            f.setCommentaire(commentaire.getText());
            f.setRate(Integer.parseInt(rate.getText()));
            fs.update(f);
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./Feedback.fxml"));
            Parent view_2=loader.load();
            Scene scene = new Scene(view_2);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UpdateFeedbackController.class.getName()).log(Level.SEVERE, null, ex);
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
