/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.viewhela;


import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import Interface.InterfaceCRUD;
import Model.Devis;
import Model.GarageC;
import Model.Maintenance;
import Model.User;
import Service.ServiceDevis;
import Service.ServiceGarageC;
import Service.ServiceMaintenance;
import Service.UserService;

import com.itextpdf.io.image.ImageData;

import com.itextpdf.layout.element.Paragraph;

import com.itextpdf.layout.properties.TextAlignment;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import Util.Data;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class ItemDevisController implements Initializable {

    @FXML
    Label TTC;
    @FXML
    private Label reduction;
    @FXML
    private Label total;
    @FXML
    private Label panne;
    @FXML
    private Label prix;
    Maintenance m;
    Devis d = new Devis();
    Devis d1;
    GarageC g;
    GarageC g1 = new GarageC();
    float TTC1;
    InterfaceCRUD sm = new ServiceMaintenance();
    ServiceMaintenance sm1 = new ServiceMaintenance();
    ServiceDevis sd = new ServiceDevis();
    InterfaceCRUD sg = new ServiceGarageC();
    @FXML
    private Button confirmer;
    @FXML
    private Label id_devis;
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
          public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public GarageC getGarage(GarageC g) {

        g1.setId_garage(g.getId_garage());
        g1.setNom_garage(g.getNom_garage());
        g1.setAdresse(g.getAdresse());
        g1.setNumero(g.getNumero());
        g1.setPanne_moteur(g.getPanne_moteur());
        g1.setPompe_a_eau(g.getPompe_a_eau());
        g1.setPatin(g.getPatin());
        g1.setEssuie_glace(g.getEssuie_glace());
        g1.setRadiateur(g.getRadiateur());
        g1.setVentilateur(g.getVentilateur());
        g1.setDuride(g.getDuride());
        g1.setFuite_d_huile(g.getFuite_d_huile());
        g1.setVidange(g.getVidange());
        g1.setFiltre(g.getFiltre());
        g1.setBatterie(g.getBatterie());
        g1.setAmortisseur(g.getAmortisseur());
        g1.setFrein_main(g.getFrein_main());
        g1.setFeu_d_eclairage(g.getFeu_d_eclairage());
        g1.setTaux_de_reduction(g.getTaux_de_reduction());
        this.g = g1;

        return g1;

    }

    public void getDevis(Devis d) {
        this.d1 = d;
        String a = "";
        String p = "";

//        if (this.m.isAmortisseur() == true) {
//            p = p + " | " + String.valueOf(g.getAmortisseur());
//            a = a + "| Amortisseur |";
//        }
//        if (this.m.isBatterie() == true) {
//            p = p + " | " + String.valueOf(g.getBatterie());
//            a = a + "| Batterie |";
//        }
//        if (this.m.isDuride() == true) {
//            p = p + " | " + String.valueOf(g.getDuride());
//            a = a + "| Duride |";
//        }
//        if (this.m.isEssuie_glace() == true) {
//            p = p + " | " + String.valueOf(g.getEssuie_glace());
//            a = a + "| Essuie_glace |";
//        }
//        if (this.m.isFeu_d_eclairage() == true) {
//            p = p + " | " + String.valueOf(g.getFeu_d_eclairage());
//            a = a + "| Feu_d_eclairage |";
//        }
//        if (this.m.isFiltre() == true) {
//            p = p + " | " + String.valueOf(g.getFiltre());
//            a = a + "| Filtre |";
//        }
//        if (this.m.isFrein_main() == true) {
//            p = p + " | " + String.valueOf(g.getFrein_main());
//            a = a + "| Frein_main |";
//        }
//        if (this.m.isFuite_d_huile() == true) {
//            p = p + " | " + String.valueOf(g.getFuite_d_huile());
//            a = a + "| Fuite_d_huile |";
//        }
//        if (this.m.isPanne_moteur() == true) {
//            p = p + " | " + String.valueOf(g.getPanne_moteur());
//            a = a + "| Panne_moteur |";
//        }
//        if (this.m.isPatin() == true) {
//            p = p + " | " + String.valueOf(g.getPatin());
//            a = a + "| Patin |";
//        }
//        if (this.m.isPompe_a_eau() == true) {
//            p = p + " | " + String.valueOf(g.getPompe_a_eau());
//            a = a + "| Pompe_a_eau |";
//        }
//        if (this.m.isRadiateur() == true) {
//            p = p + " | " + String.valueOf(g.getRadiateur());
//            a = a + "| Radiateur |";
//        }
//        if (this.m.isVentilateur() == true) {
//            p = p + " | " + String.valueOf(g.getVentilateur());
//            a = a + "| Ventilateur |";
//        }
//        if (this.m.isVidange() == true) {
//            p = p + " | " + String.valueOf(g.getVidange());
//            a = a + "| Vidange |";
//        }
if (this.m.isAmortisseur() == true) {
            p = p + "  \n" + String.valueOf(g.getAmortisseur());
            a = a + "\n Amortisseur ";
        }
        if (this.m.isBatterie() == true) {
            p = p + "  \n" + String.valueOf(g.getBatterie());
            a = a + "\n Batterie ";
        }
        if (this.m.isDuride() == true) {
            p = p + " \n " + String.valueOf(g.getDuride());
            a = a + "\n Duride ";
        }
        if (this.m.isEssuie_glace() == true) {
            p = p + "\n " + String.valueOf(g.getEssuie_glace());
            a = a + "\n Essuie_glace ";
        }
        if (this.m.isFeu_d_eclairage() == true) {
            p = p + " \n" + String.valueOf(g.getFeu_d_eclairage());
            a = a + "\n Feu_d_eclairage ";
        }
        if (this.m.isFiltre() == true) {
            p = p + " \n " + String.valueOf(g.getFiltre());
            a = a + "\n Filtre ";
        }
        if (this.m.isFrein_main() == true) {
            p = p + " \n " + String.valueOf(g.getFrein_main());
            a = a + "\n Frein_main ";
        }
        if (this.m.isFuite_d_huile() == true) {
            p = p + " \n " + String.valueOf(g.getFuite_d_huile());
            a = a + " \n Fuite_d_huile ";
        }
        if (this.m.isPanne_moteur() == true) {
            p = p + "  \n" + String.valueOf(g.getPanne_moteur());
            a = a + " \n Panne_moteur ";
        }
        if (this.m.isPatin() == true) {
            p = p + "  \n" + String.valueOf(g.getPatin());
            a = a + " \n Patin ";
        }
        if (this.m.isPompe_a_eau() == true) {
            p = p + "  \n " + String.valueOf(g.getPompe_a_eau());
            a = a + " \n Pompe_a_eau ";
        }
        if (this.m.isRadiateur() == true) {
            p = p + "  \n " + String.valueOf(g.getRadiateur());
            a = a + " \n Radiateur ";
        }
        if (this.m.isVentilateur() == true) {
            p = p + "  \n " + String.valueOf(g.getVentilateur());
            a = a + " \n Ventilateur ";
        }
        if (this.m.isVidange() == true) {
            p = p + "  \n " + String.valueOf(g.getVidange());
            a = a + " \n Vidange ";
        }
        panne.setText(a);
        prix.setText(p);
        total.setText(String.valueOf(d.getTotal()));
        reduction.setText(String.valueOf(g.getTaux_de_reduction()));
        TTC.setText(String.valueOf(TTC1));

    }
    int n = 0;

    @FXML
    private void confirmer(ActionEvent event) throws  MessagingException {

       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./Maintenance.fxml"));
            Parent view_2 = loader.load();
            n++;

            // Creating a new PDF document
            PdfWriter writer = new PdfWriter("C:\\Users\\azizh\\OneDrive\\Bureau\\pdf_hela\\devis.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            ImageData imageData = ImageDataFactory.create("http://localhost/images/en_tete1.png");
            Image image = new Image(imageData);

            document.add(image);
    System.out.println("PDF created successfully.");
            Table table = new Table(4);
                  table.addCell(new Cell().add(new Paragraph(" DEVIS N° " + n + ":")));
                  table.addCell(new Cell().add(new Paragraph("LES PANNES " + panne.getText())));
                  table.addCell(new Cell().add(new Paragraph("LES PRIX DES PANNES " + prix.getText())));
                   table.addCell(new Cell().add(new Paragraph("Taux taxe comprix (TVA 19%): \n" + TTC.getText())));
                  document.add(table);
        
            document.add(new Paragraph("Arretez la presente Piece apres une reduction de "+reduction.getText()+" % a la somme de : "+total.getText()+"DT"));

            ImageData imageData1 = ImageDataFactory.create("http://localhost/images/bas_page.png");
            Image image1 = new Image(imageData1);
            image1.setFixedPosition(pdf.getDefaultPageSize().getWidth()-700,5);
            document.add(image1);

            document.close();
            UserService us = new UserService();
            User u1 = us.readById(Data.getId_user());
            sm1.sendEmail(u1.getEmail(), "DEVIS!", "Voici votre devis du garage confirmé", "C:\\Users\\azizh\\OneDrive\\Bureau\\pdf_hela\\devis.pdf");

            MaintenanceController maintenaceController = loader.getController();
            maintenaceController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
        } catch (IOException ex) {
            Logger.getLogger(ItemDevisController.class.getName()).log(Level.SEVERE, null, ex);
        }
 








    }
}
