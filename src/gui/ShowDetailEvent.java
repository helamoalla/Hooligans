/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;

import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
import java.util.Date;

import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

import models.Event;
import services.EventService;


/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ShowDetailEvent extends BaseForm {
    EventService es = new EventService();
    
    public ShowDetailEvent(Resources res, Event p)  {
        super("Newsfeed", BoxLayout.y());
      
        try {
            Toolbar tb = new Toolbar(true);
            setToolbar(tb);
            getTitleArea().setUIID("Container");
            setTitle("Detail Event");
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




TextField username = new TextField(p.getNom_event());
username.setEditable(false);
username.setUIID("TextFieldBlack");
addStringValue("Nom Event", username);

TextField description = new TextField(p.getLieu_event());
description.setEditable(false);
description.setUIID("TextFieldBlack");
addStringValue("Lieu event", description);


TextField prix = new TextField(String.valueOf(p.getPrix()));
prix.setEditable(false);
prix.setUIID("TextFieldBlack");
addStringValue("Prix", prix);
// Créer un SimpleDateFormat pour parser la date
SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

// Parser la date de la String
Date date = inputFormat.parse(p.getDate_debut());

// Créer un SimpleDateFormat pour formater la date
SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

// Formater la date avec le SimpleDateFormat
String formattedDate = outputFormat.format(date);
//
//    // Créer le TextArea avec la date formatée
//    TextArea ta = new TextArea("Date maintenance " + formattedDate);
//    ta.setUIID("NewsTopLine1");
//    ta.setEditable(false);

TextField datedebut = new TextField(formattedDate);
datedebut.setEditable(false);
datedebut.setUIID("TextFieldBlack");
addStringValue("Date debut",datedebut);

// Créer un SimpleDateFormat pour parser la date
SimpleDateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd");

// Parser la date de la String
Date date1 = inputFormat1.parse(p.getDate_fin());

// Créer un SimpleDateFormat pour formater la date
SimpleDateFormat outputFormat1 = new SimpleDateFormat("dd/MM/yyyy");

// Formater la date avec le SimpleDateFormat
String formattedDate1 = outputFormat1.format(date1);
//
//    // Créer le TextArea avec la date formatée
//    TextArea ta = new TextArea("Date maintenance " + formattedDate);
//    ta.setUIID("NewsTopLine1");
//    ta.setEditable(false);

TextField datefin = new TextField(formattedDate1);
datefin.setEditable(false);
datefin.setUIID("TextFieldBlack");
addStringValue("Date debut",datefin);


TextField type = new TextField(p.getType_event());
type.setEditable(false);
type.setUIID("TextFieldBlack");
addStringValue("Type", type);
        } catch (ParseException ex) {
           printStackTrace(ex);
        }
       Button PayerButton = new Button("payer");
      
  PayerButton.addActionListener(e -> { 
        double id =  p.getId_e();
       es.payer(id);
        Dialog.show("Succès", "Payement effectue avec succes", "OK", null);
        this.refreshTheme();   
    } );
       
 
    Container buttonsContainer = new Container(BoxLayout.x());
    buttonsContainer.addAll(PayerButton);
   
    add(buttonsContainer) ;
    }
    
    
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
