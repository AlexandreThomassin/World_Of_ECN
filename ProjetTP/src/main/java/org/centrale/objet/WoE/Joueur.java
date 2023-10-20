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

    public Personnage getPersonnage() {
        return personnage;
    }

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
            x = gen.nextInt(4);
            y = gen.nextInt(4);
            this.personnage.setPos(x,y);
        } while (positionsOccupees.contains(this.personnage.getPos()));
        positionsOccupees.add(this.personnage.getPos());
        System.out.println(this.getPersonnage().getNom()+" est initialement à la position "+this.getPersonnage().getPos().toString());
    }
    public void faireChoix(ArrayList<Point2D> positionsOccupees,ArrayList<Creature> creatures,
                           ArrayList<Objet> objets){
        Scanner input = new Scanner(System.in);
        System.out.println("Vous voulez vous déplacer, combattre ou activer un objet de l'inventaire?\n Saisissez 'Combattre'" +
                "ou 'Se déplacer' ou 'Activer effet'");
        String choix;
        int choixNumero;
        do {
            choix= input.nextLine();
        }
        while ((!choix.equals("Combattre")&&
                !choix.equals("Se déplacer")&&!choix.equals("Activer effet"))||choix.isEmpty());
        if(choix.equals("Combattre")){
            System.out.println("Choisissez un numéro de créature à combattre entre 1 et 10");
            do {
                choix= input.nextLine();
                choixNumero=Integer.parseInt(choix);
            }
            while (choixNumero<1||choixNumero>10);
            personnage.combattre(creatures.get(choixNumero-1));
        }else if(choix.equals("Se déplacer")){
            personnage.deplace(positionsOccupees);
            personnage.ramasserObjet(objets);
        }else{
            System.out.println("Vous avez choisi d'activer un objet de l'inventaire!");
            if (!personnage.getInventaire().isEmpty()){
                for(int i=0;i<personnage.getInventaire().size();i++){
                    System.out.println("Objet n° "+(i+1)+" est de type "
                            +personnage.getInventaire().get(i).getClass().getSimpleName());
                }
                System.out.println("Veuillez choisir un numéro d'objet à utiliser");
                do {
                    choix= input.nextLine();
                    choixNumero=Integer.parseInt(choix);
                }while(choixNumero<1||choixNumero>personnage.getInventaire().size());
                System.out.println("Vous avez choisi l'objet numéro "+choixNumero);
                personnage.getEffets().add((Utilisable) personnage.getInventaire().get(choixNumero-1));
                personnage.getInventaire().remove(choixNumero-1);
                System.out.println("List des effets: "+personnage.getEffets().toString());
            }else{
                System.out.println("Oups! Votre inventaire est vide!");
            }

        }
    }
    public void utiliseEffets(){
        if(!getPersonnage().getEffets().isEmpty()){
            for(Utilisable utilisable:this.getPersonnage().getEffets()){
                utilisable.utilisePar(this.getPersonnage());
            }
        }else{
            System.out.println("Oups ! Vous n'avez pas d'effets à votre disposition");
        }
    }

}
