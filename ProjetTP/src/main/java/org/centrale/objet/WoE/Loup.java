package org.centrale.objet.WoE;

import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

public class Loup extends Monstre{
    public Loup(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos){
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
    }
    public Loup(Loup l){
        super(l);
    }
    public Loup(){
        super();
    }
    public Loup(int numeroLoup){
        super();
        setNom("Loup n "+numeroLoup);
        setDegAtt(20);
        setPageAtt(60);
        setPagePar(40);
        setPtPar(5);
    }
    
    public Loup(StringTokenizer tokenizer){
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
        return "Loup " + this.getNom() + " " + this.getPtVie() + " " + this.getDegAtt() + " " + this.getPtPar() + " "
                + this.getPageAtt() + " " + this.getPagePar() + " " + this.getPos().getX() + " " + this.getPos().getY();
    }
}
