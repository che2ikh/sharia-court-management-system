package View;

import Control.CenterPanelController;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;



public class StartPage extends Application {
    private  BorderPane root;
    private  TopPanel topPanel;
    private  CenterPanel centerPanel;
    private  NavigationPanel navPanel;
private CenterPanelController centerPanelController;
    @Override
    public void start(Stage stage) {

        root= new BorderPane();

        // Center Panel
        centerPanel = new CenterPanel();
        centerPanelController=new CenterPanelController(stage,centerPanel);
        // Navigation Panel
        navPanel = new NavigationPanel(centerPanelController);
        // Top Panel
        topPanel = new TopPanel(centerPanelController);

        setupLayout();

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        double width = bounds.getWidth() * 0.8;
        double height = bounds.getHeight() * 0.8;

        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.centerOnScreen();
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setMaxWidth(bounds.getWidth());
        stage.setMaxHeight(bounds.getHeight());
        stage.setTitle("نظام إدارة محكمة سير الشرعية");
        stage.setScene(scene);

        stage.show();
        centerPanelController.showDashboard();
    }
    private void setupLayout() {
        root.setTop(topPanel);
        root.setRight(navPanel);
        root.setCenter(centerPanel);
        BorderPane.setMargin(centerPanel, new javafx.geometry.Insets(0, 0, 0, 0));
    }

    public static void main(String[] args) {
       launch();

    }
}
