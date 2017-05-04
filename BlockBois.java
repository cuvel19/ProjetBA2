package Jeu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Nathan on 26/03/2017.
 */
public class BlockBois extends Block{
    private BufferedImage image;
    public BlockBois(int x ,int y) {
        super(x,y,"murBois");
        try {
            this.image= ImageIO.read(new File("MurBois.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void setImage(int nombre) {

    }
    public boolean isDestroyable() {
        return true;
    }
}