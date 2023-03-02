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
public class user {
    
    private int id_user ;  
    private String nom ;
    private String prenom ;
    private String mdp ;
    private String email ;
    private int num_tel ;
    private String cin ;
    private int quota ;
   // public int id_role ;
    //public String img ;
    //public int etat ;

    public user() {
    }

    public user(String nom, String prenom, String mdp, String email, int num_tel, String cin, int quota) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.num_tel = num_tel;
        this.cin = cin;
        this.quota = quota;
    }
     

   


    public user(int id_user, String nom, String prenom, String mdp, String email, int num_tel, String cin, int quota/*, int id_role, String img*/) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.num_tel = num_tel;
        this.cin = cin;
        //this.quota = quota;
       // this.id_role = id_role;
       // this.img = img;
    }

   

//    public String getImg() {
//        return img;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }
//
//    public int getEtat() {
//        return etat;
//    }
//
//    public void setEtat(int etat) {
//        this.etat = etat;
//    }

    public int getId_user() {
        return id_user;
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

    public int getNum_tel() {
        return num_tel;
    }

    public String getCin() {
        return cin;
    }

    public int getQuota() {
        return quota;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }


    
    
}
