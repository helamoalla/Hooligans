/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package View;

import Model.User;
import Service.UserService;
import Util.Data;
import Util.Stripeapi;
import com.jfoenix.controls.JFXTextArea;
import com.stripe.exception.StripeException;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizh
 */
public class AlimenterCompteController implements Initializable {

    @FXML
    private JFXTextArea carte;
    @FXML
    private ChoiceBox<String> BoxMonth;
    @FXML
    private ChoiceBox<Integer> BoxYear;
    @FXML
    private JFXTextArea CVV;
    @FXML
    private JFXTextArea montant;
    Stripeapi stp =new Stripeapi();
    UserService us=new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> months = FXCollections.observableArrayList();
        for (int i = 1; i <= 12; i++) {
            String month = String.format("%02d", i);
            months.add(month);
        }
        BoxMonth.setItems(months);

        ObservableList<Integer> years = FXCollections.observableArrayList();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int year = 1900; year <= currentYear; year++) {
            years.add(year);
        }
        BoxYear.setItems(years);
        
    }    

    @FXML
    private void Alimenter(ActionEvent event) {
        try {
            if (carte.getText().isEmpty() || BoxMonth.getValue().isEmpty() || BoxYear.getValue()==0 ||
                    CVV.getText().isEmpty()  ||
                    montant.getText().isEmpty()) {
                // Afficher un message d'alerte
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champs manquants");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs !");
                alert.showAndWait();
                return;
            }
            
            stp.verifyCardAndPay(carte.getText(), Integer.parseInt(BoxMonth.getValue()),BoxYear.getValue() , CVV.getText(), montant.getText(), "Aziz Hajjem");
            System.out.println(us.readById(Data.getId_user()));
            
          User u1=  us.readById(Data.getId_user());
                    u1.setQuota(u1.getQuota()+Double.parseDouble(montant.getText()));
            System.out.println("Beforee"+u1.getQuota());
            us.update(u1);
            System.out.println(montant.getText());
            System.out.println("Afterr "+u1.getQuota());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/SideBar.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
        } catch (StripeException ex) {
            Logger.getLogger(AlimenterCompteController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur solde insufissant or carte incorrecte");
        } catch (IOException ex) {
            Logger.getLogger(AlimenterCompteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
