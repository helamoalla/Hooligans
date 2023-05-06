
 

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;



/**
 *
 * @author User
 */
public class Event {
    private double id_e;
    private String nom_event;
    private String date_debut;
    private String date_fin;
    private String lieu_event;
    private String type_event;
    private String image ;
    private Double prix;

    public Event() {
    }

    public Event(String nom_event, String date_debut, String date_fin, String lieu_event, String type_event, String image, Double prix) {
        this.nom_event = nom_event;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.lieu_event = lieu_event;
        this.type_event = type_event;
        this.image = image;
        this.prix = prix;
    }

    public Event(double id_e, String nom_event, String date_debut, String date_fin, String lieu_event, String type_event, String image, Double prix) {
        this.id_e = id_e;
        this.nom_event = nom_event;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.lieu_event = lieu_event;
        this.type_event = type_event;
        this.image = image;
        this.prix = prix;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }



    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public double getId_e() {
        return id_e;
    }

    public String getNom_event() {
        return nom_event;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public String getLieu_event() {
        return lieu_event;
    }

    public String getType_event() {
        return type_event;
    }

    public void setId_e(double id_e) {
        this.id_e = id_e;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public void setLieu_event(String lieu_event) {
        this.lieu_event = lieu_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    @Override
    public String toString() {
        return "Event{" + "id_e=" + id_e + ", nom_event=" + nom_event + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", lieu_event=" + lieu_event + ", type_event=" + type_event + ", image=" + image + ", prix=" + prix + '}';
    }

  


    
    
}
