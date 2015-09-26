package br.com.larimaia;

import br.com.larimaia.presentation.view.impl.HomePageViewImpl;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Larimaia extends Application {

    @Override
    public void start(final Stage stage) {

        //StackPane root = new StackPane();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        
        URL url = this.getClass().getResource("/styles/Styles.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        final String css = url.toExternalForm();
        
        HomePageViewImpl view = new HomePageViewImpl();
        Scene cena = new Scene(view, 500, 200);
        cena.getStylesheets().add(css);
        
        stage.setScene(cena);
        stage.show();
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
