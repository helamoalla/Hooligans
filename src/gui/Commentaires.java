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
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
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
import com.mycompany.myapp.SessionManager;
import java.util.List;
import models.BonPlan;
import models.Feedback;
import models.Produit;
import services.FeedbackService;
import services.ProduitService;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class Commentaires extends BaseForm {

    public Commentaires(Resources res, BonPlan p) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Detail Produit");
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
// String imageUrl = "http://localhost/images/" + p.getImage();
//EncodedImage encodedImage = EncodedImage.createFromImage(Image.createImage(500, 500), false);
//URLImage urlImage = URLImage.createToStorage(encodedImage, p.getImage(), imageUrl);

// Affichage de l'image dans une image viewer
//ImageViewer produitImageViewer = new ImageViewer(urlImage.scaledWidth(Math.round(Display.getInstance().getDisplayWidth() * 0.2f)));
add(LayeredLayout.encloseIn(
    sl,
    BorderLayout.south(
        GridLayout.encloseIn(3, 
            facebook,
            FlowLayout.encloseCenter(
               new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
           
            twitter
        )
    )
));

           
   
        FeedbackService ps = FeedbackService.getInstance();
     
     
     List<Feedback> list = ps.displayComment(p.getId_bonplan());
       for (Feedback f : list) {
    Container produitContainer = new Container(new BorderLayout());
    Container infosContainer = new Container(BoxLayout.y());
           
    // Récupération de l'image à partir de l'URL
    String imageUrl = "http://localhost/images/" + f.getImg();
    if(f.getImg()== null){
         imageUrl = "http://localhost/images/nadia.jpg";
    }
    
   EncodedImage encodedImage = EncodedImage.createFromImage(Image.createImage(100, 100), false);
   URLImage urlImage = URLImage.createToStorage(encodedImage, f.getImg(), imageUrl);

    // Affichage de l'image dans une image viewer
  ImageViewer produitImageViewer = new ImageViewer(urlImage.scaledWidth(Math.round(Display.getInstance().getDisplayWidth() * 0.2f)));

  
  
  
Label nomUser = new Label( f.getNom());
    Label nomProduitLabel = new Label("Comment : " + f.getCommentaire());
    //Label rate = new Label("Rate: " + f.getRate());
     Slider stars = createStarRankSlider();
        Container rate = FlowLayout.encloseCenter(stars);
        stars.setProgress(f.getRate());
        stars.setEditable(false);
        //starField.setUIID("TextFieldBlack");
        //addStringValue("starField",starField);
    Label report = new Label("Report: " + String.valueOf(f.isReport()));
     Font fontta = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
       nomProduitLabel.getUnselectedStyle().setFont(fontta);
//    Label prixLabel = new Label("Prix: " + p.getPrix_prod()+" DT");
//    Label quantiteLabel = new Label("Quantité: " +  (int)p.getQuantite_prod());
//    
//    
//   Label descLabel = new Label("Description: " + p.getDescription_prod());
// 
 Button deleteButton = new Button("Delete");
 Button updateButton = new Button("Update");
      
        
          deleteButton.addActionListener(e -> { 
        int id = (int) f.getId_feedback();
        ps.deleteComment(id);
        Dialog.show("Succès", "Produit supprimé du panier avec succès", "OK", null);
        this.refreshTheme();   
    } );
          
           updateButton .addActionListener(e -> { 
        new UpdateComment(res,f).show();  
    } );


   
   // session
   if(f.getId_user()==SessionManager.getId()){
       if(f.getRate()>0){
           infosContainer.addAll(nomUser,nomProduitLabel,rate,updateButton,deleteButton);
       }else{
           infosContainer.addAll(nomUser,nomProduitLabel,updateButton,deleteButton);
       }
   }else {
       if(f.getRate()>0){
           infosContainer.addAll(nomUser,nomProduitLabel,rate);
       }else{
           infosContainer.addAll(nomUser,nomProduitLabel);
       }
   }
   
//    if(f.getRate()>0 ){
//        infosContainer.addAll(nomUser,nomProduitLabel,rate,updateButton,deleteButton);
//    }
//    else{
//        infosContainer.addAll(nomUser,nomProduitLabel,updateButton,deleteButton);
//    }


    

            
  produitContainer.add(BorderLayout.WEST, produitImageViewer);
    produitContainer.add(BorderLayout.CENTER, infosContainer);


    this.add(produitContainer);
}



       
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
