package org.centrale.objet.WoE;

public class Loup extends Monstre{
    public Loup(String nom, int ptVie, int degAtt, int ptPar, int pageAtt, int pagePar, Point2D pos){
        super(nom, ptVie, degAtt, ptPar, pageAtt, pagePar, pos);
    }
    public Loup(Loup l){
        super(l);
    }
    public Loup(){
        super();
    }
    public Loup(int numeroLoup){
        super();
        setNom("Loup n "+numeroLoup);
        setDegAtt(20);
        setPageAtt(60);
        setPagePar(40);
        setPtPar(5);
    }
    public void combattre(Creature c){
    }

}
