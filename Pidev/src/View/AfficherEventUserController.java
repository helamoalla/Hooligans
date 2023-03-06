/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import interfaces.InterfaceCRUD;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Event;
import model.Ticket;
import services.Service_ticket;
import services.Services_event;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AfficherEventUserController implements Initializable {
InterfaceCRUD Service_event = new Services_event();
InterfaceCRUD Service_ticket = new Service_ticket();
    @FXML
    private ListView<Event> Afficher;
    @FXML
    private Button AfficherButton;
    @FXML
    private Button payer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getAllEvent();
    }    
public void getAllEvent(){
        ObservableList<Event> Events = FXCollections.observableArrayList(Service_event.readAll());
        Afficher.setItems(Events);
         
         
          Afficher.setCellFactory(param -> new ListCell<Event>() {
            private final ImageView imageView = new ImageView();
            private final Text nom = new Text();
            private final Text dated = new Text();
            private final Text datef = new Text();
            private final Text lieu = new Text();
            private final Text type = new Text();
            private final Text prix = new Text();
            
            
            private final HBox hbox = new HBox(100,imageView,nom,lieu,type,dated,datef,prix);
            //private final HBox hbox2 = new HBox(200,imageView,nom,adresse,type,etat);
            
            {
                imageView.setFitWidth(75);
                imageView.setFitHeight(75);
            }

            @Override
            protected void updateItem(Event item, boolean empty) {
                URL imageUrl;
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                         nom.setText(item.getNom_event());
                         lieu.setText(item.getLieu_event());
    type.setText(item.getType_event());
    dated.setText(item.getDate_debut().toLocalDate().toString());
    datef.setText(item.getDate_fin().toLocalDate().toString());
    prix.setText(item.getPrix().toString());
                      //  System.out.println(item.getImage());
                        imageUrl = new URL("http://localhost/img/"+item.getImage());
                        //System.out.println(imagehttp://localhost/images/Url);
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
    private void afficher(ActionEvent event) {
        getAllEvent();
    }

    @FXML
    private void payer(ActionEvent event) {
         int selectedId= Afficher.getSelectionModel().getSelectedItem().getId_e();
         Ticket t=new Ticket();
         t.setEvent((Event) Service_event.readById(selectedId));
         t.setId_spectateur(2);
         t.setImage("dsv");
         t.setNum_ticket(123);
         Service_ticket.insert(t);
         
         
    }

    @FXML
    private void home(ActionEvent event) {
    try {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./Acceuil.fxml"));
        Parent view_2=loader.load();
        Scene scene = new Scene(view_2);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AfficherEventUserController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    
}
