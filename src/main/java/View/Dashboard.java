package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Dashboard {

    public static VBox createDashboard(){
            VBox dashboard = new VBox(20);
            dashboard.setAlignment(Pos.TOP_CENTER);
            dashboard.setPadding(new Insets(40));
            dashboard.getStyleClass().add("center-panel");

            Label welcome = new Label("أهلاً بك في نظام محكمة سير الشرعية");
            welcome.getStyleClass().add("welcome-title");

            Label subtitle = new Label("نظام متكامل لإدارة أعمال المحكمة الشرعية");
            subtitle.getStyleClass().add("subtitle");

            HBox statsCards = new HBox(20);
            statsCards.setAlignment(Pos.CENTER);

            String[] titles = {"الملفات النشطة", "الطلبات الجديدة", "المكتملة", "المؤجلة"};
            String[] values = {"128", "24", "56", "18"};
            String[] colors = {"#3498db", "#e74c3c", "#2ecc71", "#f39c12"};

            for (int i = 0; i < titles.length; i++) {
                VBox card = new VBox(10);
                card.setAlignment(Pos.CENTER);
                card.setPadding(new Insets(20));
                card.setStyle("-fx-background-color: " + colors[i]);
                card.getStyleClass().add("dashboard-card");

                Label value = new Label(values[i]);
                value.getStyleClass().add("card-value");

                Label title = new Label(titles[i]);
                title.getStyleClass().add("card-title");

                card.getChildren().addAll(value, title);
                statsCards.getChildren().add(card);
            }

            Button reportBtn = new Button("عرض تقرير زواج");
            reportBtn.getStyleClass().add("report-btn");


            dashboard.getChildren().addAll(welcome, subtitle, statsCards, reportBtn);

       return dashboard;
    }
}
