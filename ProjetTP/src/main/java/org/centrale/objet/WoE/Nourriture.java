/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;
import java.lang.reflect.Field;
import java.util.Objects;

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

    public Nourriture(Integer duree, Integer value) {
        this.duree = duree;
        this.value = value;
        this.isActive = false;
    }
    
    public Nourriture(Nourriture n){
        this.duree = n.duree;
        this.value = n.value;
        this.isActive = n.isActive;
    }
    
    public void utilisePar(Personnage personnage){
        if (!isActive){
            isActive = true;
                    personnage.setDegAtt(personnage.getDegAtt()+value);
                    System.out.println("Le degré d'Attack de "+personnage.getNom()+" devient égal à" +
                            " "+personnage.getDegAtt());
        } else {
            // On décrémente la durée à chaque utilisation
            duree = duree - 1;
            System.out.println("L'effet est déjà actif, Il reste à votre disposition pour "+duree+" tours");
            // Si la durée arrive à 0 on inverse les effets de la nourriture pour revenir à la normale
            if (duree == 0){
                        personnage.setDegAtt(personnage.getDegAtt()-value);
                        System.out.println("Oups ! Cet effet devient un Malus\nLe degré d'Attack de " +
                                personnage.getNom()+" devient "+personnage.getDegAtt());
                System.out.println("On enlève cet objet de notre collection");
                personnage.getEffets().remove(this);
                System.out.println("List des effets: "+personnage.getEffets().toString());
            }
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Nourriture o = (Nourriture) obj;

        return getPos().equals(o.getPos());
    }
    
    
    public String toSave(){
        return "";
    }
    
}
