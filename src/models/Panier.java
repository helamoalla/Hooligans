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
   private User utilisateur; 
   
   //Constructeurs
   //Constructeur par défaut, nom paramétrés 
    public Panier() {
    }
    
   //Constructeurs paramétrés
   //sans id

    public User getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(User utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Panier(int id_panier, User utilisateur) {
        this.id_panier = id_panier;
        this.utilisateur = utilisateur;
    }

    public Panier(User utilisateur) {
        this.utilisateur = utilisateur;
    }
 

    //Getters
    public int getId_panier() {
        return id_panier;
    }



    //Setters 
    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    
    //Affichage : toString

    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", utilisateur=" + utilisateur + '}';
    }
    
    
}