package Jeu;

public abstract class Block extends GameObject {
	public Block(int x, int y,String type){
		super(x,y,type);
	}
	public boolean isObstacle(){return true;};
	public boolean isDestroyable(){return false;}
}
