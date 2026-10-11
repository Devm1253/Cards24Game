package zex.com;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Card24Controller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void handleVerify(ActionEvent actionEvent) {
    }

    public void handleRefresh(ActionEvent actionEvent) {
    }

    public void handleSolve(ActionEvent actionEvent) {
    }
}
