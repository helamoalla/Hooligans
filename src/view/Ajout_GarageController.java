/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Interfaces.InterfaceCRUD;
import Models.GarageC;
import Services.ServiceGarageC;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class Ajout_GarageController implements Initializable {

    InterfaceCRUD sg=new ServiceGarageC();
    @FXML
    private Button id_ajout;
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

    @FXML
    private void ajouter_garage(ActionEvent event) throws ParseException{
       
        try {
            GarageC g=new GarageC();
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
            sg.insert(g);
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./Garage.fxml"));
            Parent view_2=loader.load();
            
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Ajout_GarageController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
       
        
    }
    

