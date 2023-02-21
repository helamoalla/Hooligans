/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Interfaces.InterfaceCRUD;
import Models.GarageC;
import Services.ServiceGarageC;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class Modifier_GarageController implements Initializable {
GarageC g;
InterfaceCRUD sg=new ServiceGarageC();
    @FXML
    private Button id_modifier;
    @FXML
    private TextField id_nom;
    @FXML
    private TextField id_adresse;
    @FXML
    private TextField id_num;
    @FXML
    private TextField id_panne_moteur;
    @FXML
    private TextField id_pompe_a_eau;
    @FXML
    private TextField id_patin;
    @FXML
    private TextField id_essuie_glace;
    @FXML
    private TextField id_radiateur;
    @FXML
    private TextField id_ventilateur;
    @FXML
    private TextField id_duride;
    @FXML
    private TextField id_fuite_d_huile;
    @FXML
    private TextField id_vidange;
    @FXML
    private TextField id_filtre;
    @FXML
    private TextField id_batterie;
    @FXML
    private TextField id_amortisseur;
    @FXML
    private TextField id_frain_main;
    @FXML
    private TextField id_feu_eclairage;
    @FXML
    private TextField id_reduction;
    @FXML
    private TextField id_autre;
    @FXML
    private TextField id_image;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        // TODO
    }   
     void getGarage(GarageC g){
    
          id_nom.setText(g.getNom_garage());
            id_adresse.setText(g.getAdresse());
           id_num.setText(String.valueOf(g.getNumero()));
            id_panne_moteur.setText(String.valueOf(g.getPanne_moteur()));
           id_pompe_a_eau.setText(String.valueOf(g.getPompe_a_eau()));
            id_patin.setText(String.valueOf(g.getPatin()));
           id_essuie_glace.setText(String.valueOf(g.getEssuie_glace()));
            id_radiateur.setText(String.valueOf(g.getRadiateur()));
           id_ventilateur.setText(String.valueOf(g.getVentilateur()));
            id_duride.setText(String.valueOf(g.getDuride()));
          id_fuite_d_huile.setText(String.valueOf(g.getFuite_d_huile()));
            id_vidange.setText(String.valueOf(g.getVidange()));
           id_filtre.setText(String.valueOf(g.getFiltre()));
        id_batterie.setText(String.valueOf(g.getBatterie()));
           id_amortisseur.setText(String.valueOf( g.getAmortisseur()));
           id_frain_main.setText(String.valueOf(g.getFrein_main()));
           id_feu_eclairage.setText(String.valueOf( g.getFeu_d_eclairage()));
           id_reduction.setText(String.valueOf(g.getTaux_de_reduction()));
            id_autre.setText(g.getAutre());
            id_image.setText(g.getImage());
}

    @FXML
    private void modifier_garage(ActionEvent event) {
        try {
             g.setNom_garage(id_nom.getText());
            g.setAdresse(id_adresse.getText());
            g.setNumero(Integer.parseInt(id_num.getText()));
            g.setPanne_moteur(Integer.parseInt(id_panne_moteur.getText()));
            g.setPompe_a_eau(Integer.parseInt(id_pompe_a_eau.getText()));
            g.setPatin(Integer.parseInt(id_patin.getText()));
            g.setEssuie_glace(Integer.parseInt(id_essuie_glace.getText()));
            g.setRadiateur(Integer.parseInt(id_radiateur.getText()));
            g.setVentilateur(Integer.parseInt(id_ventilateur.getText()));
            g.setDuride(Integer.parseInt(id_duride.getText()));
            g.setFuite_d_huile(Integer.parseInt(id_fuite_d_huile.getText()));
            g.setVidange(Integer.parseInt(id_vidange.getText()));
            g.setFiltre(Integer.parseInt(id_filtre.getText()));
            g.setBatterie(Integer.parseInt(id_batterie.getText()));
            g.setAmortisseur(Integer.parseInt(id_amortisseur.getText()));
            g.setFrein_main(Integer.parseInt(id_frain_main.getText()));
            g.setFeu_d_eclairage(Integer.parseInt(id_feu_eclairage.getText()));
            g.setTaux_de_reduction(Integer.parseInt(id_reduction.getText()));
            g.setAutre(id_autre.getText());
            g.setImage(id_image.getText());

            sg.update(g);
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./Garage.fxml"));
            Parent view_2=loader.load();
            Scene scene = new Scene(view_2);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Modifier_GarageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
