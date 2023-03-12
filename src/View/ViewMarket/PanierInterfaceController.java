/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.ViewMarket;

import Util.Data;
import Util.MyConnection;
import Interface.MyListenerP;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import Model.LignePanier;
import Service.LignePanierService;
import Service.PanierService;
import Service.ProduitService;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class PanierInterfaceController implements Initializable , MyListenerP {
     private LignePanier lp ; 
    Connection cnx = MyConnection.getInstance().getCnx();
    ObservableList<LignePanier> list = FXCollections.observableArrayList();
    List<LignePanier> listprod =new ArrayList();
    LignePanierService lps = new LignePanierService();
    @FXML
    private Label nom;
    @FXML
    private ImageView image;
    @FXML
    private Label description;
    @FXML
    private Label quantite;
    @FXML
    private Label total;
    @FXML
    private Button btnplus;
    @FXML
    private Button btnmoins;
    @FXML
    private Button btnsupprimer;
    @FXML
    private GridPane grid;
    @FXML
    private HBox chosenProduit;
    private MyListenerP myListener;
    @FXML
    private ImageView home;
    @FXML
    private TextField tfmontanttotal;
    
private PanierService ps = new PanierService();
    private BorderPane borderPane;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        //afficherpanier();
        //Calculer le monatant total d'un panier par id_user
       tfmontanttotal.setText(String.valueOf(ps.totalmontantPanier(Data.getId_user())));
    }  
            public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
            afficherpanier();}
    
    
    public void setChosenLignePanier(LignePanier lp){
        try {
            this.lp = lp;
            nom.setText(lp.getProduit().getNom_prod());
            
            URL imageUrl;
            
            imageUrl = new URL("http://localhost/images/"+lp.getProduit().getImage());
             Image images = new Image(imageUrl.toString());
            image.setImage(images);
            description.setText(lp.getProduit().getDescription_prod());
            quantite.setText(String.valueOf(lp.getQuantite()));
            total.setText(String.valueOf(lp.getProduit().getPrix_prod()*lp.getQuantite()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(PanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

     
    
    public void afficherpanier(){
    /****************Afficher panier by id_user***********************/
    
        listprod.addAll(lps.AfficherPanierbyiduser(Data.getId_user()));
      
        //System.out.println(listprod);
         if (listprod.size() > 0) {
            setChosenLignePanier(listprod.get(0));
             myListener = new MyListenerP() {
               @Override
                public void onClickListener(LignePanier lignepanier) {
                    setChosenLignePanier(lignepanier);
                    lp=lignepanier;
                }
            };}
        int column = 0;
        int row = 1;
            for (int i = 0; i < listprod.size(); i++) {
            try {
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("./LignePanier.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                LignePanierController p = fxmlLoader.getController();
                p.setData(listprod.get(i),myListener);
                
            p.setBorderPane(borderPane);
//            borderPane.setCenter(null);
//            borderPane.setCenter(anchorPane);

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
    }

    @Override
    public void onClickListener(LignePanier lignepanier) {
    }

    @FXML
    private void marketplace(MouseEvent event) {
    /****************Afficher l'interface market***********************/
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./market.fxml"));
            Parent view_2=loader.load();
            MarketController i =loader.getController();
            
                
            i.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
        } catch (IOException ex) {
            Logger.getLogger(PanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Plus1Qt(MouseEvent event) throws IOException {
         try {
             /****************Ajouter +1 de la quantité du produit par id_panier et id_produit***********************/
             
             lps.QantitePlus1(lp.getPanier().getId_panier(), lp.getProduit().getId_prod());
             //Rafraichir le contenu de l'interface
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./PanierInterface.fxml"));
             Parent view_2=loader.load();
             PanierInterfaceController i =loader.getController();
                
            i.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
         } catch (SQLException ex) {
             Logger.getLogger(PanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
         }
       

    }

    @FXML
    private void Moins1Qt(MouseEvent event) throws IOException {                
         try {
             /****************Soustraire -1 de la quantité du produit par id_panier et id_produit***********************/
             lps.Qantitemoins1(lp.getPanier().getId_panier(), lp.getProduit().getId_prod());
             //Rafraichir le contenu de l'interface
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./PanierInterface.fxml"));
             Parent view_2=loader.load();
             PanierInterfaceController i =loader.getController();
                
            i.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
         } catch (SQLException ex) {
             Logger.getLogger(PanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void SupprimerDuPanier(MouseEvent event) throws IOException {  
        /****************Supprimer produit du panier par id_panier et id_produit***********************/
        lps.SupprimerProduit_de_LignePanier(lp.getPanier().getId_panier(), lp.getProduit().getId_prod());
        //Rafraichir le contenu de l'interface
           FXMLLoader loader= new FXMLLoader(getClass().getResource("./PanierInterface.fxml"));
            Parent view_2=loader.load();
           PanierInterfaceController i =loader.getController();
            
                
            i.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Produit supprimé du panier avec succés");
            alert.show();
            
    }

    @FXML
    private void ViderPanier(MouseEvent event) throws IOException {
        /****************Vider panier by id_user***********************/
        lps.ViderLigne_panier(lp.getPanier().getId_panier());
        //Rafraichir le contenu de l'interface
           FXMLLoader loader= new FXMLLoader(getClass().getResource("./PanierInterface.fxml"));
            Parent view_2=loader.load();
           PanierInterfaceController i =loader.getController();
            
                
            i.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Panier vidé avec succés");
            alert.show();
    }

    @FXML
    private void passercommande(ActionEvent event) {
         try {
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./PasserCommande.fxml"));
             Parent view_2=loader.load();
             PasserCommandeController i =loader.getController();
             
                
            i.setBorderPane(borderPane,tfmontanttotal.getText());
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
         } catch (IOException ex) {
             Logger.getLogger(PanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @FXML
    private void consucomm(ActionEvent event) {
         try {
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./MesCommandesInterfaces.fxml"));
             Parent view_2=loader.load();
            MesCommandesInterfacesController i =loader.getController();
             
             i.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(view_2);
         } catch (IOException ex) {
             Logger.getLogger(PanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

 
  
}
