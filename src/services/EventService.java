/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Event;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
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
public class EventService {
         //var
    ConnectionRequest req;
    static EventService instance = null;

    //util
    boolean resultOK = false;
    List<Event> p = new ArrayList<>();

    //Constructor
    public EventService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }

        return instance;
    }

    public void Create(TextField NomEvent,TextField DateDebut,TextField DateFin,TextField TypeEvent ,TextField LieuEvent,TextField PrixEvent, TextField Image ) {
        
     
        
        String url = Statics.BASE_URL+"/user/signup?NomEvent="+NomEvent.getText()+"&DateDebut="+DateDebut.getText().toString()+"&DateFin="+DateFin.getText().toString()+"&TypeEvent="+TypeEvent.getText().toString()+"&LieuEvent="+LieuEvent.getText().toString()+"&PrixEvent="+PrixEvent.getText().toString()+"&Image="+Image.getText().toString();
        
        req.setUrl(url);
       
//        Control saisi
//        if(UserName.getText().equals(" ") && Password.getText().equals(" ") && Email.getText().equals(" ")) {
//            
//            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
//            
//        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData(); 
            String responseData = new String(data);
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    
    public List<Event> fetchEvent() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/getUjson";
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                p = parseEvent(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return p;
    }

    //Parse
    public List<Event> parseEvent(String jsonText) {

        //var
        p = new ArrayList<>();
        
        //DO
        //1
        JSONParser jp = new JSONParser();
        
        try {
            
            //2
            Map<String, Object> EventListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) EventListJSON.get("root");
        
            //4
            for (Map<String, Object> item : list) {
                
               Event lp = new Event();
             lp.setId_e((double) item.get("id"));
               
                lp.setNom_event((String) item.get("nom_event"));
                lp.setDate_debut((String) item.get("date_debut"));
                            lp.setDate_fin((String) item.get("date_fin"));
lp.setLieu_event((String) item.get("lieu_event"));
lp.setType_event((String) item.get("type_event"));
lp.setImage((String) item.get("image_event"));
lp.setPrix((double) item.get("prix_event"));
                 p.add(lp);
            }
        
        } catch (IOException ex) {
        }
        
        //End
        return p;
    }
    
    //Supprimer Ligne Panier(Produit du panier)
    public boolean SuppEvent(int id ) {
        String url = Statics.BASE_URL +"/supprimerjson?id="+(int)id;
        
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
        public boolean addTask(Event t) {

        //1
        String addURL = Statics.BASE_URL + "/addjson/new";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("nom_event", t.getNom_event());
        req.addArgument("date_debut", t.getDate_debut());
        req.addArgument("date_fin", t.getDate_fin());
        req.addArgument("lieu_event", t.getLieu_event());
        req.addArgument("type_event", t.getType_event());
        req.addArgument("image_event", t.getImage());
        req.addArgument("prix_event", t.getPrix().toString());


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
    
         //payer
    public boolean payer(double id ) {
           //1
        String addURL = Statics.BASE_URL + "/ayoubJSON";

        //2
        req.setUrl(addURL);

        //3
    req.setPost(false);

        //4
        req.addArgument("id_event",String.valueOf(id));
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

        return resultOK;    }
    
        
}
