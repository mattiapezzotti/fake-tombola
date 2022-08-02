package it.unitn.prog2.pezzm.tombola;

/** Gestisce tutta l'Applicazione.
 * @author Mattia Pezzotti
 * @version 0.1
 * @since 0.1
 */

import it.unitn.prog2.pezzm.tombola.window.Banco;
import it.unitn.prog2.pezzm.tombola.window.Giocatore;

import java.util.ArrayList;

public class Applicazione{
    private Banco banco;
    private ArrayList<Giocatore> giocatori;
    private Boolean check;
    private int nGiocatori;

    public Applicazione(){
        check = false;
        this.initGiocatore();
        this.initBanco();
    }

    /** Inizializza il cosiddetto "Banco" dove verranno estratti i numeri
     */
    private void initBanco() {
        this.banco = new Banco(this.giocatori);
    }

    /** Inizializza il cosiddetto "Giocatore" dove verranno visualizzate le cartelle
     */
    private void initGiocatore(){
        this.nGiocatori = Input.getIntegerInput("Numero di Giocatori", Tombola.MAX_GIOCATORI);
        this.giocatori = new ArrayList<>();
        for(int i=0; i<this.nGiocatori; i++)
            giocatori.add(new Giocatore());
    }

    /**
     * Funzione principale che visualizza le finestre
     */
    public void run(){
        for(int i = 0; i< giocatori.size(); i++)
            giocatori.get(i).showWindow("Giocatore " + (i+1));
        banco.showWindow("Banco");
    }

    /**
     * Funzione che ritorna il booleano check
     * @return un booleano che conferma se ha senso aprire o meno una finestra
     */
    public Boolean getCheck() {
        for(Giocatore g : giocatori){
            if(g.getnCartelle() > 0)
                return true;
        }
        return false;
    }
}
