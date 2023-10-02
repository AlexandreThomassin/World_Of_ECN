/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;

/**
 *
 * @author alex4
 * @author moufid
 */
public abstract class Monstre extends Creature{

    /**constructeur 1
     * @param ptVie les points de vie à donner au monstre
     * etc...*/
    public Monstre( String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
    }
    /**constructeur 2
     * @param m pour faire une copie du monstre*/
    public Monstre(Monstre m){
        super(m);
    }

    public Monstre() {
        super();
    }

    /** On surcharge toString() afin d'afficher notre monstre proprement*/
    @Override
    public String toString() {
        return "Monstre{" + "ptVie=" + getPtVie() + ", degAtt=" + getDegAtt() + ", ptPar=" + getPtPar() + ", pageAtt=" + getPageAtt() + ", pagePar=" + getPagePar() + ", pos=" + getPos() + '}';
    }
    
    public void affiche() {
        System.out.println(this.toString());
    }
}
