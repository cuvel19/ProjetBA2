package View;


import Jeu.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Map extends JPanel implements KeyListener,Runnable{
    private Fenetre fenetre;
    private Game game;
    private BufferedImage terre= null;
    private BufferedImage vie=null;
    private int moveX=0;
    private int moveY=0;
    private int hauteurh=0;
    private int longueurh=0;
    private boolean message=false;
    private int k=1;
    private ArrayList<Monstre> monstreliste;
    

    public Map(Fenetre fenetre,Game game,int hauteurh,int longueurh){
        this.game=game;
        this.hauteurh=hauteurh;
        this.longueurh=longueurh;
        addKeyListener(this);
        this.fenetre=fenetre;
        this.setFocusable(true);

        this.requestFocusInWindow();
        try {
            terre = ImageIO.read(new File("DalleTerre.jpg"));
            vie=ImageIO.read(new File("coeur.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.monstreliste=game.getMonstreliste();
    }



    public void paint(Graphics g) {
        int longueur=this.getWidth();
        int hauteur=this.getHeight();
        Personnage heros = game.getHeros();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,longueur,hauteur);
        
        int decalX=heros.getPosX();
        int decalY=heros.getPosY();
        for(int i = 1; i<longueurh; i++){							// Vire la valeur 20 et parametrer ca
            for(int j = 1; j<hauteurh; j++){
                int x = i;
                int y = j;
                g.drawImage(terre,x*50-decalX*50+(longueur/2),y*50-decalY*50+(hauteur/2),null);
            }
        }


        for(GameObject object : this.game.getGameObjects()){
            int x = object.getPosX();
            int y = object.getPosY();
            g.drawImage(object.getImage(), x * 50-decalX*50+(longueur/2), y * 50-decalY*50+(hauteur/2), null);

        }
        paintInventaire(g);

        int nombreDeVie=heros.getVie();
        for (int p =1;p<=nombreDeVie;p++){
            g.drawImage(vie,20+20*p,20,this);
        }


    }

    public void redraw(){
        this.repaint();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        switch (key){

            case KeyEvent.VK_RIGHT:  //KeyEvent.VK_ RIGHT sera l'entier renvoyé
                game.movePlayer(1, 0, 1);
                System.out.println("IL VA A DROITE");
                this.moveX=1;this.moveY=0;
                break;
            case KeyEvent.VK_LEFT:
                game.movePlayer(-1, 0, 2);
                System.out.println("IL VA A GAUCHE");
                this.moveX=-1;this.moveY=0;
                break;
            case KeyEvent.VK_DOWN:
                game.movePlayer(0, 1, 0);
                System.out.println("IL VA A BAS");
                this.moveY=1;
                this.moveX=0;
                break;
            case KeyEvent.VK_UP:
                game.movePlayer(0, -1, 3);
                System.out.println("IL VA A HAUT");
                this.moveY=-1;
                this.moveX=0;
                break;
            case KeyEvent.VK_SPACE:
                game.attaque(moveX,moveY);
                System.out.println(moveX + " "+ moveY);
                break;
            case KeyEvent.VK_1:
                game.changementArme(1);
                if(this.message==false){System.out.println("JE PRENDS L'ARME 1");}
                break;
            case KeyEvent.VK_2:
                this.message=game.changementArme(2);
                if(this.message==false){System.out.println("JE PRENDS L'ARME 2");}
                break;
            case KeyEvent.VK_3:
                this.message=game.changementArme(3);
                if(this.message==false){System.out.println("Je prends L'arme 3");}
                break;
            /*case KeyEvent.VK_B:
                game.dropBomb("bomb", player1);*/
        }
        this.redraw();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public Graphics paintInventaire(Graphics g){
        Image imgInventaire= null;
        Image imgBaton=null;
        try {
            imgInventaire = ImageIO.read(new File("Inventaire.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(imgInventaire,100,this.getHeight()-200,300,100,this);
        GameObject obj = game.getHeros();
        Heros heros=(Heros) obj;
        int number=-1;
        for(Item object : heros.getInventaire()) {
            number++;
            g.drawImage(object.getImage(), 100+number*50, this.getHeight()-200+30, this);


        }

            return g;

    }

    
    public int posXHeros() {
    	Personnage heros = game.getHeros();
    	return(heros.getPosX());
    }
    
    public int posYHeros() {
    	Personnage heros = game.getHeros();
    	return(heros.getPosY());
    }






    @Override
    public void run() {
        while (true){
            game.updateMonstre();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.redraw();
        }

    }

    /*public Graphics paintmessage(Graphics g){
    Image imgmessage=null;
        try{imgmessage=ImageIO.read(new File("message.jpg"));
    } catch (IOException e) {
        e.printStackTrace();
    }
        g.drawImage(imgmessage,1000,1000,this);
    return g;}*/

}

