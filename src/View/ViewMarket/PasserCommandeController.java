/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.ViewMarket;

import Util.Data;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Model.Commande;
import Model.LignePanier;
import Model.Panier;
import Service.CommandeService;
import Service.LignePanierService;
import Service.PanierService;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class PasserCommandeController implements Initializable {

   @FXML
    private VBox sideArea;
    @FXML
    private HBox sideControls;
    @FXML
    private Label closeButton;
    @FXML
    private VBox sideNavPanier;
    @FXML
    private Pane handPaneMac;
    @FXML
    private AnchorPane root;
    @FXML
    private Region Accueil;
    @FXML
    private Region MonPanier2;
    @FXML
    private Button btnConfirmer;
    @FXML
    private TextField tfcodepostal;
    @FXML
    private TextField tfgouvernorat;
    @FXML
    private TextField tfville;
    @FXML
    private TextField tfrue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    private void ViderPanier() {
        PanierService p =new PanierService();
        LignePanierService lps = new LignePanierService();
        lps.ViderLigne_panier(Data.getId_user());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Panier vidé avec succés");
            alert.show();
    }

    @FXML
    private void FenetreAccueil(MouseEvent event) {
      
       try {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("./market.fxml"));
           Parent view_2=loader.load();
           MarketController i =loader.getController();
           Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
           Scene scene = new Scene(view_2);
           stage.setScene(scene);
           stage.show();
       } catch (IOException ex) {
           Logger.getLogger(PasserCommandeController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }

    @FXML
    private void FenetrePanier(MouseEvent event) {
       
       try {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("./PanierInterface.fxml"));
           Parent view_2=loader.load();
           PanierInterfaceController i =loader.getController();
           Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
           Scene scene = new Scene(view_2);
           stage.setScene(scene);
           stage.show();
       } catch (IOException ex) {
           Logger.getLogger(PasserCommandeController.class.getName()).log(Level.SEVERE, null, ex);
       }
     
    }
    LignePanierService lp =new LignePanierService(); 
    @FXML
    private void ConfirmerCommande(MouseEvent event) throws FileNotFoundException, DocumentException, SQLException, IOException {
       
        CommandeService cs = new CommandeService();
        PanierService ps = new PanierService();
         Panier p = new Panier();   
        
        if(tfgouvernorat.getText().length() == 0||tfville.getText().length() == 0||tfrue.getText().length() == 0||tfcodepostal.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Veuillez remplir tous les champs"+ "");
            alert.show(); 
           }
        else if(tfgouvernorat.getText().matches("\\d*")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Le gouvernorat doit doit etre une chaine"+ "");
            alert.show(); 
    }
                else if(tfville.getText().matches("\\d*")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("La ville doit doit etre une chaine"+ "");
            alert.show(); 
    }
         else if(tfrue.getText().matches("\\d*")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("La rue doit doit etre une chaine"+ "");
            alert.show(); 
    }
                
        
        else if(!tfcodepostal.getText().matches("\\d*")||tfcodepostal.getText().length()!=4){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Le code postal doit etre un nombre de 4 chiffres"+ "");
            alert.show(); 
    }
  
            else { 
            
            if(ps.totalproduitParPanier(Data.getId_user())>10){
                        String gov= tfgouvernorat.getText();
                  String ville= tfville.getText();
                  String rue= tfrue.getText();
                  int codep= Integer.parseInt(tfcodepostal.getText());
                  Commande c = new Commande();
                c.setPanier( p = ps.readById(Data.getId_user()));
                c.setMontant(ps.totalmontantPanierAvec10Discount(Data.getId_user()));
                c.setEtat_commande("En cours de traitement");
                c.setGouvernorat(gov);
                c.setVille(ville);
                c.setRue(rue);
                c.setCode_postal(codep);
                cs.insert(c);
           
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./PanierInterface.fxml"));
           Parent view_2=loader.load();
           PanierInterfaceController i =loader.getController();
           Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
           Scene scene = new Scene(view_2);
           stage.setScene(scene);
           stage.show();
         
            }
            else{
                  String gov= tfgouvernorat.getText();
                  String ville= tfville.getText();
                  String rue= tfrue.getText();
                  int codep= Integer.parseInt(tfcodepostal.getText());
                  Commande c = new Commande();
                c.setPanier( p = ps.readById(Data.getId_user()));
                c.setMontant(ps.totalmontantPanier(Data.getId_user()));
                c.setEtat_commande("En cours de traitement");
                c.setGouvernorat(gov);
                c.setVille(ville);
                c.setRue(rue);
                c.setCode_postal(codep);
                cs.insert(c);
           
//            lp.ViderLigne_panier(Data.getId_user());
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./PanierInterface.fxml"));
           Parent view_2=loader.load();
           PanierInterfaceController i =loader.getController();
           Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
           Scene scene = new Scene(view_2);
           stage.setScene(scene);
           stage.show();
    
            }
    }
        
        
    }
}
