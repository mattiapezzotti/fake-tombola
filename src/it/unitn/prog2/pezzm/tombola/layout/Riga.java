package it.unitn.prog2.pezzm.tombola.layout;

/** Rappresenta una riga di una cartella
 * @author Mattia Pezzotti
 * @version 0.1
 * @since 0.1
 */

import it.unitn.prog2.pezzm.tombola.Tombola;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Riga extends HBox {
    private int nNumIndovinati;
    private final ArrayList<Integer> numeri;
    private final ArrayList<Casella> caselle;

    public Riga(){
        nNumIndovinati = 0;
        caselle = new ArrayList<>();
        numeri = new ArrayList<>();
    }

    /** Inizializza la parte logica della riga, aggiungendo i numeri
     * @param numeriEstraibili Lista dei numeri disponibili ancora da estrarre
     */
    public void initRiga(ArrayList<Integer> numeriEstraibili){
        Random estrazione = new Random();
        for(int i = 0; i< Tombola.NUMERI_PER_RIGA; i++) {
                int estratto = numeriEstraibili.get(estrazione.nextInt(numeriEstraibili.size()));
                this.numeri.add(estratto);
                numeriEstraibili.remove(numeriEstraibili.indexOf(estratto));
                this.caselle.add(new Casella(this.numeri.get(i) + ""));
        }

        for(int i=0; i<Tombola.COLONNE_PER_CARTELLA - Tombola.NUMERI_PER_RIGA; i++)
            this.caselle.add(new Casella(""));
        setStyle();
    }

    /** Controlla se un numero è contenuto nella riga
     * @param x numero estratto
     */
    public void checkNumero(int x){
        if (this.numeri.contains(x)){
            this.nNumIndovinati++;
            this.numeri.remove((Integer) x);
            for(Casella casella : caselle)
                if(casella.getNumero() == x)
                    casella.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        }
    }

    /** Imposta la parte grafica della Riga
     */
    private void setStyle(){
        ArrayList<Integer> dummy = new ArrayList<>();
        this.setMinSize(Tombola.DIM_CASELLA * Tombola.COLONNE_PER_CARTELLA, Tombola.DIM_CASELLA);
        for(int i=0; i<Tombola.COLONNE_PER_CARTELLA; i++) {
            dummy.add(i);
        }
        Collections.shuffle(dummy);
        for(int i=0; i<Tombola.COLONNE_PER_CARTELLA; i++) {
            this.getChildren().add(caselle.get(dummy.get(i)));
        }
    }

    public int getnNumIndovinati() {
        return nNumIndovinati;
    }

    public ArrayList<Integer> getNumeri() {
        return numeri;
    }




}
