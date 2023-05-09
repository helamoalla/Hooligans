/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Lignepanier;
import models.Panier;
import models.Produit;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.Statics;

/**
 *
 * @author choua
 */
public class PanierService {
     //var
    ConnectionRequest req;
    static PanierService instance = null;

    //util
    boolean resultOK = false;
    List<Lignepanier> p = new ArrayList<>();

    //Constructor
    private PanierService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static PanierService getInstance() {
        if (instance == null) {
            instance = new PanierService();
        }

        return instance;
    }

    //ACTIONS
    //Add


    //Affichage des produits dans un panier (Read)
    public List<Lignepanier> fetchProduitsParPanier() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/affichepanierJSON?id_user="+String.valueOf(SessionManager.getId());
        
        //2
        req.setUrl(fetchURL);
             //  req.addArgument("id_user",String.valueOf(SessionManager.getId()));

        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                p = parseLignepanier(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return p;
    }

    //Parse
    public List<Lignepanier> parseLignepanier(String jsonText) {

        //var
        p = new ArrayList<>();
        
        //DO
        //1
        JSONParser jp = new JSONParser();
        
        try {
            
            //2
            Map<String, Object> LignePanierListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) LignePanierListJSON.get("root");
        
            //4
            for (Map<String, Object> item : list) {
                
                Lignepanier lp = new Lignepanier();
                lp.setId((double) item.get("id"));
            //  lp.setPanier((Panier) item.get("panier"));
              //  lp.setProduit((Produit) item.get("produit"));
                lp.setPrix((double) item.get("prix"));
                lp.setQuantite((double) item.get("quantite"));
                lp.setNom_produit((String) item.get("nom_produit"));
                lp.setDescription_prod((String) item.get("description_prod"));
                lp.setImage((String) item.get("image"));
                
                p.add(lp);
            }
        
        } catch (IOException ex) {
        }
        
        //End
        return p;
    }
    
    //Supprimer Ligne Panier(Produit du panier)
    public boolean SuppLignePanier(int id ) {
        String url = Statics.BASE_URL +"/SupprimerProduitJSON/"+(int)id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200;
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
    
    //Vider la panier
    public boolean ViderPanier(int id ) {
        String url = Statics.BASE_URL +"/ViderPanierJSON/"+(int)id; 
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200;
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
    
    //Mettre à jour la quantite +1 d'un produit dans le panier
    public boolean QuantitePLusUN(int id ) {
        String url = Statics.BASE_URL +"/qtPlusUnJSON/"+(int)id; 
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200;
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
    
     //Mettre à jour la quantite -1 d'un produit dans le panier
    public boolean QuantiteMoinsUN(int id ) {
        String url = Statics.BASE_URL +"/qtMoinsUnJSON/"+(int)id; 
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200;
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
}