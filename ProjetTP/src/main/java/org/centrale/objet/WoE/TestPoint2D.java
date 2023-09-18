/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author alex4
 */
public class TestPoint2D {
    
    public static void main(String[] args){
        Point2D point= new Point2D();
        
        point.affichage();
        
        point.setX(5);
        point.affichage();
        
        point.setPosition(10,5);
        point.affichage();
        
        Point2D point2 = new Point2D(point);
        point2.affichage();
        
        point.translate(2, 3);
        point.affichage();
        
        System.out.println("La distance est : " + point.distance(point2));
        
    }
    
}
