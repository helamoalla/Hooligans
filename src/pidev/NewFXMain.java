/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

//import com.twilio.rest.api.v2010.account.Notification;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author helam
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        try {
           Parent root = FXMLLoader.load(getClass().getResource("../viewhela/GESTION.fxml"));
  //Parent root = FXMLLoader.load(getClass().getResource("../view/affichageGarage.fxml"));
             //Parent root = FXMLLoader.load(getClass().getResource("../view/devis.fxml"));
          // Parent root = FXMLLoader.load(getClass().getResource("../view/Maintenance.fxml"));
           // Parent root = FXMLLoader.load(getClass().getResource("../view/afficher_Maintenance.fxml"));
            Scene scene = new Scene(root);
           
            
            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
