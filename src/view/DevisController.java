/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Interfaces.InterfaceCRUD;
import Models.Devis;
import Models.GarageC;
import Models.Maintenance;
import Services.ServiceDevis;
import Services.ServiceGarageC;
import Services.ServiceMaintenance;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class DevisController implements Initializable {

    InterfaceCRUD sd = new ServiceDevis();
    InterfaceCRUD sm = new ServiceMaintenance();
    ServiceDevis SD = new ServiceDevis();
    InterfaceCRUD sg = new ServiceGarageC();
    private ListView<Devis> id_list;
    @FXML
    private GridPane grid;
    @FXML
    private HBox Vboxe;
    private List<Devis> id_list_d = new ArrayList<>();
    Devis d;
    Maintenance m;
    GarageC g;
    Devis d1 = new Devis();
    private List<Maintenance> id_list_m = new ArrayList<>();
    private List<GarageC> id_list_g = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    float TTC;

    public void getMaintenance(Maintenance m) {
        this.m = m;

        int column = 0;
        int row = 0;
        id_list_d.addAll(sd.readAll());
        id_list_m.addAll(sm.readAll());
        id_list_g.addAll(sg.readAll());
        // System.out.println(id_list_m);
        try {
            for (int i = 0; i < id_list_g.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../view/itemDevis.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemDevisController ItemDevisController = fxmlLoader.getController();

                GarageC g1 = ItemDevisController.getGarage(id_list_g.get(i));

                ItemDevisController.g = g1;
                ItemDevisController.m = m;
                d1.setId_user(m.getId_user());
                d1.setGarage(g1);
                //  d1.setGarage(id_list_g.get(i));
                d1.setMaintenance(m);
                sd.update(d1);
                this.TTC = SD.update1(d1);
                ItemDevisController.TTC1 = this.TTC;
                ItemDevisController.getDevis(d1);
                ItemDevisController.d = d1;

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width

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

    private void confirmer(ActionEvent event) {

        Devis selectedDevis = id_list.getSelectionModel().getSelectedItem();

        sd.insert(selectedDevis);

    }

}
