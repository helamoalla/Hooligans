/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;


/**
 *
 * @author Nadia
 */
public class Commande {
    
    //var
    private int id_commande ; 
    private double montant;
    private String etat_commande; 
    private Date date_commande;
    private String gouvernorat, ville, rue ;
    private int code_postal;
    private Panier panier;
    
    
    //constructeurs
    //Constructeurs par défaut, nom paramétrés 
      public Commande() {
    }
      
    //Constructeur paramétré
    //sans id

    public Commande(double montant, String etat_commande, Date date_commande, String gouvernorat, String ville, String rue, int code_postal, Panier panier) {
        this.montant = montant;
        this.etat_commande = etat_commande;
        this.date_commande = date_commande;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.rue = rue;
        this.code_postal = code_postal;
        this.panier = panier;
    }
    

    public Commande(double montant, String etat_commande, String gouvernorat, String ville, String rue, int code_postal, Panier panier) {
        this.montant = montant;
        this.etat_commande = etat_commande;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.rue = rue;
        this.code_postal = code_postal;
        this.panier = panier;
    }

    //avec id

    public Commande(int id_commande, double montant, String etat_commande, Date date_commande, String gouvernorat, String ville, String rue, int code_postal, Panier panier) {
        this.id_commande = id_commande;
        this.montant = montant;
        this.etat_commande = etat_commande;
        this.date_commande = date_commande;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.rue = rue;
        this.code_postal = code_postal;
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

    

    public int getCode_postal() {
        return code_postal;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public String getVille() {
        return ville;
    }

    public String getRue() {
        return rue;
    }

    
    //Settres 

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
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



    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    
    
    
    //toString

//    @Override
//    public String toString() {
//        
//         return "Commande numéro : " + id_commande +System.lineSeparator()+
//                "Statuts : '" + etat_commande + System.lineSeparator()+
//                "Montant total :" + montant +System.lineSeparator()+
//             //   "Date lancement de votre commande='" + date_commande + System.lineSeparator()+
//                "Code postal : " + code_postal +System.lineSeparator()+
//                "Adresse : " + adresse +System.lineSeparator();
//
//    }  

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", montant=" + montant + ", etat_commande=" + etat_commande + ", date_commande=" + date_commande + ", gouvernorat=" + gouvernorat + ", ville=" + ville + ", rue=" + rue + ", code_postal=" + code_postal + ", panier=" + panier + '}';
    }
}
