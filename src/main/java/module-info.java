module zex.com {
    requires javafx.controls;
    requires javafx.fxml;


    opens zex.com to javafx.fxml;
    exports zex.com;
}