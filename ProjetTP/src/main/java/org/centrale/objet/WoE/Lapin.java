/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 *
 * @author alex4
 */
public class Lapin extends Monstre {

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
    
    public Lapin(StringTokenizer tokenizer){
        super();
        setNom(tokenizer.nextToken());
        setPtVie(parseInt(tokenizer.nextToken()));
        setDegAtt(parseInt(tokenizer.nextToken()));
        setPtPar(parseInt(tokenizer.nextToken()));
        setPageAtt(parseInt(tokenizer.nextToken()));
        setPagePar(parseInt(tokenizer.nextToken()));
        int x = parseInt(tokenizer.nextToken());
        int y = parseInt(tokenizer.nextToken());
        Point2D pos = new Point2D(x, y);
        
        setPos(pos);
    }
    
    public void combattre(Creature c){
    }
    
    public String toSave(){
        return "Lapin " + this.getNom() + " " + this.getPtVie() + " " + this.getDegAtt() + " " + this.getPtPar() + " "
                + this.getPageAtt() + " " + this.getPagePar() + " " + this.getPos().getX() + " " + this.getPos().getY();
    }
    
}
