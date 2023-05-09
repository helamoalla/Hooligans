/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author choua
 */
public class GarageC {
      private double id_garage;
    private String nom_garage;
    private String adresse;
    private double numero;
    private double panne_moteur,pompe_a_eau,patin,essuie_glace,radiateur,ventilateur,duride,fuite_d_huile,vidange,filtre,batterie,amortisseur,frein_main,feu_d_eclairage;
    private String image;
    private double taux_de_reduction;

    public GarageC() {
    }
    
    

    public GarageC(double id_garage, String nom_garage, String adresse, double numero, double panne_moteur, double pompe_a_eau, double patin, double essuie_glace, double radiateur, double ventilateur, double duride, double fuite_d_huile, double vidange, double filtre, double batterie, double amortisseur, double frein_main, double feu_d_eclairage, String image, double taux_de_reduction) {
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

    public GarageC(String nom_garage, String adresse, double numero, double panne_moteur, double pompe_a_eau, double patin, double essuie_glace, double radiateur, double ventilateur, double duride, double fuite_d_huile, double vidange, double filtre, double batterie, double amortisseur, double frein_main, double feu_d_eclairage, String image, double taux_de_reduction) {
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

    public double getId_garage() {
        return id_garage;
    }

    public void setId_garage(double id_garage) {
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

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public double getPanne_moteur() {
        return panne_moteur;
    }

    public void setPanne_moteur(double panne_moteur) {
        this.panne_moteur = panne_moteur;
    }

    public double getPompe_a_eau() {
        return pompe_a_eau;
    }

    public void setPompe_a_eau(double pompe_a_eau) {
        this.pompe_a_eau = pompe_a_eau;
    }

    public double getPatin() {
        return patin;
    }

    public void setPatin(double patin) {
        this.patin = patin;
    }

    public double getEssuie_glace() {
        return essuie_glace;
    }

    public void setEssuie_glace(double essuie_glace) {
        this.essuie_glace = essuie_glace;
    }

    public double getRadiateur() {
        return radiateur;
    }

    public void setRadiateur(double radiateur) {
        this.radiateur = radiateur;
    }

    public double getVentilateur() {
        return ventilateur;
    }

    public void setVentilateur(double ventilateur) {
        this.ventilateur = ventilateur;
    }

    public double getDuride() {
        return duride;
    }

    public void setDuride(double duride) {
        this.duride = duride;
    }

    public double getFuite_d_huile() {
        return fuite_d_huile;
    }

    public void setFuite_d_huile(double fuite_d_huile) {
        this.fuite_d_huile = fuite_d_huile;
    }

    public double getVidange() {
        return vidange;
    }

    public void setVidange(double vidange) {
        this.vidange = vidange;
    }

    public double getFiltre() {
        return filtre;
    }

    public void setFiltre(double filtre) {
        this.filtre = filtre;
    }

    public double getBatterie() {
        return batterie;
    }

    public void setBatterie(double batterie) {
        this.batterie = batterie;
    }

    public double getAmortisseur() {
        return amortisseur;
    }

    public void setAmortisseur(double amortisseur) {
        this.amortisseur = amortisseur;
    }

    public double getFrein_main() {
        return frein_main;
    }

    public void setFrein_main(double frein_main) {
        this.frein_main = frein_main;
    }

    public double getFeu_d_eclairage() {
        return feu_d_eclairage;
    }

    public void setFeu_d_eclairage(double feu_d_eclairage) {
        this.feu_d_eclairage = feu_d_eclairage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getTaux_de_reduction() {
        return taux_de_reduction;
    }

    public void setTaux_de_reduction(double taux_de_reduction) {
        this.taux_de_reduction = taux_de_reduction;
    }

    @Override
    public String toString() {
        return "GarageC{" + "id_garage=" + id_garage + ", nom_garage=" + nom_garage + ", adresse=" + adresse + ", numero=" + numero + ", panne_moteur=" + panne_moteur + ", pompe_a_eau=" + pompe_a_eau + ", patin=" + patin + ", essuie_glace=" + essuie_glace + ", radiateur=" + radiateur + ", ventilateur=" + ventilateur + ", duride=" + duride + ", fuite_d_huile=" + fuite_d_huile + ", vidange=" + vidange + ", filtre=" + filtre + ", batterie=" + batterie + ", amortisseur=" + amortisseur + ", frein_main=" + frein_main + ", feu_d_eclairage=" + feu_d_eclairage + ", image=" + image + ", taux_de_reduction=" + taux_de_reduction + '}';
    }
    
    
    
}