package Jeu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Nathan on 26/03/2017.
 */
public class Hache extends Arme {
    private BufferedImage image=null;
    public Hache(){
        super(1);
        try {
            image= ImageIO.read(new File("Hache.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public BufferedImage getImage() {
        return image;
    }
}
