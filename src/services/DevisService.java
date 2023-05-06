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
import models.Devis;
import models.GarageC;
import util.Statics;

/**
 *
 * @author choua
 */
public class DevisService {
        //var
    ConnectionRequest req;
    static DevisService instance = null;

    //util
    boolean resultOK = false;
    List<Devis> p = new ArrayList<>();

    //Constructor
    private DevisService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static DevisService getInstance() {
        if (instance == null) {
            instance = new DevisService();
        }

        return instance;
    }

    //ACTIONS
    //Add


    //Affichage des produits dans un panier (Read)
    public List<Devis> fetchDevis() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/affichepanierJSON";
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                p = parseDevis(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return p;
    }

    //Parse
    public List<Devis> parseDevis(String jsonText) {

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
                
                Devis lp = new Devis();
              //  lp.setId_devis((double) item.get("id"));
               // lp.setGarage((GarageC) item.get("garage"));
                lp.setTVA((double) item.get("TVA"));
                lp.setTotal((double) item.get("total"));
               
                
                p.add(lp);
            }
        
        } catch (IOException ex) {
        }
        
        //End
        return p;
    }
}
