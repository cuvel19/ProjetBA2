package Jeu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nathan on 05/04/2017.
 */
public class Coffre extends GameObject implements HerosObservable{
    private BufferedImage image;
    private Item arme;
    private ArrayList<BufferedImage> listeImage = new ArrayList<BufferedImage>(2);
    private int status=0;

    public Coffre(int x,int y,Item arme){
        super(x,y,"coffre");
        this.arme=arme;
        try {
            BufferedImage coffre=ImageIO.read(new File("Coffre.png"));
            BufferedImage coffre_ouvert=ImageIO.read(new File("CoffreOuvert.png"));
            this.listeImage.add(0, coffre);
            this.listeImage.add(1, coffre_ouvert);
            this.image=coffre;
        } catch (IOException e) {
            e.printStackTrace();
        }
      }
    
    public void ouverture(){
        this.setImage(1);
        status=1; //Status 1 = Juste ouvert mais non collecté l'inventaire
    }

    public Item getItem(){
        status=2;  //Status 2 = Ouvert et est vide
       return arme;

    }
    public int getStatus(){return this.status;}

    @Override
    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void setImage(int nombre) {
        image=listeImage.get(nombre);
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
    public void herosMove(HerosMove herosMove) {
        Heros heros=(Heros) herosMove;
        if (this.getPosX()==heros.getPosX() && this.getPosY()==heros.getPosY()){
            if(this.getStatus()==1){
                System.out.println("MABIIITE");
            heros.getInventaire().add(this.getItem());}

        }
    }
}
