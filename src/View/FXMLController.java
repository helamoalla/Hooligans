/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.BonPlan;
import Service.BonPlanService;
import com.jfoenix.controls.JFXButton;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;



/**
 * FXML Controller class
 *
 * @author azizh
 */
public class FXMLController implements Initializable {
    
    BonPlanService bs=new BonPlanService();

    @FXML
    private FlowPane container;
    private BorderPane borderPane;



    /**
     * Initializes the controller class.
     */

    public void initialize(URL url, ResourceBundle rb) {
      getAllBonPlans();
      
    } 
    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
    
    private void AjouterBonPlan(ActionEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./AjouterBonPlan.fxml"));
            Parent view_2=loader.load();
            Scene scene = new Scene(view_2);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Ajouter Bon Plan");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       public void getAllBonPlans(){
           container.getChildren().clear();
            for (BonPlan data :bs.readAll() ) {
                try {
                    
                    VBox vBox = new VBox();
                    //vBox.setStyle("-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0);");
                    vBox.setPrefSize(180.0, 275.0);
                    vBox.setSpacing(10.0);
                    vBox.setPadding(new Insets(2.0));
                    vBox.setStyle("-fx-background-color: white; -fx-border-color: black;"
                            + "-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0);");
                    // header 
                    HBox hBox = new HBox();
                    hBox.setPrefSize(174.0, 26.0);
                    hBox.setSpacing(157.0);
                    hBox.setPadding(new Insets(5.0));
                    String checked="";
                    if(data.getEtat().equals("accepté"))
                        checked="../images/check.png";
                    else 
                        checked="../images/close.png";
                    ImageView checkImageView = new ImageView(new Image(getClass().getResourceAsStream(checked)));
                    checkImageView.setFitHeight(26.0);
                    checkImageView.setFitWidth(26.0);
                    checkImageView.setPickOnBounds(true);
                    checkImageView.setPreserveRatio(true);
                    
                    
                    JFXButton updateBtn = new JFXButton("");
                    updateBtn.setPrefSize(116.0, 38.0);
                    ImageView penImageView = new ImageView(new Image(getClass().getResourceAsStream("../images/pen.png")));
                    penImageView.setFitHeight(26.0);
                    penImageView.setFitWidth(26.0);
                    updateBtn.setGraphic(penImageView);
                    //penImageView.setOnMouseClicked(e->modifierBonPlan(e, data));
                    updateBtn.setOnAction(e->modifierBonPlan(e, data));
                  
                    penImageView.setPickOnBounds(true);
                    penImageView.setPreserveRatio(true);

                    hBox.getChildren().addAll(checkImageView, updateBtn);
                    
                    // set the image of the bonPlan
                    URL imageUrl = new URL("http://localhost/images/"+data.getImage());
                    Image images = new Image(imageUrl.toString());
                    ImageView mainImageView = new ImageView(images);
                    mainImageView.setFitHeight(136.0);
                    mainImageView.setFitWidth(228.0);
                    //mainImageView.setPickOnBounds(true);
                    //mainImageView.setPreserveRatio(true);
                    // set the name
                    Text mainText = new Text(data.getNom_bonplan());
                    mainText.setWrappingWidth(226.93669891357422);
                    mainText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
                    mainText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
                    mainText.setStrokeWidth(0.0);
                    mainText.setFont(new Font("Bodoni MT", 24.0)); 
                    mainText.setFill(Color.BLACK);

                    
                    // bottom
                    
                    HBox buttonBox = new HBox();
                    buttonBox.setPrefSize(227.0, 43.0);
                    buttonBox.setSpacing(7.0);

                    JFXButton detailButton = new JFXButton("   Detail");
                    detailButton.setTextFill(Color.BLACK);
                    detailButton.setPrefSize(116.0, 38.0);
                   
                    
                    ImageView detailImageView = new ImageView(new Image(getClass().getResourceAsStream("../images/info.png")));
                    detailImageView.setFitHeight(22.0);
                    detailImageView.setFitWidth(39.0);
                    detailButton.setOnAction(e->afficherDetails(e, data));
                    detailImageView.setPickOnBounds(true);
                    detailImageView.setPreserveRatio(true);

                    detailButton.setGraphic(detailImageView);
        
                    JFXButton deleteButton = new JFXButton("  Delete");
                    deleteButton.setPrefSize(111.0, 38.0);
                    deleteButton.setTextFill(Color.BLACK);

                    ImageView deleteImageView = new ImageView(new Image(getClass().getResourceAsStream("../images/delete.png")));
                    deleteImageView.setFitHeight(24.0);
                    deleteImageView.setFitWidth(22.0);
                    deleteImageView.setPickOnBounds(true);
                    deleteImageView.setPreserveRatio(true);
                    deleteButton.setGraphic(deleteImageView);
                    deleteButton.setOnAction(e->supprimerBonPlan(data));

        
                    buttonBox.getChildren().addAll(detailButton,deleteButton);
                    
                    HBox feedBackBox = new HBox();
                    feedBackBox.setPrefSize(227.0, 43.0);
                    feedBackBox.setSpacing(15.0);
                    JFXButton feedbackButton = new JFXButton("  Feeds");
                    feedbackButton.setPadding(new Insets(10.0));
                    feedbackButton.setPrefSize(116.0, 38.0);
                    feedbackButton.setTextFill(Color.BLACK);
                    ImageView feedbackView = new ImageView(new Image(getClass().getResourceAsStream("../images/feedback.png")));
                    feedbackView.setFitHeight(24.0);
                    feedbackView.setFitWidth(22.0);
                    feedbackView.setPickOnBounds(true);
                    feedbackView.setPreserveRatio(true);
                    feedbackButton.setGraphic(feedbackView);
                    
                    JFXButton validateButton = new JFXButton("Validate");
                    validateButton.setPadding(new Insets(10.0));
                    validateButton.setPrefSize(111.0, 38.0);
                    validateButton.setTextFill(Color.BLACK);
                    ImageView validateView = new ImageView(new Image(getClass().getResourceAsStream("../images/validation.png")));
                    validateView.setFitHeight(24.0);
                    validateView.setFitWidth(22.0);
                    validateView.setPickOnBounds(true);
                    validateView.setPreserveRatio(true);
                    validateButton.setGraphic(validateView);
                    
                    feedbackButton.setOnAction(e->feedbackBonPlan(e, data));
                    validateButton.setOnAction(e->validateBonPlan(e, data));
                    feedBackBox.getChildren().addAll(feedbackButton,validateButton);
        
        // add all to the vbox
                    vBox.getChildren().addAll(hBox,mainImageView,mainText,buttonBox,feedBackBox);
                    container.getChildren().addAll(vBox);
                    
                    
                } catch (Exception ex) {
                    Logger.getLogger(SideBarController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
    void supprimerBonPlan(BonPlan b){
        bs.delete(b.getId_bonplan());
        getAllBonPlans();
        
    }
    
    public void afficherDetails(Event event,BonPlan b){
    
        
        
             try {
                        
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./Detail.fxml"));
            Parent newView=loader.load();
            DetailController detailController=loader.getController();
            detailController.getBonPlan(b);
            detailController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(newView);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        
        
        }
}  
    
    
    private void modifierBonPlan(Event event,BonPlan selectedBonPlan) {
   
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./updatebonplan.fxml"));
            Parent view_2=loader.load();
            UpdatebonplanController updatebonplanController=loader.getController();
            updatebonplanController.getBonPlan(selectedBonPlan);
            updatebonplanController.b=selectedBonPlan;
            updatebonplanController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       
        public void feedbackBonPlan(Event e,BonPlan selectedBonPlan){
            try {
                        
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./Feedbacks.fxml"));
            Parent newView=loader.load();
            FeedbacksController feedbacksController=loader.getController();
            //detailController.getBonPlan(b);
            feedbacksController.setBorderPane(borderPane);
            feedbacksController.setBonPlan(selectedBonPlan);
            //feedbacksController.b=selectedBonPlan;
            borderPane.setCenter(null);
            borderPane.setCenter(newView);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        
        
        }
            
        }
        public void validateBonPlan(ActionEvent e,BonPlan b){
        bs.validateBonPlan(b);
        getAllBonPlans();
    }
      

    /*private void validerBonPlan(ActionEvent event) {
        
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
            //bonPlanService.validateBonPlan(selectedBonPlan);
            }
        } catch (Exception ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    
}
