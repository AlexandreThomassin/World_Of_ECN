package org.centrale.objet.WoE;

import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Epee extends Objet{
    private int degAtt;
    // Durée en tour de l'objet
    private Integer duree;
    // Valeur de l'effet : <0 si malus, >0 si bonus
    private Integer value;

    private boolean isActive;
    /**valeur de ptVie par défaut*/
    public Epee(){
        super();
        degAtt=10;
    }

    public Epee(StringTokenizer tokenizer){
        super();
        degAtt = parseInt(tokenizer.nextToken());
        int x = parseInt(tokenizer.nextToken());
        int y = parseInt(tokenizer.nextToken());
        Point2D pos = new Point2D(x,y);
        setPos(pos);
    }
    public Epee(Integer duree, Integer value) {
        this.duree = duree;
        this.value = value;
        this.isActive = false;
    }

    public String toSave(){
        return "Epee " + this.degAtt + " " + this.getPos().getX() + " " + this.getPos().getY();
    }
}
