/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author helam
 */
public class Devis {
    ///var
    private int id_devis;
    GarageC garage=new GarageC();
    private int TVA=19;
    private int total;
    private int id_user;
    Maintenance maintenance=new Maintenance();
    
    
    
    ///constructeur
    public Devis() {
    }

    public Devis(int id_devis, int total, int id_user) {
        this.id_devis = id_devis;
        this.total = total;
        this.id_user = id_user;
    }

    public Devis(int total, int id_user) {
        this.total = total;
        this.id_user = id_user;
    }


 
    
    ///toString
    @Override
    public String toString() {
        return "Devis{" + "id_devis=" + id_devis + ", garage=" + garage + ", TVA=" + TVA + ", total=" + total + ", id_user=" + id_user + ", maintenance=" + maintenance + '}';
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    
}
