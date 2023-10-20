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
    private static final int n= 4;
    /**Modélise le nombre de chaque catégorie de créatures (34 pour Archer voir boucle for)*/
    private static final int nb= 3;
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
    private ArrayList<Creature> creatures;

    public ArrayList<Point2D> getPositionsOccupees() {
        return positionsOccupees;
    }

    private ArrayList<Point2D> positionsOccupees;

    public ArrayList<Objet> getObjets() {
        return objets;
    }

    private ArrayList<Objet> objets;


    /**Constructeur qui initialise les objets de notre monde*/
    public World() {
        creatures=new ArrayList<Creature>();
        positionsOccupees=new ArrayList<Point2D>();
        objets=new ArrayList<Objet>();
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
        //PotionSoin potion=new PotionSoin();
        //potion.initialiserPosition(objets);
        Random gen = new Random();
        int x;
        int y;
        for (int i = 0; i < (3*nb+1); i++){
            Nourriture nourriture=new Nourriture(3,5,"degAtt");
            nourriture.initialiserPosition(objets);
        }

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

        /** Les objets effectuent leur premier déplacement*/
        for (int i = 0; i < creatures.size(); i++) {
            creatures.get(i).deplace(positionsOccupees);
        }

    }
    void tourDeJeu(Joueur joueur){
        joueur.faireChoix(positionsOccupees,creatures,objets);
        joueur.utiliseEffets();
    }
    
}
