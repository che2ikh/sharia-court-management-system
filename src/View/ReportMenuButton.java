package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ReportMenuButton extends Button {

    public ReportMenuButton(String title){
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color:#3498db ");
        getStyleClass().add("dashboard-card");
        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("card-title");
        setGraphic(titleLabel);

        setOnMouseEntered(e -> this.setStyle("-fx-background-color: #2980b9;")); // darker on hover
        setOnMouseExited(e -> this.setStyle("-fx-background-color: #3498db;")); // back to original
    }


}
