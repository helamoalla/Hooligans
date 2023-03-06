/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Event;
import services.Services_event;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ItemuserController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    @FXML
    private Label dated_Month;
    @FXML
    private Label dated_day;
    @FXML
    private Label dated_year;
    @FXML
    private Label datef_Month;
    @FXML
    private Label datef_day;
    @FXML
    private Label datef_year;
    @FXML
    private Label id_e;
 Services_event se=new Services_event();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void getEvent(Event e){
        
        try {
            nom.setText(e.getNom_event());
            id_e.setText(String.valueOf(e.getId_e()));
            //Description.setText(P.getDescription());
          //  String htdocsPath = "C:/xampp/htdocs/img/";
            URL imageUrl;
          
            imageUrl = new URL("http://localhost/img/"+e.getImage());
            Image images = new Image(imageUrl.toString());
            image.setImage(images);
            
            //this.projet= P;
            LocalDate d=e.getDate_debut().toLocalDate();
            
            dated_day.setText(String.valueOf(d.getDayOfMonth()));
            dated_Month.setText(String.valueOf(d.getMonth()));
            dated_year.setText(String.valueOf(d.getYear()));
            LocalDate f=e.getDate_fin().toLocalDate();
            
            datef_day.setText(String.valueOf(f.getDayOfMonth()));
            datef_Month.setText(String.valueOf(f.getMonth()));
            datef_year.setText(String.valueOf(f.getYear()));
            
            //System.out.println(projet);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ItemuserController.class.getName()).log(Level.SEVERE, null, ex);
        }}
    @FXML
    private void detail(ActionEvent event) {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("./detailevent.fxml"));
            Parent view_2=loader.load();
            DetaileventController DetaileventController=loader.getController();
            DetaileventController.getEvent(se.readById(Integer.valueOf(id_e.getText())));
            Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ItemtestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
