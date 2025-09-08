package View;

import Control.CenterPanelController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class NavigationPanel extends VBox {

    public NavigationPanel(CenterPanelController centerPanelController) {
        super(10); // spacing between children
        this.setPadding(new Insets(20));
        this.setPrefWidth(250);
        this.getStyleClass().add("nav-panel");

        Label navHeader = new Label("القائمة الرئيسية");
        navHeader.getStyleClass().add("nav-header");
        this.getChildren().add(navHeader);

        NavigationPanelButton newReportButton = new NavigationPanelButton("تقرير جديد");
        newReportButton.setOnAction(e -> centerPanelController.newReportClicked());
        this.getChildren().add(newReportButton);

        NavigationPanelButton addPerson=new NavigationPanelButton("اضافة شخص");
        addPerson.setOnAction(e ->centerPanelController.showPersonForm());
        this.getChildren().add(addPerson);

        NavigationPanelButton displayPersons = new NavigationPanelButton("عرض الأشخاص");
        displayPersons.setOnAction(e -> centerPanelController.displayPersons());
        this.getChildren().add(displayPersons);

        NavigationPanelButton searchPerson = new NavigationPanelButton("بحث");
        searchPerson.setOnAction(e -> centerPanelController.searchPerson());
        this.getChildren().add(searchPerson);


       /* String[] sections = {"أيتام و فاقدي الأهل", "إذن", "زواج", "رسائل", "مذكرات", "أحكام", "وثائق"};

        for (String section : sections) {
             NavigationPanelButton btn = new NavigationPanelButton(section);
            btn.setOnAction(e -> centerPanelController.showSection(section));
            this.getChildren().add(btn);
        }
*/
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        this.getChildren().add(spacer);
    }
}
