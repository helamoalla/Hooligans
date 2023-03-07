/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package View;

import Model.BonPlan;
import Model.Feedback;
import Service.FeedbackService;
import Service.UserService;
import Util.Data;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author azizh
 */
public class FeedbacksController implements Initializable {

    @FXML
    private FlowPane container;
    
    FeedbackService fs =new FeedbackService();
    BorderPane borderPane;
    UserService userService = new UserService();
    BonPlan b;

    /**
     * Initializes the controller class.
     */
     public void setBonPlan(BonPlan bonPlan){
         b=bonPlan;
         //System.out.println(b);
         //System.out.println(bonPlan);
         //System.out.println("setBonPlan called: " + b);
         getAllFeedbacks(b);
         
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
    }   
     public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
    
    
    public void getAllFeedbacks(BonPlan b){
        container.getChildren().clear();
        for(Feedback data:fs.readAllByBonPlan(b)){
            // Create the HBox container
        HBox hBox = new HBox();
        hBox.setPrefSize(642, 216);
        Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)));
        hBox.setBorder(border);
        hBox.setStyle("-fx-background-color: white;-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0);");
        hBox.setPadding(new Insets(20));
        container.getChildren().add(hBox);

        // Create the VBox container
        VBox vBox = new VBox();
        vBox.setPrefSize(602, 146);
        vBox.setSpacing(10);
        hBox.getChildren().add(vBox);

        // Create the HBox for the user information
        HBox userHBox = new HBox();
        userHBox.setPrefSize(602, 39);
        userHBox.setSpacing(50);
        vBox.getChildren().add(userHBox);

        // Create the user's profile picture
        
        
        Image imgPic= new Image("http://localhost/images/"+userService.readById(data.getUser().getId_user()).getImg());
        ImageView profilePic = new ImageView(imgPic);
            
        System.out.println(imgPic.getUrl());
        profilePic.setFitHeight(55);
        profilePic.setFitWidth(55);
        Circle clip = new Circle(profilePic.getFitWidth()/2, profilePic.getFitHeight()/2, profilePic.getFitWidth()/2);
        profilePic.setClip(clip);

        userHBox.getChildren().add(profilePic);

        // Create the user's name
        Text userName = new Text(userService.readById(data.getUser().getId_user()).getNom());
        userName.setFont(new Font("Baskerville Old Face", 19));
        
        userName.setStrokeWidth(0);
        userName.setTextAlignment(TextAlignment.CENTER);
        HBox.setMargin(userName, new Insets(15));
        userHBox.getChildren().add(userName);

        // Create the delete button
        if (userService.readById(Data.getId_user()).getRole().getId_role()==1|| Data.getId_user()==data.getUser().getId_user() ){
        JFXButton deleteBtn = new JFXButton("");
        deleteBtn.setPrefSize(116.0, 38.0);
        ImageView deleteButton = new ImageView(new Image(getClass().getResourceAsStream("../images/delete.png")));
        deleteButton.setFitHeight(32);
        deleteButton.setFitWidth(45);
        HBox.setMargin(deleteBtn, new Insets(0, 0, 0, 240));
        deleteBtn.setGraphic(deleteButton);
        userHBox.getChildren().add(deleteBtn);
        deleteBtn.setOnAction(e->deleteFeedback(e, data));
        }
        
        

        // Create the Rating control
        
        Rating rating = new Rating();
        rating.setPrefSize(176, 22);
        rating.setRating(data.getRate());
        rating.setDisable(true);
        HBox.setMargin(rating, new Insets(0, 0, 0, 100));
        vBox.getChildren().add(rating);
        if(data.getRate()==0){rating.setVisible(false);}

        // Create the user's message
        Text userMessage = new Text(data.getCommentaire());
        userMessage.setFont(new Font("Bell MT Bold", 15));
        userMessage.setStrokeWidth(0);
        userMessage.setWrappingWidth(502.9367218017578);
        HBox userMessageHBox = new HBox();
        userMessageHBox.setPrefSize(602, 82);
        userMessageHBox.getChildren().add(userMessage);
        vBox.getChildren().add(userMessageHBox);
        }
        if(fs.readAllByBonPlan(b).size()==0){
            noContent();
        }
        
    }
   
    void deleteFeedback(ActionEvent e,Feedback f){
        fs.delete(f.getId_feedback());
        getAllFeedbacks(b);
    }
    void noContent(){
        AnchorPane root = new AnchorPane();
        root.setPrefSize(1014.0, 787.0);
        root.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        root.setMinSize(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        root.setStyle("-fx-background-color: #dcdcdc;");
        container.getChildren().add(root);


        
        
         ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("../images/no-content.png")));
         
        imageView.setLayoutX(204.0);
        imageView.setLayoutY(328.0);
        imageView.setFitWidth(223.0);
        imageView.setFitHeight(219.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);


        Text text = new Text();
        text.setLayoutX(109.0);
        text.setLayoutY(278.0);
        text.setOpacity(0.64);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("No Content");
        text.setWrappingWidth(504.5366325378418);

        Font font = Font.font("Berlin Sans FB", 85.0);
        text.setFont(font);

        root.getChildren().addAll(imageView, text);
    }

    
}
