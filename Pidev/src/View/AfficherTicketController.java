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


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AfficherTicketController implements Initializable {
InterfaceCRUD Service_ticket = new Service_ticket();
Services_event Service_event = new Services_event();
    @FXML
    private ListView<Ticket> afficher;
    @FXML
    private ImageView qrcode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        // TODO
        getAllTicket();
        String nomm=Service_event.readById(1).getNom_event();
        qrcode.setImage(generateQRCodeImage(nomm,75,75));
    } catch (WriterException ex) {
        Logger.getLogger(AfficherTicketController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(AfficherTicketController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    
     public void getAllTicket(){
        ObservableList<Ticket> Tickets = FXCollections.observableArrayList(Service_ticket.readAll());
        afficher.setItems(Tickets);
         
         
          afficher.setCellFactory(param -> new ListCell<Ticket>() {
           
            private final Text num = new Text();
            private final Text id_evenement = new Text();
            private final Text id_spectateur = new Text();
            private final Text image = new Text();
           ;
            
            
            private final HBox hbox = new HBox(100,num,id_evenement,id_spectateur,image);
            //private final HBox hbox2 = new HBox(200,imageView,nom,adresse,type,etat);
            
            

            protected void updateItem(Ticket item, boolean empty) {
                URL imageUrl;
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    
                         num.setText(String.valueOf(item.getNum_ticket()));
                         id_evenement.setText(String.valueOf(item.getEvent().getId_e()));
                         id_spectateur.setText(String.valueOf(item.getId_spectateur()));
                         image.setText(item.getImage());
                        
                        setText(null);
                        setGraphic(hbox);
                    
                }
            }
        });
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
    private Image generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {

    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    ByteMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
    BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
   ByteArrayOutputStream out = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "png", out);
    out.flush();
    ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
    return new Image(in);
    // Do something with the BufferedImage, such as saving it to a file or displaying it in a GUI


}
    
    

    
}
