/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;
import java.lang.reflect.Field;

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
    
    private String attrChanged;

    public Nourriture(Integer duree, Integer value, String attrChanged) {
        this.duree = duree;
        this.value = value;
        this.attrChanged = attrChanged;
        this.isActive = false;
    }
    
    public Nourriture(Nourriture n){
        this.duree = n.duree;
        this.value = n.value;
        this.attrChanged = n.attrChanged;
        this.isActive = n.isActive;
    }
    
    public void utilise(Creature c){
        if (!isActive){
            isActive = true;
            
            try {
                Field attribut = c.getClass().getDeclaredField("degAtt");
                attribut.setAccessible(true);
                try {
                    
                    attribut.setInt(c, attribut.getInt(this) + value);
                    
                } catch (IllegalAccessException e) {
                    System.out.println("L'attribut " + attrChanged + " n'est pas accessible !");
                }
                
            } catch (NoSuchFieldException e){
                System.out.println("L'attribut " + attrChanged + " n'existe pas !");
            }
            
            
        } else {
            // On décréemente la durée à chaque utilisation
            duree = duree - 1;
            
            // Si la durée arrive à 0 on inverse les effets de la nourriture pour revenir à la normale
            if (duree == 0){
                try {
                Field attribut = c.getClass().getDeclaredField("degAtt");
                attribut.setAccessible(true);
                try {
                    
                    attribut.setInt(c, attribut.getInt(this) - value);
                    
                } catch (IllegalAccessException e) {
                    System.out.println("L'attribut " + attrChanged + " n'est pas accessible !");
                }
                
            } catch (NoSuchFieldException e){
                System.out.println("L'attribut " + attrChanged + " n'existe pas !");
            }
            }
        }
    }
    
}
