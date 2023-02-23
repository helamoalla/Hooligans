/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author asma
 */
public class Panier {
   
   //Var 
   private int id_panier; 
   private float montant; 
   private int nb_articles; 
 
  // private Commande commande ; 
   
   //Constructeurs
   //Constructeur par défaut, nom paramétrés 
    public Panier() {
    }
    
   //Constructeurs paramétrés
   //sans id
    public Panier(float montant, float guantite_produit, int nb_articles, int date_ajout) {
        this.montant = montant;
        this.nb_articles = nb_articles;
    }
    
    //avec id
    public Panier(int id_panier, float montant, float guantite_produit, int nb_articles, int date_ajout) {
        this.id_panier = id_panier;
        this.montant = montant;
        this.nb_articles = nb_articles;
    }
    
    //Getters 

    public int getId_panier() {
        return id_panier;
    }

    public float getMontant() {
        return montant;
    }

    public int getNb_articles() {
        return nb_articles;
    }

    //Setters 
    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

 

    public void setNb_articles(int nb_articles) {
        this.nb_articles = nb_articles;
    }

    
    //Affichage : toString

    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", montant=" + montant + ", nb_articles=" + nb_articles + '}';
    }
   
    
    
    
    
}
