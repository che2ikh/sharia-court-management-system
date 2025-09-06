package View;

import Control.CenterPanelController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class TopPanel extends HBox {

    private final Button settingsBtn;

    public TopPanel(CenterPanelController centerPanelController) {
        this.setPadding(new Insets(0, 20, 0, 20));
        this.setAlignment(Pos.CENTER);
        this.setPrefHeight(70);
        this.getStyleClass().add("top-panel");

        Label title = new Label("محكمة سير الشرعية");
        title.getStyleClass().add("title-label");

        settingsBtn = new Button("الإعدادات");
        settingsBtn.getStyleClass().add("settings-btn");
        settingsBtn.setOnAction(e-> centerPanelController.showDashboard());

        HBox leftSection = new HBox(settingsBtn);
        leftSection.setAlignment(Pos.CENTER_LEFT);

        HBox centerSection = new HBox(); // Empty spacer
        centerSection.setAlignment(Pos.CENTER);
        HBox.setHgrow(centerSection, Priority.ALWAYS);

        HBox rightSection = new HBox(title);
        rightSection.setAlignment(Pos.CENTER_RIGHT);

        this.getChildren().addAll(leftSection, centerSection, rightSection);
    }

    public Button getSettingsBtn() {
        return settingsBtn;
    }
}
