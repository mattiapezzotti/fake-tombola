package it.unitn.prog2.pezzm.tombola.layout;

/** Rappresenta una singola Casella di una Riga di una Cartelle di un Giocatore
 * @author Mattia Pezzotti
 * @version 0.1
 * @since 0.1
 */

import it.unitn.prog2.pezzm.tombola.Tombola;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Casella extends Label {
    private int numero;

    public Casella(String s){
        this.setText(s);
        setStyle(s.equals(""));
    }

    /** Imposta la parte grafica del Banco
     * @param isEmpty TRUE se la casella risulta vuota
     */
    private void setStyle(boolean isEmpty){
        this.setFont(new Font(15));
        this.setMinSize(Tombola.DIM_CASELLA, Tombola.DIM_CASELLA);
        this.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
        if(!isEmpty)
            this.numero = Integer.parseInt(this.getText());
        else
            this.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        this.setStyle("-fx-border-color: black;");
        this.setAlignment(Pos.CENTER);
    }

    public int getNumero() {
        return numero;
    }
}
