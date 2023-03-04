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
    private User user;
    private BonPlan bonPlan;
    private boolean report=false;
    
    // constucteur non parametré
     public Feedback(){};
     
     // constructeur parametré sans id_feedback

    public Feedback(int rate, String commentaire, User user, BonPlan bonPlan) {
        this.rate = rate;
        this.commentaire = commentaire;
        this.user = user;
        this.bonPlan = bonPlan;
    }
     
    // constucteur parametré 

    public Feedback(int id_feedback, int rate, String commentaire, User user, BonPlan bonPlan) {
        this.id_feedback = id_feedback;
        this.rate = rate;
        this.commentaire = commentaire;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

  

    public BonPlan getBonPlan() {
        return bonPlan;
    }

    public void setBonPlan(BonPlan bonPlan) {
        this.bonPlan = bonPlan;
    }

    public boolean isReport() {
        return report;
    }

    public void setReport(boolean report) {
        this.report = report;
    }


    
    
    
    // Afficher feedback

    @Override
    public String toString() {
        return "Feedback{" + "id_feedback=" + id_feedback + ", rate=" + rate + ", commentaire=" + commentaire + ", user=" + user + ", bonPlan=" + bonPlan + '}';
    }
    
    
    
}