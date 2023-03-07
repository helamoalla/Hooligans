/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import Model.Event;
import Model.Ticket;
import Model.User;
import Service.Service_ticket;
import Service.Services_event;
import Service.UserService;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TicketController implements Initializable {

    @FXML
    private ImageView codeqr;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label cin;
    @FXML
    private Label nom_event;
    @FXML
    private Label lieu;
    @FXML
    private Label type;
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
    UserService su=new UserService();
    Services_event se=new Services_event();
Service_ticket st=new Service_ticket();
    private BorderPane borderPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
   public void setBorderPane(  BorderPane borderPane){
        this.borderPane=borderPane;
    }    
    public void getTicket(Ticket t) throws WriterException, IOException{
        
        int id_u=t.getUser().getId_user();
        User u=su.readById(id_u);
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        cin.setText(String.valueOf(u.getCin()));
        lieu.setText(t.getEvent().getLieu_event());
        type.setText(t.getEvent().getType_event());
        nom_event.setText(t.getEvent().getNom_event());
        //Description.setText(P.getDescription());
        //  String htdocsPath = "C:/xampp/htdocs/img/";
        Event ev=se.readById(t.getEvent().getId_e());
          String nomm=ev.getNom_event();
        codeqr.setImage(generateQRCodeImage(nomm,75,75));
    
        //this.projet= P;
        LocalDate d=ev.getDate_debut().toLocalDate();
        dated_day.setText(String.valueOf(d.getDayOfMonth()));
        dated_Month.setText(String.valueOf(d.getMonth()));
        dated_year.setText(String.valueOf(d.getYear()));
        LocalDate f=ev.getDate_fin().toLocalDate();
        datef_day.setText(String.valueOf(f.getDayOfMonth()));
        datef_Month.setText(String.valueOf(f.getMonth()));
        datef_year.setText(String.valueOf(f.getYear()));
        
        //System.out.println(projet);
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
