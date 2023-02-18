/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


/**
 *
 * @author azizh
 */
public class Feedback {
    private int id_feedback;
    private int rate;
    private String commentaire;
    private int id_user;
    private BonPlan bonPlan;
    
    // constucteur non parametré
     public Feedback(){};
     
     // constructeur parametré sans id_feedback

    public Feedback(int rate, String commentaire, int id_user, BonPlan bonPlan) {
        this.rate = rate;
        this.commentaire = commentaire;
        this.id_user = id_user;
        this.bonPlan = bonPlan;
    }
     
    // constucteur parametré 

    public Feedback(int id_feedback, int rate, String commentaire, int id_user, BonPlan bonPlan) {
        this.id_feedback = id_feedback;
        this.rate = rate;
        this.commentaire = commentaire;
        this.id_user = id_user;
        this.bonPlan = bonPlan;
    }
    
    // getters and setters 

    public int getId_feedback() {
        return id_feedback;
    }

    public void setId_feedback(int id_feedback) {
        this.id_feedback = id_feedback;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public BonPlan getBonPlan() {
        return bonPlan;
    }

    public void setBonPlan(BonPlan bonPlan) {
        this.bonPlan = bonPlan;
    }
    
    // Afficher feedback

    @Override
    public String toString() {
        return "Feedback{" + "id_feedback=" + id_feedback + ", rate=" + rate + ", commentaire=" + commentaire + ", id_user=" + id_user + ", bonPlan=" + bonPlan + '}';
    }
    
    
    
}