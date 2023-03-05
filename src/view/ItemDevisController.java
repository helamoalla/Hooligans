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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
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
    ServiceDevis sd = new ServiceDevis();
    InterfaceCRUD sg = new ServiceGarageC();
    @FXML
    private Button confirmer;
    @FXML
    private Label id_devis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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

        if (this.m.isAmortisseur() == true) {
            p = p + " " + String.valueOf(g.getAmortisseur());
            a = a + "Amortisseur ";
        }
        if (this.m.isBatterie() == true) {
            p = p + " " + String.valueOf(g.getBatterie());
            a = a + " Batterie ";
        }
        if (this.m.isDuride() == true) {
            p = p + " " + String.valueOf(g.getDuride());
            a = a + " Duride ";
        }
        if (this.m.isEssuie_glace() == true) {
            p = p + " " + String.valueOf(g.getEssuie_glace());
            a = a + " Essuie_glace ";
        }
        if (this.m.isFeu_d_eclairage() == true) {
            p = p + " " + String.valueOf(g.getFeu_d_eclairage());
            a = a + " Feu_d_eclairage ";
        }
        if (this.m.isFiltre() == true) {
            p = p + " " + String.valueOf(g.getFiltre());
            a = a + " Filtre ";
        }
        if (this.m.isFrein_main() == true) {
            p = p + " " + String.valueOf(g.getFrein_main());
            a = a + " Frein_main ";
        }
        if (this.m.isFuite_d_huile() == true) {
            p = p + " " + String.valueOf(g.getFuite_d_huile());
            a = a + " Fuite_d_huile ";
        }
        if (this.m.isPanne_moteur() == true) {
            p = p + " " + String.valueOf(g.getPanne_moteur());
            a = a + " Panne_moteur ";
        }
        if (this.m.isPatin() == true) {
            p = p + " " + String.valueOf(g.getPatin());
            a = a + " Patin ";
        }
        if (this.m.isPompe_a_eau() == true) {
            p = p + " " + String.valueOf(g.getPompe_a_eau());
            a = a + " Pompe_a_eau ";
        }
        if (this.m.isRadiateur() == true) {
            p = p + " " + String.valueOf(g.getRadiateur());
            a = a + " Radiateur ";
        }
        if (this.m.isVentilateur() == true) {
            p = p + " " + String.valueOf(g.getVentilateur());
            a = a + " Ventilateur ";
        }
        if (this.m.isVidange() == true) {
            p = p + " " + String.valueOf(g.getVidange());
            a = a + " Vidange ";
        }
        panne.setText(a);
        prix.setText(p);
        total.setText(String.valueOf(d.getTotal()));
        reduction.setText(String.valueOf(g.getTaux_de_reduction()));
        TTC.setText(String.valueOf(TTC1));

    }

    @FXML
    private void confirmer(ActionEvent event) throws DocumentException {
        
        
//        try {
//            FXMLLoader loader= new FXMLLoader(getClass().getResource("./modifier_Garage.fxml"));
//            Parent view_2=loader.load();
//            
//              String file_name = ("C:\\Users\\helam\\Documents\\NetBeansProjects\\PIDEV\\src\\pdf\\pdf.pdf");
//        
//        Document doc = new Document();
//        PdfWriter.getInstance(doc, new FileOutputStream(file_name));
//        doc.open();
//        System.out.println("PDF created successfully.");
//        doc.add(new Paragraph("Votre DEVIS :"));
//        doc.add(new Paragraph("PANNE : "+id_devis.getSelectionModel().getSelectedItem().getId_commande()));
//        doc.add(new Paragraph("PRIX : "));
//        doc.add(new Paragraph("Taux taxe comprix : "));
//        doc.add(new Paragraph("somme apres reduction : "));
//        doc.close();
//    
//            
//            
//            
//            Modifier_GarageController Modifier_GarageController=loader.getController();
//            Modifier_GarageController.getGarage((GarageC) sd.readById(Integer.valueOf(id_devis.getText())));
//            Modifier_GarageController.g=(GarageC) sd.readById(Integer.valueOf(id_devis.getText()));
//            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(view_2);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(ItemDevisController.class.getName()).log(Level.SEVERE, null, ex);
//        }
   
    }
}
