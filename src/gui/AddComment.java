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
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
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
public class AddComment extends BaseForm {

    public AddComment(Resources res, BonPlan p) {
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

        TextField comment = new TextField("", "Enter Your Comment");
        comment.setUIID("TextFieldBlack");
        addStringValue("comment",comment);
//         TextField rate = new TextField("", "Enter Your Rate");
//        rate.setUIID("TextFieldBlack");
//        addStringValue("rate",rate);
        
        Slider stars = createStarRankSlider();
        Container starField = FlowLayout.encloseCenter(stars);
        starField.setUIID("TextFieldBlack");
        addStringValue("starField",starField);
        
        
      
         CheckBox report = new CheckBox();
        report.setUIID("TextFieldBlack");
        addStringValue("report",report);
        
        Button btnAdd = new Button("Add");
        addStringValue("", btnAdd);
        
        
        //onclick button event 

        btnAdd.addActionListener((e) ->{
            
            try {
        if(comment.getText().equals("")) {
            Dialog.show("can you review the data", "", "cancel", "OK");
        } else {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog iDialog = ip.showInfiniteBlocking();

           // Comment c = new Comment(String.valueOf(comment.getText().toString()));
           // c.setId_post(p);
           
//c.setReport(Boolean.parseBoolean(report.getText()));
             // set the id_post field
             int starRating = stars.getProgress();
System.out.println("Star Rating: " + starRating);
                      Feedback f = new Feedback();
f.setCommentaire(comment.getText());
//f.setRate((int)Float.parseFloat(rate.getText()));
f.setRate(stars.getProgress());
if(report.isSelected()){
    f.setReport("true");
}else{
    f.setReport("false");
}

            System.out.println("Comment data == " + f);

            //Calling AddComment of service category...
            FeedbackService.getInstance().addComment(p.getId_bonplan(),f);
            //ServiceComment.instance.addComment(id_post, c);
            iDialog.dispose();
            //new NewsfeedForm(res).show();
            new ShowBonPlan(res).show();
             refreshTheme();
        }
    } catch(Exception ex) {
        ex.printStackTrace();
    }
            
            
        });

    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

     private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL).
        derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        
        
        return starRank;
    }
}
