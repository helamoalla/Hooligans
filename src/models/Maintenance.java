/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 *
 * @author choua
 */
public class Maintenance {

/**
 *
 * @author Nadia
 */

    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


    //attributs
    private double id_maintenance;
    private User user;
    private String date_maintenance;
    private String panne_moteur,pompe_a_eau,patin,essuie_glace,radiateur,ventilateur,duride,fuite_d_huile,vidange,filtre,batterie,amortisseur,frein_main,feu_d_eclairage;
    private String Autre;

   
   ///constructeurs

    public Maintenance() {
    }

    public Maintenance(double id_maintenance, User user, String date_maintenance, String panne_moteur, String pompe_a_eau, String patin, String essuie_glace, String radiateur, String ventilateur, String duride, String fuite_d_huile, String vidange, String filtre, String batterie, String amortisseur, String frein_main, String feu_d_eclairage, String Autre) {
        this.id_maintenance = id_maintenance;
        this.user = user;
        this.date_maintenance = date_maintenance;
        this.panne_moteur = panne_moteur;
        this.pompe_a_eau = pompe_a_eau;
        this.patin = patin;
        this.essuie_glace = essuie_glace;
        this.radiateur = radiateur;
        this.ventilateur = ventilateur;
        this.duride = duride;
        this.fuite_d_huile = fuite_d_huile;
        this.vidange = vidange;
        this.filtre = filtre;
        this.batterie = batterie;
        this.amortisseur = amortisseur;
        this.frein_main = frein_main;
        this.feu_d_eclairage = feu_d_eclairage;
        this.Autre = Autre;
    }

    public Maintenance(User user, String panne_moteur, String pompe_a_eau, String patin, String essuie_glace, String radiateur, String ventilateur, String duride, String fuite_d_huile, String vidange, String filtre, String batterie, String amortisseur, String frein_main, String feu_d_eclairage, String Autre) {
        this.user = user;
        this.panne_moteur = panne_moteur;
        this.pompe_a_eau = pompe_a_eau;
        this.patin = patin;
        this.essuie_glace = essuie_glace;
        this.radiateur = radiateur;
        this.ventilateur = ventilateur;
        this.duride = duride;
        this.fuite_d_huile = fuite_d_huile;
        this.vidange = vidange;
        this.filtre = filtre;
        this.batterie = batterie;
        this.amortisseur = amortisseur;
        this.frein_main = frein_main;
        this.feu_d_eclairage = feu_d_eclairage;
        this.Autre = Autre;
    }

    public double getId_maintenance() {
        return id_maintenance;
    }

    public void setId_maintenance(double id_maintenance) {
        this.id_maintenance = id_maintenance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate_maintenance() {
        return date_maintenance;
    }

    public void setDate_maintenance(String date_maintenance) {
        this.date_maintenance = date_maintenance;
    }

    public String getPanne_moteur() {
        return panne_moteur;
    }

    public void setPanne_moteur(String panne_moteur) {
        this.panne_moteur = panne_moteur;
    }

    public String getPompe_a_eau() {
        return pompe_a_eau;
    }

    public void setPompe_a_eau(String pompe_a_eau) {
        this.pompe_a_eau = pompe_a_eau;
    }

    public String getPatin() {
        return patin;
    }

    public void setPatin(String patin) {
        this.patin = patin;
    }

    public String getEssuie_glace() {
        return essuie_glace;
    }

    public void setEssuie_glace(String essuie_glace) {
        this.essuie_glace = essuie_glace;
    }

    public String getRadiateur() {
        return radiateur;
    }

    public void setRadiateur(String radiateur) {
        this.radiateur = radiateur;
    }

    public String getVentilateur() {
        return ventilateur;
    }

    public void setVentilateur(String ventilateur) {
        this.ventilateur = ventilateur;
    }

    public String getDuride() {
        return duride;
    }

    public void setDuride(String duride) {
        this.duride = duride;
    }

    public String getFuite_d_huile() {
        return fuite_d_huile;
    }

    public void setFuite_d_huile(String fuite_d_huile) {
        this.fuite_d_huile = fuite_d_huile;
    }

    public String getVidange() {
        return vidange;
    }

    public void setVidange(String vidange) {
        this.vidange = vidange;
    }

    public String getFiltre() {
        return filtre;
    }

    public void setFiltre(String filtre) {
        this.filtre = filtre;
    }

    public String getBatterie() {
        return batterie;
    }

    public void setBatterie(String batterie) {
        this.batterie = batterie;
    }

    public String getAmortisseur() {
        return amortisseur;
    }

    public void setAmortisseur(String amortisseur) {
        this.amortisseur = amortisseur;
    }

    public String getFrein_main() {
        return frein_main;
    }

    public void setFrein_main(String frein_main) {
        this.frein_main = frein_main;
    }

    public String getFeu_d_eclairage() {
        return feu_d_eclairage;
    }

    public void setFeu_d_eclairage(String feu_d_eclairage) {
        this.feu_d_eclairage = feu_d_eclairage;
    }

    public String getAutre() {
        return Autre;
    }

    public void setAutre(String Autre) {
        this.Autre = Autre;
    }

   
}
