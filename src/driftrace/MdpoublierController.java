/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driftrace;

import Model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javax.mail.MessagingException;
import Service.UserService;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author 21694
 */
public class MdpoublierController implements Initializable {
    @FXML
    private TextField email;
    @FXML
    private Button send;
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb    
     */
   UserService userService = new UserService();
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
   
    }    

    @FXML
    private void sendb(ActionEvent event) throws MessagingException {
        String u=userService.readByMail(email.getText());
        userService.sendEmail(email.getText(), "Mdp oublie", "votre mot de passe est "+u);
    }

    
    
    private void retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("conx.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
           
    }
    
}
