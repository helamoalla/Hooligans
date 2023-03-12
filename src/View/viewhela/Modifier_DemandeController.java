/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.viewhela;

import Interface.InterfaceCRUD;
import Model.Maintenance;
import Service.ServiceMaintenance;
import Service.UserService;
import com.twilio.rest.api.v2010.account.Notification;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Util.Data;
import javafx.scene.layout.BorderPane;


/**
 * FXML Controller class
 *
 * @author helam
 */
public class Modifier_DemandeController implements Initializable {
Maintenance m=new Maintenance();
InterfaceCRUD sm=new ServiceMaintenance();
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
    private CheckBox id_fuite_d_huile;
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
    private Button id_modifier;
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
          public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
  void getMaintenance(Maintenance m){
    
          //id_user.setText(String.valueOf(m.getUser().getId_user()));
          
          id_panne_moteur.setSelected(m.isPanne_moteur());
           id_pompe_a_eau.setSelected(m.isPompe_a_eau());
            id_patin.setSelected(m.isPatin());
           id_essuie_glace.setSelected(m.isEssuie_glace());
            id_radiateur.setSelected(m.isRadiateur());
           id_ventilateur.setSelected(m.isVentilateur());
            id_duride.setSelected(m.isDuride());
          id_fuite_d_huile.setSelected(m.isFuite_d_huile());
            id_vidange.setSelected(m.isVidange());
           id_filtre.setSelected(m.isFiltre());
        id_batterie.setSelected(m.isBatterie());
           id_amortisseur.setSelected(m.isAmortisseur());
         id_frein_main.setSelected(m.isFrein_main());
          id_feu_d_eclairage.setSelected( m.isFeu_d_eclairage());


           }
  UserService us=new UserService();
  @FXML
    private void modifier_demande(ActionEvent event) {
        try {
             m.setUser(us.readById(Data.getId_user()));
           
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

            sm.update(m);
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./devis.fxml"));
            Parent view_2=loader.load();
                        DevisController devisController = loader.getController();
                        devisController.getMaintenance(m, borderPane);
                        devisController.m=m;
            //devisController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Modifier_DemandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
