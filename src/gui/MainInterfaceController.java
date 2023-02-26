/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author choua
 */
public class MainInterfaceController implements Initializable {
    
    @FXML
    private Button btn_ajoutPanier;
    @FXML
    private Button btn_suppPanier;
    @FXML
    private Button btn_ConsulterPanier;
    @FXML
    private Button btncommande;
   
//    @FXML
//    public void fenetre_ajout(ActionEvent event) {
//         try {
//             FXMLLoader loader= new FXMLLoader(getClass().getResource("./InterfaceajoutPanier.fxml"));
//             Parent view_2=loader.load();
//             InterfaceajoutPanierController icp = loader.getController();
//             
//            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(view_2);
//            stage.setScene(scene);
//            stage.show();
//         } catch (IOException ex) {
//             Logger.getLogger(InterfaceajoutPanierController.class.getName()).log(Level.SEVERE, null, ex);
//         }
//
//    }
    
//    @FXML
//    public void fenetre_supp(ActionEvent event){           
//try {
//             FXMLLoader loader= new FXMLLoader(getClass().getResource("./InterfaceSuppPanier.fxml"));
//             Parent view_2=loader.load();
//             InterfaceSuppPanierController i =loader.getController();
//             
//            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(view_2);
//            stage.setScene(scene);
//            stage.show();
//         } catch (IOException ex) {
//             Logger.getLogger(InterfaceSuppPanierController.class.getName()).log(Level.SEVERE, null, ex);
//         }
       


   // }
    
//    @FXML
//    public void fenetre_consulter(ActionEvent event){
//try {
//             FXMLLoader loader= new FXMLLoader(getClass().getResource("./InterfaceConsulter.fxml"));
//             Parent view_2=loader.load();
//             InterfaceConsulterController i =loader.getController();
//             
//            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(view_2);
//            stage.setScene(scene);
//            stage.show();
//         } catch (IOException ex) {
//             Logger.getLogger(InterfaceConsulterController.class.getName()).log(Level.SEVERE, null, ex);
//         }
//
//
//    }

               
    @FXML
    public void fenetre_PasserCommande(ActionEvent event){

try {
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./InterfaceCommande.fxml"));
             Parent view_2=loader.load();
             InterfaceCommandeController ic = loader.getController();
             
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
         } catch (IOException ex) {
             Logger.getLogger(InterfaceCommandeController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void fenetre_ajout(MouseEvent event) {
    }
    
}
