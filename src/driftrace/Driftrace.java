/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driftrace;

import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import utils.DataSource;


public class Driftrace extends javafx.application.Application {
  public static int idutilisateur;
  
   
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("conx.fxml"));
        
        Scene scene = new Scene(root);
        

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
