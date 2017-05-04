package View;
/**
 * Created by Nathan on 23/03/2017.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panneau extends JPanel {
    private int longueur;
    private int hauteur;
    private BufferedImage image;
    public Panneau(int largeur, int hauteur, BufferedImage image){

        this.longueur=largeur;
        this.hauteur=hauteur;
        this.image=image;
    }
    public void paintComponent(Graphics g){
            g.drawImage(image, 0, 0,longueur,hauteur, this);
            //Pour une image de fond
            //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

    }
}