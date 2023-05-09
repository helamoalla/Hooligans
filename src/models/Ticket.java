/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author azizh
 */
public class Ticket {
    private double id_ticket;
    private double num_ticket;
    private User user;
    private Event event;
    private String image;

    public Ticket(double id_ticket, double num_ticket, User user, Event event, String image) {
        this.id_ticket = id_ticket;
        this.num_ticket = num_ticket;
        this.user = user;
        this.event = event;
        this.image = image;
    }

    public Ticket() {
    }

    public Ticket(double num_ticket, User user, Event event, String image) {
        this.num_ticket = num_ticket;
        this.user = user;
        this.event = event;
        this.image = image;
    }

    public double getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(double id_ticket) {
        this.id_ticket = id_ticket;
    }

    public double getNum_ticket() {
        return num_ticket;
    }

    public void setNum_ticket(double num_ticket) {
        this.num_ticket = num_ticket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id_ticket=" + id_ticket + ", num_ticket=" + num_ticket + ", user=" + user + ", event=" + event + ", image=" + image + '}';
    }

   
    
}