package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Creature extends ElementDeJeu implements Deplaceable{
    /** points de vie de la creature*/
    private int ptVie;
    private int degAtt;
    private int ptPar;
    private int pageAtt;
    private int pagePar;


    /**constructeur 1
     * @param ptVie points de vie à donner à la créature
     * etc...*/
    public Creature(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos) {
        setNom(nom);
        this.ptVie = ptVie;
        this.degAtt = degAtt;
        this.ptPar = ptPar;
        this.pageAtt = pageAtt;
        this.pagePar = pagePar;
        setPos(new Point2D(pos));
    }
    /**constructeur 2
     * @param creature  pour faire une copie de la créature*/
    public Creature(Creature creature){
        setNom(creature.getNom());
        this.ptVie = creature.ptVie;
        this.degAtt = creature.degAtt;
        this.ptPar = creature.ptPar;
        this.pageAtt = creature.pageAtt;
        this.pagePar = creature.pagePar;
        setPos(new Point2D(creature.getPos()));
    }
    public Creature(){
        ptVie=100;
    }

    public int getPtVie() {
        return ptVie;
    }

    public int getDegAtt() {
        return degAtt;
    }

    public int getPtPar() {
        return ptPar;
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public int getPagePar() {
        return pagePar;
    }

    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    /** méthode permettant le déplacement de la creature sur une
     * case adjacente, faisant appel à la méthode translate dela classe
     * Point2D
     */
    public void deplace(ArrayList<ElementDeJeu> elementsDeJeu) {
        Random genX = new Random();
        Random genY = new Random();
        int dx=0;
        int dy=0;
        boolean positionOccupee=false;
        /**Tant que (dx=0 et dy=0) ou la nouvelle position est déjà occupée on refait le tir*/
        do {
            positionOccupee=false;
            do{
                dx = genX.nextInt(3)-1;
                dy = genY.nextInt(3)-1;
            }while((dx==0&&dy==0)||(this.getPos().getX()+dx<0||this.getPos().getY()+dy<0));
            for(ElementDeJeu elementDeJeu:elementsDeJeu){
                if(elementDeJeu.getPos().equals(new Point2D(this.getPos().getX()+dx,this.getPos().getY()+dy))){
                    positionOccupee=true;
                    break;
                }
            }
            /**On fait la translation si la case n'est pas occupee*/
            if(!positionOccupee) this.getPos().translate(dx, dy);
        }while(positionOccupee);

        System.out.println(getNom()+" est désormais à la position "+getPos().toString());
    }
    
        public void deplacePerso(ArrayList<ElementDeJeu> elementsDeJeu, ArrayList<Point2D> positionsOccupees, int n) {
            
        Scanner input = new Scanner(System.in);
    
        int dx=0;
        int dy=0;
        boolean positionOccupee=false;
        /**Tant que (dx=0 et dy=0) ou la nouvelle position est déjà occupée on refait le tir*/
        do {
            positionOccupee=false;
            do{
                System.out.println("Veuillez choisir un nombre entre -1 et 1 ou le déplacement horizontal");
                String choix = input.nextLine();
                dx = Integer.parseInt(choix);
                if ((this.getPos().getX()+dx<0) || (this.getPos().getX()+dx >= n)){
                    System.out.println("Vous ne pouvez pas vous déplacer dans cette direction, vous êtes au bord du monde");
                    
                }
                
                System.out.println("Veuillez choisir un nombre entre -1 et 1 ou le déplacement vertical");
                choix = input.nextLine();
                
                dy = Integer.parseInt(choix);
                
                if ((this.getPos().getY()+dy<0) || (this.getPos().getY()+dy >= n)){
                    System.out.println("Vous ne pouvez pas vous déplacer dans cette direction, vous êtes au bord du monde");
                    
                }
                
            }while((dx==0&&dy==0)||(this.getPos().getX()+dx<0||this.getPos().getY()+dy<0||this.getPos().getX()+dx >= n||this.getPos().getY()+dy >= n));
            for(ElementDeJeu elementDeJeu:elementsDeJeu){
                if(elementDeJeu.getPos().equals(new Point2D(this.getPos().getX()+dx,this.getPos().getY()+dy))){
                    positionOccupee=true;
                    System.out.println("La position est occupée, vous ne pouvez pas vous déplacer ici");
                    break;
                }
            }
            
            int i,j;
            boolean blocked = true;
            for(i=-1; i<=1;i++){
                for(j=-1; j<=1;j++){
                    if((i!=0) && (j!=0)){
                        if (!positionsOccupees.contains(new Point2D(this.getPos().getX()+i,this.getPos().getY()+j))){
                            blocked = false;
                        }
                    }
                }
            }
            if (blocked){
                System.out.println("Vous êtes bloqués de tout les cotés, veuillez tuer les enemis pour avancer");
                break;
            }
            
            
            /**On fait la translation si la case n'est pas occupee*/
            if(!positionOccupee) this.getPos().translate(dx, dy);
        }while(positionOccupee);

        System.out.println(getNom()+" est désormais à la position "+getPos().toString());
    }
    
    
    
    
    public void recupererPotionVie(int ptVie){
        this.ptVie+=ptVie;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        return getPos().equals(((Creature) obj).getPos());
    }

}
