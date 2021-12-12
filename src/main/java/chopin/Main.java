package chopin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) throws IOException{
        launch(args);

    }
   @Override
    public void start(Stage win) throws IOException{

        Parent root;

            root = FXMLLoader.load(getClass().getClassLoader().getResource("coreUI.fxml"));
            Scene scene = new Scene(root, 1200, 650);
            win.setTitle("Chopin");
            win.setResizable(false);
            win.setScene(scene);
            win.getIcons().add(new Image(getClass().getResourceAsStream("/Image/icon.png")));



            win.show();

    }
}