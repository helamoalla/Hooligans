/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author azizh
 */
public class BonPlan {
   private int id_bonplan;
   private String nom_bonplan;
   private String adresse;
   private String type;
   private String etat="en attente";
   private String image;
   private User user;
   

   
   // constructeur qui contient tous les params
    public BonPlan(int id_bonplan, String nom_bonplan, String adresse, String type, String etat,String image, User user) {
        this.id_bonplan = id_bonplan;
        this.nom_bonplan = nom_bonplan;
        this.adresse = adresse;
        this.type = type;
        this.etat = etat;
        this.image=image;
        this.user = user;
    }
    // constructeur qui contient tous les params sans etat
    public BonPlan(int id_bonplan, String nom_bonplan, String adresse, String type,String image, User user) {
        this.id_bonplan = id_bonplan;
        this.nom_bonplan = nom_bonplan;
        this.adresse = adresse;
        this.type = type;
        this.image=image;
        this.user = user;
    }

    
    // constructeur qui contient tous les params sauf l id_bonplan
    public BonPlan(String nom_bonplan, String adresse, String type, String etat,String image, User user) {
        this.nom_bonplan = nom_bonplan;
        this.adresse = adresse;
        this.type = type;
        this.etat = etat;
        this.image=image;
        this.user = user;
    }
    
    // constructeur qui contient tous les params sauf l id_bonplan et l'etat
    public BonPlan(String nom_bonplan, String adresse, String type,String image, User user) {
        this.nom_bonplan = nom_bonplan;
        this.adresse = adresse;
        this.type = type;
        this.image=image;
        this.user = user;
    }
    
    // constructeur non parameteré
    public BonPlan(){};
    
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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


    
    // Affichage 

    @Override
    public String toString() {
        return "BonPlan{" + "id_bonplan=" + id_bonplan + ", nom_bonplan=" + nom_bonplan + ", adresse=" + adresse + ", type=" + type + ", etat=" + etat + ", user=" + user + '}';
    }

    
    
    
}