package org.centrale.objet.WoE;

public abstract class ElementDeJeu{
    /** nom de l'element'*/
    private String nom;
    /** position du personnage*/
    private Point2D pos;
    public Point2D getPos() {
        return pos;
    }
    public void setPos(Point2D pos) {
        this.pos = new Point2D(pos);
    }
    public void setPos(int x, int y) {
        this.pos = new Point2D(x,y);
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
    public ElementDeJeu(){

    }
    public abstract String toSave();
}
