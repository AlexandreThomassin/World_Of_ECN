package org.centrale.objet.WoE;

import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

public class PotionSoin extends Objet implements Utilisable{
    // Valeur de l'effet : <0 si malus, >0 si bonus
    private int ptVie;

    @Override
    public Integer getDuree() {
        return duree;
    }

    // Durée en tour de l'objet
    private Integer duree;
    private boolean isActive;
    /**valeur de ptVie par défaut*/
    public PotionSoin(){
        super();
        ptVie=50;
    }
    public PotionSoin(Integer duree, Integer ptVie) {
        this.duree = duree;
        this.ptVie = ptVie;
        this.isActive = false;
    }
    
    public PotionSoin(StringTokenizer tokenizer){
        super();
        ptVie = parseInt(tokenizer.nextToken());
        int x = parseInt(tokenizer.nextToken());
        int y = parseInt(tokenizer.nextToken());
        Point2D pos = new Point2D(x,y);
        setPos(pos);
    }
    public void utilisePar(Personnage personnage){
        if (!isActive){
            isActive = true;
            personnage.setPtVie(personnage.getPtVie()+ptVie);
            System.out.println(personnage.getNom()+" a désormais " +
                    personnage.getPtVie()+" points vie");
            // On décrémente la durée à chaque utilisation
            duree = duree - 1;
            System.out.println("L'effet reste à votre disposition pour "+duree+" tours");
        } else {
            // Si la durée arrive à 0 on inverse les effets de la nourriture pour revenir à la normale
            if (duree == 0){
                personnage.setPtVie(personnage.getPtVie()-ptVie);
                System.out.println("Oups ! Cet effet devient un Malus\n"+
                        personnage.getNom()+" a désormais "+personnage.getPtVie()+" points vie");
                System.out.println("On enlève cet objet de notre collection");
                personnage.getEffets().remove(this);
            }else{
                personnage.setPtVie(personnage.getPtVie()+ptVie);
                System.out.println(personnage.getNom()+" a désormais " +
                        personnage.getPtVie()+" points vie");
                // On décrémente la durée à chaque utilisation
                duree = duree - 1;
                System.out.println("L'effet reste à votre disposition pour "+duree+" tours");
            }
        }
    }
    
    public String toSave(){
        return "PotionSoin " + this.ptVie + " " + this.getPos().getX() + " " + this.getPos().getY();
    }
}
