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
public class Devis {
      private double id_devis;
    GarageC garage=new GarageC();
    private double TVA=19;
    private double total;
    private User user;
    Maintenance maintenance=new Maintenance();

    public Devis() {
    }

    public Devis(double id_devis, double total, User user) {
        this.id_devis = id_devis;
        this.total = total;
        this.user = user;
    }

    public Devis(double total, User user) {
        this.total = total;
        this.user = user;
    }

    public double getId_devis() {
        return id_devis;
    }

    public void setId_devis(double id_devis) {
        this.id_devis = id_devis;
    }

    public GarageC getGarage() {
        return garage;
    }

    public void setGarage(GarageC garage) {
        this.garage = garage;
    }

    public double getTVA() {
        return TVA;
    }

    public void setTVA(double TVA) {
        this.TVA = TVA;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    
  
    @Override
    public String toString() {
        return "Devis{" + "id_devis=" + id_devis + ", garage=" + garage + ", TVA=" + TVA + ", total=" + total + ", user=" + user + ", maintenance=" + maintenance + '}';
    }
    
    
}
