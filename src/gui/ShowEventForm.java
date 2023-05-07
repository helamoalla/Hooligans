/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
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
import models.Event;

import services.EventService;


/**
 *
 * @author Nadia
 */
public class ShowEventForm extends BaseForm {
      public ShowEventForm(Resources res) throws ParseException{    
      super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Events");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("profile-background.jpg"), spacer1, " ", "", "D&R events ");
        addTab(swipe, res.getImage("profile-background.jpg"), spacer2, "  ", "", "");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("All", barGroup);
        all.setUIID("SelectBar");
        RadioButton featured = RadioButton.createToggle("Featured", barGroup);
        featured.setUIID("SelectBar");
        RadioButton popular = RadioButton.createToggle("Popular", barGroup);
        popular.setUIID("SelectBar");
        RadioButton myFavorite = RadioButton.createToggle("My Favorites", barGroup);
        myFavorite.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all, featured, popular, myFavorite),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
        bindButtonSelection(featured, arrow);
        bindButtonSelection(popular, arrow);
        bindButtonSelection(myFavorite, arrow);
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
    
//        EventService ps = EventService.getInstance();
//     List<Event> list = ps.fetchEvent();
//          for (Event l : list) {
//        addButton(l.getImage(),l.getNom_event(), l.getPrix(), l.getDate_debut().toString(),l.getDate_fin().toString(), l.getLieu_event(),l.getType_event(),l,res);
//    
//          }
        
        
//        addButton(res.getImage("news-item-1.jpg"), "Morbi per tincidunt tellus sit of amet eros laoreet.", false, 26, 32);
//        addButton(res.getImage("news-item-2.jpg"), "Fusce ornare cursus masspretium tortor integer placera.", true, 15, 21);
//        addButton(res.getImage("news-item-3.jpg"), "Maecenas eu risus blanscelerisque massa non amcorpe.", false, 36, 15);
//        addButton(res.getImage("news-item-4.jpg"), "Pellentesque non lorem diam. Proin at ex sollicia.", false, 11, 9);
//   
//        
        
        
        EventService ps = EventService.getInstance();
     
     
     List<Event> list = ps.fetchEvent();
       for (Event p : list) {
    Container produitContainer = new Container(new BorderLayout());
    Container infosContainer = new Container(BoxLayout.y());
           
    // Récupération de l'image à partir de l'URL
    String imageUrl = "http://localhost/images/" + p.getImage();
    EncodedImage encodedImage = EncodedImage.createFromImage(Image.createImage(100, 100), false);
    URLImage urlImage = URLImage.createToStorage(encodedImage, p.getImage(), imageUrl);

    // Affichage de l'image dans une image viewer
    ImageViewer produitImageViewer = new ImageViewer(urlImage.scaledWidth(Math.round(Display.getInstance().getDisplayWidth() * 0.2f)));

    Label nomEventLabel = new Label("Nom de l'evenemeent: " + p.getNom_event());
    Label prixLabel = new Label("Prix: " + p.getPrix()+" DT");
    Label DateLabel = new Label("Date debut: " +  p.getDate_debut().toString());
        Label DatefinLabel = new Label("Date fijn: " +  p.getDate_fin().toString());
   Label LieuLabel = new Label("Lieu: " + p.getLieu_event());
  Label TypeLabel = new Label("Type: " + p.getType_event());

   
   
    infosContainer.add(nomEventLabel);
    infosContainer.add(prixLabel);
    infosContainer.add(DateLabel);
     infosContainer.add(DatefinLabel);
     infosContainer.add(LieuLabel);
     infosContainer.add(TypeLabel);
            
    Button AffichedetailButton = new Button("Afficher Detail");
        AffichedetailButton.addActionListener(e -> { 
         new ShowDetailEvent(res,p).show();
    } );
    
    Container buttonsContainer = new Container(BoxLayout.x());
    buttonsContainer.addAll(AffichedetailButton);
    infosContainer.add(buttonsContainer);

    

            
    produitContainer.add(BorderLayout.WEST, produitImageViewer);
    produitContainer.add(BorderLayout.CENTER, infosContainer);


    this.add(produitContainer);
}



   }
   
       private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }

//   private void addButton(String img, String title, double prix, String datedebut,String datefin, String lieu,String type,Event l,Resources res) {
//       String imageUrl = "http://localhost/images/" +img;
//       EncodedImage encodedImage = EncodedImage.createFromImage(Image.createImage(50, 50), false);
//            URLImage urlImage = URLImage.createToStorage(encodedImage, img, imageUrl);
//       int height = Display.getInstance().convertToPixels(11.5f);
//       int width = Display.getInstance().convertToPixels(14f);
//      ImageViewer image1 = new ImageViewer(urlImage.scaledWidth(Math.round(Display.getInstance().getDisplayWidth() * 0.2f)));
//
//       Button image = new Button(urlImage.fill(width, height));
//       image.setUIID("Label");
//       Container cnt = BorderLayout.west(image);
//       cnt.setLeadComponent(image);
//       
//       TextArea ta = new TextArea("Nom Event :"+title);
//       ta.setUIID("NewsTopLine1");
//       ta.setEditable(false);
//       Font fontta = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
//       ta.getUnselectedStyle().setFont(fontta);
//       
//       TextArea p = new TextArea("Prix du event : "+String.valueOf(prix)+" DT");
//       p.setUIID("NewsSecond");
//       p.setEditable(false);
//       Font fontp = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
//       p.getUnselectedStyle().setFont(fontp);
//       
//       
//      // String quantiteDouble = datedebut; 
//      // String quantiteInt = datefin;
//       // Convertir l'entier en une chaîne de caractères
//     // String quantiteStr = Double.toString(prix);
//       TextArea qt = new TextArea("Date debut : "+datedebut );
//       qt.setUIID("Newsthird");
//       qt.setEditable(false);
//       Font fontqt= Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
//       qt.getUnselectedStyle().setFont(fontqt);
//       
//       TextArea sm = new TextArea("Date fin : "+String.valueOf(datefin));
//       sm.setUIID("Newsfourth");
//       sm.setEditable(false);
//       Font fontsm= Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
//       sm.getUnselectedStyle().setFont(fontsm);
//       
//       TextArea lm = new TextArea("Lieu Event "+String.valueOf(lieu));
//       sm.setUIID("Newsfourth");
//       sm.setEditable(false);
//       Font fontlm= Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
//       sm.getUnselectedStyle().setFont(fontlm);
//       
//       TextArea tm = new TextArea("Date fin "+String.valueOf(type));
//       sm.setUIID("Newsfourth");
//       sm.setEditable(false);
//       Font fonttm= Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
//       sm.getUnselectedStyle().setFont(fonttm);
//       
//       
//       EventService ps = EventService.getInstance();
//    Button deleteButton = new Button();
//     Image icon = FontImage.createMaterial(FontImage.MATERIAL_DELETE, deleteButton.getUnselectedStyle()).scaled(80, 80);
//    deleteButton.setIcon(icon); 
//       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
//                Button AffichedetailButton = new Button("Afficher Detail");
//        AffichedetailButton.addActionListener(e -> { 
//       new ShowDetailEvent(res,l).show();
//      
//    } );
//
//   
//
//    
// 
//    
//       cnt.add(BorderLayout.CENTER, 
//               BoxLayout.encloseY(
//                       ta,p,qt,sm,
//                       BoxLayout.encloseX(deleteButton,AffichedetailButton)
//               ));
//       add(cnt);
// 
//
//   }
     private void addButton(Image img, String title, boolean liked, int likeCount, int commentCount) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
       TextArea ta = new TextArea(title);
       ta.setUIID("NewsTopLine");
       ta.setEditable(false);

       Label likes = new Label(likeCount + " Likes  ", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       if(!liked) {
           FontImage.setMaterialIcon(likes, FontImage.MATERIAL_FAVORITE);
       } else {
           Style s = new Style(likes.getUnselectedStyle());
           s.setFgColor(0xff2d55);
           FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, s);
           likes.setIcon(heartImage);
       }
       Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       ta,
                       BoxLayout.encloseX(likes, comments)
               ));
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
   }
     
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
    
                
}


