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
import models.Ticket;
import util.Statics;

/**
 *
 * @author choua
 */
public class TicketService {
         //var
    ConnectionRequest req;
    static TicketService instance = null;

    //util
    boolean resultOK = false;
    List<Ticket> p = new ArrayList<>();

    //Constructor
    public TicketService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static TicketService getInstance() {
        if (instance == null) {
            instance = new TicketService();
        }

        return instance;
    }
//
//    public void Create(TextField NomEvent,TextField DateDebut,TextField DateFin,TextField TypeEvent ,TextField LieuEvent,TextField PrixEvent, TextField Image ) {
//        
//     
//        
//        String url = Statics.BASE_URL+"/user/signup?NomEvent="+NomEvent.getText()+"&DateDebut="+DateDebut.getText().toString()+"&DateFin="+DateFin.getText().toString()+"&TypeEvent="+TypeEvent.getText().toString()+"&LieuEvent="+LieuEvent.getText().toString()+"&PrixEvent="+PrixEvent.getText().toString()+"&Image="+Image.getText().toString();
//        
//        req.setUrl(url);
//       
//        Control saisi
//        if(UserName.getText().equals(" ") && Password.getText().equals(" ") && Email.getText().equals(" ")) {
//            
//            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
//            
//        }
        
        //hethi wa9t tsir execution ta3 url 
//        req.addResponseListener((e)-> {
//         
//            //njib data ly7atithom fi form 
//            byte[]data = (byte[]) e.getMetaData(); 
//            String responseData = new String(data);
//            
//            System.out.println("data ===>"+responseData);
//        }
//        );
//        
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        
//            
//        
//    }
    
    public List<Ticket> fetchTicket() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/getTjson?id_user="+String.valueOf(SessionManager.getId());
        
        //2
        req.setUrl(fetchURL);
        // req.addArgument("id_user",String.valueOf(SessionManager.getId()));
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                p = parseTicket(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return p;
    }

    //Parse
    public List<Ticket> parseTicket(String jsonText) {

        //var
        p = new ArrayList<>();
        
        //DO
        //1
        JSONParser jp = new JSONParser();
        
        try {
            
            //2
            Map<String, Object> TicketListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) TicketListJSON.get("root");
        
            //4
            for (Map<String, Object> item : list) {
                
               Ticket lp = new Ticket();
            lp.setId_ticket((double) item.get("id"));
               
                lp.setImage((String) item.get("image_qr"));
             lp.setNum_ticket((double) item.get("num_ticket"));
                
               
                 p.add(lp);
            }
        
        } catch (IOException ex) {
        }
        
        //End
        return p;
    }
    
//    //Supprimer Ligne Panier(Produit du panier)
//    public boolean SuppEvent(int id ) {
//        String url = Statics.BASE_URL +"/supprimerjson?id="+(int)id;
//        
//        req.setUrl(url);
//        
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                    resultOK = req.getResponseCode() == 200;
//                    req.removeResponseCodeListener(this);
//            }
//        });
//        
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return  resultOK;
//    }
//        public boolean addTask(Event t) {
//
//        //1
//        String addURL = Statics.BASE_URL + "/addjson/new";
//
//        //2
//        req.setUrl(addURL);
//
//        //3
//        req.setPost(false);
//
//        //4
//        req.addArgument("nom_event", t.getNom_event());
//        req.addArgument("date_debut", t.getDate_debut());
//        req.addArgument("date_fin", t.getDate_fin());
//        req.addArgument("lieu_event", t.getLieu_event());
//        req.addArgument("type_event", t.getType_event());
//        req.addArgument("image_event", t.getImage());
//        req.addArgument("prix_event", t.getPrix().toString());
//
//
//        //5
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200;
//                req.removeResponseListener(this);
//            }
//        });
//
//        NetworkManager.getInstance().addToQueueAndWait(req);
//
//        return resultOK;
//    }
//    
//         //payer
//    public boolean payer(double id ) {
//           //1
//        String addURL = Statics.BASE_URL + "/ayoubJSON";
//
//        //2
//        req.setUrl(addURL);
//
//        //3
//    req.setPost(false);
//
//        //4
//        req.addArgument("id_event",String.valueOf(id));
//         req.addArgument("id_user",String.valueOf(SessionManager.getId()));
//        
//        
//
//       //5
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200;
//                req.removeResponseListener(this);
//            }
//        });
//
//        NetworkManager.getInstance().addToQueueAndWait(req);
//
//        return resultOK;    }
    
          public boolean imprimer(double id ) {
           //1
        String addURL = Statics.BASE_URL + "/pdfJson";

        //2
        req.setUrl(addURL);

        //3
    req.setPost(false);

        //4
        req.addArgument("id_ticket",String.valueOf(id));
         //req.addArgument("id_user",String.valueOf(SessionManager.getId()));
        
        

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
