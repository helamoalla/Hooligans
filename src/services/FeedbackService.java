/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.SessionManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Feedback;
import util.Statics;

/**
 *
 * @author azizh
 */
public class FeedbackService {
     public static FeedbackService instance= null;
    
    public static boolean resultOk=true;
    
    
     //initillisation connection request
    private ConnectionRequest req;
    
    public static FeedbackService getInstance(){
        if (instance ==null)
            instance = new FeedbackService();
        return instance ;
    }
    
    
    private FeedbackService(){
        req = new ConnectionRequest();
    }
    
    //ADD COMMENT METHOD :
        public void addComment(int id_post,Feedback feedback){
        //&post_type=blog
        //String url = Statics.Base_URL+"/comment/newjson/"+comment.getId_post()+"?&id_user=1&comment="+comment.getComment();
        Boolean t=false;
        if(feedback.isReport()=="true"){
            t=true;
        }
        String url = Statics.BASE_URL+"/addFeedback/"+id_post+"?&id_user="+String.valueOf(SessionManager.getId())+"&comment="+feedback.getCommentaire()+"&rate="+feedback.getRate()+"&report="+feedback.isReport();
        
        req.setUrl(url);
        req.addResponseListener((e)->{
            
            String str = new String (req.getResponseData());//Response JSON THAT WE SAW IN THE NAVIGATOR 
            System.out.println("data=="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//EXECUTION OF THE REQUEST OR NOTHING GO THROUGH 
    }
        
    //DISPLAY COMMENT
//        public void displayComment(Comment comment){
//        String url = Statics.Base_URL+"/comment/JSONCOMMENTSHOW/26?";
//        req.setUrl(url);
//        req.addResponseListener((e)->{
//            
//            String str = new String (req.getResponseData());//Response JSON THAT WE SAW IN THE NAVIGATOR 
//            System.out.println("data=="+str);
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);//EXECUTION OF THE REQUEST OR NOTHING GO THROUGH 
//        }
        
        
        
        //For Test
      //  int postId = 1;
    public ArrayList<Feedback> displayComment(int postId) {
    ArrayList<Feedback> result = new ArrayList<Feedback>();
//    String url = Statics.Base_URL + "/comment/JSONCOMMENTSHOW/26?/" + postId;
String url = Statics.BASE_URL + "/allFeedsJson/" + postId;
    req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> mapComment = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapComment.get("root");
                for (Map<String, Object> obj : listOfMaps) {
                    Feedback comment = new Feedback();
                    comment.setId_user((int)Float.parseFloat(String.valueOf(obj.get("id_user"))));
                    float id_comment = Float.parseFloat(String.valueOf(obj.get("id_comment")));
                    comment.setId_feedback((int) id_comment);
                    comment.setCommentaire(obj.get("comment").toString());
             
                    comment.setRate((int)Float.parseFloat(obj.get("rate").toString()));
                     comment.setNom(obj.get("nom").toString());
                     comment.setImg(obj.get("img").toString());

                    //comment.setRate((int)Integer.parseInt(obj.get("rate").toString()));
                  comment.setReport((String.valueOf(obj.get("report"))));

                    
                    // To get the user id:
                    // float id_user = Float.parseFloat(((Map<String, Object>)obj.get("user")).get("id_user").toString());
                    // comment.setId_user((int) id_user);
                    result.add(comment);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return result;
}
    
    
    
    
    //DELETE CATEGORY 
    public boolean deleteComment(int id_comment) {
        String url = Statics.BASE_URL+"/deleteFeedJson/"+id_comment;
        
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    
    
    //UPDATE COMMENT 
       public boolean updateComment(Feedback comment){
        String url  = Statics.BASE_URL+"/editFeedJson/"+comment.getId_feedback()+"?comment="+comment.getCommentaire()+"&rate="+comment.getRate()+"&report="+comment.isReport();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ; //CODE RESPONSE HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    
}
