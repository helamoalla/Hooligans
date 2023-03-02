/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Util.Maconnexion;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Categorie;
import models.Produit;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class ViewSuppProduitController implements Initializable {
    Connection cnx = Maconnexion.getInstance().getCnx();
    ProduitService produitservice=new ProduitService() ;
    @FXML
    private ListView<Produit> listeprod;
    @FXML
    private TextField quantiteajoute;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getAllProduits();
    }    

    @FXML
    private void modifprod(ActionEvent event) {
          Produit selectedProduit= listeprod.getSelectionModel().getSelectedItem();
        
        try {
        
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./ViewUpdateProduit.fxml"));
            Parent view_1=loader.load();
            ViewUpdateProduitController updateproduitController=loader.getController();
            updateproduitController.getProduit(selectedProduit);
            updateproduitController.p=selectedProduit;
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_1);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex);   
    }}

    @FXML
    private void suppprod(ActionEvent event) {
         int selectedId= listeprod.getSelectionModel().getSelectedItem().getId_prod();
        produitservice.delete(selectedId);
        getAllProduits();
    }

    @FXML
    private void home(ActionEvent event) {
           try {
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./HomePage.fxml"));
             Parent view_2=loader.load();
             Scene scene = new Scene(view_2);
             Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(ViewSuppProduitController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void ajoutprod(ActionEvent event) {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./ViewAjoutProduit.fxml"));
        
         try {
             Parent view_2=loader.load();
             ViewAjoutProduitController ajoutproduit=loader.getController();
             
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_2);
            stage.setScene(scene);
            stage.show();
         } catch (IOException ex) {
             Logger.getLogger(ViewSuppCategorieController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void afficheprod(ActionEvent event) {
       // ObservableList <Produit> produits =FXCollections.observableArrayList(produitservice.readAll());
       //listeprod.setItems(produits);
       getAllProduits();
    }
    public void getAllProduits(){
        ObservableList<Produit> produits = FXCollections.observableArrayList(produitservice.readAll());
        listeprod.setItems(produits);
         
         
          listeprod.setCellFactory(param -> new ListCell<Produit>() {
            private final ImageView imageView = new ImageView();
            private final Text nom = new Text();
            private final Text description = new Text();
            private final Text prix=new Text();
            private final Text quantite=new Text();
            private final Text nomcat=new Text();
            
            
            private final HBox hbox = new HBox(100,imageView,nom,description,prix,quantite,nomcat);
            //private final HBox hbox2 = new HBox(200,imageView,nom,adresse,type,etat);
            
            {
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);
                hbox.setAlignment(Pos.CENTER_LEFT);
                hbox.setPrefWidth(500);
                nom.setWrappingWidth(100);
                quantite.setWrappingWidth(30);
                description.setWrappingWidth(195);
                prix.setWrappingWidth(30);
                nomcat.setWrappingWidth(100);
            }

            @Override
            protected void updateItem(Produit item, boolean empty) {
                URL imageUrl;
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                        nom.setText(item.getNom_prod());
                        description.setText(item.getDescription_prod());
                        prix.setText(Double.toString(item.getPrix_prod()));
                        quantite.setText(Integer.toString(item.getQuantite()));  
                        nomcat.setText(item.getCategorie().getNom_categorie());
                        imageUrl = new URL("http://localhost/images/"+item.getImage());
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
    private void augmenterstock(ActionEvent event) {
        try {
            int selectedId= listeprod.getSelectionModel().getSelectedItem().getId_prod();
            String req ="UPDATE produit SET quantite_prod = quantite_prod + ?  WHERE id_prod = ? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, Integer.parseInt(quantiteajoute.getText()));
            ps.setInt(2, selectedId);
            ps.executeUpdate();
            System.out.println("Quantity updated successfully !");
        } catch (SQLException ex) {
            Logger.getLogger(ViewSuppProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
