module a4.smerino.weather {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;


    opens a4.smerino.weather to javafx.fxml;
    exports a4.smerino.weather;
}