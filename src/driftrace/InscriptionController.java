/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driftrace;

import Model.Role;
import Model.User;
import Service.RoleService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Service.UserService;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class InscriptionController implements Initializable {

    @FXML
    private TextField nom1;
    @FXML
    private TextField prenom1;
    @FXML
    private TextField email1;
    @FXML
    private TextField cin1;
    @FXML
    private Button inscrire;
    @FXML
    private TextField mdp1;
    @FXML
    private TextField num_tel1;
    @FXML
    private TextField text_image;
    RoleService roleService=new RoleService();
    @FXML
    private Button img;
    @FXML
    private ImageView imageView;

   private File selectedFile;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
    
    
    
    
    
    
    
    
    @FXML
       private void addimg(ActionEvent event) {
       FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.JPG", "*.gif"));
          fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
         if (selectedFile != null) {
             //maj
                text_image.setText(selectedFile.getName().replaceAll("\\s", ""));
                 try {
                Image images = new Image("file:"+selectedFile.getPath().toString());
                imageView.setImage(images);
                System.out.println(selectedFile.getPath().toString());
        } catch (Exception ex) {
                     System.out.println(ex);
        }
                
            }
    
    }
    @FXML
    private void insc(ActionEvent event) throws IOException {
        
            
        if (nom1.getText().isEmpty() || prenom1.getText().isEmpty() || num_tel1.getText().isEmpty() || 
            email1.getText().isEmpty()  || 
            mdp1.getText().isEmpty() || cin1.getText().isEmpty() ) {
        // Afficher un message d'alerte
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champs manquants");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return;
    }
        
           String cin2 = cin1.getText();
    if (!cin2.matches("\\d{8}")) {
        // Afficher un message d'alerte
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Numéro de cin invalide");
        alert.setHeaderText(null);
        alert.setContentText("Le numéro du cin doit être composé de 8 chiffres exactement !");
        alert.showAndWait();
        return;
    }

     String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
if (!email1.getText().matches(emailRegex)) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Format email incorrect");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez saisir un email valide !");
    alert.showAndWait();
    return;
}
 String num = num_tel1.getText();
    if (!num.matches("\\d{8}")) {
        // Afficher un message d'alerte
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Numéro de téléphone invalide");
        alert.setHeaderText(null);
        alert.setContentText("Le numéro de téléphone doit être composé de 8 chiffres exactement !");
        alert.showAndWait();
        return;
    }

  
    
    
    
    
    

// Vérifier si l'email est unique
UserService userService = new UserService();
if (userService.checkEmailExists(email1.getText())) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Email existe déjà");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez saisir un email différent !");
    alert.showAndWait();
    return;
}

        String mdp2 = mdp1.getText();
    if (!mdp2.matches(".*[A-Z].*") || !mdp2.matches(".*\\d.*")) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Le mot de passe doit contenir au moins une lettre majuscule et un chiffre.");
        alert.showAndWait();
        return;
    }
        
        
        
        
        
        
        
        
        Role r1 = new Role (2,"spectateur");
        String nom = nom1.getText();
        String prenom = prenom1.getText();
                String mdp = mdp1.getText();
        String email = email1.getText();
        int num_tel = Integer.parseInt(num_tel1.getText());
        int cin = Integer.parseInt(cin1.getText());
         int quota = 0; 
         int etat_user=1;
          String nomimage = text_image.getText();
      
        
        
        
    
        
        
        
   
      

        // Créer un nouvel utilisateur avec les données du formulaire et un rôle par défaut
        User u = new User( nom,prenom,mdp,email,num_tel,cin,quota,r1,nomimage,etat_user);
        
          //b.setImage(image_label.getText());
                //b.setId_user(Integer.parseInt(idUser.getText()));
         // Copy the selected file to the htdocs directory
                 String htdocsPath = "C:/xampp/htdocs/images/";
                 File destinationFile = new File(htdocsPath + text_image.getText().replaceAll("\\s", ""));
            if(selectedFile!=null){
                try (InputStream in = new FileInputStream(selectedFile);
                 OutputStream out = new FileOutputStream(destinationFile)) {
                byte[] buf = new byte[8192];
                int length;
                while ((length = in.read(buf)) > 0) {
                    out.write(buf, 0, length);
                }


        // Utiliser le service UserService pour ajouter l'utilisateur à la base de données
      

      
    // Utiliser le service UserService pour ajouter l'utilisateur à la base de données
    userService.insert(u);

   
/*String destinationPath = "C:\\Users\\21694\\Desktop\\driftrace\\src\\image\\"+u.getEmail()+";";

// Récupérer le fichier sélectionné
        File selectedFile = new File(text_image.getText());

// Créer un nouveau fichier dans le dossier de destination avec le même nom que le fichier sélectionné
        File destinationFile = new File(destinationPath + selectedFile.getName());

// Copier le fichier sélectionné dans le dossier de destination
        Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

// Définir le nom de l'image dans l'utilisateur
        u.setImg(selectedFile.getName());*/
    // Afficher un message de confirmation
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Utilisateur ajouté avec succès !");
    alert.showAndWait();

    // Réinitialiser le formulaire
    nom1.setText("");
    prenom1.setText("");
    mdp1.setText("");
    email1.setText("");
    num_tel1.setPromptText("choisir");
    cin1.setText("");
   

    // Charger l'interface quiz_web.fxml
    Parent root = FXMLLoader.load(getClass().getResource("conx.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();}

    



}}}
    

