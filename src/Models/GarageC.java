/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.awt.Image;

/**
 *
 * @author helam
 */
public class GarageC {
   //attributs
    private int id_garage;
    private String nom_garage;
    private String adresse;
    private int numero;
    private int panne_moteur,pompe_a_eau,patin,essuie_glace,radiateur,ventilateur,duride,fuite_d_huile,vidange,filtre,batterie,amortisseur,frein_main,feu_d_eclairage;
    private String image;
    int taux_de_reduction;
    
    //contructeur

    public GarageC() {
    }

    public GarageC(int id_garage, String nom_garage, String adresse, int numero, int panne_moteur, int pompe_a_eau, int patin, int essuie_glace, int radiateur, int ventilateur, int duride, int fuite_d_huile, int vidange, int filtre, int batterie, int amortisseur, int frein_main, int feu_d_eclairage, int taux_de_reduction, String image) {
        this.id_garage = id_garage;
        this.nom_garage = nom_garage;
        this.adresse = adresse;
        this.numero = numero;
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
        this.image = image;
        this.taux_de_reduction = taux_de_reduction;
    }

    public GarageC(String nom_garage, String adresse, int numero, int panne_moteur, int pompe_a_eau, int patin, int essuie_glace, int radiateur, int ventilateur, int duride, int fuite_d_huile, int vidange, int filtre, int batterie, int amortisseur, int frein_main, int feu_d_eclairage, int taux_de_reduction,String image) {
        this.nom_garage = nom_garage;
        this.adresse = adresse;
        this.numero = numero;
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
        this.image = image;
        this.taux_de_reduction = taux_de_reduction;
    }

   
    
    //getters & setters

    public int getId_garage() {
        return id_garage;
    }

    public void setId_garage(int id_garage) {
        this.id_garage = id_garage;
    }

    public String getNom_garage() {
        return nom_garage;
    }

    public void setNom_garage(String nom_garage) {
        this.nom_garage = nom_garage;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPanne_moteur() {
        return panne_moteur;
    }

    public void setPanne_moteur(int panne_moteur) {
        this.panne_moteur = panne_moteur;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPompe_a_eau() {
        return pompe_a_eau;
    }

    public void setPompe_a_eau(int pompe_a_eau) {
        this.pompe_a_eau = pompe_a_eau;
    }

    public int getPatin() {
        return patin;
    }

    public void setPatin(int patin) {
        this.patin = patin;
    }

    public int getEssuie_glace() {
        return essuie_glace;
    }

    public void setEssuie_glace(int essuie_glace) {
        this.essuie_glace = essuie_glace;
    }

    public int getRadiateur() {
        return radiateur;
    }

    public void setRadiateur(int radiateur) {
        this.radiateur = radiateur;
    }

    public int getVentilateur() {
        return ventilateur;
    }

    public void setVentilateur(int ventilateur) {
        this.ventilateur = ventilateur;
    }

    public int getDuride() {
        return duride;
    }

    public void setDuride(int duride) {
        this.duride = duride;
    }

    public int getFuite_d_huile() {
        return fuite_d_huile;
    }

    public void setFuite_d_huile(int fuite_d_huile) {
        this.fuite_d_huile = fuite_d_huile;
    }

    public int getVidange() {
        return vidange;
    }

    public void setVidange(int vidange) {
        this.vidange = vidange;
    }

    public int getFiltre() {
        return filtre;
    }

    public void setFiltre(int filtre) {
        this.filtre = filtre;
    }

    public int getBatterie() {
        return batterie;
    }

    public void setBatterie(int batterie) {
        this.batterie = batterie;
    }

    public int getAmortisseur() {
        return amortisseur;
    }

    public void setAmortisseur(int amortisseur) {
        this.amortisseur = amortisseur;
    }

    public int getFrein_main() {
        return frein_main;
    }

    public void setFrein_main(int frein_main) {
        this.frein_main = frein_main;
    }

    public int getFeu_d_eclairage() {
        return feu_d_eclairage;
    }

    public void setFeu_d_eclairage(int feu_d_eclairage) {
        this.feu_d_eclairage = feu_d_eclairage;
    }

    public int getTaux_de_reduction() {
        return taux_de_reduction;
    }

    public void setTaux_de_reduction(int taux_de_reduction) {
        this.taux_de_reduction = taux_de_reduction;
    }

     
    
    ///toString 

    @Override
    public String toString() {
        return "GarageC{" + ", nom_garage=" + nom_garage + ", adresse=" + adresse + ", numero=" + numero + ", panne_moteur=" + panne_moteur + ", pompe_a_eau=" + pompe_a_eau + ", patin=" + patin + ", essuie_glace=" + essuie_glace + ", radiateur=" + radiateur + ", ventilateur=" + ventilateur + ", duride=" + duride + ", fuite_d_huile=" + fuite_d_huile + ", vidange=" + vidange + ", filtre=" + filtre + ", batterie=" + batterie + ", amortisseur=" + amortisseur + ", frein_main=" + frein_main + ", feu_d_eclairage=" + feu_d_eclairage + ", image=" + image + ", taux_de_reduction=" + taux_de_reduction + '}';
    }

   
   
    
}