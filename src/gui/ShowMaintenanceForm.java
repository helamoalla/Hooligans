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
import static com.codename1.io.Log.p;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.Font;
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
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.Date;
import java.util.List;
import models.Lignepanier;
import models.Maintenance;
import services.DevisService;
import services.MaintenanceService;
import services.PanierService;

/**
 *
 * @author choua
 */
public class ShowMaintenanceForm  extends BaseForm{
    
    public ShowMaintenanceForm(Resources res) throws ParseException {    
      super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Maintenance");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("back.jpg"), spacer1, "", "", "Maintenance");
                
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
        RadioButton featured = RadioButton.createToggle("demander maintenance", barGroup);
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
        
        ///bouton demande maintenance 
        featured.addActionListener(e -> { 
         new addMaintenance(res).show();
    } );
        
        
        MaintenanceService ps = MaintenanceService.getInstance();
     List<Maintenance> list = ps.fetchMaintenance();
          for (Maintenance l : list) {
        addButton(l.getDate_maintenance(),l.getAmortisseur(), l.getBatterie(), l.getDuride(), l.getEssuie_glace(),l.getFeu_d_eclairage(),l.getFiltre(),l.getFrein_main(),l.getFuite_d_huile(),l.getPanne_moteur(),l.getPatin(),l.getPompe_a_eau(),l.getRadiateur(),l.getVentilateur(),l.getVidange());
         DevisService ds= DevisService.getInstance();
         Button devis = new Button("DEVIS");
         this.add(devis);
           devis.addActionListener(e -> { 
     new ShowDevisForm(res,l).show();
       
    } );
         
          }
          

   }
private void addButton(String date_maintenance, String Amortisseur, String Batterie, String Duride, String Essuie_glace, String Feu_d_eclairage, String Filtre, String Frein_main, String Fuite_d_huile, String Panne_moteur, String Patin, String Pompe_a_eau, String Radiateur, String Ventilateur, String Vidange) throws ParseException {

    // Créer un SimpleDateFormat pour parser la date
    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Parser la date de la String
    Date date = inputFormat.parse(date_maintenance);

    // Créer un SimpleDateFormat pour formater la date
    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

    // Formater la date avec le SimpleDateFormat
    String formattedDate = outputFormat.format(date);

    // Créer le TextArea avec la date formatée
    TextArea ta = new TextArea("Date maintenance " + formattedDate);
    ta.setUIID("NewsTopLine1");
    ta.setEditable(false);

    TextArea panne = new TextArea("Les pannes : ");
    panne.setUIID("NewsTopLine1");
    panne.setEditable(false);

    // Ajouter les pannes au conteneur s'ils sont true
    Container content = BoxLayout.encloseY(ta, panne);
    if (Boolean.valueOf(Amortisseur)) {
        TextArea a = new TextArea("Amortisseur");
        a.setUIID("NewsTopLinea");
        a.setEditable(false);
        content.add(BoxLayout.encloseX(a));
    }
    if (Boolean.valueOf(Patin)) {
        TextArea p = new TextArea("Patin");
        p.setUIID("NewsTopLinep");
        p.setEditable(false);
        content.add(BoxLayout.encloseX(p));
    }
if (Boolean.valueOf(Batterie)) {
    TextArea b = new TextArea("Batterie");
    b.setUIID("NewsTopLineb");
    b.setEditable(false);
    content.add(BoxLayout.encloseX(b));
}

if (Boolean.valueOf(Duride)) {
    TextArea d = new TextArea("Duride");
    d.setUIID("NewsTopLined");
    d.setEditable(false);
    content.add(BoxLayout.encloseX(d));
}

if (Boolean.valueOf(Essuie_glace)) {
    TextArea e = new TextArea("Essuie glace");
    e.setUIID("NewsTopLinee");
    e.setEditable(false);
    content.add(BoxLayout.encloseX(e));
}

if (Boolean.valueOf(Feu_d_eclairage)) {
    TextArea f = new TextArea("Feu d'éclairage");
    f.setUIID("NewsTopLinef");
    f.setEditable(false);
    content.add(BoxLayout.encloseX(f));
}

if (Boolean.valueOf(Filtre)) {
    TextArea fi = new TextArea("Filtre");
    fi.setUIID("NewsTopLinefi");
    fi.setEditable(false);
    content.add(BoxLayout.encloseX(fi));
}

if (Boolean.valueOf(Frein_main)) {
    TextArea fr = new TextArea("Frein à main");
    fr.setUIID("NewsTopLinefr");
    fr.setEditable(false);
    content.add(BoxLayout.encloseX(fr));
}

if (Boolean.valueOf(Fuite_d_huile)) {
    TextArea fu = new TextArea("Fuite d'huile");
    fu.setUIID("NewsTopLinefu");
    fu.setEditable(false);
    content.add(BoxLayout.encloseX(fu));
}

if (Boolean.valueOf(Panne_moteur)) {
    TextArea pm = new TextArea("Panne moteur");
    pm.setUIID("NewsTopLinepm");
    pm.setEditable(false);
    content.add(BoxLayout.encloseX(pm));
}

if (Boolean.valueOf(Pompe_a_eau)) {
    TextArea pa = new TextArea("Pompe à eau");
    pa.setUIID("NewsTopLinepa");
    pa.setEditable(false);
    content.add(BoxLayout.encloseX(pa));
}

if (Boolean.valueOf(Radiateur)) {
    TextArea r = new TextArea("Radiateur");
    r.setUIID("NewsTopLiner");
    r.setEditable(false);
    content.add(BoxLayout.encloseX(r));
}

if (Boolean.valueOf(Ventilateur)) {
    TextArea v = new TextArea("Ventilateur");
    v.setUIID("NewsTopLinev");
    v.setEditable(false);
    content.add(BoxLayout.encloseX(v));
}

if (Boolean.valueOf(Vidange)) {
    TextArea vi = new TextArea("Vidange");
    vi.setUIID("NewsTopLinevi");
    vi.setEditable(false);
    content.add(BoxLayout.encloseX(vi));
}

    // Ajoutez les autres vérifications ici pour les autres pannes
    // en utilisant le même modèle

    // ajouter le contenu au conteneur parent
     Button submitBtn = new Button("Submit");

        //actions
        submitBtn.addActionListener((evt) -> {
       //     new ShowDevisForm(res).show();
  
});
          add(content);
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
    
      
       
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
}
