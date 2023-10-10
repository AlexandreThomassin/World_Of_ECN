/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.LinkedList;
import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author alex4
 * @author moufid
 */
public class World {
    /**Tailledu monde*/
    private static final int n= 50;
    /**Modélise le nombre de chaque catégorie de créatures (34 pour Archer voir boucle for)*/
    private static final int nb= 33;
    /**notre Archer Robin*/
    private Archer robin;
    /**notre Paysan Peon*/
    private Paysan peon;
    /**notre Lapin Bugs1*/
    private Lapin bugs1;
    private Lapin bugs2;
    /**notre Archer GuillaumeT*/
    private Archer guillaumeT;
    private Guerrier grosBill;
    private Loup wolfie;
    private LinkedList<Creature> creatures;
    private ArrayList<Point2D> positionsOccupees;
    private ArrayList<PotionSoin> positionsPotions;


    /**Constructeur qui initialise les objets de notre monde*/
    public World() {
        creatures=new LinkedList<Creature>();
        positionsOccupees=new ArrayList<Point2D>();
        positionsPotions=new ArrayList<PotionSoin>();
    }

    /**Cette methode initialiserPositions() a pour but de répartir les différents
     * protagonistes sur notre grille de manière aléatoire et sans aucune collision
     * entre nos créatures, on a ainsi nos positions initiales.
     * Le but c'est d'optimiser le code le plus possible, on ajoute donc un attribut
     * creatures qui est un tableau de creatures et qui contient toutes nos creatures.
     * Ensuite dans notre méthode on définit un tableau positionsOccupees qui va
     * stocker au fur et a mesure les positions interdites.
     * Puis avec deux simples boucles for et do while on parcourt notre tableau de creatures
     * et on affecte les positions initiales a ces dernieres sans aucun probleme
     */
    public void initialiserPositions() {
        /**On commence par positionner quelques potions*/
        positionsPotions.add(new PotionSoin());

        Random gen = new Random();
        int x;
        int y;

        /**Positionner nos Creatures*/
        for (int i = 0; i < (3*nb+1); i++) {
            if(i>=0&&i<nb+1){
                creatures.add(new Archer(i+1));
            }else if(i>=nb+1&&i<2*nb+1){
                creatures.add(new Guerrier(i+1));
            }else{
                creatures.add(new Loup(i+1));
            }
            do {
                x = gen.nextInt(n);
                y = gen.nextInt(n);
                creatures.get(creatures.size()-1).setPos(x, y);
            } while (positionsOccupees.contains(creatures.get(creatures.size()-1).getPos()));
            positionsOccupees.add(creatures.get(creatures.size()-1).getPos());
        }
        System.out.println("Nombre total de créatures : "+creatures.size());

    }
    /**Méthode de creation de notre monde la ou tous les événements se déroulent*/
    public void creerMondeAlea() {
        /**On va d'abord positionner nos creature de facon a ce qu'ils ne seraient
         * pas sur la meme case
         */
        initialiserPositions();
        /**ptVie total*/
        int totalPtVie=0;
        for (int i = 0; i < creatures.size(); i++) {
            /**On calcule le total de ptVie au départ*/
            totalPtVie+=creatures.get(i).getPtVie();
        }
        System.out.println("Total de ptVie au départ: "+totalPtVie);

        /** Les objets effectuent leur premier déplacement*/
        for (int i = 0; i < creatures.size(); i++) {
            creatures.get(i).deplace(positionsOccupees);
        }

    }
    void tourDeJeu(){
        System.out.println("Un tour de jeu démarre !");
        creatures.get(0).combattre(creatures.get(15));
        /**ptVie total*/
        int totalptVie=0;
        long debutN=System.nanoTime();
        for (int i = 0; i < creatures.size(); i++) {
            /**On calcule le total de ptVie après combat*/
            totalptVie+=creatures.get(i).getPtVie();
        }
        long finN=System.nanoTime();
        System.out.println("Temps de calcul de totalPtVie : "+(finN-debutN)+" ns");
        System.out.println("Total de ptVie à la fin du tour: "+totalptVie);
        positionsOccupees.get(0).setPosition(1,1);

    }
    
}
