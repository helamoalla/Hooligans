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
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
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
import java.util.List;
import models.BonPlan;
import models.Feedback;
import models.Produit;
import services.BonPlanService;
import services.FeedbackService;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ShowDetailBonPlan extends BaseForm {

    public ShowDetailBonPlan(Resources res, BonPlan p) {
        super("Newsfeed", BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Detail BonPlan");
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

           
        Container infosContainer = new Container(BoxLayout.y());

        TextField nombonplan = new TextField(p.getNom_bonplan());
        nombonplan.setEditable(false);
        nombonplan.setUIID("TextFieldBlack");
        addStringValue("Nom Bon Plan", nombonplan);

         TextField description = new TextField(p.getDescription());
        description.setEditable(false);
        description.setUIID("TextFieldBlack");
        addStringValue("Description", description);
        
        
      TextField gouvenorat = new TextField(String.valueOf(p.getGouvernorat()));
        gouvenorat.setEditable(false);
        gouvenorat.setUIID("TextFieldBlack");
        addStringValue("Gouvernorat", gouvenorat);
        
  TextField ville = new TextField(String.valueOf(p.getVille()));
        ville.setEditable(false);
        ville.setUIID("TextFieldBlack");
        addStringValue("Ville", ville);
        
          TextField rue = new TextField(String.valueOf(p.getRue()));
        rue.setEditable(false);
        rue.setUIID("TextFieldBlack");
        addStringValue("Rue", rue);

          TextField type = new TextField(String.valueOf(p.getType()));
        type.setEditable(false);
        type.setUIID("TextFieldBlack");
        addStringValue("Etat", type);
        
        
        
        
        CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
        CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb2.setUIID("Label");
        cb2.setPressedIcon(res.getImage("on-off-on.png"));
        
         addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
        addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2));
         Button AddComment = new Button("Ajouter commentaire");
           Button affichefeed = new Button("Affichefeed");
         


     
     

      





    this.add(AddComment);
    this.add(affichefeed);
    //infosContainer.add(buttonsContainer);
        AddComment.addActionListener(e -> { 
        new AddComment(res,p).show();
    } );
        
         affichefeed.addActionListener(e -> { 
        new Commentaires(res,p).show();
    } );
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
