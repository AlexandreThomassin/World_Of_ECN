/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author alex4
 */
public class Point2D {
    
    /*
    Position X and Y
    */
    private int x,y ;
    
    Point2D(){
        x = 0;
        y = 0;
    }
    
    Point2D (int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    Point2D (Point2D pt) {
        this.x = pt.getX();
        this.y = pt.getY();
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void affichage() {
        System.out.println("Les coordonnées sont : [" + this.x + "; " + this.y + "]" );
    }
    
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;    
    }
    
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;              
    }
    
    public double distance(Point2D p){
        int dx = this.x - p.getX();
        int dy = this.y - p.getY();
        double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) );
        
        return dist;
    }
            
}
