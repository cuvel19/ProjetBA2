package Jeu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Nathan on 26/03/2017.
 */
public class Baton extends Arme{
    private BufferedImage imgBaton= null;
    public Baton(){
        super(1);
        try {
            this.imgBaton= ImageIO.read(new File("Baton.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public BufferedImage getImage(){
        return imgBaton;
    }

}
