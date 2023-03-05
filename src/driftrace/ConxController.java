/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driftrace;
import com.mysql.cj.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import Service.UserService;
import Util.Data;
import Util.MyConnection;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;


import javax.mail.Authenticator;













public class ConxController implements Initializable {

    @FXML
    private TextField adresse;
    @FXML
    private Button connect;
    @FXML
    private Button inscription;
private Connection connection;
    @FXML
    private TextField pswd;
    @FXML
    private Button mot;
    @FXML
    private ToggleButton showbotton;
    @FXML
    private Text mdpshow;

    private boolean labelVisible = false;
    @FXML
    private FlowPane container;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        container.setStyle("-fx-background-color: white; -fx-border-color: black;"
                            + "-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0);");
       mdpshow.textProperty().bind(pswd.textProperty());

    mdpshow.setVisible(false); 
    }    


    @FXML
    private void con(ActionEvent event) throws IOException {
        connection = MyConnection.getInstance().getCnx();
    String email = adresse.getText();
    String mdp = pswd.getText();
    // Récupérer l'adresse email de l'utilisateur


// Rechercher l'utilisateur par email
UserService userService = new UserService();


    if (email.isEmpty() || mdp.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir l'email et le mot de passe.");
        alert.showAndWait();
        return;
    } 

    try {
        String requete = "SELECT * FROM user WHERE email = ? AND mdp = ?";
        PreparedStatement statement = connection.prepareStatement(requete);
        statement.setString(1, email);
        statement.setString(2, mdp);
        ResultSet resultSet = statement.executeQuery();


        if (resultSet.next()) {
            
           int etatUser = resultSet.getInt("etat");
        if (etatUser == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Votre compte est suspendu.");
            alert.showAndWait();
            return;}
        
                //resultSet.getInt(id_user)
                Data.setId_user(resultSet.getInt("id_user"));
            // Utilisateur trouvé
            int idRole = resultSet.getInt("id_role");
         
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Vous êtes maintenant connecté!");
                alert.showAndWait();
                Driftrace.idutilisateur = resultSet.getInt("id_user");
                UserService sc = new UserService();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/SideBar.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            
        } else{
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Email ou password incorecte");
                alert.showAndWait();
        }
        resultSet.close();
        statement.close();
      
    } catch (SQLException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Impossible de se connecter à la base de données.");
        alert.showAndWait();
    }  
    
    
    }

    @FXML
    private void insc(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("inscription.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

   @FXML
private void motoublier(ActionEvent event) throws SQLException, IOException, NoSuchAlgorithmException, MessagingException {

    connection = MyConnection.getInstance().getCnx();
    String email = adresse.getText();
    if (email.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir l'email.");
        alert.showAndWait();
        return;
    }
    // Generate a new password
    String newPassword = generateRandomPassword();
    // Encrypt the new password
    //String encryptedPassword = encryptPassword(newPassword);
    // Update the user's password in the database
    String updateQuery = "UPDATE user SET mdp = ? WHERE email = ?";
    try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
        statement.setString(2, newPassword);
        statement.setString(1, email);
        statement.executeUpdate();
    }
    // Send the new password to the user's email
    //sendEmail(email, "Nouveau mot de passe", "votre nouveau mot de passe" + newPassword);

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText(null);
    alert.setContentText("Un nouveau mot de passe a été envoyé à votre adresse email!");
    alert.showAndWait();
}
/*private void sendEmail(String to, String subject, String body) throws MessagingException {
    // Send an email
    // ...
    String from = "khadamni12@gmail.com";
    String password = "cndodswgpbuheaht";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

   Session session = Session.getInstance(props, new Authenticator() {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(from, password);
    }
});

    

    Message message = new MimeMessage((MimeMessage) session);
    message.setFrom(new InternetAddress(from));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
    message.setSubject(subject);
    message.setText(body);

    Transport.send(message);
}*/
    


   private String generateRandomPassword() {
    // Generate a new password with 8 characters
    String passwordChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    StringBuilder passwordBuilder = new StringBuilder();
    for (int i = 0; i < 8; i++) {
        int index = (int) (Math.random() * passwordChars.length());
        passwordBuilder.append(passwordChars.charAt(index));
    }
    return passwordBuilder.toString();
}

    @FXML
    private void showuserpw(ActionEvent event) {
        labelVisible = !labelVisible; // toggle the visibility
    mdpshow.setVisible(labelVisible);


    }
    
}
