package Jeu;

public abstract class GameObject implements Drawable{
    protected int posX;
    protected int posY;
    protected String type;
    
    public GameObject(){} //constructeur par défaut
    

    public GameObject(int X, int Y,String type){
        this.posX = X;
        this.posY = Y;
        this.type = type;

    }

    public int getPosX(){
        return this.posX;
    }

    public int getPosY(){
        return this.posY;
    }

    public String getType(){return this.type;}



    public boolean isAtPosition(int x, int y){
        return this.posX == x && this.posY == y;
    }

    public abstract boolean isObstacle();

    public abstract boolean isDestroyable();



}
