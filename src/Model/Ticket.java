/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author azizh
 */
public class Ticket {
    private int id_ticket;
    private int num_ticket;
    private User user;
    private Event event;
    private String image;

    public Ticket() {
    }

    public Ticket(int id_ticket, int num_ticket, User user, Event event, String image) {
        this.id_ticket = id_ticket;
        this.num_ticket = num_ticket;
        this.user = user;
        this.event = event;
        this.image = image;
    }

    public Ticket(int num_ticket, User user, Event event, String image) {
        this.num_ticket = num_ticket;
        this.user = user;
        this.event = event;
        this.image = image;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public void setNum_ticket(int num_ticket) {
        this.num_ticket = num_ticket;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id_ticket=" + id_ticket + ", num_ticket=" + num_ticket + ", user=" + user + ", event=" + event + ", image=" + image + '}';
    }
    
}