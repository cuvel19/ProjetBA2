package Jeu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TrouNoire extends GameObject{
	private GameObject heros;
    private BufferedImage image;

	
	public TrouNoire(int posX,int posY, GameObject heros){
		super(posX,posY,"Trou Noire");
		this.heros=heros;
		try {
            this.image=ImageIO.read(new File("TrouNoire.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	 @Override
	    public BufferedImage getImage() {
	        return this.image;
	    }

	    @Override
	    public void setImage(int nombre) {

	    }
	    
	    public boolean isObstacle(){return false;};
		public boolean isDestroyable(){return false;}


}
