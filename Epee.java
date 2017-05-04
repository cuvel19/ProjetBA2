package Jeu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Nathan on 26/03/2017.
 */
public class Epee extends Arme{
    private BufferedImage image=null;
    public Epee(){
        super(3);
        try {
            image= ImageIO.read(new File("Epee.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getImage(){return image;}
}
