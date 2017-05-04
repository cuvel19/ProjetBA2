package Jeu;

/**
 * Created by Nathan on 26/03/2017.
 */
public abstract class Arme extends Item {
    private int degats;
    public Arme(int x){
        this.degats=x;

    }
    public int getDommages(){
     return this.degats;
    }
}
