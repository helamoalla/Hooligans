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
public class User {
    private int id_user, cin, num_tel, etat;
    private String nom, prenom, mdp, email, img; 
    private float quota; 
    private Role role; 

    public User() {
    }

    public User(int id_user, int cin, int num_tel, int etat, String nom, String prenom, String mdp, String email, String img, float quota, Role role) {
        this.id_user = id_user;
        this.cin = cin;
        this.num_tel = num_tel;
        this.etat = etat;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.img = img;
        this.quota = quota;
        this.role = role;
    }

    public User(int cin, int num_tel, int etat, String nom, String prenom, String mdp, String email, String img, float quota, Role role) {
        this.cin = cin;
        this.num_tel = num_tel;
        this.etat = etat;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.img = img;
        this.quota = quota;
        this.role = role;
    }

    public int getId_user() {
        return id_user;
    }

    public int getCin() {
        return cin;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public int getEtat() {
        return etat;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public String getEmail() {
        return email;
    }

    public String getImg() {
        return img;
    }

    public float getQuota() {
        return quota;
    }

    public Role getRole() {
        return role;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setQuota(float quota) {
        this.quota = quota;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", cin=" + cin + ", num_tel=" + num_tel + ", etat=" + etat + ", nom=" + nom + ", prenom=" + prenom + ", mdp=" + mdp + ", email=" + email + ", img=" + img + ", quota=" + quota + ", role=" + role + '}';
    }
    
    
    
}
