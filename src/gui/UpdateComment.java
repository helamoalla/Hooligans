/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import models.BonPlan;
import models.Feedback;
import services.FeedbackService;

/**
 *
 * @author azizh
 */
public class UpdateComment extends BaseForm {

    public UpdateComment(Resources res, Feedback p) {
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
// String imageUrl = "http://localhost/images/" + p.getImage();
//EncodedImage encodedImage = EncodedImage.createFromImage(Image.createImage(500, 500), false);
//URLImage urlImage = URLImage.createToStorage(encodedImage, p.getImage(), imageUrl);

// Affichage de l'image dans une image viewer
//ImageViewer produitImageViewer = new ImageViewer(urlImage.scaledWidth(Math.round(Display.getInstance().getDisplayWidth() * 0.2f)));
//add(LayeredLayout.encloseIn(
//    sl,
//    BorderLayout.south(
//        GridLayout.encloseIn(3, 
//            facebook,
//            FlowLayout.encloseCenter(
//                new Label(urlImage.scaled(300, 300))
//            ),
//            twitter
//        )
//    )
//));

           
        Container infosContainer = new Container(BoxLayout.y());

        TextField comment = new TextField("", "Enter Your Comment");
        comment.setUIID("TextFieldBlack");
        comment.setText(p.getCommentaire());
        addStringValue("comment",comment);
//         TextField rate = new TextField("", "Enter Your Rate");
//        rate.setUIID("TextFieldBlack");
//        rate.setText(String.valueOf(p.getRate()));
        Slider stars = createStarRankSlider();
        Container starField = FlowLayout.encloseCenter(stars);
       
        starField.setUIID("TextFieldBlack");
        addStringValue("Rate",starField);
         stars.setProgress(p.getRate());
        //addStringValue("rate",rate);
         CheckBox report = new CheckBox();
        report.setUIID("TextFieldBlack");
        if(p.isReport()=="true"){
            report.setSelected(true);
        }else{
            report.setSelected(false);
        }
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
                      //Feedback f = new Feedback();
p.setCommentaire(comment.getText());
//f.setRate((int)Float.parseFloat(rate.getText()));
p.setRate(stars.getProgress());
if(report.isSelected()){
    p.setReport("true");
}else{
    p.setReport("false");
}

            System.out.println("Comment data == " + p);

            //Calling AddComment of service category...
            FeedbackService.getInstance().updateComment(p);
            //ServiceComment.instance.addComment(id_post, c);
            iDialog.dispose();
            new ShowBonPlan(res).show();
            //new NewsfeedForm(res).show();
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
