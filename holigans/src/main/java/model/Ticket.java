/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
public class Ticket {
    private int id_ticket;
    private int num_ticket;
    private int id_spectateur;
    private Event event;
    private String image;

    public Ticket() {
    }

    public Ticket(int id_ticket, int num_ticket, int id_spectateur, Event event, String image) {
        this.id_ticket = id_ticket;
        this.num_ticket = num_ticket;
        this.id_spectateur = id_spectateur;
        this.event = event;
        this.image = image;
    }

    public Ticket(int num_ticket, int id_spectateur, Event event, String image) {
        this.num_ticket = num_ticket;
        this.id_spectateur = id_spectateur;
        this.event = event;
        this.image = image;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public void setNum_ticket(int num_ticket) {
        this.num_ticket = num_ticket;
    }

    public void setId_spectateur(int id_spectateur) {
        this.id_spectateur = id_spectateur;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public int getNum_ticket() {
        return num_ticket;
    }

    public int getId_spectateur() {
        return id_spectateur;
    }

    public Event getEvent() {
        return event;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id_ticket=" + id_ticket + ", num_ticket=" + num_ticket + ", id_spectateur=" + id_spectateur + ", event=" + event + ", image=" + image + '}';
    }
    
}