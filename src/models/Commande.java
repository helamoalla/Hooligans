/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.util.Date;

/**
 *
 * @author asma
 */
public class Commande {
    
    //var
    private int id_commande ; 
    private double montant;
    private String etat_commande; 
    private Date date_commande;
    private String adresse ;
    private int code_postal;
    private String email; 
    private Panier panier;
    
    
    //constructeurs
    //Constructeurs par défaut, nom paramétrés 
      public Commande() {
    }
      
    //Constructeur paramétré
    //sans id
    public Commande(double montant,String etat_commande, Date date_commande, String adresse, int code_postal, String email, Panier panier) {
        this.montant = montant;
        this.etat_commande = etat_commande;
        this.date_commande = date_commande;
        this.adresse =adresse;
        this.code_postal = code_postal;
        this.email = email;
        this.panier = panier;
    }

    public Commande(double montant, String etat_commande, String adresse, int code_postal, String email, Panier panier) {
        this.montant = montant;
        this.etat_commande = etat_commande;
        this.adresse = adresse;
        this.code_postal = code_postal;
        this.email = email;
        this.panier = panier;
    }
    
    

    //avec id

    public Commande(int id_commande, double montant, String etat_commande, Date date_commande, String adresse, int code_postal, String email, Panier panier) {
        this.id_commande = id_commande;
        this.montant = montant;
        this.etat_commande = etat_commande;
        this.date_commande = date_commande;
        this.adresse = adresse;
        this.code_postal = code_postal;
        this.email = email;
        this.panier = panier;
    }

    //Getters
    public int getId_commande() {
        return id_commande;
    }

    public double getMontant() {
        return montant;
    }


    public String getEtat_commande() {
        return etat_commande;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public Panier getPanier() {
        return panier;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public String getEmail() {
        return email;
    }
    
    
    
    //Settres 

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setEtat_commande(String etat_commande) {
        this.etat_commande = etat_commande;
    }

    public void setDate_commande(Date Date_commande) {
        this.date_commande = Date_commande;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }


    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    //Affichage : toString

    @Override
    public String toString() {
        
         return "Commande numéro : " + id_commande +System.lineSeparator()+
                "Statuts : '" + etat_commande + System.lineSeparator()+
                "Montant total :" + montant +System.lineSeparator()+
             //   "Date lancement de votre commande='" + date_commande + System.lineSeparator()+
                "Code postal : " + code_postal +System.lineSeparator()+
                "Adresse : " + adresse +System.lineSeparator();

    }  
        
}
