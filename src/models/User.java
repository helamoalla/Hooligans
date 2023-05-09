/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


public class User {
    private double id_user ;  
    private String nom ;
    private String prenom ;
    private double num_tel ;
    private String adresse ;
    private String centre_intere ;
    private String adresse_entreprise ;
    private String nom_entreprise ;
    private double id_role ;
    private String email ;
    private String mdp ;
    private String cv;
    private double etat_user;
    private double age;
    private int note;
    public User() {
    }

    public double getId_user() {
        return id_user;
    }

    public void setId_user(double id_user) {
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

    public double getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(double num_tel) {
        this.num_tel = num_tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCentre_intere() {
        return centre_intere;
    }

    public void setCentre_intere(String centre_intere) {
        this.centre_intere = centre_intere;
    }

    public String getAdresse_entreprise() {
        return adresse_entreprise;
    }

    public void setAdresse_entreprise(String adresse_entreprise) {
        this.adresse_entreprise = adresse_entreprise;
    }

    public String getNom_entreprise() {
        return nom_entreprise;
    }

    public void setNom_entreprise(String nom_entreprise) {
        this.nom_entreprise = nom_entreprise;
    }

    public double getId_role() {
        return id_role;
    }

    public void setId_role(double id_role) {
        this.id_role = id_role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public double getEtat_user() {
        return etat_user;
    }

    public void setEtat_user(double etat_user) {
        this.etat_user = etat_user;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public User(String nom, String prenom, double num_tel, String adresse, String email, String mdp, double age) {
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.adresse = adresse;
        this.email = email;
        this.mdp = mdp;
        this.age = age;
    }

    public User(double id_user, double id_role, String email, String mdp, double etat_user) {
        this.id_user = id_user;
        this.id_role = id_role;
        this.email = email;
        this.mdp = mdp;
        this.etat_user = etat_user;
    }
    
    

}
  

   