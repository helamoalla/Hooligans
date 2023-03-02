/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author asma
 */

//Var
public class LignePanier {
    private int id_ligne;
    private Panier panier ; 
    private Produit produit ; 
    private double prix_u; 
    private int quantite; 
    private String nom_produit;
    private String description;
    private String image ; 
    
//Constructeurs 
//Constructeur par défaut
    public LignePanier() {
    }
    
//Constructeurs paramétrés
   //sans id
    public LignePanier(Panier panier, Produit produit, double prix_u, int quantite, String nom_produit, String description, String image) {
        this.panier = panier;
        this.produit = produit;
        this.prix_u = prix_u;
        this.quantite = quantite;
        this.nom_produit = nom_produit;
        this.description = description;
        this.image = image;
    }

    //Avec id
    public LignePanier(int id_ligne, Panier panier, Produit produit, double prix_u, int quantite, String nom_produit, String description, String image) {
        this.id_ligne = id_ligne;
        this.panier = panier;
        this.produit = produit;
        this.prix_u = prix_u;
        this.quantite = quantite;
        this.nom_produit = nom_produit;
        this.description = description;
        this.image = image;
    }


//Getters :
    public int getId_ligne() {
        return id_ligne;
    }

    public Panier getPanier() {
        return panier;
    }

    public Produit getProduit() {
        return produit;
    }

    public double getPrix_u() {
        return prix_u;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
     
    
//Setters : 
    public void setId_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setPrix_u(double prix_u) {
        this.prix_u = prix_u;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    
//ToString 
    @Override
    public String toString() {
//        return  "" + produit.getImage()+System.lineSeparator()+
             return   "Nom Article : " + produit.getNom_prod()+System.lineSeparator()+
//                "Catégorie : " + produit.getCategorie().getDescription_categorie()+System.lineSeparator()+
                "Description : " + produit.getDescription_prod() +System.lineSeparator()+
                "Prix : " + produit.getPrix_prod() +System.lineSeparator()+
                "Quantite : " + quantite +System.lineSeparator();
    }
    
}
