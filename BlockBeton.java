package Jeu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Nathan on 26/03/2017.
 */
public class BlockBeton extends Block {
    private BufferedImage image;
    public BlockBeton(int x ,int y) {
        super(x,y,"mur");
        try {
            this.image=ImageIO.read(new File("MurHorizontal.jpg"));
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

}
