/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.util.ArrayList;

/**
 *
 * @author alex4
 */
public class TestWoE {
    public static void main(String[] args) {
        World monde = new World();
        
        monde.creerMondeAlea();
        
        System.out.println("Robin : " + monde.robin.pos + "\nPeon : " + monde.peon.pos + "\nBugs : " + monde.bugs.pos);
    }
}
