package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PersonSearchBox extends VBox {


    Button searchButton;

    public Button getSearchButton() {
        return searchButton;
    }

    public PersonSearchBox() {
        super(20); // spacing between children

        // Title
        Label title = new Label("بحث عن شخص");
        title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 22));
        title.setTextFill(Color.web("#2c3e50"));

        // Input field
        TextField searchField = new TextField();
        searchField.setPromptText("ادخل اسم الشخص");
        searchField.setStyle("""
            -fx-font-size: 14px;
            -fx-background-radius: 6;
            -fx-border-color: #dcdfe6;
            -fx-border-radius: 6;
            -fx-padding: 6px;
        """);

        // Search button
         searchButton = new Button("بحث");
        searchButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        searchButton.setStyle("""
            -fx-background-color: #2c3e50;
            -fx-text-fill: white;
            -fx-padding: 10px 20px;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """);

        searchButton.setOnMouseEntered(e -> searchButton.setStyle("""
            -fx-background-color: #1abc9c;
            -fx-text-fill: white;
            -fx-padding: 10px 20px;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """));
        searchButton.setOnMouseExited(e -> searchButton.setStyle("""
            -fx-background-color: #2c3e50;
            -fx-text-fill: white;
            -fx-padding: 10px 20px;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """));

        // Result label
        Label resultLabel = new Label();
        resultLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        resultLabel.setTextFill(Color.web("#34495e"));

        // Search action logic (temporary)
        searchButton.setOnAction(e -> {
            String name = searchField.getText().trim();
            if (name.equalsIgnoreCase("John")) {
                resultLabel.setText("تم العثور على: John Doe");
            } else {
                resultLabel.setText("لم يتم العثور على الشخص.");
            }
        });

        // Main container styling
        this.setPadding(new Insets(30));
        this.setAlignment(Pos.CENTER);
        this.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 12;
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.08), 10, 0, 0, 5);
        """);

        this.getChildren().addAll(title, searchField, searchButton, resultLabel);
    }
}
