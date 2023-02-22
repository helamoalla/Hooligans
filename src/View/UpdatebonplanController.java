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
import java.net.MalformedURLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizh
 */
public class UpdatebonplanController implements Initializable {

    @FXML
    private TextField adresseBonPlan;
    @FXML
    private TextField nomBonPlan;
    @FXML
    private ChoiceBox<String> typeBonPlan;
    @FXML
    private Text image_label;
    @FXML
    private ImageView image_view;
    
        BonPlanService bonPlanService=new BonPlanService();

    
    private File selectedFile;
    
    BonPlan b;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeBonPlan.getItems().add("Garage");
        typeBonPlan.getItems().add("Circuit");
    }
void getBonPlan(BonPlan b){
    URL imageUrl;
        
    try {
        nomBonPlan.setText(b.getNom_bonplan());
        adresseBonPlan.setText(b.getAdresse());
        typeBonPlan.setValue(b.getType());
        image_label.setText(b.getImage());
        imageUrl = new URL("http://localhost/images/"+b.getImage());
        Image images = new Image(imageUrl.toString());
        image_view.setImage(images);
} catch (MalformedURLException ex) {
         System.out.println(ex);
                    }
        
}       


    @FXML
    private void backToMain(ActionEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./BonPlan.fxml"));
            Parent view_2=loader.load();
            Scene scene = new Scene(view_2);
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterBonPlanController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void updateBonPlan(ActionEvent event) {
     
                b.setNom_bonplan(nomBonPlan.getText());
                b.setAdresse(adresseBonPlan.getText());
                b.setType(typeBonPlan.getValue());
                b.setImage(image_label.getText());
                
         // Copy the selected file to the htdocs directory
                 String htdocsPath = "C:/xampp/htdocs/images/";
                 File destinationFile = new File(htdocsPath + image_label.getText().replaceAll("\\s", ""));
            if(selectedFile!=null){
                try (InputStream in = new FileInputStream(selectedFile);
                 OutputStream out = new FileOutputStream(destinationFile)) {
                    System.out.println(in);
                    System.out.println(out);
                byte[] buf = new byte[8192];
                int length;
                while ((length = in.read(buf)) > 0) {
                    out.write(buf, 0, length);
                }
            
            bonPlanService.update(b);
            // return to the main 
                FXMLLoader loader= new FXMLLoader(getClass().getResource("./BonPlan.fxml"));
                Parent view_2=loader.load();
                Scene scene = new Scene(view_2);
                Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            }else{
                bonPlanService.update(b);
                System.out.println("selected file is null "+selectedFile);
            }
            
        }
    
    
}
