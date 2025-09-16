package TemplateFill;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("test");
       TemplateFillView templateFillView= new TemplateFillView(stage);


        Scene scene = new Scene(templateFillView, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}