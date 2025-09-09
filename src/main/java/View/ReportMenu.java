package View;

import Control.CenterPanelController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReportMenu extends VBox {

    public ReportMenu(CenterPanelController centerPanelController){

     super(20);


       setAlignment(Pos.TOP_CENTER);
     setPadding(new Insets(40));
        getStyleClass().add("center-panel");

        Label welcome = new Label("انشاء تقرير جديد");
        welcome.getStyleClass().add("welcome-title");

        Label subtitle = new Label("اختر نوع التقرير الذي تريد انشاؤه");
        subtitle.getStyleClass().add("subtitle");

        HBox reportsCards = new HBox(20);
        reportsCards.setAlignment(Pos.CENTER);

        ReportMenuButton cardKhula = new ReportMenuButton("خلع");
        cardKhula.setOnAction(e->centerPanelController.openKhula());

        ReportMenuButton cardTalaq = new ReportMenuButton( "طلاق");
        cardTalaq.setOnAction(e->centerPanelController.openTalaq());


        reportsCards.getChildren().addAll(cardKhula,cardTalaq);

        getChildren().addAll(welcome,subtitle,reportsCards);
    }
}
