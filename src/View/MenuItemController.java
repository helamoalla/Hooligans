/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package View;

import Service.UserService;
import Util.Data;
import View.View.EventuserController;
import View.View.Test1Controller;
import View.ViewMarket.GestionCategorieController;
import View.ViewMarket.GestionProduitController;
import View.ViewMarket.HomePageController;
import View.ViewMarket.MarketController;
import View.viewhela.AffichageGarageController;
import View.viewhela.Afficher_MaintenanceController;
import View.viewhela.GESTIONController;
import View.viewhela.MaintenanceController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import driftrace.ConnecterController;
import driftrace.ModifierMdpController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author azizh
 */
public class MenuItemController implements Initializable {

    @FXML
    private JFXButton goback;
    
    private BorderPane borderPane;
    @FXML
    private JFXButton BonPlanLink;
    @FXML
    private JFXButton AddBonPlanLink;
    @FXML
    private JFXButton Hiiii;
    @FXML
    private VBox btns;
    
    private boolean isVboxVisible = false;
    @FXML
    private JFXButton user;
    @FXML
    private ImageView userImg;
    UserService userService=new UserService();
    @FXML
    private JFXButton users;
    @FXML
    private JFXButton events;
    @FXML
    private JFXButton GarageC;
    @FXML
    private JFXButton Market;
    @FXML
    private JFXButton Product;
    @FXML
    private JFXButton Categorie;
    @FXML
    private JFXButton Maintenance;
    @FXML
    private JFXButton modifierMdp;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(userService.readById(Data.getId_user()).getRole().getId_role()!=1){
            users.setVisible(false);
            users.setManaged(false);
            Product.setVisible(false);
            Product.setManaged(false);
            Categorie.setVisible(false);
            Categorie.setManaged(false);
            Maintenance.setVisible(false);
            Maintenance.setManaged(false);
        }
        if(userService.readById(Data.getId_user()).getRole().getId_role()==1){
            Market.setVisible(false);
            Market.setManaged(false);
           
        }
        
        Image imgPic= new Image("http://localhost/images/"+userService.readById(Data.getId_user()).getImg());
        userImg.setImage(imgPic);
        userImg.setFitHeight(46);
        userImg.setFitWidth(46);
        Circle clip = new Circle(userImg.getFitWidth()/2, userImg.getFitHeight()/2, userImg.getFitWidth()/2);
        userImg.setClip(clip);
        user.setText("  "+userService.readById(Data.getId_user()).getNom());
        btns.setVisible(isVboxVisible);
        
    }    
        public void setBorderPane(BorderPane borderPane) {
        try {
            this.borderPane = borderPane;
            Parent newView = FXMLLoader.load(getClass().getResource("Home.fxml"));
            borderPane.setCenter(null);
            borderPane.setCenter(newView);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoBack(ActionEvent event) {
        try {
           
            Parent newView = FXMLLoader.load(getClass().getResource("Home.fxml"));
            borderPane.setCenter(null);
            borderPane.setCenter(newView);
        } catch (IOException ex) {
            Logger.getLogger(AjouterBonPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void bonPlanLink(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BonPlan.fxml"));
            Parent AllBonPlans = loader.load();            
            FXMLController fxmlController = loader.getController();
            fxmlController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(AllBonPlans);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   /* private void feedBackLink(ActionEvent event) {
        try {
            Parent newView = FXMLLoader.load(getClass().getResource("Feedback.fxml"));
            borderPane.setCenter(null);
            borderPane.setCenter(newView);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    @FXML
    private void addBonPlanLink(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterBonPlan.fxml"));
            Parent ajouterBonPlan = loader.load();            
            AjouterBonPlanController ajouterController = loader.getController();
            ajouterController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(ajouterBonPlan);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showbtns(ActionEvent event) {
        if (!isVboxVisible) {
                    // Show the VBox
                    btns.setVisible(true);
                    // Create a FadeTransition to add a fade animation
                    FadeTransition ft = new FadeTransition(Duration.millis(1000), btns);
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();

                    isVboxVisible = true;
                } else {
                    // Hide the VBox
                    btns.setVisible(false);
                    isVboxVisible = false;
                    
                    

                }
    }

    @FXML
    private void GoUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../driftrace/connecter.fxml"));
            Parent user = loader.load();            
            ConnecterController connecterController = loader.getController();
            connecterController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(user);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ManageUsers(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../driftrace/admin.fxml"));
            Parent profile = loader.load();            
            borderPane.setCenter(null);
            borderPane.setCenter(profile);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logOut(ActionEvent event) {
        try {
            Data.setId_user(0);
            Parent root = FXMLLoader.load(getClass().getResource("../driftrace/conx.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoEvents(ActionEvent event) {
        try {
            FXMLLoader loader ;
            if(userService.readById(Data.getId_user()).getRole().getId_role()==1){
               loader = new FXMLLoader(getClass().getResource("../View/View/test1.fxml"));
               Parent test = loader.load();            

               Test1Controller test1 = loader.getController();
                test1.setBorderPane(borderPane);
                borderPane.setCenter(null);
                borderPane.setCenter(test);
            }
            else{
            loader = new FXMLLoader(getClass().getResource("../View/View/eventuser.fxml"));
            Parent events = loader.load();            
            EventuserController eventUserController = loader.getController();
            eventUserController.setBorderPane(borderPane);
            borderPane.setCenter(null);
             borderPane.setCenter(events);
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/viewhela/Gestion.fxml"))
    @FXML
    private void GoToGarage(ActionEvent event) {
        
            
              try {         
                  FXMLLoader loader ;

                  if(userService.readById(Data.getId_user()).getRole().getId_role()==1){
                  loader = new FXMLLoader(getClass().getResource("../View/viewhela/affichageGarage.fxml"));
                  Parent garageAdmin = loader.load();
                  
                  AffichageGarageController test1 = loader.getController();
                  test1.setBorderPane(borderPane);
                  borderPane.setCenter(null);
                  borderPane.setCenter(garageAdmin);
             
            }
            else{
            loader = new FXMLLoader(getClass().getResource("../View/viewhela/Maintenance.fxml"));
            Parent maint = loader.load();            
                      MaintenanceController maintController = loader.getController();
            maintController.setBorderPane(borderPane);
            borderPane.setCenter(null);
             borderPane.setCenter(maint);
            } } catch (IOException ex) {
                  Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
              }
    }

    @FXML
    private void GoToMarket(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ViewMarket/market.fxml"));
            Parent market = loader.load();            
            MarketController marketController = loader.getController();
            marketController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(market);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoToProduct(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ViewMarket/GestionProduit.fxml"));
            Parent market = loader.load();            
            GestionProduitController gestionProduitController = loader.getController();
            gestionProduitController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(market);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoToCategorie(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ViewMarket/GestionCategorie.fxml"));
            Parent market = loader.load();            
             GestionCategorieController gestionCategorieController = loader.getController();
            gestionCategorieController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(market);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoToMaintenance(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/viewhela/afficher_Maintenance.fxml"));
            Parent maintAdmin = loader.load();            
            Afficher_MaintenanceController afficherMaintController = loader.getController();
            afficherMaintController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(maintAdmin);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoToModifierMdp(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../driftrace/ModifierMdp.fxml"));
            Parent mdofierMdp = loader.load();            
            ModifierMdpController modfifierMdpController = loader.getController();
            modfifierMdpController.setBorderPane(borderPane);
            borderPane.setCenter(null);
            borderPane.setCenter(mdofierMdp);
        } catch (IOException ex) {
            Logger.getLogger(MenuItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
