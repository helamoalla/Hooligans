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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.GarageC;
import util.Statics;

/**
 *
 * @author choua
 */
public class GarageCService {
     //var
    ConnectionRequest req;
    static GarageCService instance = null;

    //util
    boolean resultOK = false;
    List<GarageC> p = new ArrayList<>();

    //Constructor
    private GarageCService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static GarageCService getInstance() {
        if (instance == null) {
            instance = new GarageCService();
        }

        return instance;
    }

    //ACTIONS
    //Add


    //Affichage des produits dans un panier (Read)
    public List<GarageC> fetchGarageC() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/afficheGCjson";
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                p = parseGarageC(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return p;
    }

    //Parse
    public List<GarageC> parseGarageC(String jsonText) {

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
                
                GarageC lp = new GarageC();
                lp.setId_garage((double) item.get("id"));
            //  lp.setPanier((Panier) item.get("panier"));
              //  lp.setProduit((Produit) item.get("produit"));
                lp.setAdresse((String) item.get("adresse"));
                lp.setNom_garage((String) item.get("nom_garage"));
                lp.setNumero((double) item.get("numero"));
                  lp.setTaux_de_reduction((double) item.get("taux_de_reduction"));
                 lp.setPatin((double) item.get("patin"));
               lp.setAmortisseur((double) item.get("amortisseur"));
                lp.setBatterie((double) item.get("batterie"));
                 lp.setDuride((double) item.get("duride"));
                  lp.setEssuie_glace((double) item.get("essuie_glace"));
                   lp.setFeu_d_eclairage((double) item.get("feu_d_eclairage"));
                    lp.setFiltre((double) item.get("filtre"));
                     lp.setFrein_main((double) item.get("frein_main"));
                      lp.setFuite_d_huile((double) item.get("fuite_d_huile"));
                       lp.setPanne_moteur((double) item.get("panne_moteur"));
                        lp.setPompe_a_eau((double) item.get("pompe_a_eau"));
                         lp.setRadiateur((double) item.get("radiateur"));
                          lp.setVentilateur((double) item.get("ventilateur"));
                           lp.setVidange((double) item.get("vidange"));
                lp.setImage((String) item.get("image"));
                
                p.add(lp);
            }
        
        } catch (IOException ex) {
        }
        
        //End
        return p;
    }
    
}