package Jeu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MonstreVertical extends Monstre implements Drawable {
    private BufferedImage image = null;
    private ArrayList<GameObject> objects;
    private int i = 1;

    public MonstreVertical(int posX, int posY, ArrayList<GameObject> objects) {
        super(posX, posY, 1, "monstreVertical");
        try {
            image = ImageIO.read(new File("Monster_Down.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.objects = objects;
    }

    public void moves() {
        boolean obstacle = false;
        int nextX = posX;
        int nextY = posY + i;
        for (GameObject object : objects) {
            if (object.isAtPosition(nextX, nextY)) {
                obstacle = object.isObstacle();
                if (object instanceof Heros) {
                    Heros heros = (Heros) object;
                    heros.perdVie(1);
                }
            }
            if (obstacle == true) {
                i = i * (-1);
                break;
            }


        }
        for (GameObject object : objects) {
            nextX = posX;
            nextY = posY + i;
            if (object.isAtPosition(nextX, nextY)) {
                obstacle = object.isObstacle();
                if (object instanceof Heros) {
                    Heros heros = (Heros) object;
                    heros.perdVie(1);
                }
            }
        }

        this.posY = posY + i;


    }

    public BufferedImage getImage() {
        return this.image;
    }

    @Override
    public void setImage(int nombre) {

    }


    @Override
    public void herosMove(HerosMove herosMove) {
        Heros heros = (Heros) herosMove;
        if (this.getPosX() == heros.getPosX() && this.getPosY() == heros.getPosY()) {
            heros.perdVie(1);

        }
    }
}