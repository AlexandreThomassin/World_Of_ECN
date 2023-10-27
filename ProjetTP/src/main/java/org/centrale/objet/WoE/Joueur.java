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
        System.out.println("Un joueur vient d'être créé, nom : "+this.nom);
        Class guerrier=Guerrier.class;
        Class archer=Archer.class;
        Scanner input = new Scanner(System.in);
        System.out.println("Choisir un type de personnage : Guerrier | Archer");
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
        System.out.println("Le personnage choisi est de type " +personnage.getClass().getSimpleName()+
                " et a pour nom : "+personnage.getNom());
    }
    
    public Joueur(StringTokenizer tokenizer){
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
    
    public void initialiserPosition(ArrayList<Point2D> positionsOccupees,int n){
        Random gen= new Random();
        int x,y;
        do {
            x = gen.nextInt(n);
            y = gen.nextInt(n);
            this.personnage.setPos(x,y);
        } while (positionsOccupees.contains(this.personnage.getPos()));
        positionsOccupees.add(this.personnage.getPos());
        System.out.println(this.getPersonnage().getNom()+" est initialement à la position "+this.getPersonnage().getPos().toString());
    }
    public void faireChoix(ArrayList<Point2D> positionsOccupees, ArrayList<ElementDeJeu> creatures,
                           ArrayList<Objet> objets, int n){
        Scanner input = new Scanner(System.in);
        System.out.println("Vous voulez vous déplacer, combattre ou activer un objet de l'inventaire?\n Saisissez 'Combattre'" +
                "ou 'Se deplacer' ou 'Activer effet'");
        String choix;
        int choixNumero;
        do {
            choix= input.nextLine();
        }
        while ((!choix.equals("Combattre")&&
                !choix.equals("Se deplacer")&&!choix.equals("Activer effet"))||choix.isEmpty());
        if(choix.equals("Combattre")){
            System.out.println("Choisissez un numéro de créature à combattre entre 1 et "+creatures.size());

            do {
                choix= input.nextLine();
                choixNumero=Integer.parseInt(choix);
            }
            while (choixNumero<1||choixNumero>creatures.size());
            if(personnage.getClass().getSimpleName().equals("Guerrier")){
                ((Guerrier)personnage).combattre((Creature) creatures.get(choixNumero-1));
            }else {
                ((Archer)personnage).combattre((Creature) creatures.get(choixNumero-1));
            }

        }else if(choix.equals("Se deplacer")){
            personnage.deplacePerso(creatures, positionsOccupees, n);
            personnage.checkCase(objets);
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
            }else{
                System.out.println("Oups! Votre inventaire est vide!");
            }

        }
    }
    public void utiliseEffets(){
        Scanner input = new Scanner(System.in);
        int i=0;
        String choix;
        int choixNumero;
        System.out.println("Le joueur doit maintenant choisir l'un de ces effets à utiliser !");
        if(!getPersonnage().getEffets().isEmpty()){
            System.out.println("Voici la liste de vos effets : ");
            for(Utilisable utilisable:this.getPersonnage().getEffets()){
                System.out.println("Effet numéro "+(i+1)+" est de type "+utilisable.getClass().getSimpleName()+
                        ", il est à votre disposition pour "+utilisable.getDuree()+" tours");
                i+=1;
            }
            System.out.println("Veuillez choisir un numéro d'effet à utiliser");
            do {
                choix= input.nextLine();
                choixNumero=Integer.parseInt(choix);
            }while(choixNumero<1||choixNumero>personnage.getEffets().size());
            System.out.println("Vous avez choisi l'effet numéro "+choixNumero);
            ((ArrayList<Utilisable>) personnage.getEffets()).get(choixNumero-1).utilisePar(personnage);
        }else{
            System.out.println("Oups ! Vous n'avez pas d'effets à votre disposition");
        }
    }
    
    public String toSave(){
        return "Joueur " + this.personnage.toSavePerso();
    }

}
