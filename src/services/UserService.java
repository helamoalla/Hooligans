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
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;

import com.mycompany.myapp.SessionManager;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import models.Event;
import models.User;
import util.Statics;

/**
 *
 * @author helam
 */
public class UserService {
    
    
 


    
     
    boolean result = false;
   //var
    ConnectionRequest req;
    static UserService instance = null;

    //util
    boolean resultOK = false;
    List<User> p = new ArrayList<>();

    //Constructor
    public UserService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }

        return instance;
    }

    //Signup
    public void signup(TextField username,TextField userprenom,TextField numteluser,TextField adresseuser, ComboBox<String> roles ,TextField emailuser,TextField mdpuser,TextField ageuser, Resources res ) {
        
     
        
        String url = Statics.BASE_URL+"/registerjson/new/?nom="+username.getText().toString()+"&prenom="+userprenom.getText().toString()+
                "&numTel="+numteluser.getText().toString()+"&adresse="+adresseuser.getText().toString()+"&idRole="+roles.getSelectedItem().toString()+"&email="+emailuser.getText().toString()+"&mdp="+mdpuser.getText().toString()+"&age="+ageuser.getText().toString();
        
        req.setUrl(url);
      
        //Control saisi
        if(username.getText().equals(" ") && mdpuser.getText().equals(" ") && emailuser.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    
    
    //SignIn
    
   public boolean signIn(TextField username, TextField password) {
    String url = Statics.BASE_URL + "/user/signeIn?email=" + username.getText().toString() + "&mdp=" + password.getText().toString();
    req = new ConnectionRequest(url, false);
    req.setUrl(url);
   
   
    
    req.addResponseListener((e) ->{
        JSONParser j = new JSONParser();
        String json = new String(req.getResponseData()) + "";
        
        try {
            if((json.equals("incorrect password"))||(json.equals("user not found"))) {
                
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
                
            
            }
            else {
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                //Session 
                float id = Float.parseFloat(user.get("idUser").toString());
                SessionManager.setId((int)id);
                SessionManager.setMdp(user.get("mdp").toString());
                SessionManager.setNom(user.get("nom").toString());
                SessionManager.setEmail(user.get("email").toString());
                
                result = true;
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    return result;
}

 
   
   
  
     public boolean addTask(User t) {

        //1
        String addURL = Statics.BASE_URL + "/user/registerjson/new";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("nom", t.getNom());
        req.addArgument("prenom", t.getPrenom());
        req.addArgument("numTel", String.valueOf(t.getNum_tel()));
        req.addArgument("email", t.getEmail());
        req.addArgument("mdp", t.getMdp());
        req.addArgument("adresse", t.getAdresse());
        req.addArgument("age", String.valueOf(t.getAge()));
       

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
}
