/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
/**
 *
 * @author helam
 */
public class Maintenance {
    //attributs
    private int id_maintenance;
    private User user;
    private Date date_maintenance;
    private boolean panne_moteur,pompe_a_eau,patin,essuie_glace,radiateur,ventilateur,duride,fuite_d_huile,vidange,filtre,batterie,amortisseur,frein_main,feu_d_eclairage;
    private String Autre;

   
   ///constructeurs

    public Maintenance() {
    }

    public Maintenance(int id_maintenance, User user, Date date_maintenance, boolean panne_moteur, boolean pompe_a_eau, boolean patin, boolean essuie_glace, boolean radiateur, boolean ventilateur, boolean duride, boolean fuite_d_huile, boolean vidange, boolean filtre, boolean batterie, boolean amortisseur, boolean frein_main, boolean feu_d_eclairage, String Autre) {
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

    public Maintenance(User user, boolean panne_moteur, boolean pompe_a_eau, boolean patin, boolean essuie_glace, boolean radiateur, boolean ventilateur, boolean duride, boolean fuite_d_huile, boolean vidange, boolean filtre, boolean batterie, boolean amortisseur, boolean frein_main, boolean feu_d_eclairage, String Autre) {
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

    
   
   
   
   
   ///toString 
    @Override
    public String toString() {
        return "Maintenance{" + ", id_user=" + user + ", date_maintenance=" + date_maintenance + ", panne_moteur=" + panne_moteur + ", pompe_a_eau=" + pompe_a_eau + ", patin=" + patin + ", essuie_glace=" + essuie_glace + ", radiateur=" + radiateur + ", ventilateur=" + ventilateur + ", duride=" + duride + ", fuite_d_huile=" + fuite_d_huile + ", vidange=" + vidange + ", filtre=" + filtre + ", batterie=" + batterie + ", amortisseur=" + amortisseur + ", frein_main=" + frein_main + ", feu_d_eclairage=" + feu_d_eclairage + ", Autre=" + Autre + '}';
    }

    ///getters & setters
    public int getId_maintenance() {
        return id_maintenance;
    }

    public void setId_maintenance(int id_maintenance) {
        this.id_maintenance = id_maintenance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate_maintenance() {
        return date_maintenance;
    }

    public void setDate_maintenance(Date date_maintenance) {
        this.date_maintenance = date_maintenance;
    }

    public boolean isPanne_moteur() {
        return panne_moteur;
    }

    public void setPanne_moteur(boolean panne_moteur) {
        this.panne_moteur = panne_moteur;
    }

    public boolean isPompe_a_eau() {
        return pompe_a_eau;
    }

    public void setPompe_a_eau(boolean pompe_a_eau) {
        this.pompe_a_eau = pompe_a_eau;
    }

    public boolean isPatin() {
        return patin;
    }

    public void setPatin(boolean patin) {
        this.patin = patin;
    }

    public boolean isEssuie_glace() {
        return essuie_glace;
    }

    public void setEssuie_glace(boolean essuie_glace) {
        this.essuie_glace = essuie_glace;
    }

    public boolean isRadiateur() {
        return radiateur;
    }

    public void setRadiateur(boolean radiateur) {
        this.radiateur = radiateur;
    }

    public boolean isVentilateur() {
        return ventilateur;
    }

    public void setVentilateur(boolean ventilateur) {
        this.ventilateur = ventilateur;
    }

    public boolean isDuride() {
        return duride;
    }

    public void setDuride(boolean duride) {
        this.duride = duride;
    }

    public boolean isFuite_d_huile() {
        return fuite_d_huile;
    }

    public void setFuite_d_huile(boolean fuite_d_huile) {
        this.fuite_d_huile = fuite_d_huile;
    }

    public boolean isVidange() {
        return vidange;
    }

    public void setVidange(boolean vidange) {
        this.vidange = vidange;
    }

    public boolean isFiltre() {
        return filtre;
    }

    public void setFiltre(boolean filtre) {
        this.filtre = filtre;
    }

    public boolean isBatterie() {
        return batterie;
    }

    public void setBatterie(boolean batterie) {
        this.batterie = batterie;
    }

    public boolean isAmortisseur() {
        return amortisseur;
    }

    public void setAmortisseur(boolean amortisseur) {
        this.amortisseur = amortisseur;
    }

    public boolean isFrein_main() {
        return frein_main;
    }

    public void setFrein_main(boolean frein_main) {
        this.frein_main = frein_main;
    }

    public boolean isFeu_d_eclairage() {
        return feu_d_eclairage;
    }

    public void setFeu_d_eclairage(boolean feu_d_eclairage) {
        this.feu_d_eclairage = feu_d_eclairage;
    }

    public String getAutre() {
        return Autre;
    }

    public void setAutre(String Autre) {
        this.Autre = Autre;
    }

   
   
   
    
   
}