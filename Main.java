package View;

import Jeu.Game;

import java.io.IOException;

/**
 * Created by Nathan on 24/03/2017.
 */
public class Main {
    public static void main(String[] args)  {
        Game game = new Game();
        Fenetre fenetre1= null;
        try {
            fenetre1 = new Fenetre(game,1400,800);
        } catch (IOException e) {
            e.printStackTrace();
        }
        game.setFenetre(fenetre1);



    }}