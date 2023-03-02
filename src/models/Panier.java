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
   private int utilisateur; 
   
   //Constructeurs
   //Constructeur par défaut, nom paramétrés 
    public Panier() {
    }
    
   //Constructeurs paramétrés
   //sans id
    public Panier(int utilisateur) {
        this.utilisateur = utilisateur;
    }
  
    
    //avec id
    public Panier(int id_panier, int utilisateur) {
        this.id_panier = id_panier;
        this.utilisateur = utilisateur;
    }

    //Getters
    public int getId_panier() {
        return id_panier;
    }

    public int getUtilisateur() {
        return utilisateur;
    }


    //Setters 
    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;
    }

    
    //Affichage : toString

    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", utilisateur=" + utilisateur + '}';
    }
    
    
}