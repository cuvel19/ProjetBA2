package Jeu;

import View.Fenetre;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Nathan on 30/03/2017.
 */
public class Game {
    private ArrayList<GameObject> objects = new ArrayList<>();
    private ArrayList<Monstre> monstreListe=new ArrayList<>();
    private Fenetre fenetre;


    public Game() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("Niveau1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Creating one Player at position (1,1)
        int j = 0;
        while (sc.hasNextLine()) {
            String scline = sc.nextLine();
            scline = scline.replaceAll(" ", "");
            for (int i = 0; i < scline.length(); i++) {
                char s = scline.charAt(i);
                String p = Character.toString(s);
                if (p.equals("M")) {
                    objects.add(new BlockBeton(i, j));
                }
                if (p.equals("H")) {
                    objects.add(new Heros(i, j));
                }
                if (p.equals("B")) {
                    objects.add(new BlockBois(i, j));
                }
                if (p.equals("C")) {
                    Coffre coffre1=new Coffre(i, j, new Epee());
                    objects.add(coffre1);
                    getHeros().herosmoveAttach(coffre1);

                }
                if (p.equals("J")) {
                    Coffre coffre2=new Coffre(i, j, new Hache());
                    objects.add(coffre2);
                    getHeros().herosmoveAttach(coffre2);
                }
                if (p.equals("V")) {
                    PotionVie potionVie=new PotionVie(i,j);
                    objects.add(potionVie);
                    getHeros().herosmoveAttach(potionVie);

                }
                if (p.equals("I")){
                    Monstre monstreV=new MonstreVertical(i,j,objects);
                    objects.add(monstreV);
                    monstreListe.add(monstreV);
                    getHeros().herosmoveAttach(monstreV);
                }
                if (p.equals("U")){
                    Monstre monstreH=new MonstreHorizontal(i,j,objects);
                    objects.add(monstreH);
                    monstreListe.add(monstreH);
                    getHeros().herosmoveAttach(monstreH);
                }
                if (p.equals("E")){
                    Monstre momie=new Momie(i,j, getHeros(),objects);
                    objects.add(momie);
                    monstreListe.add(momie);
                    }
                if (p.equals("T")){
                    TrouNoire trouNoire =new TrouNoire(i,j, getHeros());
                    objects.add(trouNoire);
                    }
            }

            j++;
            }

        getHeros().setObjects(this.objects);
        }




    public  void movePlayer(int x, int y, int nombre) {
        Heros heros = getHeros();
        heros.setFenetre(this.fenetre);
        //Design de l'image du heros
        heros.setImage(nombre);
        int nextX = heros.getPosX() + x;
        int nextY = heros.getPosY() + y;
        boolean obstacle = false;
        for (GameObject object : objects) {
            if (object.isAtPosition(nextX, nextY)) {
                obstacle = object.isObstacle();
                if (object instanceof Coffre && ((Coffre) object).getStatus()==1){
                    objects.remove(object);
                    break;
                }
                if (object instanceof PotionVie ){
                	objects.remove(object);
                	break;
                }
                if (object instanceof TrouNoire ){
                	heros.RetourDepart();
                	obstacle=true;
                }
            }
            if (obstacle == true) {
                break;
            }
        }

        if (obstacle == false) {
            heros.move(x, y);

        }




    }

    public ArrayList<GameObject> getGameObjects() {
        return this.objects;
    }

    public void attaque(int orientationX,int orientationY) {
        String nameBaton="Baton";
        System.out.println("ATTAQUE");
        Heros heros = getHeros();
        boolean destroyable = false;
        int posX = heros.getPosX();
        int posY = heros.getPosY();
        for (GameObject object : objects) {
            if(orientationX ==0 && orientationY ==0){
                break;}
                if (object.isAtPosition(posX + orientationX, posY+ orientationY)) {
                destroyable = object.isDestroyable();
                }
                if (heros.getArme() instanceof Hache && object instanceof BlockBois && object.isAtPosition(posX + orientationX, posY+ orientationY) ){
                    objects.remove(object);
                    break;
                }
                if (object instanceof Coffre && object.isAtPosition(posX + orientationX, posY+ orientationY)){
                    ((Coffre) object).ouverture();
                }
                if (object instanceof Monstre && object.isAtPosition(posX+orientationX,posY+orientationY)){
                    Monstre monstre=(Monstre) object;
                    monstre.perdVie(heros.attaquer());
                    if (monstre.isAlive()==false){objects.remove(object);}
                    break;
                }


        }
    }

    public Heros getHeros(){
        Heros heros= null;
        for (GameObject o: objects){
            if (o instanceof Heros){
                heros = (Heros)o;
                break;
            }

        }
        return heros;


    }
    public ArrayList<Monstre> getMonstreliste(){return monstreListe;}

    public boolean changementArme(int nombre){
        Heros heros=this.getHeros();
        boolean message=heros.changementArme(nombre);
        return message;

    }
    public void updateMonstre(){
        for (Monstre monstre:monstreListe){
            monstre.moves();
        }
    }




    public void setFenetre(Fenetre fenetre){
        this.fenetre=fenetre;
    }



}
