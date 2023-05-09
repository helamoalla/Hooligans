/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author choua
 */
public class Commande {
    
    private Double id, code_postal; 
    private Panier panier; 
    private double montant; 
    private String etat_commande, gouvernorat, ville, rue;
    private String date_commande; 

    public Commande() {
    }

    public Commande(Double id, Double code_postal, Panier panier, double montant, String etat_commande, String gouvernorat, String ville, String rue, String date_commande) {
        this.id = id;
        this.code_postal = code_postal;
        this.panier = panier;
        this.montant = montant;
        this.etat_commande = etat_commande;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.rue = rue;
        this.date_commande = date_commande;
    }

    public Commande(Double code_postal, Panier panier, double montant, String etat_commande, String gouvernorat, String ville, String rue, String date_commande) {
        this.code_postal = code_postal;
        this.panier = panier;
        this.montant = montant;
        this.etat_commande = etat_commande;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.rue = rue;
        this.date_commande = date_commande;
    }

    public Commande(Double code_postal, String gouvernorat, String ville, String rue) {
        this.code_postal = code_postal;
        this.gouvernorat = gouvernorat;
        this.ville = ville;
        this.rue = rue;
    }
    
    

    public Double getId() {
        return id;
    }

    public Double getCode_postal() {
        return code_postal;
    }

    public Panier getPanier() {
        return panier;
    }

    public double getMontant() {
        return montant;
    }

    public String getEtat_commande() {
        return etat_commande;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public String getVille() {
        return ville;
    }

    public String getRue() {
        return rue;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public void setCode_postal(Double code_postal) {
        this.code_postal = code_postal;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setEtat_commande(String etat_commande) {
        this.etat_commande = etat_commande;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
    }

   
    

    

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", code_postal=" + code_postal + ", panier=" + panier + ", montant=" + montant + ", etat_commande=" + etat_commande + ", gouvernorat=" + gouvernorat + ", ville=" + ville + ", rue=" + rue + ", date_commande=" + date_commande + '}';
    }
    
    
}
