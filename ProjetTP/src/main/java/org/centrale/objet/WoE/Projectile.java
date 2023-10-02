package org.centrale.objet.WoE;

public class Projectile {
    private String nom;
    private int nombre;
    public Projectile(){
    }
    public Projectile(String nom, int nombre){
        this.nom=nom;
        this.nombre=nombre;
    }
    public Projectile(Projectile p){
        this.nom=p.nom;
        this.nombre=p.nombre;
    }
    public String getNom() {
        return nom;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

}
