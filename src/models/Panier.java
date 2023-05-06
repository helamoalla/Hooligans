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
public class Panier {
    private int id ; 
    private User user ; 

    public Panier() {
    }

    public Panier(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public Panier(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", user=" + user + '}';
    }
    
    
}
