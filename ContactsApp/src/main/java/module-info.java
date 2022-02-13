module a3.smerino.contactsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens a3.smerino.contactsapp to javafx.fxml;
    exports a3.smerino.contactsapp;
}