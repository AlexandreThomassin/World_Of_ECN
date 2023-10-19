    package org.centrale.objet.WoE;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Joueur {
    public String getNom() {
        return nom;
    }

    private String nom;
    private String mail;
    private Date dateNaissance;
    private Personnage personnage;
    public Joueur(String nom){
        this.nom=nom;
        Class guerrier=Guerrier.class;
        Class archer=Archer.class;
        Scanner input = new Scanner(System.in);
        System.out.println("Choisir un type de personnage !");
        String typePersonnage;
        do {
            typePersonnage= input.nextLine();
        }while ((!typePersonnage.equals(guerrier.getSimpleName())&&
                !typePersonnage.equals(archer.getSimpleName()))||typePersonnage.isEmpty());
        System.out.println("Choisir un nom du personnage !");
        String nomPersonnage;
        do {
            nomPersonnage= input.nextLine();
        }while(nomPersonnage.isEmpty());
        if(typePersonnage.equals("Guerrier")){
            personnage=new Guerrier(nomPersonnage);
        }else{
            personnage=new Archer(nomPersonnage);
        }
    }
    
    public Joueur(StringTokenizer tokenizer){
        this.nom = tokenizer.nextToken();
        if (tokenizer.hasMoreTokens()){
            String choix = tokenizer.nextToken();
            System.out.println(choix);
            if (choix.equals("Guerrier")){
                this.personnage = new Guerrier(tokenizer);
            } else if(choix.equals("Archer")) {
                this.personnage = new Archer(tokenizer);
            }
            else {
                System.out.println("La classe sauvegardée pour le joueur est incorrecte");
            }
        }
    }

    public Personnage getPersonnage() {
        return personnage;
    }
    
    public void initialiserPosition(ArrayList<Point2D> positionsOccupees){
        Random gen= new Random();
        int x,y;
        do {
            x = gen.nextInt(50);
            y = gen.nextInt(50);
            this.personnage.setPos(x,y);
        } while (positionsOccupees.contains(this.personnage));
        positionsOccupees.add(this.personnage.getPos());
    }
    public void faireChoix(ArrayList<Point2D> positionsOccupees,ArrayList<Creature> creatures){
        Scanner input = new Scanner(System.in);
        System.out.println("Vous voulez vous déplacer ou combattre?\n Saisissez 'Combattre'" +
                "ou 'Se déplacer'");
        String choix;
        do {
            choix= input.nextLine();
        }
        while ((!choix.equals("Combattre")&&
                !choix.equals("Se déplacer"))||choix.isEmpty());
        if(choix.equals("Combattre")){
            System.out.println("Choisissez un numéro de créature à combattre entre 0 et 99");
            int choixNumero;
            do {
                choix= input.nextLine();
                choixNumero=Integer.parseInt(choix);
            }
            while (choixNumero<0||choixNumero>99);
            personnage.combattre(creatures.get(choixNumero));
        }else {
            personnage.deplace(positionsOccupees);
        }
    }

}
