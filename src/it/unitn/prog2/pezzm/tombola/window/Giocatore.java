package it.unitn.prog2.pezzm.tombola.window;

/** Rappresenta tutto ciò che serve a un giocatore.
 * @author Mattia Pezzotti
 * @version 0.1
 * @since 0.1
 */

import it.unitn.prog2.pezzm.tombola.Input;
import it.unitn.prog2.pezzm.tombola.Tombola;
import it.unitn.prog2.pezzm.tombola.layout.Cartella;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Giocatore extends Finestre {
    private final GridPane gridPane;
    private final ArrayList<Cartella> cartelle;
    private final ArrayList<Label> labels;
    private int nCartelle;

    public Giocatore(){
        this.gridPane = new GridPane();
        this.labels = new ArrayList<>();
        this.cartelle = new ArrayList<>();
        nCartelle = Input.getIntegerInput("Numero di Cartelle", Tombola.MAX_CARTELLE_PER_GIOCATORE);
        if(nCartelle > 0)
            this.setStyle();
    }

    /** Passa in rassegna ogni cartella del giocatore per cercare una corrispondenza e aggiorna la label
     * @param num Il numero da ricercare
     */
    public void checkNumber(int num){
        for(Cartella x : cartelle) {
            x.checkNumber(num);
        }
        updateLabel();
    }

    /** Ogni volta che viene trovata una corrispondenza tra il numero estratto e una casella del giocatore viene aggiornata una Label
     * descrivendo lo stato della cartella.
     */
    public void updateLabel(){
        for(int i = 0; i<cartelle.size(); i++) {
            Cartella x = cartelle.get(i);
            switch(x.getMaxNumeriIndovinati()){
                case 2: this.labels.get(i).setText("AMBO!");
                    break;
                case 3: this.labels.get(i).setText("TERNA!");
                    break;
                case 4: this.labels.get(i).setText("QUATERNA!");
                    break;
                case 5: this.labels.get(i).setText("CINQUINA!");
                    break;
                case 15: this.labels.get(i).setText("!! TOMBOLA !!");
                    break;
            }
        }
    }

    /** Imposta la parte grafica del Giocatore
     */
    public void setStyle(){
        for(int i=0; i<nCartelle; i++) {
            this.cartelle.add(new Cartella());
            this.labels.add(new Label(""));
        }

        int j = 0;
        for(Cartella x : cartelle){
            this.gridPane.add(x,0,j);
            j+=2;
        }

        j=1;
        for(Label x : labels){
            this.gridPane.add(x,0,j);
            j+=2;
        }
        for(Label x : labels) {
            x.setMinSize(Tombola.COLONNE_PER_CARTELLA * Tombola.DIM_CASELLA,Tombola.DIM_LABEL);
            x.setAlignment(Pos.CENTER);
            x.setFont(new Font(25));
            x.setStyle("-fx-border-color: black;");
        }
        this.gridPane.autosize();
        this.gridPane.setMinSize((Tombola.COLONNE_PER_CARTELLA + 1) * Tombola.DIM_CASELLA,
                (Tombola.COLONNE_PER_CARTELLA * Tombola.DIM_CASELLA) + (Tombola.DIM_CASELLA * Tombola.COLONNE_PER_CARTELLA));
        this.scene = new Scene(gridPane, (Tombola.COLONNE_PER_CARTELLA + 1) * Tombola.DIM_CASELLA,
                (Tombola.RIGHE_PER_CARTELLA * Tombola.DIM_CASELLA) + (Tombola.DIM_CASELLA * Tombola.COLONNE_PER_CARTELLA));
    }

    public int getnCartelle() {
        return nCartelle;
    }
}
