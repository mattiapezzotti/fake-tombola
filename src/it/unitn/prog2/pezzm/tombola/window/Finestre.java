package it.unitn.prog2.pezzm.tombola.window;

/** Classe astratta che definisce le proprietà principali di una finestra
 * @author Mattia Pezzotti
 * @version 0.1
 * @since 0.1
 */

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public abstract class Finestre extends Window {
    protected Stage primaryStage;
    protected Scene scene;

    /** Classe astratta che definisce le proprietà principali di una finestra
     * @param windowName Nome delle finestra
     * */
    public void showWindow(String windowName){
        primaryStage = new Stage();
        primaryStage.setTitle(windowName);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

}
