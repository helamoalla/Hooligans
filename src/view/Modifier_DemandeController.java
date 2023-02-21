/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Interfaces.InterfaceCRUD;
import Models.Maintenance;
import Services.ServiceMaintenance;
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

/**
 * FXML Controller class
 *
 * @author helam
 */
public class Modifier_DemandeController implements Initializable {
Maintenance m=new Maintenance();
InterfaceCRUD sm=new ServiceMaintenance();
    @FXML
    private TextField id_user;
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
    private TextField id_autre;
    @FXML
    private Button id_modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  void getMaintenance(Maintenance m){
    
          id_user.setText(String.valueOf(m.getId_user()));
           id_autre.setText(m.getAutre());
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
    @FXML
    private void modifier_demande(ActionEvent event) {
        try {
            // m.setId_user(Integer.parseInt(id_user.getText()));
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

            sm.update(m);
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./Maintenance.fxml"));
            Parent view_2=loader.load();
            Scene scene = new Scene(view_2);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Modifier_DemandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
