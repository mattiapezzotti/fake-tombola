package it.unitn.prog2.pezzm.tombola.layout;

/** Rappresenta una cartella.
 * @author Mattia Pezzotti
 * @version 0.1
 * @since 0.1
 */

import it.unitn.prog2.pezzm.tombola.Tombola;
import javafx.scene.layout.*;

import java.util.ArrayList;


public class Cartella extends VBox {
    private final ArrayList<Riga> righe;
    private final ArrayList<Integer> numeri;
    private final ArrayList<Integer> numeriEstraibili;
    private int maxNumeroIndovinato;
    private int nNumeriIndovinati;


    public Cartella() {
        this.maxNumeroIndovinato = 0;
        this.numeriEstraibili = new ArrayList<>();
        for(int i = 1; i<= Tombola.NUMERO_MASSIMO; i++)
            numeriEstraibili.add(i);
        this.righe = new ArrayList<>();
        this.numeri = new ArrayList<>();
        this.initNumeri();
        this.getNumeri();
        this.setStyle();
    }

    /** Inizializza la parte logica della Riga, aggiungendo i numeri
     */
    private void initNumeri(){
        for (int i = 0; i < Tombola.RIGHE_PER_CARTELLA; i++) {
                righe.add(new Riga());
                righe.get(i).initRiga(this.numeriEstraibili);
        }
    }

    /** Controlla se un numero è presente in una delle Righe della Cartella, tenendo conto del fatto che una volta fatto un "Checkpoint"
     * (Ambo, Terna, ...) questo rimane
     * @param x numero estratto
     */
    public void checkNumber(int x){
        if(!(maxNumeroIndovinato == Tombola.NUMERI_PER_RIGA * Tombola.RIGHE_PER_CARTELLA)){
            this.nNumeriIndovinati = 0;
            this.maxNumeroIndovinato = 0;
            if (this.numeri.contains(x)) {
                this.numeri.remove((Integer) x);
                for (Riga riga : this.righe) {
                    riga.checkNumero(x);
                    nNumeriIndovinati += riga.getnNumIndovinati();
                    if (riga.getnNumIndovinati() > this.maxNumeroIndovinato)
                        this.maxNumeroIndovinato = riga.getnNumIndovinati();
                }
                if (nNumeriIndovinati == Tombola.NUMERI_PER_RIGA * Tombola.RIGHE_PER_CARTELLA)
                    maxNumeroIndovinato = nNumeriIndovinati;
            }
        }
    }

    /** Aggiunge alla Collection numeri tutti i numeri contenuti in ogni riga della cartella
     */
    private void getNumeri(){
        for(Riga r : righe)
            numeri.addAll(r.getNumeri());
    }

    /** Imposta la parte grafica della Cartella
     */
    private void setStyle(){
        this.setMinSize(Tombola.DIM_CASELLA * Tombola.COLONNE_PER_CARTELLA,Tombola.RIGHE_PER_CARTELLA * Tombola.DIM_CASELLA);
        for(Riga riga : this.righe)
            this.getChildren().add(riga);
    }

    public int getMaxNumeriIndovinati(){
        return maxNumeroIndovinato;
    }
}
