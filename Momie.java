package Jeu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Momie extends Monstre implements Drawable{
	// Monstre qui suit le joueur une fois rentre dans le champs de vision (ici 4)
	private BufferedImage image=null;
	private Heros heros;
	private boolean suivreActif = false;
	private int priviousposX;
	private int priviousposY;
	private boolean detourX = false;
	private boolean detourY = false;
	private int directionVoulueX;
	private int directionVoulueY;
	private int directionDetour;
	private int j=1;
    private ArrayList<GameObject> objects;
    private int k=1;

		
public Momie(int posX, int posY, Heros heros,ArrayList<GameObject> objects) {
    super(posX,posY,1,"momie");
    this.heros=heros;
    try {
        image= ImageIO.read(new File("Monster_Up.png"));
    } catch (IOException e) {
        e.printStackTrace();}
    this.objects=objects;
    }
    
public void moves(){
    boolean obstacle;
    
    int directionX=heros.getPosX()-posX;
    int directionY=heros.getPosY()-posY;
    int vision =4; //distance du heros pour le suivre
        
    if ((Math.abs(directionX)<=vision)&&(Math.abs(directionY)<=vision)){
    	suivreActif=true;
    }
        
    if (suivreActif){
    	
    	if(detourX){
    		detour(objects,"X",directionDetour);
    	}
    	
    	else if(detourY){
    		detour(objects,"Y",directionDetour);
    	}
    	
    	else{
	    	if (Math.abs(directionX)>Math.abs(directionY)){
	    		directionX=normeDeplacement(directionX);
	    		directionY=0;
	    	}
	    	else{
	    		directionY=normeDeplacement(directionY);
	    		directionX=0;
	    	}
	    	
	    	obstacle=isObstacle(directionX,directionY,objects);
	    	System.out.println(obstacle+"  "+ directionX+"  "+directionY);
	        
	        if (obstacle){
	        	if (directionX==0){
	        		detourX=true;
	        		directionDetour=normeDeplacement(heros.getPosX()-posX);
	        	}
	        	else if(directionY==0){
	        		detourY=true;
	        		directionDetour=normeDeplacement(heros.getPosY()-posY);
	        	}
	        	j=1;
	        	directionVoulueX=directionX;
	        	directionVoulueY=directionY;
	        	//System.out.println("detour activ� avec direction "+directionVoulueX+"  "+directionVoulueY+ "et direction detour "+ directionDetour);
	        }
		     
	        else{
			    seDeplace(directionX,directionY);
			}
			    
	    }
    }

    

        
    }
    	
    public BufferedImage getImage(){return this.image;}

    @Override
    public void setImage(int nombre) {

    }
    public boolean isAlive(){
        return nombredevie>=0;
        }
    private int normeDeplacement(int i)    {
    	if (i>0 ){
        	i=1;
        }
        else if (i<0 ){
        	i=-1;
        	}
        else{
        	i=0;
        	}
    	return i;
    }
    
    private boolean isObstacle(int directionX, int directionY, ArrayList<GameObject> objects){//verifie si obstacle et retire vie au passage
    	boolean obstacle = false;
    	int nextX=posX+ directionX;
    	int nextY=posY+ directionY;
    	for (GameObject object : objects) {
    		if (object.isAtPosition(nextX, nextY)) {
                obstacle = object.isObstacle();
                if (object instanceof Heros){
                    Heros heros=(Heros) object;
                    heros.perdVie(1);
                }
            break;
            }
        }
    	return obstacle;
    }
    
    private void seDeplace(int directionX,int directionY){
    	this.posX=posX+directionX;
        this.posY=posY+directionY;
	    priviousposX=-directionX;
	    priviousposY=-directionY;
    	//System.out.println("je me suis deplace en ("+posX+"   "+posY);
    }
    
    private void detour(ArrayList<GameObject> objects, String direct, int norme){
    	norme= norme==0? 1 : norme;
    	int a=norme*j;
    	int b=0;
    	int directionX= direct=="X"? a:b;
    	int directionY= direct=="Y"? a:b;	
    	
    	//System.out.println("je suis en detour "+direct+" objectif: "+directionVoulueX+ "   "+directionVoulueY);
    	
		if(isObstacle(directionVoulueX,directionVoulueY,objects)){		
	    	boolean obstacle=isObstacle(directionX,directionY,objects);
	    	if(obstacle){
	    		if (j==-1){
	    			detourX=false;
	    			detourY=false;
	    		}
	    		j=j*(-1);
	    		}
	    	else{
	    		seDeplace(directionX,directionY);
	    	}
	    }
		else{
			seDeplace(directionVoulueX,directionVoulueY);
			detourX=false;
			detourY=false;
			System.out.println("je sors du detour");
		}
		
	}

	@Override
	public void herosMove(HerosMove herosMove) {

	}
}

  
