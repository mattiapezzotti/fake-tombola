package it.unitn.prog2.pezzm.tombola;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class Input {
    public Input(){

    }

    public static int getIntegerInput(String title, int maxInt){
        Alert alert;
        TextInputDialog dialog;
        int x = 0;
        do {
            dialog = new TextInputDialog("" + maxInt);
            dialog.setTitle(title);
            dialog.setHeaderText("Inserisci un numero tra 1 e " + maxInt);
            dialog.setContentText("Inserisci il numero:");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                x = Integer.parseInt(result.get());
                if(x < 1 || x > maxInt) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore");
                    alert.setHeaderText("Errore inserimento dati");
                    alert.setContentText("Inserisci un numero tra 1 e " + maxInt);
                    alert.showAndWait();
                }
            }
        } while(x < 1 || x > maxInt);
        return x;
    }
}
