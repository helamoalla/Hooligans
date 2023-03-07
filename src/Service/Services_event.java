/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.InterfaceCRUD;
import Model.Event;
import Model.Ticket;
import Model.User;
import Util.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author azizh
 */
public class Services_event implements InterfaceCRUD<Event> {

Connection cnx = MyConnection.getInstance().getCnx();

    
@Override
    public void insert(Event e) {
         try {
            String req = "INSERT INTO `event`(`nom_event`, `date_debut`, `date_fin`, `lieu_event`, `type_event` , `image_event` , `prix_event`) VALUES ('"+ e.getNom_event()+"','"+e.getDate_debut()+"','"+e.getDate_fin()+"','"+e.getLieu_event()+"','"+e.getType_event()+"','"+ e.getImage()+"','"+e.getPrix()+"')";
            Statement st = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.executeUpdate(req);
            System.out.println("Event ajouté avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(Services_event.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
                     Event e =readById(id);
            Service_ticket sert=new Service_ticket();
        ArrayList<Ticket> lt=sert.chercher(e.getId_e());
      UserService us =new UserService();
      for(Ticket t: lt){
          System.out.println(t.getImage());
         User u= us.readById(t.getUser().getId_user());
         u.setQuota(u.getQuota()+e.getPrix());
         us.update(u);
        String to = u.getEmail(); // Adresse email du destinataire
          System.out.println(u.getEmail());
        String from = "ayoub.bbarnat@gmail.com"; // Adresse email de l'expéditeur
        String host = "smtp.gmail.com"; // Serveur SMTP de votre fournisseur d'email
        // Paramètres SMTP
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
         // Création d'une session SMTP sécurisée
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ayoub.bbarnat@gmail.com", "flaqdmkgsnieumou"); // Adresse email de l'expéditeur et mot de passe
            }
        });
        try {
            // Création d'un message email
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Evenement annule");
            message.setText(" l'evenement "+e.getNom_event()+" a été annule , Votre solde a ete mis a jour ");

            // Envoi de l'email
            Transport.send(message);
            System.out.println("Email envoyé avec succès");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }}
        String req="DELETE FROM event WHERE (`id_e`='"+id+"' )";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Event supprimé avec succes");
   
    } catch (SQLException ex) {
        Logger.getLogger(Services_event.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

   
@Override
    public void update(Event e) {
        
 Service_ticket sert=new Service_ticket();
    try {
        String req="UPDATE `event` SET `nom_event`='"+e.getNom_event()+"', `date_debut`='"+e.getDate_debut()+"', `date_fin`='"+e.getDate_fin()+"', `lieu_event`='"+e.getLieu_event()+"' ,`type_event`='"+e.getType_event()+"' ,`image_event`='"+e.getImage()+"' ,`prix_event`="+e.getPrix()+"WHERE `id_e`="+e.getId_e()+" ";
        Statement st = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.executeUpdate(req);
        System.out.println("Event modifie avec succes");
      ArrayList<Ticket> lt=sert.chercher(e.getId_e());
      UserService us =new UserService();
      for(Ticket t: lt){
          System.out.println(t.getImage());
         User u= us.readById(t.getUser().getId_user());
        String to = u.getEmail(); // Adresse email du destinataire
          System.out.println(u.getEmail());
        String from = "ayoub.bbarnat@gmail.com"; // Adresse email de l'expéditeur
        String host = "smtp.gmail.com"; // Serveur SMTP de votre fournisseur d'email
        // Paramètres SMTP
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
         // Création d'une session SMTP sécurisée
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ayoub.bbarnat@gmail.com", "flaqdmkgsnieumou"); // Adresse email de l'expéditeur et mot de passe
            }
        });
        try {
            // Création d'un message email
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Sujet de l'email");
            message.setText("la date de l'evenement a été modifier , l'evenement sera planifie pour "+e.getDate_debut()+" et se termine le "+e.getDate_fin() );

            // Envoi de l'email
            Transport.send(message);
            System.out.println("Email envoyé avec succès");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
          
      }
        
        
    } catch (SQLException ex) {
        Logger.getLogger(Services_event.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public ArrayList readAll() {
            List<Event> le=new ArrayList<>(); 
        try {
        String req="SELECT * FROM `event`";
        Statement ste = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet res=ste.executeQuery(req);
        while(res.next()){
            Event e=new Event();
            e.setId_e(res.getInt(1));
            e.setNom_event(res.getString(2));
            e.setDate_debut(res.getDate(3));
            e.setDate_fin(res.getDate(4));
            e.setLieu_event(res.getString(5));
            e.setType_event(res.getString(6));
            e.setImage(res.getString(7));
             e.setPrix(res.getDouble(8));
            
            le.add(e);
            
        }   } catch (SQLException ex) {
        Logger.getLogger(Services_event.class.getName()).log(Level.SEVERE, null, ex);
    }


    return (ArrayList<Event>) le;
    }

    @Override
    public Event readById(int id) {
       Event e=new Event();
        try {
        String req="SELECT * FROM `event` WHERE `id_e`='"+id+"'";
        Statement ste = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet res=ste.executeQuery(req);
        while(res.next()){
            
            e.setId_e(res.getInt(1));
            e.setNom_event(res.getString(2));
            e.setDate_debut(res.getDate(3));
            e.setDate_fin(res.getDate(4));
            e.setLieu_event(res.getString(5));
            e.setType_event(res.getString(6));
            e.setImage(res.getString(7));
                         e.setPrix(res.getDouble(8));

            
        }
           } catch (SQLException ex) {
        Logger.getLogger(Services_event.class.getName()).log(Level.SEVERE, null, ex);
    }
       return e;
    }

    @Override
    public ArrayList sortBy(String nom_column, String Asc_Dsc) {
List<Event> le=new ArrayList<>(); 
        try {
        String req="SELECT * FROM `event` ORDER BY `"+nom_column+"` "+Asc_Dsc+"";
        Statement ste = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet res=ste.executeQuery(req);
        while(res.next()){
            Event e=new Event();
            e.setId_e(res.getInt(1));
            e.setNom_event(res.getString(2));
            e.setDate_debut(res.getDate(3));
            e.setDate_fin(res.getDate(4));
            e.setLieu_event(res.getString(5));
            e.setType_event(res.getString(6));
              e.setImage(res.getString(7));
                         e.setPrix(res.getDouble(8));
            le.add(e);
            
        }   } catch (SQLException ex) {
        Logger.getLogger(Services_event.class.getName()).log(Level.SEVERE, null, ex);
    }
     return (ArrayList<Event>) le; }
    
   

  
    }