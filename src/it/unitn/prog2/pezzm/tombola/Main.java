package it.unitn.prog2.pezzm.tombola;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage){
        Applicazione app = new Applicazione();
        if(app.getCheck()) {
            app.run();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
