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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author alex4
 * @author moufid
 */
public class World {
    public static int getN() {
        return n;
    }

    /**Tailledu monde*/
    private static int n= 11;
    /**Modélise le nombre de chaque catégorie de créatures (34 pour Archer voir boucle for)*/
    private static final int nb= 5;
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
    private ArrayList<ElementDeJeu> creatures;

    public ArrayList<Point2D> getPositionsOccupees() {
        return positionsOccupees;
    }

    private ArrayList<Point2D> positionsOccupees;

    public ArrayList<Objet> getObjets() {
        return objets;
    }

    private ArrayList<Objet> objets;
    
    private Joueur joueur;

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }


    /**Constructeur qui initialise les objets de notre monde*/
    public World() {
        creatures=new ArrayList<ElementDeJeu>();
        positionsOccupees=new ArrayList<Point2D>();
        objets=new ArrayList<Objet>();
    }
    
    public ArrayList<ElementDeJeu> getCreatures() {
        return creatures;
    }

    /**Cette methode creerMondeAlea() a pour but de répartir les différents
     * protagonistes sur notre grille de manière aléatoire et sans aucune collision
     * entre nos créatures, on a ainsi nos positions initiales.
     * Le but c'est d'optimiser le code le plus possible, on ajoute donc un attribut
     * creatures qui est un tableau de creatures et qui contient toutes nos creatures.
     * Ensuite dans notre méthode on définit un tableau positionsOccupees qui va
     * stocker au fur et a mesure les positions interdites.
     * Puis avec deux simples boucles for et do while on parcourt notre tableau de creatures
     * et on affecte les positions initiales a ces dernieres sans aucun probleme
     */
    public void creerMondeAlea() {
        /**On commence par positionner quelques objets utilisables*/
        Random gen = new Random();
        int x;
        int y;
        for (int i = 0; i < (3*nb+1); i++){
            if(i>=0&&i<nb+1){
                Nourriture nourriture=new Nourriture(3,5);
                nourriture.initialiserPosition(objets,n);
            }else if(i>=nb+1&&i<2*nb+1){
                PotionSoin potion =new PotionSoin(1,50);
                potion.initialiserPosition(objets,n);
            }else{
                Epee epee=new Epee(5,10);
                epee.initialiserPosition(objets,n);
            }
        }
        /**On positionne quelques nuages toxiques*/
        for (int i = 0; i < 10; i++){
            NuageToxique nuageToxique=new NuageToxique(20);
            nuageToxique.initialiserPosition(objets,n);
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
    void tourDeJeu(Joueur joueur){
        displayWorld();
        System.out.println("---------------");
        joueur.faireChoix(positionsOccupees,creatures,objets);
        joueur.utiliseEffets();
    }
    
    public void chargementPartie(String name){
        
        /**On définit nos délémiteurs*/
        String delimiters = " ";
        StringTokenizer tokenizer;
        
        try {
            String line;
            /**On crée notre buffer pour lire le fichier*/
            BufferedReader fichier = new BufferedReader(new FileReader(name));
            
            /**On lit la première ligne*/
            line = fichier.readLine();
            while (line != null){
                
                /**On sépare les éléments contenus dans la ligne*/
                tokenizer = new StringTokenizer(line, delimiters);
                
                
                if (tokenizer.hasMoreTokens()){
                    String mot = tokenizer.nextToken();
                    
                    
                    /**On passe le mot en lettre minuscule pour éviter les mot mal écrit*/
                    mot = mot.toLowerCase();
                    
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
                                //this.positionsOccupees.add(p.getPos());
                                
                            } catch (Exception e){
                                System.out.println("Sauvegarde incorrecte : Il manque des arguments pour la création d'une potion de soin");
                            }
                            break;
                            
                        case "epee":
                            try {
                                Epee e;
                                e = new Epee(tokenizer);
                                this.objets.add(e);  
                                //this.positionsOccupees.add(p.getPos());

                            } catch (Exception e){
                                System.out.println("Sauvegarde incorrecte : Il manque des arguments pour la création d'une epee");
                            }
                            break;
                            
                        case "nourriture":
                            try {
                                Nourriture n;
                                n = new Nourriture(tokenizer);
                                this.objets.add(n);  
                                //this.positionsOccupees.add(p.getPos());

                            } catch (Exception e){
                                System.out.println("Sauvegarde incorrecte : Il manque des arguments pour la création d'une nourriture");
                            }
                            break;
                            
                            
                        case "joueur":
                            // On charge le joueur
                            try {
                                Joueur j = new Joueur(tokenizer);
                                this.joueur = j;
                                
                                this.positionsOccupees.add(j.getPersonnage().getPos());

                            } catch (Exception e){
                                System.out.println("Sauvegarde incorrecte : Il manque des arguments pour la création d'un joueur");
                            }                                    
                            
         
                            break;
                            
                        case "inventaire":
                            if (tokenizer.nextToken().equals("PotionSoin")){
                                try {
                                    PotionSoin p;
                                    p = new PotionSoin(tokenizer);
                                    this.objets.add(p);  
                                    //this.positionsOccupees.add(p.getPos());

                                } catch (Exception e){
                                    System.out.println("Sauvegarde incorrecte : Il manque des arguments pour la création d'une potion de soin");
                                }
                                
                            } else if (tokenizer.nextToken().equals("Epee")){
                                try {
                                    Epee e;
                                    e = new Epee(tokenizer);
                                    this.objets.add(e);  
                                    //this.positionsOccupees.add(p.getPos());

                                } catch (Exception e){
                                    System.out.println("Sauvegarde incorrecte : Il manque des arguments pour la création d'une epee");
                                }
                            } else if (tokenizer.nextToken().equals("Nourriture")){
                                try {
                                    Nourriture n;
                                    n = new Nourriture(tokenizer);
                                    this.objets.add(n);  
                                    //this.positionsOccupees.add(p.getPos());

                                } catch (Exception e){
                                    System.out.println("Sauvegarde incorrecte : Il manque des arguments pour la création d'une nourriture");
                                }
                            }
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestamp = dateFormat.format(new Date());
            nom = "save_" + timestamp + ".txt";
        }
       
        BufferedWriter bufferedWriter = null;
        
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(nom));
            
            // On sauvegarde les dimensions du monde
            bufferedWriter.write("Largeur " + this.n);
            bufferedWriter.newLine();
            bufferedWriter.write("Hauteur " + this.n);
            bufferedWriter.newLine();
            
            // On sauvegarde d'abord toutes les créatures
            for (ElementDeJeu c: this.creatures){
                bufferedWriter.write(c.toSave());
                bufferedWriter.newLine();
            }
            
            // On sauvegarde ensuite tous les objets
            for (Objet o: this.objets){
                bufferedWriter.write(o.toSave());
                bufferedWriter.newLine();
            }
            
            // On finit par sauvegarder le joueur et son inventaire
            bufferedWriter.write(this.joueur.toSave());
            bufferedWriter.newLine();
            
            for (ElementDeJeu e: this.joueur.getPersonnage().getInventaire()){
                bufferedWriter.write("Inventaire " + e.toSave());
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
    
    public void displayWorld(){
        String display = "- ".repeat(this.n+2) + "\n";
        display += ("| " + "+ ".repeat(this.n) + "| \n").repeat(this.n);
        display += "- ".repeat(this.n+2) + "\n";
        
        display += "Légende :\nP: Paysan | G: Guerrier | A: Archer\nW: Loup | L: Lapin | S: Epee\nF: Nourriture | H: Potion de Soin";
        
        StringBuilder disp = new StringBuilder(display); 
        
        for(ElementDeJeu c: creatures){
            int x = c.getPos().getX();
            int y = c.getPos().getY();
            
            char chr = '+';
            
            if(c instanceof Archer){
                chr = 'A';
            }
            
            if(c instanceof Guerrier){
                chr = 'G';
            }
         
            if(c instanceof Loup){
                chr = 'W';
            }
            
            if(c instanceof Lapin){
                chr = 'L';
            }
            
            if(c instanceof Paysan){
                chr = 'P';
            }
            
            disp.setCharAt((x + 1)*2 + (this.n - y)*(this.n*2+5), chr); 
        }
        
        for(Objet o: objets){
            int x = o.getPos().getX();
            int y = o.getPos().getY();
            
            char chr = '+';
            
            if(o instanceof Epee){
                chr = 'S';
            }
            
            if(o instanceof Nourriture){
                chr = 'F';
            }
         
            if(o instanceof PotionSoin){
                chr = 'H';
            }
            
            
            disp.setCharAt((x + 1)*2 + (this.n - y)*(this.n*2+5), chr); 
        }
        
        int Jx = this.joueur.getPersonnage().getPos().getX();
        int Jy = this.joueur.getPersonnage().getPos().getY();
        
        disp.setCharAt((Jx + 1)*2 + (this.n - Jy)*(this.n*2+5), 'J'); 
        
        System.out.println(disp);
        
    }


}
