/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Interfaces.InterfaceCRUD;
import Models.GarageC;
import Models.Maintenance;
import util.Maconnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author helam
 */
public class ServiceMaintenance implements InterfaceCRUD<Maintenance>{
Connection cnx = Maconnexion.getInstance().getCnx();
    @Override
    public void insert(Maintenance m) {
try {
              String req = "INSERT INTO `maintenance`(`id_user`, `panne_moteur`, `pompe_a_eau` , `patin`, `essuie_glace`, `radiateur`, `ventilateur`, `duride`, `fuite_d_huile`, `vidange`, `filtre`, `batterie`, `amortisseur`, `frein_main`, `feu_d_eclairage`, `Autre`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
              PreparedStatement ps = cnx.prepareStatement(req);
              ps.setInt(1, m.getId_user());
             // ps.setDate(2, (Date) m.getDate_maintenance());
              ps.setBoolean(2, m.isPanne_moteur());
              ps.setBoolean(3, m.isPompe_a_eau());
              ps.setBoolean(4, m.isPatin());
              ps.setBoolean(5, m.isEssuie_glace());
              ps.setBoolean(6, m.isRadiateur());
              ps.setBoolean(7, m.isVentilateur());
              ps.setBoolean(8,m.isDuride());
              ps.setBoolean(9, m.isFuite_d_huile());
              ps.setBoolean(10, m.isVidange());
              ps.setBoolean(11, m.isFiltre());
              ps.setBoolean(12, m.isBatterie());
              ps.setBoolean(13, m.isAmortisseur());
              ps.setBoolean(14, m.isFrein_main());
              ps.setBoolean(15, m.isFeu_d_eclairage());
              ps.setString(16, m.getAutre());
              ps.executeUpdate();
              System.out.println("Maintenace ajouter/demander avec succes!");
          } catch (SQLException ex) {
              Logger.getLogger(ServiceGarageC.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void delete(int id) {
try {
              String req="DELETE FROM `maintenance` WHERE (`id_maintenance`='"+id+"' )";
              Statement st = cnx.createStatement();
              st.executeUpdate(req);
              System.out.println("Maintenance supprimé avec succes");
          } catch (SQLException ex) {
              Logger.getLogger(ServiceGarageC.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void update(Maintenance m) {
try {
              String req="UPDATE `maintenance` SET `panne_moteur`="+m.isPanne_moteur()+" ,`pompe_a_eau`="+m.isPompe_a_eau()+" ,`patin`="+m.isPatin()+" ,`essuie_glace`="+m.isEssuie_glace()+" ,`radiateur`="+m.isRadiateur()+" ,`ventilateur`="+m.isVentilateur()+" ,`duride`="+m.isDuride()+" ,`fuite_d_huile`="+m.isFuite_d_huile()+" ,`vidange`="+m.isVidange()+" ,`filtre`="+m.isFiltre()+" ,`batterie`="+m.isBatterie()+", `amortisseur`="+m.isAmortisseur()+" ,`frein_main`="+m.isFrein_main()+" ,`feu_d_eclairage`="+m.isFeu_d_eclairage()+" ,`Autre`='"+m.getAutre()+"' WHERE `id_user`="+m.getId_user()+" ";
              Statement st = cnx.createStatement();
              st.executeUpdate(req);
              System.out.println("maintenance modifier avec succes");
          } catch (SQLException ex) {
              Logger.getLogger(ServiceGarageC.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public ArrayList<Maintenance> readAll() {
List<Maintenance> lm=new ArrayList<>();
        try {
              String req="SELECT * FROM `maintenance` ";
              Statement ste = cnx.createStatement();
              ResultSet res=ste.executeQuery(req);
              while(res.next()){
                  
                  Maintenance m=new Maintenance();
                  m.setId_maintenance(res.getInt(1));
                  m.setId_user(res.getInt(2));
                  m.setDate_maintenance(res.getDate(3));
                  m.setPanne_moteur(res.getBoolean(4));
                  m.setPompe_a_eau(res.getBoolean(5));
                  m.setPatin(res.getBoolean(6));
                  m.setEssuie_glace(res.getBoolean(7));
                  m.setRadiateur(res.getBoolean(8));
                  m.setVentilateur(res.getBoolean(9));
                  m.setDuride(res.getBoolean(10));
                  m.setFuite_d_huile(res.getBoolean(11));
                  m.setVidange(res.getBoolean(12));
                  m.setFiltre(res.getBoolean(13));
                  m.setBatterie(res.getBoolean(14));
                  m.setAmortisseur(res.getBoolean(15));
                  m.setFrein_main(res.getBoolean(16));
                  m.setFeu_d_eclairage(res.getBoolean(17));
                  m.setAutre(res.getString(18));
                  lm.add(m);
              }
              
          } catch (SQLException ex) {
              Logger.getLogger(ServiceGarageC.class.getName()).log(Level.SEVERE, null, ex);
          }
           return (ArrayList<Maintenance>) lm;

    }

    @Override
    public Maintenance readById(int id) {
 Maintenance m=new Maintenance();
        try {
              String req="SELECT * FROM `maintenance` WHERE `id_maintenance`='"+id+"'";
              Statement ste = cnx.createStatement();
              ResultSet res=ste.executeQuery(req);
            
                   while(res.next()){
                 m.setId_maintenance(res.getInt(1));
                  m.setId_user(res.getInt(2));
                  m.setDate_maintenance(res.getDate(3));
                  m.setPanne_moteur(res.getBoolean(4));
                  m.setPompe_a_eau(res.getBoolean(5));
                  m.setPatin(res.getBoolean(6));
                  m.setEssuie_glace(res.getBoolean(7));
                  m.setRadiateur(res.getBoolean(8));
                  m.setVentilateur(res.getBoolean(9));
                  m.setDuride(res.getBoolean(10));
                  m.setFuite_d_huile(res.getBoolean(11));
                  m.setVidange(res.getBoolean(12));
                  m.setFiltre(res.getBoolean(13));
                  m.setBatterie(res.getBoolean(14));
                  m.setAmortisseur(res.getBoolean(15));
                  m.setFrein_main(res.getBoolean(16));
                  m.setFeu_d_eclairage(res.getBoolean(17));
                  m.setAutre(res.getString(18));
                   }
          } catch (SQLException ex) {
              Logger.getLogger(ServiceGarageC.class.getName()).log(Level.SEVERE, null, ex);
          }
           return m;

    }

    @Override
    public ArrayList<Maintenance> sortBy(String nom_column, String Asc_Dsc) {
List<Maintenance> lm=new ArrayList<>();
        try {
              String req="SELECT * FROM `maintenance` ORDER BY `"+nom_column+"` "+Asc_Dsc+" ";
              Statement ste = cnx.createStatement();
              ResultSet res=ste.executeQuery(req);
              while(res.next()){
                  
                  Maintenance m=new Maintenance();
                  m.setId_maintenance(res.getInt(1));
                  m.setId_user(res.getInt(2));
                  m.setDate_maintenance(res.getDate(3));
                  m.setPanne_moteur(res.getBoolean(4));
                  m.setPompe_a_eau(res.getBoolean(5));
                  m.setPatin(res.getBoolean(6));
                  m.setEssuie_glace(res.getBoolean(7));
                  m.setRadiateur(res.getBoolean(8));
                  m.setVentilateur(res.getBoolean(9));
                  m.setDuride(res.getBoolean(10));
                  m.setFuite_d_huile(res.getBoolean(11));
                  m.setVidange(res.getBoolean(12));
                  m.setFiltre(res.getBoolean(13));
                  m.setBatterie(res.getBoolean(14));
                  m.setAmortisseur(res.getBoolean(15));
                  m.setFrein_main(res.getBoolean(16));
                  m.setFeu_d_eclairage(res.getBoolean(17));
                  m.setAutre(res.getString(18));
                  lm.add(m);
              }
              
          } catch (SQLException ex) {
              Logger.getLogger(ServiceGarageC.class.getName()).log(Level.SEVERE, null, ex);
          }
           return (ArrayList<Maintenance>) lm;

    }

    @Override
    public ArrayList<Maintenance> chercher(int id) {
List<Maintenance> lm=new ArrayList<>();
        try {
              String req="SELECT * FROM `maintenance` WHERE ((`id_user`='"+id+"') AND (extract(day from CURRENT_TIMESTAMP) - extract(day from `date_maintenance`) <= 2 )) ";
              Statement ste = cnx.createStatement();
              ResultSet res=ste.executeQuery(req);
            
                   while(res.next()){
                       Maintenance m=new Maintenance();
                 m.setId_maintenance(res.getInt(1));
                  m.setId_user(res.getInt(2));
                  m.setDate_maintenance(res.getDate(3));
                  m.setPanne_moteur(res.getBoolean(4));
                  m.setPompe_a_eau(res.getBoolean(5));
                  m.setPatin(res.getBoolean(6));
                  m.setEssuie_glace(res.getBoolean(7));
                  m.setRadiateur(res.getBoolean(8));
                  m.setVentilateur(res.getBoolean(9));
                  m.setDuride(res.getBoolean(10));
                  m.setFuite_d_huile(res.getBoolean(11));
                  m.setVidange(res.getBoolean(12));
                  m.setFiltre(res.getBoolean(13));
                  m.setBatterie(res.getBoolean(14));
                  m.setAmortisseur(res.getBoolean(15));
                  m.setFrein_main(res.getBoolean(16));
                  m.setFeu_d_eclairage(res.getBoolean(17));
                  m.setAutre(res.getString(18));
                  lm.add(m);
                   }
          } catch (SQLException ex) {
              Logger.getLogger(ServiceGarageC.class.getName()).log(Level.SEVERE, null, ex);
          }
           return (ArrayList<Maintenance>) lm;  
    }
public void sendEmail(String to, String subject, String body, String attachmentFilePath) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("helamoalla91@gmail.com","jelufkavicintkva");
                
            }
          });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("helamoalla91@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));
        message.setSubject(subject);
       BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachmentFilePath);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(attachmentFilePath);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
   
}