/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.ViewMarket;

import Util.MyConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import Model.Categorie;
import Model.Produit;
import org.controlsfx.control.Notifications;
import Service.CategorieService;
import Service.LignePanierService;
import Service.ProduitService;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class ViewAfficherProduitUserController implements Initializable {
    Connection cnx = MyConnection.getInstance().getCnx();
 ProduitService produitservice=new ProduitService() ;
   CategorieService categorieservice =new CategorieService() ;
   LignePanierService lignepanier =new LignePanierService();

    @FXML
    private ListView<Produit> listeprod;
    @FXML
    private ListView<Categorie> listcategorie;
    @FXML
    private TextField quantite;
    private BorderPane borderPane;
 

    /**
     * Initializes the controller class.
     */
            public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
            getAllProduits();}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //getAllProduits();
            ObservableList<Categorie> categories = FXCollections.observableArrayList(categorieservice.readAll());
        listcategorie.setItems(categories);
         
         
          listcategorie.setCellFactory(param -> new ListCell<Categorie>() {
       
            private final Text nom = new Text();
           
            
             
            
            private final HBox hbox = new HBox(100,nom);
            //private final HBox hbox2 = new HBox(200,imageView,nom,adresse,type,etat);
            
           

            protected void updateItem(Categorie item, boolean empty) {
                URL imageUrl;
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    nom.setText(item.getNom_categorie());
                    setText(null);
                    setGraphic(hbox);
                }
            }
        }); 
    }    
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
           
            
             
            
            private final HBox hbox = new HBox(100,imageView,nom,description);
            //private final HBox hbox2 = new HBox(200,imageView,nom,adresse,type,etat);
            
            {
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
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
    private void home(ActionEvent event) {
    }

    @FXML
    
private void ajoutaupanier(ActionEvent event) {
        try {
            int selectedId= listeprod.getSelectionModel().getSelectedItem().getId_prod();
            String selectednom= listeprod.getSelectionModel().getSelectedItem().getNom_prod();
            Double selectedprix=listeprod.getSelectionModel().getSelectedItem().getPrix_prod();
            String selecteddesc=listeprod.getSelectionModel().getSelectedItem().getDescription_prod();
            String selectedimage=listeprod.getSelectionModel().getSelectedItem().getImage();
            int quantiteprod=Integer.parseInt(quantite.getText());
            
            
            String req = "INSERT INTO `lignepanier`(`id_produit`, `id_panier`, `quantite`, `prix`, `nom_produit`, `description_prod`, `image`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,selectedId);
            ps.setInt(2,3);
            ps.setInt(3,quantiteprod);
            ps.setDouble(4, selectedprix);
            ps.setString(5, selectednom);
            ps.setString(6,selecteddesc);
            ps.setString(7, selectedimage);
            ps.executeUpdate();
            String req1 ="UPDATE produit SET quantite_prod = quantite_prod - ?  WHERE id_prod = ? ";
                    PreparedStatement ps1 = cnx.prepareStatement(req1);
                    ps1.setInt(1, quantiteprod);
                    ps1.setInt(2,selectedId);
                    ps1.executeUpdate();
            System.out.println("ligne panier remplie Successfully!");
            
               ///////////////
            String req2 = "SELECT quantite_prod FROM produit WHERE id_prod = "+selectedId;
            
            Statement st = cnx.createStatement();
              ResultSet rs=st.executeQuery(req2);
              rs.beforeFirst();
              rs.next();
              int nb =rs.getInt("quantite_prod");
        
             //PreparedStatement ps2 = cnx.prepareStatement(req2);
                 //ps2.setInt(1,selectedId );
                 //ResultSet rs = ps2.executeQuery(req2);
                 System.out.println(nb);
                 
          
            if(nb<5){
              Notifications NotificationBuilder = Notifications.create()
            .title("Attention !! ")
            .text("Le stock de sécurite risque d'être achevé").hideAfter(Duration.seconds(5))
                      .position(Pos.BOTTOM_RIGHT);
              NotificationBuilder.show();
            }
    
            
            
            /////////////////
        } catch (SQLException ex) {
            Logger.getLogger(ViewAfficherProduitUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

  
   
    
}
