/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author User
 */
public class user {
    
 private int id_user ;  
    private String nom ;
    private String prenom ;
    private String mdp ;
    private String email ;
    private int num_tel ;
    private int cin ;
    private Double quota ;
    public int id_role ;

    @Override
    public String toString() {
        return "user{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", mdp=" + mdp + ", email=" + email + ", num_tel=" + num_tel + ", cin=" + cin + ", quota=" + quota + ", id_role=" + id_role + '}';
    }

    public user() {
    }
public user(int id_user) {
        this.id_user = id_user;
    }

    public user(String nom, String prenom, String mdp, String email, int num_tel, int cin, Double quota, int id_role) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.num_tel = num_tel;
        this.cin = cin;
        this.quota = quota;
        this.id_role = id_role;
    }

    public user(int id_user, String nom, String prenom, String mdp, String email, int num_tel, int cin, Double quota, int id_role) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.num_tel = num_tel;
        this.cin = cin;
        this.quota = quota;
        this.id_role = id_role;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public user(String nom, String prenom, String mdp, String email, int num_tel, int cin, int id_role) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.num_tel = num_tel;
        this.cin = cin;
        this.id_role = id_role;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }



   

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    
}

