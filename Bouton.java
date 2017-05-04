package View;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Bouton extends JButton{
    private String name;
    private ImageIcon image;
    private Fenetre fenetre1;
    public Bouton(String name, ImageIcon image, Fenetre fenetre1){
        super(name,image);
        this.fenetre1=fenetre1;

    }
    public Bouton(String name, Fenetre fenetre1){
        super(name);
        this.fenetre1=fenetre1;
    }
    public Dimension getPreferredSize(){
        return new Dimension(150,150);

    }

}
