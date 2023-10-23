/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
 *
 * @author alex4
 */
public class TestWoE {
    public static void main(String[] args) {
        /**Initialisation de la partie*/
        World monde = new World();
        Scanner input = new Scanner(System.in);
        //monde.chargementPartie("Sauvegarde-WoE.txt");
        
        System.out.println("Bonjour !\nVous allez jouer à World of ECN !");
        System.out.println("Faites un choix : \n1: Charger une partie.\n2: Démarrer une nouvelle partie.");
        
        String mode = input.nextLine();
        boolean choice = false;
        int cMode = 2;
        do{
            try{
                cMode = parseInt(mode);
                choice = true;
            } catch(Exception e){
                System.out.println("Ceci n'est pas une possibilité correcte !");
            }
        }while(!choice);
        
        if(cMode == 1){
            System.out.println("Entrer le nom de la sauvegarde :");
            String saveName = input.nextLine();
            try{
                monde.chargementPartie(saveName);
            } catch(Exception e){
                System.out.println("Le nom de sauvegarde est incorrect. Abandon");
            }
        } else {
            monde.creerMondeAlea();
            System.out.println("Une partie de jeu démarre !");
            System.out.println("Choisir un nom du joueur !");

            String nomJoueur;
            do {
                nomJoueur= input.nextLine();
            }while(nomJoueur.isEmpty());
            monde.setJoueur(new Joueur(nomJoueur));
            monde.getJoueur().initialiserPosition(monde.getPositionsOccupees(),monde.getN());
            monde.getJoueur().getPersonnage().checkCase(monde.getObjets());
        }
        

        //monde.chargementPartie("test.txt");
        /**Tours de jeu a effectuer*/
        int i=0;
        
        System.out.println("Veuillez choisir le nombre de tours à jouer");
        boolean playable = false;
        int nbTours = 1;
        do {
            String choix = input.nextLine();
            try{
                nbTours = parseInt(choix);
                playable = true;
            } catch (Exception e){
                System.out.println("Ce n'est pas un chiffre correct, réessayez !");
            }
        }while(!playable);
        
        while (i<nbTours){
            System.out.println("===========================");
            System.out.println("Tour n° "+(i+1));
            monde.tourDeJeu(monde.getJoueur());
            System.out.println("Sauvegarde de la partie");
            monde.sauvegardePartie("");
            i+=1;
        }
        monde.sauvegardePartie("test.txt");
//        monde.chargementPartie("test.txt");

    }
}
