package it.unitn.prog2.pezzm.tombola.window;

/** Rappresenta il banco della Tombola
 * @author Mattia Pezzotti
 * @version 0.1
 * @since 0.1
 */

import it.unitn.prog2.pezzm.tombola.Tombola;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Random;

public class Banco extends Finestre {
    private Label label;
    private final Random estrazione;
    private final ArrayList<Giocatore> giocatori;
    private ArrayList<Integer> numeriDaEstrarre;

    /** Inizializza la parte logica della riga, aggiungendo i numeri
     * @param giocatori ArrayList contente i giocatori appartenenti a questo Banco
     */
    public Banco(ArrayList<Giocatore> giocatori){
        this.initLabel();
        this.initNumeri();
        this.giocatori = giocatori;
        this.estrazione = new Random();
        this.setStyle();
    }

    /** Inizializza l'Array che contiene i numeri estraibili
     */
    private void initNumeri(){
        numeriDaEstrarre = new ArrayList<>();
        for(int i = 0; i<= Tombola.NUMERO_MASSIMO; i++)
            numeriDaEstrarre.add(i);
    }

    /** Inizializza la label dove verrà mostrato il numero estratto
     */
    private void initLabel(){
        label = new Label("");
        this.label.setOnMouseClicked((mouseEvent -> {
            this.estrazione();
        }));
    }

    /** Gestisce l'estrazione di un numero estraibile, comunica ai giocatori che un numero è stato estratto
     *  e modifica la label mostrando il numero estratto, chiude il banco se sono stati estratti tutti i numeri
     */
    public void estrazione(){
        if (!numeriDaEstrarre.isEmpty()){
            int estratto = numeriDaEstrarre.get(estrazione.nextInt(numeriDaEstrarre.size()));
            numeriDaEstrarre.remove(numeriDaEstrarre.indexOf(estratto));
            this.label.setText(estratto + "");
            for(Giocatore x : giocatori) {
                x.checkNumber(estratto);
            }
        }
        else this.fine();
    }

    /** Gestisce il momento in cui tutti i numeri estraibili sono stati estratti e il Banco viene chiuso
     */
    public void fine(){
        this.label.setText("Fine.");
        this.label.setOnMouseClicked((mouseEvent -> {
        }));
    }

    /** Imposta la parte grafica del Banco
     */
    private void setStyle(){
        this.label.setMinSize(150,150);
        this.label.setFont(new Font(40));
        this.label.setAlignment(Pos.CENTER);
        this.label.setStyle("-fx-border-color: black;");
        this.label.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));

        StackPane root = new StackPane();
        root.getChildren().add(label);
        this.scene = new Scene(root, Tombola.DIM_LABEL * 4, Tombola.DIM_LABEL * 4);
    }
}
