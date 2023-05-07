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
import models.BonPlan;
import models.User;
import util.Statics;

/**
 *
 * @author Nadia
 */
public class BonPlanService {
    

//var
    ConnectionRequest req;
    static BonPlanService instance = null;

    //util
    boolean resultOK = false;
    List<BonPlan> p = new ArrayList<>();

    //Constructor
    private BonPlanService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static BonPlanService getInstance() {
        if (instance == null) {
            instance = new BonPlanService();
        }

        return instance;
    }

    //ACTIONS
    //Add


    //Affichage des produits dans un panier (Read)
    public List<BonPlan> fetchBonPlan() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/admAllBonplanJson";
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                p =parseBonPlan(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return p;
    }

    //Parse
public List<BonPlan> parseBonPlan(String jsonText) {

        //var
        p = new ArrayList<>();
        
        //DO
        //1
        JSONParser jp = new JSONParser();
        
        try {
            
            //2
            Map<String, Object> BonPlanJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) BonPlanJSON.get("root");
        
            //4
            for (Map<String, Object> item : list) {
                
                BonPlan lp = new BonPlan();
              lp.setId_bonplan((int) (double) item.get("id"));
              
                lp.setNom_bonplan((String) item.get("nom_bonplan"));
                  lp.setDescription((String) item.get("description"));
                      lp.setRue((String) item.get("rue"));
                          lp.setGouvernorat((String) item.get("gouvernorat"));
                             lp.setVille((String) item.get("ville"));
                             
                  lp.setEtat((String) item.get("etat"));
                    lp.setType((String) item.get("type"));
                      lp.setImage((String) item.get("image"));
                              

       
              
             
                
                p.add(lp);
            }
        
        } catch (IOException ex) {
        }
        
        //End
        return p;
    }

    
    //Afficher detail d'un produit
    public boolean supprimer(int id ) {
        String url = Statics.BASE_URL +"/deleteBonplanJson" ;
        req.addArgument("id_bonplan", String.valueOf(id));
    
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

