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
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
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
import models.GarageC;
import services.GarageCService;
import com.mycompany.myapp.BaseForm;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;
import models.Devis;
import models.Maintenance;
import services.DevisService;

/**
 *
 * @author choua
 */
public class ShowDevisForm extends BaseForm {
     public ShowDevisForm(Resources res,Maintenance id) {    
      super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Devis");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("back.jpg"), spacer1, "", "", "Devis");
                
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
        
        DevisService ps = DevisService.getInstance();
     List<Devis> list = ps.fetchDevis(id);
     
//          for (Lignepanier l : list) {
//        addButton(l.getImage(),l.getNom_produit(), l.getPrix(), l.getQuantite(), l.getPrix()*l.getQuantite(), l.getId());
//          }

       for (Devis l : list) {
    Container produitContainer = new Container(new BorderLayout());
    Container infosContainer = new Container(BoxLayout.y());
           
   Label devis = new Label("devis: " );
    Label nomProduitLabel = new Label("TVA: " + l.getTVA());
    nomProduitLabel.setBadgeUIID("1");
    Label prixLabel = new Label("Total apres reduction: " + l.getTotal());
     prixLabel.setBadgeUIID("2");
     // Label moteur = new Label("panne moteur: " + l.getMaintenance().getPanne_moteur());
    // prixLabel.setBadgeUIID("2");
      
 
     
   
 infosContainer.add(devis);
    infosContainer.add(nomProduitLabel);
    infosContainer.add(prixLabel);

    
       
     
  //  produitContainer.add(BorderLayout.WEST, produitImageViewer);
    produitContainer.add(BorderLayout.CENTER, infosContainer);

    Button factureButton = new Button("devis");
  factureButton.addActionListener(e -> { 
        int myInt2 = Double.valueOf(l.getId_devis()).intValue(); 
        ps.sendmail(myInt2);
        Dialog.show("Succès", "Vous avez reçu le devis par mail ", "OK", null);
        try {
            //this.refreshTheme();
            new ShowMaintenanceForm(res).show();
        } catch (ParseException ex) {
            printStackTrace(ex);
        }
    } );
this.add(factureButton);
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
//    
//       private void addButton(String img, String title, double prix, Double quantite, Double sousmontant, Double id) {
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
//       TextArea ta = new TextArea("Nom du produit "+title);
//       ta.setUIID("NewsTopLine1");
//       ta.setEditable(false);
//       Font fontta = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
//       ta.getUnselectedStyle().setFont(fontta);
//       
//       TextArea p = new TextArea("Prix du produit "+String.valueOf(prix)+" DT");
//       p.setUIID("NewsSecond");
//       p.setEditable(false);
//       Font fontp = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
//       p.getUnselectedStyle().setFont(fontp);
//       double quantiteDouble = quantite; 
//       int quantiteInt = (int) quantiteDouble;
//       // Convertir l'entier en une chaîne de caractères
//       String quantiteStr = Integer.toString(quantiteInt);
//       TextArea qt = new TextArea("Quantité : "+quantiteStr);
//       qt.setUIID("Newsthird");
//       qt.setEditable(false);
//       Font fontqt= Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
//       qt.getUnselectedStyle().setFont(fontqt);
//       
//       TextArea sm = new TextArea("Sous montant "+String.valueOf(sousmontant)+" DT");
//       sm.setUIID("Newsfourth");
//       sm.setEditable(false);
//       Font fontsm= Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
//       sm.getUnselectedStyle().setFont(fontsm); 
//       PanierService ps = PanierService.getInstance();
//    Button deleteButton = new Button();
//     Image icon = FontImage.createMaterial(FontImage.MATERIAL_DELETE, deleteButton.getUnselectedStyle()).scaled(80, 80);
//    deleteButton.setIcon(icon); 
//           deleteButton.addActionListener(e -> { 
//        int myInt2 = Double.valueOf(id).intValue(); 
//        ps.SuppLignePanier(myInt2);
//        Dialog.show("Succès", "Produit supprimé du panier avec succès", "OK", null);
//        this.refreshTheme();   
//    } );
//
//    Button plusButton = new Button();
//    Image icon1 = FontImage.createMaterial(FontImage.MATERIAL_ADD_CIRCLE, plusButton.getUnselectedStyle()).scaled(80, 80);
//    plusButton.setIcon(icon1); 
//    
//   plusButton.addActionListener(e -> { 
//   int myInt2 = Double.valueOf(id).intValue(); 
//   ps.QuantitePLusUN(myInt2);
//   Dialog.show("Succès", "Quantité mise à jour avec succès", "OK", null);
//   this.refreshTheme();   
//    } );
//
//    
//    Button moinsButton = new Button();
//     Image icon2 = FontImage.createMaterial(FontImage.MATERIAL_REMOVE_CIRCLE, moinsButton.getUnselectedStyle()).scaled(80, 80);
//    moinsButton.setIcon(icon2); 
//    
//    moinsButton.addActionListener(e -> { 
//    int myInt2 = Double.valueOf(id).intValue();
//    ps.QuantiteMoinsUN(myInt2);
//    Dialog.show("Succès", "Quantité mise à jour avec succès", "OK", null);
//    this.refreshTheme();   
//    } );
//    
//       cnt.add(BorderLayout.CENTER, 
//               BoxLayout.encloseY(
//                       ta,p,qt,sm,
//                       BoxLayout.encloseX(deleteButton, plusButton,moinsButton)
//               ));
//       add(cnt);
//       image.addActionListener(e -> ToastBar.showMessage(title, FontImage.MATERIAL_INFO));
//        
//
//   }
       
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
}