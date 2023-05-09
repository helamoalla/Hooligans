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
import com.mycompany.myapp.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Maintenance;
import util.Statics;

/**
 *
 * @author choua
 */
public class MaintenanceService {
    //var
    ConnectionRequest req;
    static MaintenanceService instance = null;

    //util
    boolean resultOK = false;
    List<Maintenance> p = new ArrayList<>();

    //Constructor
    public MaintenanceService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static MaintenanceService getInstance() {
        if (instance == null) {
            instance = new MaintenanceService();
        }

        return instance;
    }

    //ACTIONS
    //Add


    //Affichage des produits dans un panier (Read)
    public List<Maintenance> fetchMaintenance() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/afficheMUjson";
        
        //2
        req.setUrl(fetchURL);
       

        //3
        req.setPost(false);
         req.addArgument("id_user",String.valueOf(SessionManager.getId()));
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                p = parseMaintenance(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return p;
    }

    //Parse
    public List<Maintenance> parseMaintenance(String jsonText) {

        //var
        p = new ArrayList<>();
        
        //DO
        //1
        JSONParser jp = new JSONParser();
        
        try {
            
            //2
            Map<String, Object> MaintenanceListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) MaintenanceListJSON.get("root");
        
            //4
            for (Map<String, Object> item : list) {
                
                Maintenance lp = new Maintenance();
            // lp.setAmortisseur((boolean) item.get("amortisseur"));
               
              //  lp.setBatterie((boolean) item.get("Batterie"));
                lp.setDate_maintenance((String) item.get("date_maintenance"));
                lp.setId_maintenance((double) item.get("id"));
               // lp.setUser((User) item.get("user"));
              lp.setPatin((String) item.get("patin"));
               lp.setAmortisseur((String) item.get("amortisseur"));
                lp.setBatterie((String) item.get("batterie"));
                 lp.setDuride((String) item.get("duride"));
                  lp.setEssuie_glace((String) item.get("essuie_glace"));
                   lp.setFeu_d_eclairage((String) item.get("feu_d_eclairage"));
                    lp.setFiltre((String) item.get("filtre"));
                     lp.setFrein_main((String) item.get("frein_main"));
                      lp.setFuite_d_huile((String) item.get("fuite_d_huile"));
                       lp.setPanne_moteur((String)item.get("panne_moteur"));
                        lp.setPompe_a_eau((String) item.get("pompe_a_eau"));
                         lp.setRadiateur((String) item.get("radiateur"));
                          lp.setVentilateur((String) item.get("ventilateur"));
                           lp.setVidange((String) item.get("vidange"));
                
                p.add(lp);
            }
        
        } catch (IOException ex) {
        }
        
        //End
        return p;
    }
    
    ////ajout maintenance
     public boolean addMaintenance(Maintenance t) {

        //1
        String addURL = Statics.BASE_URL + "/ajouterMjson/new";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("Amortisseur", t.getAmortisseur());
        req.addArgument("Batterie", t.getBatterie());
            req.addArgument("Duride", t.getDuride());
        req.addArgument("Essuie_glace", t.getEssuie_glace());
            req.addArgument("Feu_d_eclairage", t.getFeu_d_eclairage());
        req.addArgument("Filtre", t.getFiltre());
           req.addArgument("Frein_main", t.getFrein_main());
            req.addArgument("Fuite_d_huile", t.getFuite_d_huile());
             req.addArgument("Panne_moteur", String.valueOf(t.getPanne_moteur()));
              req.addArgument("Patin", t.getPatin());
               req.addArgument("Pompe_a_eau", t.getPompe_a_eau());
                req.addArgument("Radiateur", t.getRadiateur());
                 req.addArgument("Ventilateur", t.getVentilateur());
                  req.addArgument("Vidange", t.getVidange());
req.addArgument("id_user",String.valueOf(SessionManager.getId()));

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
    
    //Supprimer Ligne Panier(Produit du panier)
    public boolean SuppMaintenance(int id ) {
        String url = Statics.BASE_URL +"/supprimerMjson?id="+(int)id;
        
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