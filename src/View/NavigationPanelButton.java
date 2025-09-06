package View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;


public class NavigationPanelButton extends Button {

    public NavigationPanelButton(String title){
      super(title);
        this.getStyleClass().add("nav-button");
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(this, Priority.ALWAYS);
    }
}
