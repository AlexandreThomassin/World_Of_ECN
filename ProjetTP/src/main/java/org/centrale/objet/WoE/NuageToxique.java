package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Random;

public class NuageToxique extends Objet implements Deplaceable,Combat{
    private int ptDegat;
    public NuageToxique(int ptDegat){
        this.ptDegat=ptDegat;
    }

    public String toSave(){
        return "Epee " + this.ptDegat + " " + this.getPos().getX() + " " + this.getPos().getY();
    }
    public void deplace(ArrayList<ElementDeJeu> elementsDeJeu) {
        Random genX = new Random();
        Random genY = new Random();
        int dx;
        int dy;
        /**Tant que (dx=0 et dy=0) ou la nouvelle position est déjà occupée on refait le tir*/
        do {
            dx = genX.nextInt(3)-1;
            dy = genY.nextInt(3)-1;
        } while ((dx==0 &&dy==0)||elementsDeJeu.contains(new Point2D(getPos().getX()+dx,getPos().getY()+dy)));

        /**On fait la translation*/
        this.getPos().translate(dx, dy);
        System.out.println(getNom()+" est désormais à la position "+getPos().toString());
    }
    public void combattre(Creature c){
        System.out.println("Le nuage va attacker "+c.getNom());
        System.out.println("Points de vie de "+c.getNom()+" avant attack : "+c.getPtVie());
        c.setPtVie(c.getPtVie()-ptDegat);
        System.out.println("Points de vie de "+c.getNom()+" après attack : "+c.getPtVie());

    }
}
