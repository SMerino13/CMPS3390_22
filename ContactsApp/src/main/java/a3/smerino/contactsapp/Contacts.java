package a3.smerino.contactsapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Contacts extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Contacts.class.getResource("list-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ListViewController controller = fxmlLoader.getController();
        stage.setTitle("Contacts");
        stage.setScene(scene);
        stage.setOnHidden(e -> controller.shutdown());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}