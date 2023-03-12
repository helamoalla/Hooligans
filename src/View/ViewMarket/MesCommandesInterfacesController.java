/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.ViewMarket;


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

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import Model.Commande;
import Service.CommandeService;
import Interface.MyListenerC;

import java.sql.Connection;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import Model.User;
import Service.ServiceMaintenance;
import Service.UserService;
import Util.MyConnection;
import Util.Data;
import View.viewhela.ItemDevisController;
import View.viewhela.MaintenanceController;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.VerticalAlignment;
import javafx.scene.Parent;





import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

import javax.mail.MessagingException;




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
      Connection cnx = MyConnection.getInstance().getCnx();
    ObservableList<Commande> list = FXCollections.observableArrayList();
    List<Commande> listprod =new ArrayList();
    private MyListenerC myListener;
    private BorderPane borderPane;
ServiceMaintenance sm1 = new ServiceMaintenance();
UserService us = new UserService();
    User u1= us.readById(Data.getId_user());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         //affichercommande();
        
    } 
            public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
            affichercommande();}

public void setChosenCommande(Commande c){

            this.c = c;
             tfnum.setText(String.valueOf(c.getId_commande()));
//            LocalDate ld=c.getDate_commande().toLocalDate() ;
//
//                   tfdate.setText(String.valueOf(ld));
             tfdate.setText(String.valueOf(c.getDate_commande().toLocalDate()));
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
                
            p.setBorderPane(borderPane);
//            borderPane.setCenter(null);
//            borderPane.setCenter(anchorPane);
                    
                    if (column == 1) {
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
    private void Imprimer_facture(MouseEvent event) throws MessagingException  {
        
         try {
           

            // Creating a new PDF document
            PdfWriter writer = new PdfWriter("C:\\Users\\azizh\\OneDrive\\Bureau\\pdf_hela\\facture.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            ImageData imageData = ImageDataFactory.create("http://localhost/images/en_tete.png");
            Image image = new Image(imageData);

            document.add(image);
    System.out.println("PDF created successfully.");
             //Body
                  document.add(new Paragraph("Vendeur : Drift&Race"));
                  document.add(new Paragraph("___________________________________"));
                  document.add(new Paragraph("Client : "+u1.getNom()+" "+u1.getPrenom()));
                  document.add(new Paragraph("___________________________________"));
                  document.add(new Paragraph(""));
                  Table table = new Table(4);
                  table.addHeaderCell(new Cell().add(new Paragraph("Numéro de la commnde")).setVerticalAlignment(VerticalAlignment.MIDDLE));
                  table.addHeaderCell(new Cell().add(new Paragraph("Status")).setVerticalAlignment(VerticalAlignment.MIDDLE));
                  table.addHeaderCell(new Cell().add(new Paragraph("Adresse de livraison")).setVerticalAlignment(VerticalAlignment.MIDDLE));
                  table.addHeaderCell(new Cell().add(new Paragraph("Montant total à payer")).setVerticalAlignment(VerticalAlignment.MIDDLE));
                  table.addCell(new Cell().add(new Paragraph(""+c.getId_commande())).setVerticalAlignment(VerticalAlignment.MIDDLE));
                  table.addCell(new Cell().add(new Paragraph(""+c.getEtat_commande())).setVerticalAlignment(VerticalAlignment.MIDDLE));
                  table.addCell(new Cell().add(new Paragraph(""+c.getRue()+" "+c.getVille()+" "+c.getGouvernorat())).setVerticalAlignment(VerticalAlignment.MIDDLE));
                   table.addCell(new Cell().add(new Paragraph(""+c.getMontant()+" "+"DT")).setVerticalAlignment(VerticalAlignment.MIDDLE));
                  document.add(table);
                  document.add(new Paragraph(""));
                  document.add(new Paragraph("Merci pour votre confiance"));
                  
            ImageData imageData1 = ImageDataFactory.create("http://localhost/images/bas_page.png");
            Image image1 = new Image(imageData1);
            image1.setFixedPosition(pdf.getDefaultPageSize().getWidth()-700,5);
            document.add(image1);

            document.close();
            UserService us = new UserService();
            User u1 = us.readById(Data.getId_user());
            sm1.sendEmail(u1.getEmail(), "Facture!", "Voici votre ci joint votre facture cher client", "C:\\Users\\azizh\\OneDrive\\Bureau\\pdf_hela\\facture.pdf");

           
        } catch (IOException ex) {
            Logger.getLogger(ItemDevisController.class.getName()).log(Level.SEVERE, null, ex);
        }
 


//           
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

