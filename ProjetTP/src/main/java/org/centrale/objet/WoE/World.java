/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.LinkedList;
import java.util.Random;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 *
 * @author alex4
 * @author moufid
 */
public class World {
    /**Tailledu monde*/
    private static int n= 50;
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
    private ArrayList<Creature> creatures;
    private ArrayList<Point2D> positionsOccupees;
    private ArrayList<Objet> objets;


    /**Constructeur qui initialise les objets de notre monde*/
    public World() {
        creatures=new ArrayList<Creature>();
        positionsOccupees=new ArrayList<Point2D>();
        objets=new ArrayList<Objet>();
    }
    
    public ArrayList<Creature> getCreatures() {
        return creatures;
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
        PotionSoin potion=new PotionSoin();
        potion.initialiserPosition(objets);

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
        //creatures.get(0).combattre(creatures.get(15));
        Joueur joueur=new Joueur("Alexandre");
        joueur.initialiserPosition(positionsOccupees);
        System.out.println("Un joueur vient d'être créé, nom : "+joueur.getNom());
        joueur.faireChoix(positionsOccupees,creatures);
    }
    
    public void chargementPartie(String name){
        
        // On définit nos délémiteurs
        String delimiters = " ";
        StringTokenizer tokenizer;
        
        try {
            String line;
            // On crée notre buffer pour lire le fichier
            BufferedReader fichier = new BufferedReader(new FileReader(name));
            
            // On lit la première ligne
            line = fichier.readLine();
            while (line != null){
                
                // On sépare les éléments contenus dans la ligne
                tokenizer = new StringTokenizer(line, delimiters);
                
                
                if (tokenizer.hasMoreTokens()){
                    String mot = tokenizer.nextToken();
                    
                    
                    // On passe le mot en lettre minuscule pour éviter les mot mal écrit
                    mot = mot.toLowerCase();
                    System.out.println(mot);
                    
                    switch(mot){
                        case "largeur":
                            try {
                                this.n = parseInt(tokenizer.nextToken());
                            } catch (Exception e){
                                System.out.println("Sauvegarde incorrecte : Taille du monde manquante");
                            }
                            break;
                            
                        case "archer":
                            try {
                                Archer a = new Archer(tokenizer);
                                this.creatures.add(a);
                                this.positionsOccupees.add(a.getPos());
                                
                            } catch (Exception e){
                                System.out.println("Sauvegarde incorrecte : Il manque des arguments pour la création d'un archer");
                            }
                            break;
                        
                        case "guerrier":
                            try {
                                Guerrier g = new Guerrier(tokenizer);
                                this.creatures.add(g);
                                this.positionsOccupees.add(g.getPos());
                                
                            } catch (Exception e){
                                System.out.println("Sauvegarde incorrecte : Il manque des arguments pour la création d'un guerrier");
                            }
                            break;
                            
                        case "paysan":
                            try {
                                Paysan p;
                                p = new Paysan(tokenizer);
                                this.creatures.add(p);
                                this.positionsOccupees.add(p.getPos());
                                
                            } catch (Exception e){
                                System.out.println("Sauvegarde incorrecte : Il manque des arguments pour la création d'un paysan");
                            }
                            break;
                            
                        case "loup":
                            try {
                                Loup l;
                                l = new Loup(tokenizer);
                                this.creatures.add(l);
                                this.positionsOccupees.add(l.getPos());
                                
                            } catch (Exception e){
                                System.out.println("Sauvegarde incorrecte : Il manque des arguments pour la création d'un loup");
                            }
                            break;
                            
                        case "lapin":
                            try {
                                Lapin l;
                                l = new Lapin(tokenizer);
                                this.creatures.add(l);
                                this.positionsOccupees.add(l.getPos());
                                
                            } catch (Exception e){
                                System.out.println("Sauvegarde incorrecte : Il manque des arguments pour la création d'un lapin");
                            }
                            break;
                            
                        case "potionsoin":
                            try {
                                PotionSoin p;
                                p = new PotionSoin(tokenizer);
                                this.objets.add(p);  
                                this.positionsOccupees.add(p.getPos());
                                
                            } catch (Exception e){
                                System.out.println("Sauvegarde incorrecte : Il manque des arguments pour la création d'une potion de soin");
                            }
                            break;
                            
                        case "joueur":
                            // On charge le joueur
                            try {
                                Joueur j = new Joueur(tokenizer);
                                this.creatures.add(j.getPersonnage());
                                this.positionsOccupees.add(j.getPersonnage().getPos());

                            } catch (Exception e){
                                System.out.println("Sauvegarde incorrecte : Il manque des arguments pour la création d'un joueur");
                            }                                    
                            
                            // On charge ensuite l'inventaire
                            
                            
                            break;
                            
                        default:
                            System.out.println("La classe " + mot + " n'est pas reconnu");
                    }
                }
                
                line = fichier.readLine();
            }
           
            
            fichier.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        
        
    }


    public void sauvegardePartie(String nom){
        //On enlève les espaces inutiles
        nom = nom.trim();
        
        if (nom.isEmpty()){
            // Si le nom du fichier de sauvegarde est vide, on en crée un automatiquement
            
            
        }
        
        
        BufferedWriter bufferedWriter = null;
        
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(nom));
            
            bufferedWriter.write("Largeur " + this.n);
            bufferedWriter.newLine();
            bufferedWriter.write("Hauteur " + this.n);
            bufferedWriter.newLine();
            
            for (Creature c: this.creatures){
                bufferedWriter.write(c.toSave());
                bufferedWriter.newLine();
            }
            
            for (Objet o: this.objets){
                bufferedWriter.write(o.toSave());
                bufferedWriter.newLine();
            }
            
            
        }
        
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        
        finally {
            try {
                if (bufferedWriter != null){
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        
        
    }
    
    


}
