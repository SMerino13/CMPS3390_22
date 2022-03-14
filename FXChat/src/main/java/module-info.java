module a6.smerino.fxchat.fxchat {
    requires javafx.controls;
    requires javafx.fxml;


    opens a6.smerino.fxchat.fxchat to javafx.fxml;
    exports a6.smerino.fxchat.fxchat;
}