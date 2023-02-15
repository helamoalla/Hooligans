/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author 21694
 */
public class user {

    public static Object readAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private int id_user ;  
    private String nom ;
    private String prenom ;
    private String mdp ;
    private String email ;
    private String datenaissance ;
    private int cin ;
    private int quota ;
    public role id_role ;

    @Override
    public String toString() {
        return "user{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", mdp=" + mdp + ", email=" + email + ", datenaissance=" + datenaissance + ", cin=" + cin + ", quota=" + quota + ", id_role=" + id_role + '}';
    }

    public user() {
    }
public user(int id_user) {
        this.id_user = id_user;
    }

    public user(String nom, String prenom, String mdp, String email, String datenaissance, int cin, int quota, role id_role) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.datenaissance = datenaissance;
        this.cin = cin;
        this.quota = quota;
        this.id_role = id_role;
    }

    public user(int id_user, String nom, String prenom, String mdp, String email, String datenaissance, int cin, int quota, role id_role) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.datenaissance = datenaissance;
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

    public String getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public role getId_role() {
        return id_role;
    }

    public void setId_role(role id_role) {
        this.id_role = id_role;
    }
    /**
     *
     * @param nom
     * @param prenom
     * @param mdp
     * @param email
     * @param datenaissance
     * @param cin
     * @param quota
     * @param id_role
     */
    
    



    
    
    
}
