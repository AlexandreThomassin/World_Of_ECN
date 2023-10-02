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
 *
 */
public abstract class Personnage extends Creature{

    /** distance d'attaque maximale*/
    private int distAttMax;

    /**constructeur 1
     * @param nom c'est le nom à donner au personnage
     * etc...*/
    public Personnage(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        super(nom,ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
        this.distAttMax = distAttMax;

    }
    /**constructeur 2
     * @param perso c'est pour faire une copie du personnage*/
    public Personnage(Personnage perso){
        super(perso);
        this.distAttMax = perso.distAttMax;

    }

    public Personnage() {
        super();
    }
    /** getters et setters des différents attributs*/

    public int getDistAttMax() {
        return distAttMax;
    }

    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }


    /** On surcharge toString() afin d'afficher notre personnage proprement*/
    @Override
    public String toString() {
        return "Personnage{" + "nom=" + this.getNom() + ", ptVie=" + getPtVie() + ", degAtt=" + getDegAtt() + ", ptPar=" + getPtPar() + ", pageAtt=" + getPageAtt() + ", pagePar=" + getPagePar() + ", distAttMax=" + distAttMax + ", pos=" + getPos() + '}';
    }
    
    public void affiche() {
        System.out.println(this.toString());
    }

    
    
    
    
    
}
