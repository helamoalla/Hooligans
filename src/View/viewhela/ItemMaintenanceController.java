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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class ItemMaintenanceController implements Initializable {
InterfaceCRUD sm=new ServiceMaintenance();
    @FXML
    private Label id_user;
    @FXML
    private Label date_maintenance;
    @FXML
    private Label panne;
Maintenance m;
    @FXML
    private Button id_supp;
    @FXML
    private Label id_maintenance;
//private List<Maintenance> id_list = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setData(Maintenance m)
    {  
        this.m =m;
        id_user.setText(String.valueOf(m.getUser().getId_user()));
       date_maintenance.setText(String.valueOf(m.getDate_maintenance()));
       id_maintenance.setText(String.valueOf(m.getId_maintenance()));
         String a="";
     
       if(m.isAmortisseur()==true )
           a=a+"Amortisseur ";
        if(m.isBatterie()==true)
            a=a+" Batterie ";
        if(m.isDuride()==true)
            a=a+" Duride ";
        if(m.isEssuie_glace()==true)
            a=a+" Essuie_glace ";
        if(m.isFeu_d_eclairage()==true)
            a=a+" Feu_d_eclairage ";
        if(m.isFiltre()==true)
            a=a+" Filtre ";
        if(m.isFrein_main()==true)
            a=a+" Frein_main ";
        if(m.isFuite_d_huile()==true)
            a=a+" Fuite_d_huile ";
        if(m.isPanne_moteur()==true)
            a=a+" Panne_moteur ";
        if(m.isPatin()==true)
            a=a+" Patin ";
        if(m.isPompe_a_eau()==true)
            a=a+" Pompe_a_eau ";
        if(m.isRadiateur()==true)
            a=a+" Radiateur ";
        if(m.isVentilateur()==true)
            a=a+" Ventilateur ";
           if(m.isVidange()==true)
            a=a+" Vidange "; 
       panne.setText(a);
       
       
       
    }

    @FXML
    private void supprimer_Maintenance(ActionEvent event) {
         
    try {
        sm.delete(Integer.valueOf(id_maintenance.getText()));
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./afficher_Maintenance.fxml"));
        Parent view_2=loader.load();
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
        // afficher_garage(event);
    } catch (IOException ex) {
        Logger.getLogger(ItemMaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
    }
   
    }
    }

