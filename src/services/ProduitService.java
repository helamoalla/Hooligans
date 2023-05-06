/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import models.Categorie;
import models.Produit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import util.Statics;

/**
 *
 * @author Nadia
 */
public class ProduitService {
    

//var
    ConnectionRequest req;
    static ProduitService instance = null;

    //util
    boolean resultOK = false;
    List<Produit> p = new ArrayList<>();

    //Constructor
    private ProduitService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static ProduitService getInstance() {
        if (instance == null) {
            instance = new ProduitService();
        }

        return instance;
    }

    //ACTIONS
    //Add


    //Affichage des produits dans un panier (Read)
    public List<Produit> fetchProduits() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/userafficheproduitJSON";
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                p =parseProduit(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return p;
    }

    //Parse
public List<Produit> parseProduit(String jsonText) {

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
                
                Produit lp = new Produit();
              lp.setId((int) (double) item.get("id"));
               
                lp.setCategorie((Categorie) item.get("Categorie"));
                lp.setPrix_prod((float) (double) item.get("prix_prod"));
                lp.setQuantite_prod((int) (double) item.get("quantite_prod"));
                lp.setNom_prod((String) item.get("nom_prod"));
                lp.setDescription_prod((String) item.get("description_prod"));
                lp.setImage((String) item.get("image"));
                
                p.add(lp);
            }
        
        } catch (IOException ex) {
        }
        
        //End
        return p;
    }

    
    //Afficher detail d'un produit
    public boolean Afficherdetail(int id ) {
        String url = Statics.BASE_URL +"/useraffichedetailproduitNJson?id="+(int)id;
        
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

