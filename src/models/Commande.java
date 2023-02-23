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
    private float montant, qte_produit;
    private String etat_commande; 
   private Date date_commande;
    private Panier panier;
    
    //constructeurs
    //Constructeurs par défaut, nom paramétrés 
      public Commande() {
    }
      
    //Constructeur paramétré
    //sans id
    public Commande(float montant, float qte_produit, String etat_commande, Panier panier) {
        this.montant = montant;
        this.qte_produit = qte_produit;
        this.etat_commande = etat_commande;
        //this.date_commande = date_commande;
        this.panier =panier;
    }
    //avec id 
    public Commande(int id_commande, float montant, float qte_produit, String etat_commande, Panier panier) {
        this.id_commande = id_commande;
        this.montant = montant;
        this.qte_produit = qte_produit;
        this.etat_commande = etat_commande;
        //this.date_commande = date_commande;
        this.panier =panier;
    }
    
    //Getters

    public int getId_commande() {
        return id_commande;
    }

    public float getMontant() {
        return montant;
    }

    public float getQte_produit() {
        return qte_produit;
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
    
    //Settres 

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setQte_produit(float qte_produit) {
        this.qte_produit = qte_produit;
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
    
    //Affichage : toString

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", montant=" + montant + ", qte_produit=" + qte_produit + ", etat_commande=" + etat_commande + ", date_commande=" + date_commande + ", panier=" + panier + '}';
    }

   
    
    
        
}
