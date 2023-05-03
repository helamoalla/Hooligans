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
public class Lignepanier {
    
    private double id;
    private Panier panier ; 
    private Produit produit ; 
    private double prix; 
    private double quantite; 
    private String nom_produit;
    private String description_prod;
    private String image ;

    public Lignepanier() {
    }

    public Lignepanier(Panier panier, Produit produit, double prix, double quantite, String nom_produit, String description_prod, String image) {
        this.panier = panier;
        this.produit = produit;
        this.prix = prix;
        this.quantite = quantite;
        this.nom_produit = nom_produit;
        this.description_prod = description_prod;
        this.image = image;
    }

    public Lignepanier(double id, Panier panier, Produit produit, double prix, double quantite, String nom_produit, String description_prod, String image) {
        this.id = id;
        this.panier = panier;
        this.produit = produit;
        this.prix = prix;
        this.quantite = quantite;
        this.nom_produit = nom_produit;
        this.description_prod = description_prod;
        this.image = image;
    }

    public double getId() {
        return id;
    }

    public Panier getPanier() {
        return panier;
    }

    public Produit getProduit() {
        return produit;
    }

    public double getPrix() {
        return prix;
    }

    public double getQuantite() {
        return quantite;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public String getDescription_prod() {
        return description_prod;
    }

    public String getImage() {
        return image;
    }

    public void setId(double id) {
        this.id = id;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public void setDescription_prod(String description_prod) {
        this.description_prod = description_prod;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Lignepanier{" + "id=" + id + ", panier=" + panier + ", produit=" + produit + ", prix=" + prix + ", quantite=" + quantite + ", nom_produit=" + nom_produit + ", description_prod=" + description_prod + ", image=" + image + '}';
    }
    
    
    
    
    
    
}
