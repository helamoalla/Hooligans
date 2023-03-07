/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.InterfaceCRUD;
import Model.GarageC;
import Util.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


/**
 *
 * @author helam
 */
public class ServiceGarageC implements InterfaceCRUD<GarageC>{
      Connection cnx = MyConnection.getInstance().getCnx();

   /* @Override
    public void ajouterGarage(GarageC g) {
          try {
              String req = "INSERT INTO `garagec`(`nom_garage`, `adresse`, `numero`, `panne_moteur`) VALUES ('"+ g.getNom_garage()+"','"+g.getAdresse()+"',"+g.getNumero()+","+g.getPanne_moteur()+")";
              Statement st = cnx.createStatement();
              st.executeUpdate(req);
              System.out.println("Garage conventinné ajouter avec succes");
          } catch (SQLException ex) {
              Logger.getLogger(ServiceGarageC.class.getName()).log(Level.SEVERE, null, ex);
          }
    }*/

   /* @Override
    public void afficherGarage() {
        
          try {
              String req="SELECT * FROM `garagec`";
               Statement ste = cnx.createStatement();
              ResultSet res=ste.executeQuery(req);
              while(res.next()){
                  System.out.println(res.getString(2));
                  System.out.println(res.getString(3));
                  System.out.println(res.getInt(4));
                  System.out.println(res.getInt(5));
                  
              }
          } catch (SQLException ex) {
              Logger.getLogger(ServiceGarageC.class.getName()).log(Level.SEVERE, null, ex);
          }
    }*/
   public static final String ACCOUNT_SID = "AC897724b24d27bff3bfd176fce2cee841";     /// 
    public static final String AUTH_TOKEN = "a7ac03e9f2e383338173210eb0d0dc9e"; ///   
    public static final String TWILIO_NUMBER = "+12765660986";

    @Override
    public void insert(GarageC g) {
  try {
              String req = "INSERT INTO `garagec`(`nom_garage`, `adresse`, `numero`, `panne_moteur` , `pompe_a_eau` , `patin`, `essuie_glace`, `radiateur`, `ventilateur`, `duride`, `fuite_d_huile`, `vidange`, `filtre`, `batterie`, `amortisseur`, `frein_main`, `feu_d_eclairage`, `taux_de_reduction`, `image`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
              PreparedStatement ps = cnx.prepareStatement(req);
              ps.setString(1, g.getNom_garage());
              ps.setString(2, g.getAdresse());
              ps.setInt(3, g.getNumero());
              ps.setInt(4, g.getPanne_moteur());
              ps.setInt(5, g.getPompe_a_eau());
              ps.setInt(6, g.getPatin());
              ps.setInt(7, g.getEssuie_glace());
              ps.setInt(8, g.getRadiateur());
              ps.setInt(9, g.getVentilateur());
              ps.setInt(10, g.getDuride());
              ps.setInt(11, g.getFuite_d_huile());
              ps.setInt(12, g.getVidange());
              ps.setInt(13, g.getFiltre());
              ps.setInt(14, g.getBatterie());
              ps.setInt(15, g.getAmortisseur());
              ps.setInt(16, g.getFrein_main());
              ps.setInt(17, g.getFeu_d_eclairage());
              ps.setInt(18, g.getTaux_de_reduction());
              ps.setString(19, g.getImage());           
              ps.executeUpdate();
              System.out.println("garage conventinné ajouter avec succes!");
            
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Remplacez le numéro de téléphone ci-dessous par le numéro de téléphone tunisien que vous voulez envoyer le SMS
       String phoneNumber = "+216"+String.valueOf(g.getNumero());
      
      //  Message message = Message.creator(new PhoneNumber(phoneNumber),new PhoneNumber(TWILIO_NUMBER),"garage ajouter").create();
         Message message = Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(TWILIO_NUMBER),"Felicitation! la conventient avec votre garage "+g.getNom_garage()+" a été accepté.").create();
        
        System.out.println(message.getSid());
          } catch (SQLException ex) {
              Logger.getLogger(ServiceGarageC.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void delete(int id_garage) {
 try {
              String req="DELETE FROM `garagec` WHERE (`id_garage`='"+id_garage+"' )";
              Statement st = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
              st.executeUpdate(req);
              System.out.println("Garage conventinné supprimé avec succes");
          } catch (SQLException ex) {
              Logger.getLogger(ServiceGarageC.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void update(GarageC g) {
 try {
              String req="UPDATE `garagec` SET `nom_garage`='"+g.getNom_garage()+"', `adresse`='"+g.getAdresse()+"', `numero`="+g.getNumero()+", `panne_moteur`="+g.getPanne_moteur()+" ,`pompe_a_eau`="+g.getPompe_a_eau()+" ,`patin`="+g.getPatin()+" ,`essuie_glace`="+g.getEssuie_glace()+" ,`radiateur`="+g.getRadiateur()+" ,`ventilateur`="+g.getVentilateur()+" ,`duride`="+g.getDuride()+" ,`fuite_d_huile`="+g.getFuite_d_huile()+" ,`vidange`="+g.getVidange()+" ,`filtre`="+g.getFiltre()+" ,`batterie`="+g.getBatterie()+", `amortisseur`="+g.getAmortisseur()+" ,`frein_main`="+g.getFrein_main()+" ,`feu_d_eclairage`="+g.getFeu_d_eclairage()+" ,`taux_de_reduction`="+g.getTaux_de_reduction()+" ,`image`='"+g.getImage()+"'  WHERE `id_garage`='"+g.getId_garage()+"' ";
              Statement st = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
              st.executeUpdate(req);
              System.out.println("Garage conventinné modifie avec succes");
          } catch (SQLException ex) {
              Logger.getLogger(ServiceGarageC.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public ArrayList<GarageC> readAll() {
List<GarageC> lg=new ArrayList<>();
        try {
              String req="SELECT * FROM `garagec`";
              Statement ste = cnx.createStatement();
              ResultSet res=ste.executeQuery(req);
              while(res.next()){
                  
                  GarageC g=new GarageC();
                  g.setId_garage(res.getInt(1));
                  g.setNom_garage(res.getString(2));
                  g.setAdresse(res.getString(3));
                  g.setNumero(res.getInt(4));
                  g.setPanne_moteur(res.getInt(5));
                  g.setPompe_a_eau(res.getInt(6));
                  g.setPatin(res.getInt(7));
                  g.setEssuie_glace(res.getInt(8));
                  g.setRadiateur(res.getInt(9));
                  g.setVentilateur(res.getInt(10));
                  g.setDuride(res.getInt(11));
                  g.setFuite_d_huile(res.getInt(12));
                  g.setVidange(res.getInt(13));
                  g.setFiltre(res.getInt(14));
                  g.setBatterie(res.getInt(15));
                  g.setAmortisseur(res.getInt(16));
                  g.setFrein_main(res.getInt(17));
                  g.setFeu_d_eclairage(res.getInt(18));
                  g.setTaux_de_reduction(res.getInt(19));
                  g.setImage(res.getString(20));
                
                  lg.add(g);
              }
              
          } catch (SQLException ex) {
              Logger.getLogger(ServiceGarageC.class.getName()).log(Level.SEVERE, null, ex);
          }
           return (ArrayList<GarageC>) lg;
    }

    @Override
    public GarageC readById(int id_garage) {
        GarageC g=new GarageC();
        try {
              String req="SELECT * FROM `garagec` WHERE `id_garage`='"+id_garage+"'";
              Statement ste = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
              ResultSet res=ste.executeQuery(req);
            
                   while(res.next()){
                  g.setId_garage(res.getInt(1));
                  g.setNom_garage(res.getString(2));
                  g.setAdresse(res.getString(3));
                  g.setNumero(res.getInt(4));
                  g.setPanne_moteur(res.getInt(5));
                  g.setPompe_a_eau(res.getInt(6));
                  g.setPatin(res.getInt(7));
                  g.setEssuie_glace(res.getInt(8));
                  g.setRadiateur(res.getInt(9));
                  g.setVentilateur(res.getInt(10));
                  g.setDuride(res.getInt(11));
                  g.setFuite_d_huile(res.getInt(12));
                  g.setVidange(res.getInt(13));
                  g.setFiltre(res.getInt(14));
                  g.setBatterie(res.getInt(15));
                  g.setAmortisseur(res.getInt(16));
                  g.setFrein_main(res.getInt(17));
                  g.setFeu_d_eclairage(res.getInt(18));
                  g.setTaux_de_reduction(res.getInt(19));
                  g.setImage(res.getString(20));
                   }
          } catch (SQLException ex) {
              Logger.getLogger(ServiceGarageC.class.getName()).log(Level.SEVERE, null, ex);
          }
           return g;
    }

    @Override
    public ArrayList sortBy(String nom_column, String Asc_Dsc) {
List<GarageC> lg=new ArrayList<>();
        try {
              String req="SELECT * FROM `garagec` ORDER BY `"+nom_column+"` "+Asc_Dsc+" ";
              Statement ste = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
              ResultSet res=ste.executeQuery(req);
              while(res.next()){
                  
                  GarageC g=new GarageC();
                 g.setId_garage(res.getInt(1));
                  g.setNom_garage(res.getString(2));
                  g.setAdresse(res.getString(3));
                  g.setNumero(res.getInt(4));
                  g.setPanne_moteur(res.getInt(5));
                  g.setPompe_a_eau(res.getInt(6));
                  g.setPatin(res.getInt(7));
                  g.setEssuie_glace(res.getInt(8));
                  g.setRadiateur(res.getInt(9));
                  g.setVentilateur(res.getInt(10));
                  g.setDuride(res.getInt(11));
                  g.setFuite_d_huile(res.getInt(12));
                  g.setVidange(res.getInt(13));
                  g.setFiltre(res.getInt(14));
                  g.setBatterie(res.getInt(15));
                  g.setAmortisseur(res.getInt(16));
                  g.setFrein_main(res.getInt(17));
                  g.setFeu_d_eclairage(res.getInt(18));
                  g.setTaux_de_reduction(res.getInt(19));
                  g.setImage(res.getString(20));
                  lg.add(g);
              }
              
          } catch (SQLException ex) {
              Logger.getLogger(ServiceGarageC.class.getName()).log(Level.SEVERE, null, ex);
          }
           return (ArrayList<GarageC>) lg;
    }

    
    public ArrayList<GarageC> chercher(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
  

   

    



}