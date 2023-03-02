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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Produit;
import org.controlsfx.control.Notifications;
import pidev1.MyListener;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class MarketController implements Initializable , MyListener {
     ProduitService ps=new ProduitService() ;
       Connection cnx = Maconnexion.getInstance().getCnx();
     
     ObservableList<Produit> list = FXCollections.observableArrayList();
     List<Produit> listprod =new ArrayList();
    @FXML
    private Label nomproduit;
    @FXML
    private Label prixproduit;
    @FXML
    private ImageView imageproduit;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private MyListener myListener;
    @FXML
    private HBox chosenProduit;
    @FXML
    private Text desc;
    @FXML
    private TextField quanti;
    
    Produit p;

    /**
     * Initializes the controller class.
     */
    
     private void setChosenFruit(Produit p) {
         try {
             nomproduit.setText(p.getNom_prod());
             prixproduit.setText(p.getPrix_prod().toString()+"DT");
             URL imageUrl;
             
             imageUrl = new URL("http://localhost/images/"+p.getImage());
             Image images = new Image(imageUrl.toString());
             imageproduit.setImage(images);
             desc.setText(p.getDescription_prod());
             
             
            
         } catch (MalformedURLException ex) {
             Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //   
       listprod.addAll(ps.readAll());
         if (listprod.size() > 0) {
            setChosenFruit(listprod.get(1));
            myListener = new MyListener() {
               @Override
                public void onClickListener(Produit prod) {
                    setChosenFruit(prod);
                    p=prod;
                }
            };}
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < listprod.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/Produit.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ProduitController prodController = fxmlLoader.getController();
                prodController.setData(listprod.get(i),myListener);

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
            }   
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public void onClickListener(Produit prod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void ajoutaupanier(ActionEvent event,Produit p) {
       
        
    }

    @FXML
    private void ajouteraupanier(ActionEvent event) {
         try {
            /*int selectedId= listeprod.getSelectionModel().getSelectedItem().getId_prod();
            String selectednom= listeprod.getSelectionModel().getSelectedItem().getNom_prod();
            Double selectedprix=listeprod.getSelectionModel().getSelectedItem().getPrix_prod();
            String selecteddesc=listeprod.getSelectionModel().getSelectedItem().getDescription_prod();
            String selectedimage=listeprod.getSelectionModel().getSelectedItem().getImage();*/
            int quantiteprod=Integer.parseInt(quanti.getText());
            
            
            String req = "INSERT INTO `lignepanier`(`id_produit`, `id_panier`, `quantite`, `prix`, `nom_produit`, `description_prod`, `image`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,p.getId_prod());
            ps.setInt(2,3);
            ps.setInt(3,quantiteprod);
            ps.setDouble(4, p.getPrix_prod());
            ps.setString(5, p.getNom_prod());
            ps.setString(6,p.getDescription_prod());
            ps.setString(7, p.getImage());
            ps.executeUpdate();
            String req1 ="UPDATE produit SET quantite_prod = quantite_prod - ?  WHERE id_prod = ? ";
                    PreparedStatement ps1 = cnx.prepareStatement(req1);
                    ps1.setInt(1, quantiteprod);
                    ps1.setInt(2,p.getId_prod());
                    ps1.executeUpdate();
            System.out.println("ligne panier remplie Successfully!");
            
               ///////////////
            String req2 = "SELECT quantite_prod FROM produit WHERE id_prod = "+p.getId_prod();
            
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
             Logger.getLogger(ViewSuppCategorieController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
