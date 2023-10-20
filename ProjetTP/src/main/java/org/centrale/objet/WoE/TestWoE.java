/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.ArrayList;

/**
 *
 * @author alex4
 */
public class TestWoE {
    public static void main(String[] args) {
        /**Initialisation de la partie*/
        World monde = new World();
        monde.chargementPartie("Sauvegarde-WoE.txt");
        monde.creerMondeAlea();
        System.out.println("Une partie de jeu démarre !");
        Joueur joueur=new Joueur("Alexandre");
        System.out.println("Un joueur vient d'être créé, nom : "+joueur.getNom());
        System.out.println("Son personnage choisi est de type " +joueur.getPersonnage().getClass().getSimpleName()+
                " et a pour nom : "+joueur.getPersonnage().getNom());
        joueur.initialiserPosition(monde.getPositionsOccupees());
        joueur.getPersonnage().ramasserObjet(monde.getObjets());
        monde.chargementPartie("test.txt");
        /**Tours de jeu a effectuer*/
        int i=0;
        while (i<10){
            System.out.println("Tour n° "+(i+1));
            monde.tourDeJeu(joueur);
            i+=1;
        }
        monde.sauvegardePartie("test.txt");

    }
}
