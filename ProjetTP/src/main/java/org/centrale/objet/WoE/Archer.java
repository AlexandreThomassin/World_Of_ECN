/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;

/**
 *
 * @author alex4
 */
public class Archer extends Personnage {
    private Projectile fleche;

    public Archer(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, int distAttMax, Point2D pos,Projectile p) {
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, distAttMax, pos);
        this.fleche=new Projectile(p);
    }

    public Archer(Archer a) {
        super(a);
    }

    public Archer() {
    }
    public Archer(int numeroArcher) {
       super();
       setNom("Archer n "+numeroArcher);
       setDegAtt(25);
       setPageAtt(75);
       setDistAttMax(5);
       setPagePar(40);
       setPtPar(5);
       fleche=new Projectile("flèche",5);
    }
    public void combattre(Creature c){
        System.out.println(this.getNom()+" décide d'attaquer "+c.getNom());
        Random jetDeAttaquant = new Random();
        Random jetDeDefenseur = new Random();
        int resAttaquant;
        int resDefenseur;
        double distance = this.getPos().distance(c.getPos());
        System.out.println("Distance entre les deux protagonistes = "+distance);
        if(distance==1){
            System.out.println("Attaque corps à corps !");
            resAttaquant=jetDeAttaquant.nextInt(100)+1;
            System.out.println("L'attaquant fait un jet de dé et obtient "+resAttaquant);
            if(resAttaquant<=this.getPageAtt()){
                System.out.println("L'attaque est réussie ! Le tir réalisé par l'attaquant " +
                        "est inférieur à son pageAtt "+resAttaquant+"<= "+getPageAtt());
                resDefenseur=jetDeDefenseur.nextInt(100)+1;
                System.out.println("Le défensuer fait un jet de dé et obtient "+resDefenseur);
                if(resDefenseur>c.getPagePar()){
                    System.out.println("Le défenseur ne va pas pouvoir se défendre ! Son tir " +
                            "est supérieur à son pagePar "+resDefenseur+"> "+c.getPagePar());
                    System.out.println("Points de vie du défenseur avant attaque : "+c.getPtVie());
                    c.setPtVie(c.getPtVie()-this.getDegAtt());
                }else{
                    System.out.println("Le défenseur va pouvoir se défendre !Son tir " +
                            "est inférieur à son pagePar "+resDefenseur+"<="+c.getPagePar());
                    System.out.println("Points de vie du défenseur avant attaque : "+c.getPtVie());
                    c.setPtVie(c.getPtVie()-this.getDegAtt()+c.getPtPar());
                }
            }
        }else if (distance>1&&distance<this.getDistAttMax()){
            System.out.println("Attaque à distance !");
            resAttaquant=jetDeAttaquant.nextInt(100)+1;
            System.out.println("L'attaquant fait un jet de dé et obtient "+resAttaquant);
            System.out.println("Nombre de projectile avant attaque : "+this.fleche.getNombre());
            System.out.println("L'attaquant retire un projectile !");
            this.fleche.setNombre(this.fleche.getNombre()-1);
            if(resAttaquant<=this.getPageAtt()){
                System.out.println("L'attaque est réussie ! Le tir réalisé par l'attaquant " +
                        "est inférieur à son pageAtt "+resAttaquant+"<= "+getPageAtt());
                System.out.println("Le défenseur ne peut pas se défendre !");
                System.out.println("Points de vie du défenseur avant attaque : "+c.getPtVie());
                c.setPtVie(c.getPtVie()-this.getDegAtt());
            }
        }else {
            System.out.println("Le défenseur est trop loin de l'attaquant !");
        }
        System.out.println("Points de vie du défenseur après attaque : "+c.getPtVie());
        System.out.println("Nombre de projectile après attaque : "+this.fleche.getNombre());
    }
    
}
