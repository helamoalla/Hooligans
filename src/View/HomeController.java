/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.File;
import java.io.IOException;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizh
 */
public class HomeController implements Initializable {

    @FXML
    private MediaView mediaView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String path = "C:/Users/azizh/OneDrive/Bureau/holigans/MyVideo.mp4";
File file = new File(path);
Media media = new Media(file.toURI().toString());
MediaPlayer mediaPlayer = new MediaPlayer(media);
mediaView.setMediaPlayer(mediaPlayer);

mediaPlayer.play();
    }    


    
}
