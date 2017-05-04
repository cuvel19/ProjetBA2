package Jeu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MonstreHorizontal extends Monstre implements Drawable{
    private BufferedImage image=null;
    private ArrayList<GameObject> objects;
    private int i=1;


    
    public MonstreHorizontal(int posX, int posY,ArrayList<GameObject> objects) {
        super(posX,posY,1,"monstreHorizontal");
        try {
            image= ImageIO.read(new File("Monster_Down.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.objects=objects;

    }

    public void moves(){
        boolean obstacle=false;
        int nextX=posX+i;
        int nextY=posY;
        for (GameObject object : objects) {
            if (object.isAtPosition(nextX, nextY)) {
                obstacle = object.isObstacle();

            }
            if (obstacle) {
                i=i*(-1);
                break;
            }
        }
        for (GameObject object : objects) {
            nextX=posX+i;
             nextY=posY;
            if (object.isAtPosition(nextX, nextY)) {
                obstacle = object.isObstacle();
                if (object instanceof Heros) {
                    Heros heros = (Heros) object;
                    if(this.isAlive()){
                    heros.perdVie(1);}
                }
            }
        }

        this.posX=posX+i;
        this.posY=posY;




    }
    public BufferedImage getImage(){return this.image;}

    @Override
    public void setImage(int nombre) {

    }


    @Override
    public void herosMove(HerosMove herosMove) {
        Heros heros=(Heros) herosMove;
        if(this.getPosX()==heros.getPosX() && this.getPosY()==heros.getPosY()) {

            if (this.isAlive()) {
                heros.perdVie(1);
            }


        }

    }
}
