module a8.smerino.javafxtodo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;


    opens a8.smerino.javafxtodo to javafx.fxml;
    exports a8.smerino.javafxtodo;
}