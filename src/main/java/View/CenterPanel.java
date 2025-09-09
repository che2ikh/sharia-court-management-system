package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class CenterPanel extends VBox {

    private final AddPersonPage personForm;
    private final VBox dashboard;
    public CenterPanel() {

        this.setSpacing(0);
        this.setPadding(Insets.EMPTY);
        //this.setSpacing(20);
        this.setAlignment(Pos.TOP_CENTER);
        //this.setPadding(new Insets(40));
        this.getStyleClass().add("center-panel");

        this.dashboard=Dashboard.createDashboard();
        this.personForm =new AddPersonPage();

        System.out.println(this.getPadding());
        System.out.println(this.getSpacing());
    }

    // Expose personForm safely
    public AddPersonPage getPersonForm() {
        return personForm;
    }

    public VBox getDashBoard() {
        return dashboard;
    }


    // Update the content of center panel with any Node
    public void setContent(Node node) {
        this.getChildren().setAll(node);
    }
}
