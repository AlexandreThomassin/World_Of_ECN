/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Scanner;

/**
 *
 * @author alex4
 */
public class TestWoE {
    public static void main(String[] args) {
        /**Initialisation de la partie*/
        World monde = new World();
        //monde.chargementPartie("Sauvegarde-WoE.txt");
        monde.creerMondeAlea();
        System.out.println("Une partie de jeu démarre !");
        System.out.println("Choisir un nom du joueur !");
        Scanner input = new Scanner(System.in);
        String nomJoueur;
        do {
            nomJoueur= input.nextLine();
        }while(nomJoueur.isEmpty());
        Joueur joueur=new Joueur(nomJoueur);
        joueur.initialiserPosition(monde.getPositionsOccupees(),monde.getN());
        joueur.getPersonnage().checkCase(monde.getObjets());
        //monde.chargementPartie("test.txt");
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
