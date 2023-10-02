/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author alex4
 */
public abstract class Lapin extends Monstre {

    public Lapin(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
    }

    public Lapin(Lapin l) {
        super(l);
    }

    public Lapin() {
    }
    public Lapin(int numeroLapin){
        super();
        setNom("Lapin n "+numeroLapin);
        setDegAtt(0);
        setPageAtt(0);
        setPagePar(10);
        setPtPar(2);
    }
    
}
