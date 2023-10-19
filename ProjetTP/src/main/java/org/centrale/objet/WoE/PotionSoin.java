package org.centrale.objet.WoE;

import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

public class PotionSoin extends Objet{
    private int ptVie;
    /**valeur de ptVie par défaut*/
    public PotionSoin(){
        super();
        ptVie=50;
    }
    
    public PotionSoin(StringTokenizer tokenizer){
        super();
        ptVie = parseInt(tokenizer.nextToken());
        int x = parseInt(tokenizer.nextToken());
        int y = parseInt(tokenizer.nextToken());
        Point2D pos = new Point2D(x,y);
        setPos(pos);
    }
    
    public String toSave(){
        return "PotionSoin " + this.ptVie + " " + this.getPos().getX() + " " + this.getPos().getY();
    }
}
