/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewMarket;

import Util.Data;
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
import interfaces.MyListenerC;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import models.LignePanier;
import Util.Maconnexion;
import java.time.LocalDate;





/**
 * FXML Controller class
 *
 * @author choua
 */
public class MesCommandesInterfacesController implements Initializable, MyListenerC {

    @FXML
    private HBox chosenProduit;
    @FXML
    private Label nomproduit;
    @FXML
    private Label tfnum;
    @FXML
    private Label tfdate;
    @FXML
    private Label tfmontant;
    @FXML
    private Label tfrue;
    @FXML
    private Label tfville;
    @FXML
    private Label tfgov;
    @FXML
    private Button btnimprimer;
    @FXML
    private Button btnsupp;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
     private Commande c ;
     private CommandeService cs = new CommandeService();
      Connection cnx = Maconnexion.getInstance().getCnx();
    ObservableList<Commande> list = FXCollections.observableArrayList();
    List<Commande> listprod =new ArrayList();
    private MyListenerC myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         affichercommande();
        
    }    

public void setChosenCommande(Commande c){

            this.c = c;
             tfnum.setText(String.valueOf(c.getId_commande()));
//            LocalDate ld=c.getDate_commande().toLocalDate() ;
//
//                   tfdate.setText(String.valueOf(ld));
             
             tfmontant.setText(String.valueOf(c.getMontant()));
             tfrue.setText(c.getRue());
             tfville.setText(c.getVille());
             tfgov.setText(c.getGouvernorat());
      
    }

public void affichercommande(){
        try {
            /****************Afficher commande by id_user***********************/
            listprod.addAll(cs.AfficherCommandesByid_user(Data.getId_user()));
            System.out.println(listprod);
            if (listprod.size() > 0) {
                setChosenCommande(listprod.get(0));
                myListener = new MyListenerC() {
                    @Override
                    public void onClickListener(Commande commande) {
                        setChosenCommande(commande);
                        c=commande;
                    }
                };};
            int column = 0;
            int row = 0;
            for (int i = 0; i < listprod.size(); i++) {
                try {
                    System.out.println("iteration"+i);
                    System.out.println(listprod.size());
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("./Commande.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    
                    CommandeController p = fxmlLoader.getController();
                    p.setData(listprod.get(i),myListener);
                    
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
                    
                    GridPane.setMargin(anchorPane, new Insets(10));
                    GridPane.setColumnIndex(anchorPane, column);
                } catch (IOException ex) {
                    Logger.getLogger(PanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {    
            Logger.getLogger(MesCommandesInterfacesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Imprimer_facture(MouseEvent event) throws FileNotFoundException, DocumentException {
        
//        String file_name = ("C:\\Users\\choua\\OneDrive\\Documents\\Files\\facture.pdf");
//        Document doc = new Document();
//        PdfWriter.getInstance(doc, new FileOutputStream(file_name));
//        doc.open();
//      System.out.println("PDF created successfully.");
//        doc.add(new Paragraph("Votre Facture :"));
//        doc.add(new Paragraph("Facture numéro : "+listviewC.getSelectionModel().getSelectedItem().getId_commande()));
//        doc.add(new Paragraph("Montant total à payer en DT : "+listviewC.getSelectionModel().getSelectedItem().getMontant()));
//        doc.add(new Paragraph("Statut de votre commande : "+listviewC.getSelectionModel().getSelectedItem().getEtat_commande()));
//        //doc.add(new Paragraph("La commande va etre livré à l'adresse : "+listviewC.getSelectionModel().getSelectedItem().getAdresse()));
//        doc.close();
               
    }
                      

    @FXML
    private void Annuler_Commande(MouseEvent event) {
        CommandeService cs =new CommandeService(); 
        cs.delete(c.getId_commande());
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Commande annulée avec succés");
            alert.show();
    }

    @FXML
    private void supp(ActionEvent event) {
    }

    @FXML
    private void modif(ActionEvent event) {
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @Override
    public void onClickListener(Commande commande) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void chercherparcat(MouseEvent event) {
    }

}

