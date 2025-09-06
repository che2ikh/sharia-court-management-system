package View;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AddPersonPage extends VBox {
    TextField nameField,idField,phoneField,addressField;

    public TextField getNameField() {
        return nameField;
    }

    public TextField getIdField() {
        return idField;
    }

    public TextField getPhoneField() {
        return phoneField;
    }

    public TextField getAddressField() {
        return addressField;
    }

    public ComboBox<String> getGenderBox() {
        return genderBox;
    }

    public DatePicker getBirthDatePicker() {
        return birthDatePicker;
    }

    ComboBox<String> genderBox;
    DatePicker birthDatePicker;

    public  AddPersonPage() {
       super(20);

        this.setPadding(new Insets(30));
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("""
            -fx-background-color: #f5f7fa;
        """);

        Label title = new Label("إضافة شخص جديد");
        title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 26));
        title.setTextFill(Color.web("#2c3e50"));

        GridPane form = new GridPane();
        form.setHgap(15);
        form.setVgap(15);
        form.setAlignment(Pos.CENTER_RIGHT);
        form.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 12;
            -fx-padding: 30px;
            -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.08), 10, 0, 0, 5);
        """);

        // Form fields
        Label nameLabel = new Label("الاسم الكامل:");
         nameField = new TextField();

        Label idLabel = new Label("الرقم الوطني:");
         idField = new TextField();

        Label genderLabel = new Label("الجنس:");
        genderBox = new ComboBox<>();
        genderBox.getItems().addAll("ذكر", "أنثى");

        Label birthLabel = new Label("تاريخ الميلاد:");
       birthDatePicker = new DatePicker();

        Label addressLabel = new Label("العنوان:");
        addressField = new TextField();

        Label phoneLabel = new Label("رقم الهاتف:");
        phoneField = new TextField();

        // Add components to grid (labels on the right for Arabic layout)
        form.add(nameLabel, 0, 0); form.add(nameField, 1, 0);
        form.add(idLabel, 0, 1); form.add(idField, 1, 1);
        form.add(genderLabel, 0, 2); form.add(genderBox, 1, 2);
        form.add(birthLabel, 0, 3); form.add(birthDatePicker, 1, 3);
        form.add(addressLabel, 0, 4); form.add(addressField, 1, 4);
        form.add(phoneLabel, 0, 5); form.add(phoneField, 1, 5);

     form.setAlignment(Pos.CENTER_LEFT);
        // Arabic font
        for (Node node : form.getChildren()) {
            if (node instanceof Label label) {
                label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                label.setTextFill(Color.web("#34495e"));
            } else if (node instanceof TextField || node instanceof ComboBox || node instanceof DatePicker) {
                ((Region) node).setStyle("""
                    -fx-font-size: 14px;
                    -fx-background-radius: 6;
                    -fx-border-color: #dcdfe6;
                    -fx-border-radius: 6;
                    -fx-padding: 6px;
                """);
            }
        }

        // Submit button
        Button submitBtn = new Button("حفظ البيانات");
        submitBtn.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        submitBtn.setStyle("""
            -fx-background-color: #2c3e50;
            -fx-text-fill: white;
            -fx-padding: 12px 30px;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """);

        submitBtn.setOnMouseEntered(e -> submitBtn.setStyle("""
            -fx-background-color: #1abc9c;
            -fx-text-fill: white;
            -fx-padding: 12px 30px;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """));
        submitBtn.setOnMouseExited(e -> submitBtn.setStyle("""
            -fx-background-color: #2c3e50;
            -fx-text-fill: white;
            -fx-padding: 12px 30px;
            -fx-background-radius: 8;
            -fx-cursor: hand;
        """));



        this.getChildren().addAll(title, form, submitBtn);
       this.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
    }
}
