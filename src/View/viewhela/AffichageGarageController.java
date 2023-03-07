/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.viewhela;

import Interface.InterfaceCRUD;
import Model.GarageC;
import Service.ServiceGarageC;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author helam
 */
public class AffichageGarageController implements Initializable {

    private GridPane garage_grid;
private List<GarageC> id_list = new ArrayList<>();

    InterfaceCRUD sg=new ServiceGarageC();
    @FXML
    private HBox Vboxe;
    @FXML
    private GridPane grid;
    @FXML
    private Button id_ajout;

    /**
     * Initializes the controller class.
     */
      private GarageC data(){

    GarageC g =new GarageC();
    g.setNom_garage(g.getNom_garage());
    g.setAdresse(g.getAdresse());
    g.setNumero(g.getNumero());
  
    return g;
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

// refreshNodes();


 int column = 0;
        int row = 0;
        id_list.addAll(sg.readAll());
        System.out.println(id_list);
        try { 
            for (int i = 0; i < id_list.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
               fxmlLoader.setLocation(getClass().getResource("../viewhela/lesGarages.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                LesGaragesController itemController = fxmlLoader.getController();
                itemController.setData(id_list.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

             
               grid.add(anchorPane, column++, row); //(child,column,row)
            //set grid width
             //  column++;
            
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

               GridPane.setMargin(anchorPane,new Insets(5));
             GridPane.setColumnIndex(anchorPane, column);
              
                   
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
     
    }   
  
    
     private void refreshNodes()
    {
        Vboxe.getChildren().clear();
         List<GarageC> lg=sg.readAll();
     //  id_list = new ArrayList<>(data());      
        Node [] nodes = new  Node[15];
        
        for(int i = 0; i<lg.size(); i++)
        {
            try { FXMLLoader fxmlLoader = new FXMLLoader();
                 fxmlLoader.setLocation(getClass().getResource("../viewhela/lesGarages.fxml"));
                 AnchorPane abc = fxmlLoader.load();
                 LesGaragesController itemcontroller = fxmlLoader.getController();
                 itemcontroller.setData(lg.get(i));
                 
            
                Vboxe.getChildren().add(abc);
              
              //////////
            
                
//                nodes[i] = (Node)FXMLLoader.load(getClass().getResource("../view/lesGarages.fxml"));
//               Vboxe.getChildren().add(nodes[i]);
//                
           
           
        }   catch (IOException ex) {  
                Logger.getLogger(AffichageGarageController.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }
    }

    @FXML
    private void ajouter_garage(ActionEvent event) {
         try {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./Ajout_Garage.fxml"));
        Parent view_2=loader.load();
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(GarageController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void retour(ActionEvent event) {
         try{
         FXMLLoader loader= new FXMLLoader(getClass().getResource("./GESTION.fxml"));
        Parent view_2=loader.load();
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(GESTIONController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
}
