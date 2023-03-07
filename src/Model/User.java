/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author azizh
 */
public class User {
    private int id_user ;  
    private String nom ;
    private String prenom ;
    private String mdp ;
    private String email ;
    private int num_tel ;
    private int cin ;
    private double quota ;
    public Role role ;
    public String img ;
    public int etat ;

    public User(String nom, String prenom, String mdp, String email, int num_tel, int cin, double quota, Role role, String img, int etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.num_tel = num_tel;
        this.cin = cin;
        this.quota = quota;
        this.role = role;
        this.img = img;
        this.etat = etat;
    }

    

    

    public User(int id_user, String nom, String prenom, String mdp, String email, int num_tel, int cin, double quota, Role role, String img) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.num_tel = num_tel;
        this.cin = cin;
        this.quota = quota;
        this.role = role;
        this.img = img;
    }

    public User(int id_user, String nom, String mdp, String email, int num_tel, int cin, double quota, Role role, String img, int etat) {
        this.id_user = id_user;
        this.nom = nom;
        this.mdp = mdp;
        this.email = email;
        this.num_tel = num_tel;
        this.cin = cin;
        this.quota = quota;
        this.role = role;
        this.img = img;
        this.etat = etat;
    }
    
    public User(String nom, String prenom, String mdp, String email, int num_tel, int cin, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.num_tel = num_tel;
        this.cin = cin;
        this.role = role;
    }

   

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "user{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", mdp=" + mdp + ", email=" + email + ", num_tel=" + num_tel + ", cin=" + cin + ", quota=" + quota + ", role=" + role + '}';
    }

    public User() {
    }
public User(int id_user) {
        this.id_user = id_user;
    }

    public User(String nom, String prenom, String mdp, String email, int num_tel, int cin, double quota, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.num_tel = num_tel;
        this.cin = cin;
        this.quota = quota;
        this.role = role;
    }

    public User(int id_user, String nom, String prenom, String mdp, String email, int num_tel, int cin, double quota, Role role) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.num_tel = num_tel;
        this.cin = cin;
        this.quota = quota;
        this.role = role;
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

    public double getQuota() {
        return quota;
    }

    public void setQuota(double quota) {
        this.quota = quota;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

   

}
