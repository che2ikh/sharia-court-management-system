package View;


import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class TemplateViewMenu extends MenuBar {

    public TemplateViewMenu() {
        // File menu
        Menu fileMenu = new Menu("File");
        MenuItem newFile = new MenuItem("New");
        MenuItem openFile = new MenuItem("Open");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> System.exit(0));
        fileMenu.getItems().addAll(newFile, openFile, exit);

        // Edit menu
        Menu editMenu = new Menu("Edit");
        MenuItem copy = new MenuItem("Copy");
        MenuItem paste = new MenuItem("Paste");
        editMenu.getItems().addAll(copy, paste);

        // Help menu
        Menu helpMenu = new Menu("Help");
        MenuItem about = new MenuItem("About");
        about.setOnAction(e -> System.out.println("About clicked"));
        helpMenu.getItems().add(about);

        // Add menus to bar
        this.getMenus().addAll(fileMenu, editMenu, helpMenu);

        // Optional styling
        this.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #4e9af1, #1c63d1);" +
                        "-fx-text-fill: white;" +
                        "-fx-border-color: #2c4a8c;" +
                        "-fx-border-width: 0 0 2 0;"
        );
    }
}
