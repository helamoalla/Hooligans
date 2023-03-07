/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package View;

import View.AjouterBonPlanController;
import Model.BonPlan;
import Model.Feedback;
import Service.BonPlanService;
import Service.FeedbackService;
import Service.UserService;
import Util.Data;
import View.FXMLController;
import View.MenuItemController;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author azizh
 */
public class DetailController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private Text name;
    @FXML
    private Text adresse;
    @FXML
    private Text type;
    @FXML
    private Rating rating;
    @FXML
    private JFXTextArea rate;
    @FXML
    private JFXTextArea commentaire;
    
    FeedbackService feedbackService=new FeedbackService();
    BonPlanService bs=new BonPlanService();
    
    BonPlan bonplan;
    BorderPane borderPane;
    @FXML
    private JFXToggleButton report;
    
    private boolean isreported=false;
    @FXML
    private Rating ratingAvg;
    @FXML
    private Text rateAvg;
    UserService userService=new UserService();
    @FXML
    private HBox feedsContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(userService.readById(Data.getId_user()).getRole().getId_role()==1){
            feedsContainer.setVisible(false);
        }
        report.selectedProperty().addListener((obs, oldState, newState) -> {
            if (newState) {
                System.out.println("Toggle switch is on");
                isreported=true;
            } else {
                System.out.println("Toggle switch is off");
                isreported=false;
            }
        });
        commentaire.setStyle("-fx-text-fill: gray;");
        rate.setStyle("-fx-text-fill: gray;");

        
    }   
      public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
      private void backToMain(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BonPlan.fxml"));
            Parent AllBonPlans = loader.load();            
            FXMLController fxmlController = loader.getController();
            fxmlController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(AllBonPlans);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    void getBonPlan(BonPlan b){
    URL imageUrl;
        
    try {
        name.setText(b.getNom_bonplan());
        adresse.setText(b.getAdresse());
        type.setText(b.getType());
        //image_label.setText(b.getImage());
        imageUrl = new URL("http://localhost/images/"+b.getImage());
        Image images = new Image(imageUrl.toString());
        imageView.setImage(images);
        bonplan=b;
        ratingAvg.setRating(feedbackService.RatingAvg(b));
        rateAvg.setText(Double.toString(feedbackService.RatingAvg(b)));
         if(feedbackService.checkIfRated(bonplan)==0){
            rating.ratingProperty().addListener((observable, oldValue, newValue) -> {
            rate.setText( Integer.toString(newValue.intValue()));
        });
        }
        else{
            rating.setRating(feedbackService.checkIfRated(bonplan));
            //rating.setPartialRating(true);
            //rating.setRating(3.3);
            rating.setDisable(true);
        }
         if(feedbackService.checkIfAlreadyReported(b)){
             report.setVisible(false);
         }
        System.out.println(b);
} catch (MalformedURLException ex) {
         System.out.println(ex);
                    }
        
}       

    @FXML
    private void ajouterFeedback(ActionEvent event) {
        if (commentaire.getText().length() == 0) {
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
         else {
            try {
                Feedback f=new Feedback();
                f.setCommentaire(commentaire.getText());
                if(feedbackService.checkIfRated(bonplan)==0){
                   //rate.setText("1");
                f.setRate(Integer.parseInt(rate.getText()));}
                else 
                    f.setRate(0);
                //f.setId_user(Integer.parseInt(idUser.getText()));
                
                f.setBonPlan(bonplan);
                f.setReport(isreported);
                feedbackService.insert(f);
                feedbackService.countReports();
                backToMain(event);
                
            } catch (Exception ex) {
                Logger.getLogger(AjouterBonPlanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
