/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author alex4
 */
public class Personnage {
    public String nom;
    public int ptVie;
    public int degAtt;
    public int ptPar;
    public int pageAtt;
    public int pagePar;
    public int distAttMax;
    public Point2D pos;

    public Personnage(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        this.nom = nom;
        this.ptVie = ptVie;
        this.degAtt = degAtt;
        this.ptPar = ptPar;
        this.pageAtt = pageAtt;
        this.pagePar = pagePar;
        this.distAttMax = distAttMax;
        this.pos = new Point2D(pos);
    }
    
    public Personnage(Personnage perso){
        this.nom = perso.nom;
        this.ptVie = perso.ptVie;
        this.degAtt = perso.degAtt;
        this.ptPar = perso.ptPar;
        this.pageAtt = perso.pageAtt;
        this.pagePar = perso.pagePar;
        this.distAttMax = perso.distAttMax;
        this.pos = new Point2D(perso.pos);
    }

    public Personnage() {
    
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

    public int getDistAttMax() {
        return distAttMax;
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

    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }

    public void setPos(Point2D pos) {
        this.pos = new Point2D(pos);
    }
    
    public void deplace() {
        this.pos.translate(1, 1);
    }

    @Override
    public String toString() {
        return "Personnage{" + "nom=" + nom + ", ptVie=" + ptVie + ", degAtt=" + degAtt + ", ptPar=" + ptPar + ", pageAtt=" + pageAtt + ", pagePar=" + pagePar + ", distAttMax=" + distAttMax + ", pos=" + pos + '}';
    }
    
    void affiche() {
        System.out.println(this.toString());
    }
    
    
    
    
    
    
}
