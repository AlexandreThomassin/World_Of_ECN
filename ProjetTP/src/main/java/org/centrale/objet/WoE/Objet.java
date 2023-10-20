package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Random;

public abstract class Objet {
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    public void setPos(int x, int y) {
        this.pos = new Point2D(x,y);
    }

    public Point2D getPos() {
        return pos;
    }

    private String nom;
    private Point2D pos;
    public boolean isUtilisable() {
        return utilisable;
    }

    public void setUtilisable(boolean utilisable) {
        this.utilisable = utilisable;
    }

    private boolean utilisable;

    /**Afin de simplifier, on part du principe qu'on positionne nos objets
     * sans tester la présence d'autres objets sur la même case, le cas echeant
     * l'objet antecedant sera ecrase
     */
    public Objet(){
    }
    public void initialiserPosition(ArrayList<Objet> objets){
        Random gen= new Random();
        int x,y;
        do {
            x = gen.nextInt(50);
            y = gen.nextInt(50);
            this.setPos(x,y);
        } while (objets.contains(this));
        objets.add(this);
    }

    public abstract String toSave();
    
}
