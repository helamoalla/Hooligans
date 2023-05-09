/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Commande;
import models.Panier;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import util.Statics;

/**
 *
 * @author choua
 */
public class CommandeService {
     //var
    ConnectionRequest req;
    static CommandeService instance = null;

    //util
    boolean resultOK = false;
    List<Commande> p = new ArrayList<>();

    //Constructor
    private CommandeService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static CommandeService getInstance() {
        if (instance == null) {
            instance = new CommandeService();
        }
      return instance;
    }

    //ACTIONS
    //Add


    //Affichage des commandes d'un user (Read)
    public List<Commande> fetchCommandes() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/afficheCommandeJSON?id_user="+String.valueOf(SessionManager.getId());
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                p = parseCommande(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return p;
    }

    //Parse
    public List<Commande> parseCommande(String jsonText) {

        //var
        p = new ArrayList<>();
        
        //DO
        //1
        JSONParser jp = new JSONParser();
        
        try {
            
            //2
            Map<String, Object> CommandesListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) CommandesListJSON.get("root");
        
            //4
            for (Map<String, Object> item : list) {
                
                Commande c = new Commande();
                c.setId((double) item.get("id"));
                c.setCode_postal((double) item.get("code_postal"));
                //c.setPanier((Panier) item.get("panier"));
                c.setMontant((double) item.get("montant"));
                c.setEtat_commande((String) item.get("etat_commande"));
                c.setGouvernorat((String) item.get("gouvernorat"));
                c.setVille((String) item.get("ville"));
                c.setRue((String) item.get("rue"));
                c.setDate_commande((String) item.get("date_commande"));
                
                p.add(c);
            }
        
        } catch (IOException ex) {
        }
        
        //End
        return p;
    }
    
         
      public boolean addCommande(Commande c) {

        //1
        String addURL = Statics.BASE_URL + "/passercommandeJSON?id_user="+String.valueOf(SessionManager.getId());

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("code_postal", c.getCode_postal().toString());
        req.addArgument("gouvernorat", c.getGouvernorat());
        req.addArgument("ville", c.getVille());
        req.addArgument("rue", c.getRue());
           
        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }
      
          public boolean SuppCommande(int id ) {
        String url = Statics.BASE_URL +"/suppCommandeJSON/"+(int)id;
        
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
        
       public boolean facture(int id ) {
        String url = Statics.BASE_URL +"/imprimerfacture/"+(int)id;
        
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