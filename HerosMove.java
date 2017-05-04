package Jeu;

/**
 * Created by jpc on 28-04-17.
 */
public interface HerosMove {
    void herosmoveAttach(HerosObservable herosObservable)  ;
    void herosmoveNotifyObserver();
    void herosmoveDisattach(HerosObservable herosObservable);
}
