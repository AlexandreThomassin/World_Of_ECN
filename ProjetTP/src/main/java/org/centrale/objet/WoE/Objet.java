package org.centrale.objet.WoE;

import java.util.Random;

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

    /**Afin de simplifier, on part du principe qu'on positionne nos objets
     * sans tester la présence d'autres objets sur la même case, le cas echeant
     * l'objet antecedant sera ecrase
     */
    public Objet(){
        int x,y;
        Random gen = new Random();
            x = gen.nextInt(50);
            y = gen.nextInt(50);
            this.setPos(new Point2D(x,y));
    }

}
