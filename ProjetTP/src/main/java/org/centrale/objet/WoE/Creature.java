package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Random;

public abstract class Creature implements Deplaceable{
    /** nom de la creature*/
    private String nom;
    /** points de vie de la creature*/
    private int ptVie;
    private int degAtt;
    private int ptPar;
    private int pageAtt;
    private int pagePar;
    /** position du personnage*/
    private Point2D pos;

    /**constructeur 1
     * @param ptVie points de vie à donner à la créature
     * etc...*/
    public Creature(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        this.nom = nom;
        this.ptVie = ptVie;
        this.degAtt = degAtt;
        this.ptPar = ptPar;
        this.pageAtt = pageAtt;
        this.pagePar = pagePar;
        this.pos = new Point2D(pos);
    }
    /**constructeur 2
     * @param creature  pour faire une copie de la créature*/
    public Creature(Creature creature){
        this.nom=creature.nom;
        this.ptVie = creature.ptVie;
        this.degAtt = creature.degAtt;
        this.ptPar = creature.ptPar;
        this.pageAtt = creature.pageAtt;
        this.pagePar = creature.pagePar;
        this.pos = new Point2D(creature.pos);
    }
    public Creature(){
        ptVie=100;
    }
    public String getNom() {
        return nom;
    }
    public int getPtVie() {
        return ptVie;
    }

    public int getDegAtt() {
        return degAtt;
    }

    public int getPtPar() {
        return ptPar;
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public int getPagePar() {
        return pagePar;
    }
    public Point2D getPos() {
        return pos;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }
    public void setPos(Point2D pos) {
        this.pos = new Point2D(pos);
    }
    public void setPos(int x, int y) {
        this.pos = new Point2D(x,y);
    }
    /** méthode permettant le déplacement de la creature sur une
     * case adjacente, faisant appel à la méthode translate dela classe
     * Point2D
     */
    public void deplace(ArrayList<Point2D> positionsOccupees) {
        Random genX = new Random();
        Random genY = new Random();
        int dx;
        int dy;
        /**Tant que (dx=0 et dy=0) ou la nouvelle position est déjà occupée on refait le tir*/
        do {
            dx = genX.nextInt(3)-1;
            dy = genY.nextInt(3)-1;
        } while ((dx==0 &&dy==0)||positionsOccupees.contains(new Point2D(pos.getX()+dx,pos.getY()+dy)));
        /**Si la position actuelle existait déjà dans notre tableau de positions occupées, on retire
         * celle-ci de notre tableau avant de faire la translation
         */
        if(positionsOccupees.contains(getPos())){
            positionsOccupees.remove(getPos());
        }
        /**On fait la translation*/
        this.getPos().translate(dx, dy);
        /**On ajoute la nouvelle positions aux positions occupees*/
        positionsOccupees.add(getPos());
        System.out.println(nom+" est désormais à la position x= "+getPos().getX()+", y = "+getPos().getY()+"\n");
    }
    public abstract void combattre(Creature c);
    public void recupererPotionVie(int ptVie){
        this.ptVie+=ptVie;
    }
    
    public abstract String toSave();

}
