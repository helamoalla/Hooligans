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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.MessagingException;
import models.Categorie;
import models.Produit;
import org.controlsfx.control.Notifications;
import interfaces.MyListener;
import services.CategorieService;
import services.LignePanierService;
import services.PanierService;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class MarketController implements Initializable , MyListener {
     ProduitService ps=new ProduitService() ;
     LignePanierService lp=new LignePanierService();
       Connection cnx = Maconnexion.getInstance().getCnx();
     
     ObservableList<Produit> list = FXCollections.observableArrayList();
     List<Produit> listprod =new ArrayList();
     List<Produit> prod =new ArrayList();
      List<Produit> prodcat =new ArrayList();
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
    private TextField nom_prod;
    @FXML
    private TextField nompro;
    @FXML
    private ChoiceBox<String> choiceCP;

    /**
     * Initializes the controller class.
     */
     CategorieService catser = new CategorieService();
      
    
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
       CategorieService catser = new CategorieService();
      ArrayList <Categorie> liste = catser.readAll();
     
     ObservableList<Categorie> categories =FXCollections.observableArrayList(catser.readAll());
    
           for (Categorie c :categories){
           choiceCP.getItems().add(c.getNom_categorie());
           
           }
        
        afficherall();
      
      //   
    }
    public void afficherall(){
        
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
    
  
    PanierService panierser=new PanierService();
    @FXML
    private void ajouteraupanier(ActionEvent event) {
         try {
             // try {
             
             
             int quantiteprod=Integer.parseInt(quanti.getText());
             if (p.getQuantite()-quantiteprod>0){
                 lp.Create_LignePanier(p, quantiteprod);
                 String productName = p.getNom_prod();
                 Double a=panierser.totalmontantPanier(3);
                 System.out.println(a);
                 Notifications NotificationBuilder = Notifications.create()
                         .title("Reminder !! ")
                         .text("Votre panier costs now " + a).hideAfter(Duration.seconds(5))
                         .position(Pos.BOTTOM_RIGHT);
                 NotificationBuilder.show();}
             else {
                 
                 Notifications NotificationBuilder = Notifications.create()
                         .title("Desole ")
                         .text("notre produit " + p.getNom_prod()+"  Est en repture de stock").hideAfter(Duration.seconds(5))
                         .position(Pos.BOTTOM_RIGHT);
                 NotificationBuilder.show();}
             
             
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
             
//
if(nb<5){
               String productName = p.getNom_prod();
//              Notifications NotificationBuilder = Notifications.create()
//            .title("Attention !! ")
//            .text("Le stock de sécurite du produit   risque d'être achevé").hideAfter(Duration.seconds(5))
//                      .position(Pos.BOTTOM_RIGHT);
//              NotificationBuilder.show();
//
try {
    ps.sendEmail("balloumfarah@gmail.com", "Attention!!!!", " Le produit  "+productName+" risque d'être achevé");
} catch (MessagingException ex) {
    Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
}
System.out.println("rodbelek");
}



///////////////
         } catch (SQLException ex) {
             Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
         }}
      
    

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
             Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    }

    @FXML
    private void chercher(ActionEvent event) {
        prod.addAll(ps.chercher("nom_prod", nompro.getText()));
        setChosenFruit(prod.get(0));
       
        grid.getChildren().clear();
         int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < prod.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/Produit.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ProduitController prodController = fxmlLoader.getController();
                prodController.setData(prod.get(i),myListener);

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

    @FXML
    private void showall(ActionEvent event) {
       listprod.clear();
       afficherall();    
    }


    @FXML
    private void chercherparcat(MouseEvent event) {    
    }

    @FXML
    private void searchparcateg(ActionEvent event) {
        prodcat.clear();
        grid.getChildren().clear();
        Categorie c1 =catser.RetournerT(choiceCP.getSelectionModel().getSelectedItem());
      String a=Integer.toString(c1.getId_categorie());
         prodcat.addAll(ps.chercher("id_categorie", a));
         System.out.println(prodcat);
         
         
        
         int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < prodcat.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/Produit.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ProduitController prodController = fxmlLoader.getController();
                prodController.setData(prodcat.get(i),myListener);

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

    @FXML
    private void gotopanier(ActionEvent event) {
        
         try {
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./PanierInterface.fxml"));
             Parent view_2=loader.load();
             Scene scene = new Scene(view_2);
             Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(MarketController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }

  
}
