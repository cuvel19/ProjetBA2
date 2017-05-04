package Jeu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by jpc on 21-04-17.
 */
public class PotionInvul extends GameObject{
    private BufferedImage image=null;
        public PotionInvul(int x, int y){
            super (x,y, "vie");
            try {
                image= ImageIO.read(new File("Invul.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }




    }

    @Override
    public boolean isObstacle() {
        return false;
    }

    @Override
    public boolean isDestroyable() {
        return false;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void setImage(int nombre) {

    }
}
