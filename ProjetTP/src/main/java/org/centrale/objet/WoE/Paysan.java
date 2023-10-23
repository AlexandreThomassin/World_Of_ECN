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
public class Paysan extends Personnage {

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
    
    public Paysan(StringTokenizer tokenizer){
        super();
        setNom(tokenizer.nextToken());
        setPtVie(parseInt(tokenizer.nextToken()));
        setDegAtt(parseInt(tokenizer.nextToken()));
        setPtPar(parseInt(tokenizer.nextToken()));
        setPageAtt(parseInt(tokenizer.nextToken()));
        setPagePar(parseInt(tokenizer.nextToken()));
        setDistAttMax(parseInt(tokenizer.nextToken()));
        int x = parseInt(tokenizer.nextToken());
        int y = parseInt(tokenizer.nextToken());
        Point2D pos = new Point2D(x, y);
        
        setPos(pos);
    }
    
    public void combattre(Creature c){
        System.out.println("Les paysans ne se battent pas. Passage à la créature suivante");
    }
    
    public String toSave(){
        return "Paysan " + this.getNom().substring(this.getNom().length()-2) + " " + this.getPtVie() + " " + this.getDegAtt() + " " + this.getPtPar() + " " 
                + this.getPageAtt() + " " + this.getPagePar() + " " + this.getDistAttMax() + " "  
                + this.getPos().getX() + " " + this.getPos().getY();
    }
    
}
