/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import models.User;

/**
 *
 * @author azizh
 */
public class BonPlan {
   private int id_bonplan;
   private String nom_bonplan;
   private String gouvernorat;
    private String ville;
   private String rue;
   private String type;
   private String etat="en attente";
   private String image;
     private String description;
   private User user;
   
   

   
  
    // constructeur qui contient tous les params avec etat

    public BonPlan(int id_bonplan, String nom_bonplan, String gouvernorat, String ville, String rue, String type, String image, String description, User user) {
        this.id_bonplan = id_bonplan;
        this.nom_bonplan = nom_bonplan;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.rue = rue;
        this.type = type;
        this.image = image;
        this.description = description;
        this.user = user;
    }

    // constructeur qui contient tous les params sauf l id_bonplan

    public BonPlan(String nom_bonplan, String gouvernorat, String ville, String rue, String type, String image, String description, User user) {
        this.nom_bonplan = nom_bonplan;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.rue = rue;
        this.type = type;
        this.image = image;
        this.description = description;
        this.user = user;
    }
  
    
    // constructeur qui contient tous les params sauf l id_bonplan et l'etat
    public BonPlan(String nom_bonplan, String gouvernorat, String ville, String rue, String image, String description, User user) { 
        this.nom_bonplan = nom_bonplan;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.rue = rue;
        this.image = image;
        this.description = description;
        this.user = user;
    }

    // constructeur non parameteré
    public BonPlan() {
    }
    
    // getters and setters 

    public int getId_bonplan() {
        return id_bonplan;
    }

    public void setId_bonplan(int id_bonplan) {
        this.id_bonplan = id_bonplan;
    }

    public String getNom_bonplan() {
        return nom_bonplan;
    }

    public void setNom_bonplan(String nom_bonplan) {
        this.nom_bonplan = nom_bonplan;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    
    // Affichage 

    @Override
    public String toString() {
        return "BonPlan{" + "id_bonplan=" + id_bonplan + ", nom_bonplan=" + nom_bonplan + ", gouvernorat=" + gouvernorat + ", ville=" + ville + ", rue=" + rue + ", type=" + type + ", etat=" + etat + ", image=" + image + ", description=" + description + ", user=" + user + '}';
    }

 

    

    
    
    
}