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
public class Produit {
    
    private int id,quantite_prod; 
    private float prix_prod;
    private String nom_prod, description_prod, image; 
    private Categorie  categorie; 

    public Produit() {
    }

    public Produit(int id, int quantite_prod, float prix_prod, String nom_prod, String description_prod, String image, Categorie categorie) {
        this.id = id;
        this.quantite_prod = quantite_prod;
        this.prix_prod = prix_prod;
        this.nom_prod = nom_prod;
        this.description_prod = description_prod;
        this.image = image;
        this.categorie = categorie;
    }

    public Produit(int quantite_prod, float prix_prod, String nom_prod, String description_prod, String image, Categorie categorie) {
        this.quantite_prod = quantite_prod;
        this.prix_prod = prix_prod;
        this.nom_prod = nom_prod;
        this.description_prod = description_prod;
        this.image = image;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public int getQuantite_prod() {
        return quantite_prod;
    }

    public float getPrix_prod() {
        return prix_prod;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public String getDescription_prod() {
        return description_prod;
    }

    public String getImage() {
        return image;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantite_prod(int quantite_prod) {
        this.quantite_prod = quantite_prod;
    }

    public void setPrix_prod(float prix_prod) {
        this.prix_prod = prix_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public void setDescription_prod(String description_prod) {
        this.description_prod = description_prod;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", quantite_prod=" + quantite_prod + ", prix_prod=" + prix_prod + ", nom_prod=" + nom_prod + ", description_prod=" + description_prod + ", image=" + image + ", categorie=" + categorie + '}';
    }
    
    
    
}
