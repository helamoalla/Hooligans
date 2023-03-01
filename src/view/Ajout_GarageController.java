/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Interfaces.InterfaceCRUD;
import Models.GarageC;
import Services.ServiceGarageC;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.ParseException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class Ajout_GarageController implements Initializable {

    InterfaceCRUD sg=new ServiceGarageC();
    @FXML
    private Button id_ajout;
    @FXML
    private TextField id_nom;
    @FXML
    private TextField id_adresse;
    @FXML
    private TextField id_num;
    @FXML
    private TextField id_panne_moteur;
    @FXML
    private TextField id_pompe_a_eau;
    @FXML
    private TextField id_patin;
    @FXML
    private TextField id_essuie_glace;
    @FXML
    private TextField id_radiateur;
    @FXML
    private TextField id_ventilateur;
    @FXML
    private TextField id_duride;
    @FXML
    private TextField id_fuite_d_huile;
    @FXML
    private TextField id_vidange;
    @FXML
    private TextField id_filtre;
    @FXML
    private TextField id_batterie;
    @FXML
    private TextField id_amortisseur;
    @FXML
    private TextField id_frain_main;
    @FXML
    private TextField id_feu_eclairage;
    @FXML
    private TextField id_reduction;
    @FXML
    private Label id_image;
    @FXML
    private ImageView image_view;
    @FXML
    private Button id_import_image;
    private File selectedFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter_garage(ActionEvent event) throws ParseException{
       
 
            GarageC g=new GarageC();
            

    // Vérifier que les champs ne sont pas vides
     if (id_nom.getText().length() == 0||id_adresse.getText().length() == 0||id_num.getText().length() ==0||id_panne_moteur.getText().length() == 0||id_pompe_a_eau.getText().length() ==0||id_patin.getText().length() ==0||id_essuie_glace.getText().length() ==0||id_radiateur.getText().length() ==0|| id_ventilateur.getText().length() == 0||id_duride.getText().length() == 0||id_fuite_d_huile.getText().length() == 0||id_vidange.getText().length() == 0||id_filtre.getText().length() == 0||id_batterie.getText().length() == 0||id_amortisseur.getText().length() == 0||id_frain_main.getText().length() == 0||id_feu_eclairage.getText().length() == 0||id_reduction.getText().length() == 0||id_image.getText().length()==0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("SVP remplir tous les champs"+ "");
            alert.show();
return;
        }
    if(id_num.getText().length() !=8)
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("numero de telephone incorrecte!!"+ "");
            alert.show();
            return;
        }
     
    try {
      int panne_moteur =Integer.parseInt(id_panne_moteur.getText());
      int pompe_a_eau =Integer.parseInt(id_pompe_a_eau.getText());
      int patin =Integer.parseInt(id_patin.getText());
      int essuie_glace =Integer.parseInt(id_essuie_glace.getText());
      int radiateur =Integer.parseInt(id_radiateur.getText());
      int ventilateur =Integer.parseInt(id_ventilateur.getText());
      int duride =Integer.parseInt(id_duride.getText());
      int fuite_d_huile =Integer.parseInt(id_fuite_d_huile.getText());
      int vidange =Integer.parseInt(id_vidange.getText());
      int filtre =Integer.parseInt(id_filtre.getText());
      int batterie =Integer.parseInt(id_batterie.getText());
      int amortisseur =Integer.parseInt(id_amortisseur.getText());
      int frain_main =Integer.parseInt(id_frain_main.getText());
      int feu_eclairage =Integer.parseInt(id_feu_eclairage.getText());
      int reduction =Integer.parseInt(id_reduction.getText());
     if (panne_moteur <= 0 ||pompe_a_eau <=0 ||patin <=0 ||essuie_glace <=0 ||radiateur <=0 || ventilateur <=0 || duride <=0 || fuite_d_huile<=0 || vidange <=0 || filtre <=0 || batterie<=0 || amortisseur<=0 ||frain_main <=0 ||feu_eclairage<=0 || reduction<=0) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le prix doit être supérieur à zéro.");
            alert.showAndWait();
            return;
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le prix doit être un nombre");
        alert.showAndWait();
        return;
    }
     
    
             g.setNom_garage(id_nom.getText());
            g.setAdresse(id_adresse.getText());
            g.setNumero(Integer.parseInt(id_num.getText()));
            g.setPanne_moteur(Integer.parseInt(id_panne_moteur.getText()));
            g.setPompe_a_eau(Integer.parseInt(id_pompe_a_eau.getText()));
            g.setPatin(Integer.parseInt(id_patin.getText()));
            g.setEssuie_glace(Integer.parseInt(id_essuie_glace.getText()));
            g.setRadiateur(Integer.parseInt(id_radiateur.getText()));
            g.setVentilateur(Integer.parseInt(id_ventilateur.getText()));
            g.setDuride(Integer.parseInt(id_duride.getText()));
            g.setFuite_d_huile(Integer.parseInt(id_fuite_d_huile.getText()));
            g.setVidange(Integer.parseInt(id_vidange.getText()));
            g.setFiltre(Integer.parseInt(id_filtre.getText()));
            g.setBatterie(Integer.parseInt(id_batterie.getText()));
            g.setAmortisseur(Integer.parseInt(id_amortisseur.getText()));
            g.setFrein_main(Integer.parseInt(id_frain_main.getText()));
            g.setFeu_d_eclairage(Integer.parseInt(id_feu_eclairage.getText()));
            g.setTaux_de_reduction(Integer.parseInt(id_reduction.getText()));
            g.setImage(id_image.getText());
            String htdocsPath = "C:/xampp/htdocs/images/";
                 File destinationFile = new File(htdocsPath + id_image.getText().replaceAll("\\s+", ""));
            if(selectedFile!=null){
                try (InputStream in = new FileInputStream(selectedFile);
                 OutputStream out = new FileOutputStream(destinationFile)) {
                byte[] buf = new byte[8192];
                int length;
                while ((length = in.read(buf)) > 0) {
                    out.write(buf, 0, length);
                }
             
            sg.insert(g);
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./affichageGarage.fxml"));
            Parent view_2=loader.load();
    
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
                   } catch (IOException ex) {
                ex.printStackTrace();
            }
          
     }  else{
                System.out.println("selected file is null "+selectedFile);
            }
     }
    
    
    
        
     
    

    @FXML
    private void importer_image(ActionEvent event) {
       FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.JPG", "*.gif"));
          fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
         if (selectedFile != null) {
                id_image.setText(selectedFile.getName().replaceAll("\\s+", ""));
                 try {
                Image images = new Image("file:"+selectedFile.getPath().toString());
                image_view.setImage(images);
                System.out.println(selectedFile.getPath().toString());
        } catch (Exception ex) {
                     System.out.println(ex);
        }
                
            }
    }
    }
    

