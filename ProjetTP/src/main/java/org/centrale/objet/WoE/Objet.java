package org.centrale.objet.WoE;

public class Objet {
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public Point2D getPos() {
        return pos;
    }

    private String nom;
    private Point2D pos;
    public Objet(){
    }

}
