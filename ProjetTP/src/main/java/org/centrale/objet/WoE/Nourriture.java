/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *
 * @author alex4
 */
public class Nourriture extends Objet implements Utilisable {
    // Durée en tour de l'objet
    private Integer duree;
    // Valeur de l'effet : <0 si malus, >0 si bonus
    private Integer value;
    
    private boolean isActive;
    
    
    public void utilise(){
        if (!isActive){
            isActive = true;
        }
    }
    
}
