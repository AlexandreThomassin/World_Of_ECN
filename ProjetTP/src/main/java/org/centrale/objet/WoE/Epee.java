package org.centrale.objet.WoE;

import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class Epee extends Objet implements Utilisable{
    private int degAtt;

    @Override
    public Integer getDuree() {
        return duree;
    }

    // Durée en tour de l'objet
    private Integer duree;
    // Valeur de l'effet : <0 si malus, >0 si bonus

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
    public Epee(Integer duree, Integer degAtt) {
        this.duree = duree;
        this.degAtt = degAtt;
        this.isActive = false;
    }
    public void utilisePar(Personnage personnage){
        if (!isActive){
            isActive = true;
            personnage.setDegAtt(personnage.getDegAtt()+degAtt);
            System.out.println("Le degré d'Attack de "+personnage.getNom()+" devient égal à" +
                    " "+personnage.getDegAtt());
            duree = duree - 1;
            System.out.println("L'effet reste à votre disposition pour "+duree+" tours");
        } else {
            if (duree == 0){
                personnage.setDegAtt(personnage.getDegAtt()-degAtt);
                System.out.println("Oups ! Cet effet devient un Malus\nLe degré d'Attack de " +
                        personnage.getNom()+" devient "+personnage.getDegAtt());
                System.out.println("On enlève cet objet de notre collection");
                personnage.getEffets().remove(this);
            }else{
                personnage.setDegAtt(personnage.getDegAtt()+degAtt);
                System.out.println("Le pourcentage d'Attack de "+personnage.getNom()+" devient égal à" +
                        " "+personnage.getDegAtt());
                duree = duree - 1;
                System.out.println("L'effet reste à votre disposition pour "+duree+" tours");
            }
        }
    }

    public String toSave(){
        return "Epee " + this.degAtt + " " + this.getPos().getX() + " " + this.getPos().getY();
    }
}
