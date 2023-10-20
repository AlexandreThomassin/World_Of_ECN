package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Random;

public class NuageToxique extends Objet implements Deplaceable{
    private int pageDegat;
    public NuageToxique(int pageDegat){
        this.pageDegat=pageDegat;
    }

    public String toSave(){
        return "Epee " + this.pageDegat + " " + this.getPos().getX() + " " + this.getPos().getY();
    }
    public void deplace(ArrayList<Point2D> positionsOccupees) {
        Random genX = new Random();
        Random genY = new Random();
        int dx;
        int dy;
        /**Tant que (dx=0 et dy=0) ou la nouvelle position est déjà occupée on refait le tir*/
        do {
            dx = genX.nextInt(3)-1;
            dy = genY.nextInt(3)-1;
        } while ((dx==0 &&dy==0)||positionsOccupees.contains(new Point2D(getPos().getX()+dx,getPos().getY()+dy)));
        /**Si la position actuelle existait déjà dans notre tableau de positions occupées, on retire
         * celle-ci de notre tableau avant de faire la translation
         */
        if(positionsOccupees.contains(getPos())){
            positionsOccupees.remove(getPos());
        }
        /**On fait la translation*/
        this.getPos().translate(dx, dy);
        /**On ajoute la nouvelle positions aux positions occupees*/
        positionsOccupees.add(getPos());
        System.out.println(getNom()+" est désormais à la position "+getPos().toString());
    }
}
