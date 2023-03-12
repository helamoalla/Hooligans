/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.viewhela;

import Interface.InterfaceCRUD;
import Model.GarageC;
import Model.Maintenance;
import Service.ServiceGarageC;
import Service.ServiceMaintenance;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import Util.Data;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class MaintenanceController implements Initializable {

    ServiceGarageC sg = new ServiceGarageC();
    ServiceMaintenance sm = new ServiceMaintenance();
    private List<GarageC> id_list_g = new ArrayList<>();

    Maintenance m = new Maintenance();
    @FXML
    private Button id_demande;
    @FXML
    private ListView<Maintenance> id_list;
    @FXML
    private Button id_afficher;
     private List<Maintenance> id_list_M=new ArrayList<>();

    @FXML
    private Button id_retour;

    @FXML
    private GridPane grid;
    @FXML
    private HBox Vboxe;
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    private GarageC data() {
        // List<GarageC> lg=sg.readAll();
        // List<GarageC> lg=new ArrayList<>();
        GarageC g = new GarageC();
        g.setNom_garage(g.getNom_garage());
        g.setAdresse(g.getAdresse());
        g.setNumero(g.getNumero());
        // lg.add(g);
        return g;

    }
          public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
        int column = 0;
        int row = 0;
        id_list_g.addAll(sg.readAll());
        System.out.println(id_list);
        try {
            for (int i = 0; i < id_list_g.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../viewhela/lesGaragesUser.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                LesGaragesUserController itemController = fxmlLoader.getController();
                itemController.setData(id_list_g.get(i));
                           
            itemController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(anchorPane);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                //  column++;

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(5));
                GridPane.setColumnIndex(anchorPane, column);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        int column = 0;
//        int row = 0;
//        id_list_g.addAll(sg.readAll());
//        System.out.println(id_list);
//        try {
//            for (int i = 0; i < id_list_g.size(); i++) {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("../viewhela/lesGaragesUser.fxml"));
//                AnchorPane anchorPane = fxmlLoader.load();
//
//                LesGaragesUserController itemController = fxmlLoader.getController();
//                itemController.setData(id_list_g.get(i));
//                           
//            itemController.setBorderPane(borderPane);
//            borderPane.setCenter(null);
//            borderPane.setCenter(anchorPane);
//
//                if (column == 3) {
//                    column = 0;
//                    row++;
//                }
//
//                grid.add(anchorPane, column++, row); //(child,column,row)
//                //set grid width
//                //  column++;
//
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);
//
//                GridPane.setMargin(anchorPane, new Insets(5));
//                GridPane.setColumnIndex(anchorPane, column);
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//         ObservableList<GarageC> g=FXCollections.observableArrayList(sg.readAll());
//      id_list.setItems(g);
    }

    @FXML
    private void demander_maintenance(ActionEvent event) {
        try {
           id_list_M.addAll(sm.chercher(Data.getId_user()));
            if(id_list_M.size()==0){
                //7atou aziz
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./demander_maintenance.fxml"));
            Parent view_2 = loader.load();

                        Demander_maintenanceController demanderMaintController = loader.getController();
            demanderMaintController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);}
            else
            {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur !");
            alert.setContentText("vous avez deja une demande ! vous pouvez la modifier ou attendre 2 jours pour demander une autre "+ "");
            alert.show();
            
            }
        } catch (IOException ex) {
            Logger.getLogger(GarageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afficher_demande(ActionEvent event) {

        try {
            ObservableList<Maintenance> d = FXCollections.observableArrayList(sm.chercher(Data.getId_user()));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./MesDemandes.fxml"));
            Parent view_2 = loader.load();

            MesDemandesController itemcontroller = loader.getController();
            // itemcontroller.d=d;
            // ArrayList<Maintenance> id_list= (ArrayList<Maintenance>) itemcontroller.data(d);
            // itemcontroller.id_list=id_list;

            //  itemcontroller.id=Integer.valueOf(id_user.getText());
                      
            itemcontroller.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
            ///ArrayList<Maintenance> g=sm.chercher(2);
            // id_list.setItems(d);
        } catch (IOException ex) {
            Logger.getLogger(MaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int setId_user(int parseInt) {
        return parseInt;
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./GESTION.fxml"));
            Parent view_2 = loader.load();

                        GESTIONController gestionController = loader.getController();
            gestionController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
        } catch (IOException ex) {
            Logger.getLogger(GESTIONController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void afficher_garage(ActionEvent event) {
        ObservableList<GarageC> d = FXCollections.observableArrayList(sg.readAll());
        // id_list_G.setItems(d);
    }

}
