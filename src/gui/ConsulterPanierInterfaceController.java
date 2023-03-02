/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import models.LignePanier;
import services.LignePanierService;
import services.PanierService;

/**
 * FXML Controller class
 *
 * @author choua
 */
public class ConsulterPanierInterfaceController implements Initializable {

    @FXML
    private VBox sideArea;
    @FXML
    private HBox sideControls;
    @FXML
    private Label closeButton;
    @FXML
    private SVGPath quitter;
    @FXML
    private VBox sideNavPanier;
    @FXML
    private Region Accueil1;
    @FXML
    private Region MonPanier1;
    @FXML
    private Pane handPaneMac;
    @FXML
    private ListView<LignePanier> listviewPanier;
    @FXML
    private Label lbtotal;
    @FXML
    private TextField MonatantTotal;
    @FXML
    private Button btnVider;
    @FXML
    private Button btnCommander;
    @FXML
    private Button btnplus;
    @FXML
    private Button btnmoins;
    @FXML
    private AnchorPane root1;
    @FXML
    private Button btnsupprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //int idpanier = 1;
        Mon_panier();
                
    }    
    
   
    
    

    @FXML
    private void Accueil(MouseEvent event) {
         try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./AccueilInterface.fxml"));
            Parent view_2=loader.load();
            AccueilInterfaceController i =loader.getController();
            
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterPanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void Mon_panier() {
        LignePanierService lps =new LignePanierService();
        PanierService p =new PanierService();
        List<LignePanier> pa = lps.AfficherPanierbyiduser(1);
        ObservableList<LignePanier> obslist = FXCollections.observableArrayList(pa);
        listviewPanier.setItems(obslist);
        MonatantTotal.setText(String.valueOf(p.totalmontantPanier(1)) + " DT");
    }

    @FXML
    private void ViderPanier(MouseEvent event) {
        PanierService p =new PanierService();
        LignePanierService lps = new LignePanierService();
        lps.ViderLigne_panier(1);
        Mon_panier();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Panier vidé avec succés");
            alert.show();
    }

    @FXML
    private void PlusUn(MouseEvent event) {
         LignePanierService lps =new LignePanierService();
        int selectedidpanier = listviewPanier.getSelectionModel().getSelectedItem().getPanier().getId_panier();
        int selectedidproduit = listviewPanier.getSelectionModel().getSelectedItem().getProduit().getId_prod();
        try {
            lps.QantitePlus1(selectedidpanier,selectedidproduit);
            System.out.println("Quantité mise à jour de +1");
           Mon_panier();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void MoinsUn(MouseEvent event) {
        LignePanierService lps =new LignePanierService();
        int selectedidpanier = listviewPanier.getSelectionModel().getSelectedItem().getPanier().getId_panier();
        int selectedidproduit = listviewPanier.getSelectionModel().getSelectedItem().getProduit().getId_prod();
        try {
            lps.Qantitemoins1(selectedidpanier,selectedidproduit);
            System.out.println("Quantité mise à jour de -1");
           Mon_panier();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void SupprimerLignePanier(MouseEvent event) {
        PanierService ps =new PanierService();
        LignePanierService lps = new LignePanierService();
        int selectedidpanier = listviewPanier.getSelectionModel().getSelectedItem().getPanier().getId_panier();
        int selectedidproduit = listviewPanier.getSelectionModel().getSelectedItem().getProduit().getId_prod();
        lps.SupprimerProduit_de_LignePanier(selectedidpanier,selectedidproduit);
        Mon_panier();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Produit supprimé du panier avec succés");
            alert.show();

    }

    @FXML
    private void PasserCommande(MouseEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./PasserCommandeInterface.fxml"));
            Parent view_2=loader.load();
            PasserCommandeInterfaceController i =loader.getController();
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterPanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
