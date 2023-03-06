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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Commande;
import services.CommandeService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.scene.control.Alert;





/**
 * FXML Controller class
 *
 * @author choua
 */
public class MesCommandesInterfacesController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private VBox sideArea;
    @FXML
    private HBox sideControls;
    @FXML
    private VBox sideNavPanier;
    @FXML
    private Region home;
    @FXML
    private Region MonPanier;
    @FXML
    private Pane handPaneMac;
    @FXML
    private ListView<Commande> listviewC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         MesCommandes();
    }    

    @FXML
    private void fenetre_accueil(MouseEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./AccueilInterface.fxml"));
            Parent view_2=loader.load();
            AccueilInterfaceController i =loader.getController();
            
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MesCommandesInterfacesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void MontrerMonPanier(MouseEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./ConsulterPanierInterface.fxml"));
            Parent view_2=loader.load();
            ConsulterPanierInterfaceController i =loader.getController();
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MesCommandesInterfacesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Imprimer_facture(MouseEvent event) throws FileNotFoundException, DocumentException {
        
        String file_name = ("C:\\Users\\choua\\OneDrive\\Documents\\Files\\facture.pdf");
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(file_name));
        doc.open();
      System.out.println("PDF created successfully.");
        doc.add(new Paragraph("Votre Facture :"));
        doc.add(new Paragraph("Facture numéro : "+listviewC.getSelectionModel().getSelectedItem().getId_commande()));
        doc.add(new Paragraph("Montant total à payer en DT : "+listviewC.getSelectionModel().getSelectedItem().getMontant()));
        doc.add(new Paragraph("Statut de votre commande : "+listviewC.getSelectionModel().getSelectedItem().getEtat_commande()));
        //doc.add(new Paragraph("La commande va etre livré à l'adresse : "+listviewC.getSelectionModel().getSelectedItem().getAdresse()));
        doc.close();
               
    }
    
    void MesCommandes() {
        try {
            CommandeService cs =new CommandeService();
            List<Commande> pa = cs.AfficherCommandesByidpanier(1);
            ObservableList<Commande> obslist = FXCollections.observableArrayList(pa);
            listviewC.setItems(obslist);
        } catch (SQLException ex) {
            Logger.getLogger(MesCommandesInterfacesController.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }                    

    @FXML
    private void Annuler_Commande(MouseEvent event) {
        CommandeService cs =new CommandeService(); 
        int selectedidcommande = listviewC.getSelectionModel().getSelectedItem().getId_commande();
        cs.delete(selectedidcommande);
       MesCommandes();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Commande annulée avec succés");
            alert.show();
    }
}

