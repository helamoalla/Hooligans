/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.BaseForm;
import models.GarageC;

/**
 *
 * @author choua
 */
public class ShowDetailGarageForm extends BaseForm{
    public ShowDetailGarageForm(Resources res, GarageC p) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Detail Garage");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
   
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
 String imageUrl = "http://localhost/images/" + p.getImage();
EncodedImage encodedImage = EncodedImage.createFromImage(Image.createImage(500, 500), false);
URLImage urlImage = URLImage.createToStorage(encodedImage, p.getImage(), imageUrl);

// Affichage de l'image dans une image viewer
ImageViewer produitImageViewer = new ImageViewer(urlImage.scaledWidth(Math.round(Display.getInstance().getDisplayWidth() * 0.2f)));
add(LayeredLayout.encloseIn(
    sl,
    BorderLayout.south(
        GridLayout.encloseIn(3, 
            facebook,
            FlowLayout.encloseCenter(
                new Label(urlImage.scaled(300, 300))
            ),
            twitter
        )
    )
));

           


        TextField username = new TextField(p.getNom_garage());
        username.setEditable(false);
        username.setUIID("TextFieldBlack");
        addStringValue("Nom garage", username);

         TextField description = new TextField(p.getAdresse());
        description.setEditable(false);
        description.setUIID("TextFieldBlack");
        addStringValue("adresse", description);
        
        
      TextField quantite = new TextField(String.valueOf(p.getNumero()));
        quantite.setEditable(false);
        quantite.setUIID("TextFieldBlack");
        addStringValue("numero", quantite);
        
         TextField Taux_de_reduction = new TextField(String.valueOf(p.getTaux_de_reduction()));
        Taux_de_reduction.setEditable(false);
        Taux_de_reduction.setUIID("TextFieldBlack");
        addStringValue("Taux_de_reduction", Taux_de_reduction);
        
  TextField Amortisseur = new TextField(String.valueOf(p.getAmortisseur()));
        Amortisseur.setEditable(false);
        Amortisseur.setUIID("TextFieldBlack");
        addStringValue("Amortisseur", Amortisseur);
        
          TextField Batterie = new TextField(String.valueOf(p.getBatterie()));
        Batterie.setEditable(false);
        Batterie.setUIID("TextFieldBlack");
        addStringValue("Batterie", Batterie);
        
          TextField Duride = new TextField(String.valueOf(p.getDuride()));
        Duride.setEditable(false);
        Duride.setUIID("TextFieldBlack");
        addStringValue("Duride", Duride);
        
          TextField Essuie_glace = new TextField(String.valueOf(p.getEssuie_glace()));
        Essuie_glace.setEditable(false);
        Essuie_glace.setUIID("TextFieldBlack");
        addStringValue("Essuie_glace", Essuie_glace);
        
          TextField Feu_d_eclairage = new TextField(String.valueOf(p.getFeu_d_eclairage()));
        Feu_d_eclairage.setEditable(false);
        Feu_d_eclairage.setUIID("TextFieldBlack");
        addStringValue("Feu_d_eclairage", Feu_d_eclairage);
        
          TextField Filtre = new TextField(String.valueOf(p.getFiltre()));
        Filtre.setEditable(false);
        Filtre.setUIID("TextFieldBlack");
        addStringValue("Filtre", Filtre);
        
          TextField Frein_main = new TextField(String.valueOf(p.getFrein_main()));
        Frein_main.setEditable(false);
        Frein_main.setUIID("TextFieldBlack");
        addStringValue("Frein_main", Frein_main);
        
        
          TextField Fuite_d_huile = new TextField(String.valueOf(p.getFuite_d_huile()));
        Fuite_d_huile.setEditable(false);
        Fuite_d_huile.setUIID("TextFieldBlack");
        addStringValue("Fuite_d_huile", Fuite_d_huile);
        
          TextField Panne_moteur = new TextField(String.valueOf(p.getPanne_moteur()));
        Panne_moteur.setEditable(false);
        Panne_moteur.setUIID("TextFieldBlack");
        addStringValue("Panne_moteur", Panne_moteur);
        
          TextField Patin = new TextField(String.valueOf(p.getPatin()));
        Patin.setEditable(false);
        Patin.setUIID("TextFieldBlack");
        addStringValue("Patin", Patin);
        
          TextField Pompe_a_eau = new TextField(String.valueOf(p.getPompe_a_eau()));
        Pompe_a_eau.setEditable(false);
        Pompe_a_eau.setUIID("TextFieldBlack");
        addStringValue("Pompe_a_eau", Pompe_a_eau);
        
          TextField Radiateur = new TextField(String.valueOf(p.getRadiateur()));
        Radiateur.setEditable(false);
        Radiateur.setUIID("TextFieldBlack");
        addStringValue("Radiateur", Radiateur);
        
         
         TextField Vidange = new TextField(String.valueOf(p.getVidange()));
        Vidange.setEditable(false);
        Vidange.setUIID("TextFieldBlack");
        addStringValue("Vidange", Vidange);
        

       
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}

