package Jeu;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class PotionVie extends GameObject implements HerosObservable{
	private BufferedImage image;
	
	public PotionVie(int x, int y){
		super (x,y, "vie");
		try {
            image= ImageIO.read(new File("coeur.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	

	
	public void setImage(int nmb){}
	
	public BufferedImage getImage(){return this.image;}
	
	 @Override
	    public boolean isObstacle() {
	        return false;
	    }

	   @Override
	   public boolean isDestroyable() {
	        return false;
	   }

	@Override
	public void herosMove(HerosMove herosMove) {
		Heros heros=(Heros) herosMove;
		if (this.getPosX()==heros.getPosX() && this.getPosY()==heros.getPosY()){
			heros.gagneVie(1);
			heros.herosmoveDisattach(this);


		}

	}
}

