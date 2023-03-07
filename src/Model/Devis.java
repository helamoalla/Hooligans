/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author helam
 */
public class Devis {
    ///var
    private int id_devis;
    GarageC garage=new GarageC();
    private int TVA=19;
    private float total;
    private User user;
    Maintenance maintenance=new Maintenance();
    
    
    
    ///constructeur
    public Devis() {
    }

    public Devis(User user, float total, int id_user) {
        this.id_devis = id_devis;
        this.total = total;
        this.user = user;
    }

    public Devis(float total, User user) {
        this.total = total;
        this.user = user;
    }


 
    
    ///toString
    @Override
    public String toString() {
        return "Devis{" + ", garage=" + garage + ", TVA=" + TVA + ", total=" + total + ", id_user=" + user+ ", maintenance=" + maintenance + '}';
    }

    /////getters & setters
    public int getId_devis() {
        return id_devis;
    }

    public void setId_devis(int id_devis) {
        this.id_devis = id_devis;
    }

    public GarageC getGarage() {
        return garage;
    }

    public void setGarage(GarageC garage) {
        this.garage = garage;
    }

    public int getTVA() {
        return TVA;
    }

    public void setTVA(int TVA) {
        this.TVA = TVA;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }


    
}
