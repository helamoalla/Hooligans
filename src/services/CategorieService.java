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
public class CategorieService {
    
//var
    ConnectionRequest req;
    static CategorieService instance = null;

    //util
    boolean resultOK = false;
    List<Categorie> p = new ArrayList<>();

    //Constructor
    private CategorieService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static CategorieService getInstance() {
        if (instance == null) {
            instance = new CategorieService();
        }

        return instance;
    }

    //ACTIONS
    //Add


    //Affichage des produits dans un panier (Read)
    public List<Categorie> fetchCategorie() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/admaffichecategorieJson";
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                p = parseCategorie(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return p;
    }

    //Parse
    public List<Categorie> parseCategorie(String jsonText) {

        //var
        p = new ArrayList<>();
        
        //DO
        //1
        JSONParser jp = new JSONParser();
        
        try {
            
            //2
            Map<String, Object> LigneCategorieListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) LigneCategorieListJSON.get("root");
        
            //4
            for (Map<String, Object> item : list) {
                
                Categorie lp = new Categorie();
             lp.setId((int) (double) item.get("id"));
               
                lp.setNom_categorie((String) item.get("nom_categorie"));
                lp.setDescription_categorie((String) item.get("description_categorie"));
                lp.setType_categorie((String) item.get("type_categorie"));
               
                
                p.add(lp);
            }
        
        } catch (IOException ex) {
        }
        
        //End
        return p;
    }
    
    //Supprimer Ligne Panier(Produit du panier)
    public boolean SuppCategorie(int id ) {
        String url = Statics.BASE_URL +"/adminsupprimeproduitjson?id="+(int)id;
        
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
