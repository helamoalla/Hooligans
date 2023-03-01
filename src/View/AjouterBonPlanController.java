/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.BonPlan;
import Service.BonPlanService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizh
 */
public class AjouterBonPlanController implements Initializable {
    
    BonPlanService bonPlanService=new BonPlanService();

    @FXML
    private TextField adresseBonPlan;
    @FXML
    private TextField nomBonPlan;
    @FXML
    private ChoiceBox<String> typeBonPlan;
    @FXML
    private Button Add_btn;
    @FXML
    private Button retour_btn;
    @FXML
    private Text image_label;
    @FXML
    private ImageView image_view;
    
    private File selectedFile;
    private BorderPane borderPane;
    
    //Tooltip tooltip = new Tooltip("Please enter a valid input.");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeBonPlan.getItems().add("Garage");
        typeBonPlan.getItems().add("Circuit");
        //nomBonPlan.setTooltip(tooltip);

    } 
      public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }
    

    @FXML
    private void ajouterBonPlan(ActionEvent event) {
        if (nomBonPlan.getText().length() == 0||adresseBonPlan.getText().length() == 0||typeBonPlan.getValue() == null||image_view.getImage()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Please remplir tous les champs"+ "");
            alert.show();

        }
        else if(nomBonPlan.getText().matches("\\d*")||adresseBonPlan.getText().matches("\\d*")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("The Name or the Adress must be a String!!"+ "");
            alert.show();
            
        } 
        else {
            
                BonPlan b=new BonPlan();
                b.setNom_bonplan(nomBonPlan.getText());
                b.setAdresse(adresseBonPlan.getText());
                b.setType(typeBonPlan.getValue());
                b.setImage(image_label.getText());
                //b.setId_user(Integer.parseInt(idUser.getText()));
         // Copy the selected file to the htdocs directory
                 String htdocsPath = "C:/xampp/htdocs/images/";
                 File destinationFile = new File(htdocsPath + image_label.getText().replaceAll("\\s", ""));
            if(selectedFile!=null){
                try (InputStream in = new FileInputStream(selectedFile);
                 OutputStream out = new FileOutputStream(destinationFile)) {
                byte[] buf = new byte[8192];
                int length;
                while ((length = in.read(buf)) > 0) {
                    out.write(buf, 0, length);
                }
            
            bonPlanService.insert(b);
            // return to the main 
                    backToMain(event);
            
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            }else{
                System.out.println("selected file is null "+selectedFile);
            }
            
        }
    }

    @FXML
    private void backToMain(ActionEvent event) {
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

    @FXML
    private void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.JPG", "*.gif"));
          fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
         if (selectedFile != null) {
             //maj
                image_label.setText(selectedFile.getName().replaceAll("\\s", ""));
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
