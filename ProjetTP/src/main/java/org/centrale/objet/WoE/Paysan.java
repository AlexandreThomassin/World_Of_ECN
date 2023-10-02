/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author alex4
 */
public abstract class Paysan extends Personnage {

    public Paysan(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, pos);
    }

    public Paysan(Paysan p) {
        super(p);
    }

    public Paysan() {
    }
    public Paysan(int numeroPaysan){
        super();
        setNom("Paysan n "+numeroPaysan);
        setDegAtt(0);
        setPageAtt(0);
        setPagePar(10);
        setPtPar(2);
        setDistAttMax(0);

    }
    
}
