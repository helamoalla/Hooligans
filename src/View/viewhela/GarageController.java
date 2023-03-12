/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.viewhela;

import Interface.InterfaceCRUD;
import Model.GarageC;
import Service.ServiceGarageC;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class GarageController implements Initializable {
InterfaceCRUD sg=new ServiceGarageC();
    @FXML
    private ListView<GarageC> id_list;
    @FXML
    private Button id_afficher;
    @FXML
    private Button id_suppr;
    @FXML
    private Button id_ajout;
    @FXML
    private Button id_modifier;
    @FXML
    private Button id_retour;
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
          public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getAllGarage();
    }    
public void getAllGarage(){
        ObservableList<GarageC> garage = FXCollections.observableArrayList(sg.readAll());
        id_list.setItems(garage);
         
         
          id_list.setCellFactory(param -> new ListCell<GarageC>() {
            private final ImageView imageView = new ImageView();
            private final Text nom_garage = new Text();
            private final Text adresse = new Text();
            private final Text numero = new Text();
      
          
            
            
            private final HBox hbox = new HBox(100,imageView,nom_garage,adresse,numero);
            //private final HBox hbox2 = new HBox(200,imageView,nom,adresse,type,etat);
            
            {
                imageView.setFitWidth(75);
                imageView.setFitHeight(75);
            }

            @Override
            protected void updateItem(GarageC item, boolean empty) {
                URL imageUrl;
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                         nom_garage.setText(item.getNom_garage());
                         adresse.setText(item.getAdresse());
    numero.setText(String.valueOf(item.getNumero()));
   
                      //  System.out.println(item.getImage());
                        imageUrl = new URL("http://localhost/images/"+item.getImage());
                        //System.out.println(imagehttp://localhost/images/Url);
                        Image images = new Image(imageUrl.toString());
                        imageView.setImage(images);
                        setText(null);
                        setGraphic(hbox);
                    } catch (MalformedURLException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
    }
    @FXML
    private void afficher_garage(ActionEvent event) {
getAllGarage();
    }

    @FXML
    private void supprimer_Garage(ActionEvent event) {

        int selectedId= id_list.getSelectionModel().getSelectedItem().getId_garage();
        sg.delete(selectedId);
         afficher_garage(event);
      
        
    }

    @FXML
    private void ajouter_garage(ActionEvent event) {
    try {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./Ajout_Garage.fxml"));
        Parent view_2=loader.load();
        
            Ajout_GarageController ajouterGarageController = loader.getController();
            ajouterGarageController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
    } catch (IOException ex) {
        Logger.getLogger(GarageController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void modifier_garage(ActionEvent event) {
    try {
        GarageC selectedGarage=id_list.getSelectionModel().getSelectedItem();
        
        
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./modifier_Garage.fxml"));
        Parent view_2=loader.load();
        Modifier_GarageController Modifier_GarageController=loader.getController();
        Modifier_GarageController.getGarage(selectedGarage);
        Modifier_GarageController.g=selectedGarage;
                    
            Modifier_GarageController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
    } catch (IOException ex) {
        Logger.getLogger(GarageController.class.getName()).log(Level.SEVERE, null, ex);
    }
   
    }

    @FXML
    private void retour(ActionEvent event) {
         try{
         FXMLLoader loader= new FXMLLoader(getClass().getResource("./GESTION.fxml"));
        Parent view_2=loader.load();
        
                    GESTIONController gestionController = loader.getController();
            gestionController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
    } catch (IOException ex) {
        Logger.getLogger(GESTIONController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
