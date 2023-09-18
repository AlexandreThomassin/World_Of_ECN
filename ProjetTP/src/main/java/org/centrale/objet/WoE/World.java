/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author alex4
 */
public class World {
    public Archer robin;
    public Paysan peon;
    public Lapin bugs;

    public World() {
        this.bugs = new Lapin();
        this.peon = new Paysan();
        this.robin = new Archer();
    }
    
    public void creerMondeAlea() {
        Random genX = new Random();
        Random genY = new Random();
        
        ArrayList<Point2D> positions = new ArrayList<Point2D>();
        
        // On crée la position de robin      
        int x = genX.nextInt(2);
        int y = genY.nextInt(2);
        
        Point2D pos = new Point2D(x,y);
        positions.add(pos);
        robin.setPos(pos);
        
        
        
        // On crée la position de Peon

        while (positions.contains(pos)) {
            x = genX.nextInt(2);
            y = genY.nextInt(2);

            pos = new Point2D(x,y);
        }
        
        positions.add(pos);
        peon.setPos(pos);
        
        // On crée la position de Peon
        
        while (positions.contains(pos)) {
            x = genX.nextInt(2);
            y = genY.nextInt(2);

            pos = new Point2D(x,y);
        }
        
        positions.add(pos);
        bugs.setPos(pos);
        
        
        
        System.out.println(positions);
    }
    
}
