package Jeu;

/**
 * Created by Nathan on 08/04/2017.
 */
public abstract class Monstre extends Personnage implements HerosObservable{
    public Monstre(int posX, int posY, int nombredevie, String type) {
        super(posX, posY, nombredevie, type);
    }
    public abstract void moves();
    public boolean isAlive(){
        return nombredevie>0;
        };

}
