
package zex.com;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Card24Controller {

    @FXML
    private ImageView card1;
    @FXML
    private ImageView card2;
    @FXML
    private ImageView card3;
    @FXML
    private ImageView card4;
    @FXML
    private TextField textField;

    private final int[] cardValues = new int[4];
    private final Random random = new Random();

    @FXML
    public void initialize() {
        generateCards();
    }

    private void generateCards() {
        ImageView[] images = {card1, card2, card3, card4};
        ArrayList<Integer> numbers = new ArrayList<>();

        // Generate 4 unique random numbers
        while (numbers.size() < 4) {
            int value = random.nextInt(13) + 1;

            if (!numbers.contains(value)) {
                numbers.add(value);
            }
        }

        // Display the cards
        for (int i = 0; i < 4; i++) {
            int value = numbers.get(i);
            cardValues[i] = value;

            String rank;

            switch (value) {
                case 1:
                    rank = "A";
                    break;
                case 11:
                    rank = "J";
                    break;
                case 12:
                    rank = "Q";
                    break;
                case 13:
                    rank = "K";
                    break;
                default:
                    rank = String.valueOf(value);
            }

            String path = "/cards/card" + rank + ".jpg";

            Image image = new Image(
                    getClass().getResource(path).toExternalForm()
            );

            images[i].setImage(image);
        }
    }

    @FXML
    private void handleRefresh() {
        generateCards();
        textField.clear();
    }

    @FXML
    private void handleVerify() {
        String input = textField.getText();

        System.out.println("Input: " + input);

        try {
            // Get the numbers entered by the player
            ArrayList<Integer> enteredNumbers = new ArrayList<>();

            String[] parts = input.split("[+\\-*/()]");

            for (String part : parts) {
                if (!part.trim().isEmpty()) {
                    enteredNumbers.add(Integer.parseInt(part.trim()));
                }
            }

            // Get the displayed card values
            ArrayList<Integer> cards = new ArrayList<>();

            for (int value : cardValues) {
                cards.add(value);
            }

            // Sort both lists to compare the values
            Collections.sort(enteredNumbers);
            Collections.sort(cards);

            System.out.println("Entered numbers: " + enteredNumbers);
            System.out.println("Card values: " + cards);

            // Check that all four cards were used exactly once
            if (!enteredNumbers.equals(cards)) {
                showMessage("Invalid Cards",
                        "You must use each displayed card exactly once!");
                return;
            }

            // Calculate the expression
            System.out.println("Starting calculation...");

            Calculate calculator = new Calculate();
            double result = calculator.evaluate(input);

            System.out.println("Result: " + result);

            // Check whether the answer equals 24
            if (Math.abs(result - 24) < 0.000001) {
                showMessage("Correct!", "You made 24!");
            } else {
                showMessage("Incorrect",
                        "Your expression equals " + result);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Error", e.toString());
        }
    }


    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
