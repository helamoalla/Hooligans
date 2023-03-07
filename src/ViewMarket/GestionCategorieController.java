package ViewMarket;

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
import javafx.scene.control.Alert;
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
import interfaces.MyListener1;
import services.CategorieService;
import services.LignePanierService;
import services.PanierService;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class GestionCategorieController implements Initializable , MyListener1 {
     CategorieService ps=new CategorieService() ;
     
     LignePanierService lp=new LignePanierService();
     Connection cnx = Maconnexion.getInstance().getCnx();
     ObservableList<Categorie> list = FXCollections.observableArrayList();
     List<Categorie> listcateg =new ArrayList();
     List<Categorie> categ =new ArrayList();
      List<Categorie> prodcat =new ArrayList();
    @FXML
    private Label nomproduit;
   
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
   
    @FXML
    private HBox chosenProduit;
    @FXML
    private Text desc;
   
    MyListener1 myListener ;
    Categorie c;
    private TextField nom_prod;
    @FXML
    private TextField nompro;
    @FXML
    private ChoiceBox<String> choiceCP;

    /**
     * Initializes the controller class.
     */
     CategorieService catser = new CategorieService();
    @FXML
    private ImageView imagecategorie;
   
      
    
     private void setChosenCategorie(Categorie p) {
         nomproduit.setText(p.getNom_categorie());
         
         
         desc.setText(p.getDescription_categorie());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       CategorieService catser = new CategorieService();
      ArrayList <Categorie> liste = catser.readAll();
       
     
     
        
        afficherall();
      
      //   
    }
    public void afficherall(){
       
       listcateg.addAll(ps.readAll());
       
         if (listcateg.size() > 0) {
            setChosenCategorie(listcateg.get(1));
            myListener = new MyListener1() {
               @Override
                public void onClickListener(Categorie ca) {
                    setChosenCategorie(ca);
                    c=ca;
                }
                
            };}
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < listcateg.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ViewMarket/Categorie.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CategorieController catController = fxmlLoader.getController();
                catController.setData(listcateg.get(i),myListener);

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
    private void home(ActionEvent event) {
        
         try {
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./HomePage.fxml"));
             Parent view_2=loader.load();
             Scene scene = new Scene(view_2);
             Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(GestionCategorieController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }

    @FXML
    private void chercher(ActionEvent event) {
        categ.addAll(ps.chercher("nom_categorie", nompro.getText()));
        setChosenCategorie(categ.get(0));
       
        grid.getChildren().clear();
         int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < categ.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ViewMarket/Categorie.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CategorieController prodController = fxmlLoader.getController();
                prodController.setData(categ.get(i),myListener);

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
       listcateg.clear();
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
                fxmlLoader.setLocation(getClass().getResource("/ViewMarket/Categorie.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CategorieController prodController = fxmlLoader.getController();
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
    private void supp(ActionEvent event) {
        grid.getChildren().clear();
        
        ps.delete(c.getId_categorie());
         listcateg.clear();
        afficherall();
    }

    @FXML
    private void modif(ActionEvent event) {
         try {
        
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./ViewUpdateCategorie.fxml"));
            Parent view_1=loader.load();
            ViewUpdateCategorieController updateproduitController=loader.getController();
            updateproduitController.getCategorie(c);
            updateproduitController.c=c;
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(view_1);
            stage.setScene(scene);
            stage.show();
             
        } catch (Exception ex) {
            System.out.println(ex);   
    }
    }

    @FXML
    private void ajout(ActionEvent event) {
        
         try {
             FXMLLoader loader= new FXMLLoader(getClass().getResource("./ViewAjoutCategorie.fxml"));
             Parent view_2=loader.load();
             ViewAjoutCategorieController ajoutproduit=loader.getController();
             Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
             Scene scene = new Scene(view_2);
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(GestionCategorieController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }

    @Override
    public void onClickListener(Categorie cat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
  
  
}