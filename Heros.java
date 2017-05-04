package Jeu;

import View.Fenetre;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nathan on 30/03/2017.
 */
public class Heros extends Personnage implements HerosMove{
    private Arme arme=new Baton();
    private ArrayList<BufferedImage> listeImage = new ArrayList<BufferedImage>(4);
    private ArrayList<Item> inventaire=new ArrayList<Item>();
    private BufferedImage image;
    private Fenetre fenetre;
    private ArrayList<HerosObservable> observers= new ArrayList<HerosObservable>();
    private ArrayList<GameObject> objects=new ArrayList<>();
    //private Inventaire inventaire=new Inventaire();
    
 

    public Heros(int posX,int posY)  {
        super(posX, posY, 3,"heros");
        inventaire.add(0,arme); //Ajoute le baton
        try {
            BufferedImage heros_bas=ImageIO.read(new File("Heros_bas.png"));
            BufferedImage heros_droite=ImageIO.read(new File("Heros_droite.png"));
            BufferedImage heros_gauche=ImageIO.read(new File("Heros_gauche.png"));
            BufferedImage heros_haut=ImageIO.read(new File("Heros_haut.png"));
            this.listeImage.add(0, heros_bas);
            this.listeImage.add(1, heros_droite);
            this.listeImage.add(2, heros_gauche);
            this.listeImage.add(3, heros_haut);
            this.image= listeImage.get(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public int attaquer(){
        return arme.getDommages();

    }
    public void setObjects(ArrayList<GameObject> objects){
        this.objects=objects;
    }

    @Override
    public BufferedImage getImage() {return image;

    }
    public void RetourDepart(){
		posX=1;
		posY=1;
	}

    public void setImage(int nombre) {
        image=listeImage.get(nombre);
    }

    public Item getArme(){return arme;}
    
    

    public ArrayList<Item> getInventaire(){return inventaire;}

    public boolean changementArme(int number){
        try{if(inventaire.get(number-1) instanceof Arme){
        this.arme= (Arme) inventaire.get(number-1);
        }}
        catch (Exception e){
            System.out.println("Pas d'arme à cet endroit");
            return true;
        }
        return false;
    }



    public void gagneVie(int nombre){
        this.nombredevie+=nombre;
        System.out.println("il a maintenant " + this.getVie() + " vies");
    }
    public void perdVie(int nombre){
        this.nombredevie-=nombre;
        System.out.println("il a maintenant " + this.getVie() + " vies");
        if(nombredevie<=0){this.fenetre.findepartie();}

    }
    public void setFenetre(Fenetre fenetre){this.fenetre=fenetre;}


    @Override
    public void herosmoveAttach(HerosObservable herosObservable) { observers.add(herosObservable);

    }

    @Override
    public  void  herosmoveNotifyObserver() {
        for (HerosObservable herosObservable:observers){
            herosObservable.herosMove(this);
        }

    }

    @Override
    public void herosmoveDisattach(HerosObservable herosObservable) {
        observers.remove(herosObservable);
    }

    public void move(int X,int Y){
        this.posX = this.posX + X;
        this.posY = this.posY + Y;
        this.herosmoveNotifyObserver();
    }
}