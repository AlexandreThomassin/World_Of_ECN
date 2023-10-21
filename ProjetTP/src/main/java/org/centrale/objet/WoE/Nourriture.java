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
    @Override
    public Integer getDuree() {
        return duree;
    }

    // Durée en tour de l'objet
    private Integer duree;
    // Valeur de l'effet : <0 si malus, >0 si bonus
    private Integer pageAtt;
    
    private boolean isActive;

    public Nourriture(Integer duree, Integer pageAtt) {
        this.duree = duree;
        this.pageAtt = pageAtt;
        this.isActive = false;
    }
    
    public Nourriture(Nourriture n){
        this.duree = n.duree;
        this.pageAtt = n.pageAtt;
        this.isActive = n.isActive;
    }
    
    public void utilisePar(Personnage personnage){
        if (!isActive){
            isActive = true;
                    personnage.setPageAtt(personnage.getPageAtt()+pageAtt);
                    System.out.println("Le pourcentage d'Attack de "+personnage.getNom()+" devient égal à" +
                            " "+personnage.getPageAtt());
            duree = duree - 1;
            System.out.println("L'effet reste à votre disposition pour "+duree+" tours");
        } else {
            if (duree == 0){
                        personnage.setPageAtt(personnage.getPageAtt()-pageAtt);
                        System.out.println("Oups ! Cet effet devient un Malus\nLe pourcentage d'Attack de " +
                                personnage.getNom()+" devient "+personnage.getPageAtt());
                System.out.println("On enlève cet objet de notre collection");
                personnage.getEffets().remove(this);
                System.out.println("List des effets: "+personnage.getEffets().toString());
            }else{
                personnage.setPageAtt(personnage.getPageAtt()+pageAtt);
                System.out.println("Le pourcentage d'Attack de "+personnage.getNom()+" devient égal à" +
                        " "+personnage.getPageAtt());
                duree = duree - 1;
                System.out.println("L'effet reste à votre disposition pour "+duree+" tours");
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
