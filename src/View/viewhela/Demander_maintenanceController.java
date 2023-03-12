/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.viewhela;

import Interface.InterfaceCRUD;
import Model.GarageC;
import Model.Maintenance;
import Service.ServiceMaintenance;
import Service.UserService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Properties;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import Util.Data;
import javafx.scene.layout.BorderPane;


/**
 * FXML Controller class
 *
 * @author helam
 */
public class Demander_maintenanceController implements Initializable {

    InterfaceCRUD sm = new ServiceMaintenance();
    ServiceMaintenance sm1 = new ServiceMaintenance();
    @FXML
    private CheckBox id_panne_moteur;
    @FXML
    private CheckBox id_pompe_a_eau;
    @FXML
    private CheckBox id_patin;
    @FXML
    private CheckBox id_essuie_glace;
    @FXML
    private CheckBox id_radiateur;
    @FXML
    private CheckBox id_ventilateur;
    @FXML
    private CheckBox id_duride;
    @FXML
    private CheckBox id_vidange;
    @FXML
    private CheckBox id_filtre;
    @FXML
    private CheckBox id_batterie;
    @FXML
    private CheckBox id_amortisseur;
    @FXML
    private CheckBox id_frein_main;
    @FXML
    private CheckBox id_feu_d_eclairage;
    @FXML
    private TextField id_autre;
    @FXML
    private Button id_demande;
    @FXML
    private CheckBox id_fuite_d_huile;
UserService us=new UserService();
    private BorderPane borderPane;
    /**
     * Initializes the controller class.
     */

           public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void demander_maintenance(ActionEvent event) {

        try {
            Maintenance m = new Maintenance();
            m.setUser(us.readById(Data.getId_user()));
            m.setAutre(id_autre.getText());
            m.setPanne_moteur(id_panne_moteur.isSelected());
            m.setPompe_a_eau(id_pompe_a_eau.isSelected());
            m.setPatin(id_patin.isSelected());
            m.setEssuie_glace(id_essuie_glace.isSelected());
            m.setRadiateur(id_radiateur.isSelected());
            m.setVentilateur(id_ventilateur.isSelected());
            m.setDuride(id_duride.isSelected());
            m.setFuite_d_huile(id_fuite_d_huile.isSelected());
            m.setVidange(id_vidange.isSelected());
            m.setFiltre(id_filtre.isSelected());
            m.setBatterie(id_batterie.isSelected());
            m.setAmortisseur(id_amortisseur.isSelected());
            m.setFrein_main(id_frein_main.isSelected());
            m.setFeu_d_eclairage(id_feu_d_eclairage.isSelected());

            sm.insert(m);
            System.out.println(m);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("./devis.fxml"));
            Parent view_2 = loader.load();
            DevisController devisController = loader.getController();

            //  GarageC g=new GarageC();
            devisController.getMaintenance(m,borderPane);
            System.out.println(m);
            devisController.m = m;

                        
            devisController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
        } catch (IOException ex) {
            Logger.getLogger(Demander_maintenanceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 

}
