package View;

import Jeu.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Fenetre extends JFrame implements MouseListener{
    private Map map;
    private boolean arret=false;
    private int largeurh=0;
    private int longueurh=0;
    private ImageIcon image1=new ImageIcon("Jouer.jpg");
    private Bouton bouton1=new Bouton("Jouer",image1,this);
    private BufferedImage image3 = ImageIO.read(new File("Jeu.jpg"));
    private BufferedImage image5 = ImageIO.read(new File ("Fin.jpg"));
    private int longueur;
    private int hauteur;
    public Fenetre(Game game,int x,int y) throws IOException {
        calcullongueur();
        this.map=new Map(this,game,largeurh,longueurh);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image1=new ImageIcon("Jouer.jpg");
        this.setTitle("Jeu Donjon");
        this.setSize(x,y);
        this.longueur=this.getWidth();
        this.hauteur=this.getHeight();
        Panneau panneau=new Panneau(longueur,hauteur,image3);

        panneau.add(bouton1);
        bouton1.addMouseListener(this);


        panneau.setBackground(Color.RED);
        this.setContentPane(panneau);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
    }
    public void affichagejeu(){
        Panneau panneau3=new Panneau(longueur,hauteur,image3);
        panneau3.add(map);
        map.setPreferredSize(new Dimension(longueur,hauteur));
        this.setContentPane(panneau3);
        this.setVisible(true);
        Thread thread=new Thread(this.map);
        thread.start();//Lance le thread des monstres qui bougent
    }

    public void findepartie(){
        this.arret=true;
        Panneau panneau4=new Panneau(longueur,hauteur,image5);
        System.out.println("FINNNN");
        this.setContentPane(panneau4);
        this.setVisible(true);
        }




    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == bouton1) {this.affichagejeu();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void calcullongueur(){
        Scanner sc = null;
        try {
            sc = new Scanner(new File("Niveau1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String scligne=sc.nextLine();
        scligne=scligne.replaceAll(" ","");
        longueurh=scligne.length();
        sc.reset();
        while (sc.hasNextLine()) {
            String scline = sc.nextLine();
            scline = scline.replaceAll(" ", "");
            this.largeurh++;
        }
        this.largeurh--;
    sc.close();
    }
    public boolean getArret(){return this.arret;}


}
