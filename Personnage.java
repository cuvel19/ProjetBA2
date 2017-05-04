package Jeu;

/**
 * Created by Nathan on 26/03/2017.
 */
public abstract class Personnage extends GameObject {
    protected int nombredevie;
    

    public Personnage(int posX,int posY,int nombredevie,String type) {
        super(posX,posY,type);
        this.nombredevie = nombredevie;

    }
    public boolean isObstacle(){return false;};

    public void move(int X, int Y){
        this.posX = this.posX + X;
        this.posY = this.posY + Y;
    }
    public boolean isDestroyable(){return true;}
    
    public int getVie(){
    	return this.nombredevie;
    }

    public void perdVie(int nombre){
    	this.nombredevie-=nombre;
    	System.out.println("il a maintenant " + this.getVie() + " vies");
    }
   }



