package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Random;

public abstract class Objet extends ElementDeJeu{

    /**Afin de simplifier, on part du principe qu'on positionne nos objets
     * sans tester la présence d'autres objets sur la même case, le cas echeant
     * l'objet antecedant sera ecrase
     */
    public Objet(){
    }
    public void initialiserPosition(ArrayList<Objet> objets, int n){
        Random gen= new Random();
        int x,y;
        do {
            x = gen.nextInt(n);
            y = gen.nextInt(n);
            this.setPos(x,y);
        } while (objets.contains(this));
        objets.add(this);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null ||((this.getClass().getSimpleName().equals("NuageToxique")&&!obj.getClass().getSimpleName().equals("NuageToxique"))||(!this.getClass().getSimpleName().equals("NuageToxique")&&obj.getClass().getSimpleName().equals("NuageToxique")))){
            return false;
        }
        return getPos().equals(((Objet) obj).getPos());
    }

    public abstract String toSave();
    
}
