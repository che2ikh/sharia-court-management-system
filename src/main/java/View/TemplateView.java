package View;

import javafx.application.Application;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Control.DocxReader;
import Control.DocxTemplateUtil;

public class TemplateView extends BorderPane {

    File templateFile;
    String  template;

    private TextFlow textFlow = new TextFlow();
    private List<TextField> placeholders = new ArrayList<>();
    private List<String> placeholderValues = new ArrayList<>();
    private boolean editMode = false;

    private ScrollPane scrollPane;

    public TemplateView (String templateFileString) {
        templateFile=new File(templateFileString);
          template = DocxReader.readTemplate(templateFile);



        // Menu
        HBox menuBox = new HBox(10);
        menuBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        Button enableEdit = new Button("تعديل");
        Button stopEdit = new Button("حفظ");
        Button openTemplatesBtn = new Button("Show Templates");
        Button openOutputsBtn = new Button("Show Outputs");
        menuBox.getChildren().addAll(enableEdit, stopEdit);
        menuBox.getChildren().addAll(openTemplatesBtn, openOutputsBtn);

        menuBox.setStyle(
                "-fx-padding: 10;" +                        // space inside
                        "-fx-background-color: linear-gradient(to bottom, #4e9af1, #1c63d1);" + // gradient
                        "-fx-border-color: #2c4a8c;" +             // border color
                        "-fx-border-width: 0 0 2 0;" +             // bottom border only
                        "-fx-alignment: center-left;"              // align items left
        );
        openTemplatesBtn.setOnAction(e -> {
            try {
                File templateDir = new File("C:\\Users\\Lenovo\\Documents\\Intellij Codes\\try dotx template\\");
                if (templateDir.exists()) {
                    Desktop.getDesktop().open(templateDir);
                } else {
                    System.out.println("Template directory not found!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

// handle opening outputs directory
        openOutputsBtn.setOnAction(e -> {
            try {
                File outputDir = new File("C:\\Users\\Lenovo\\Documents\\Intellij Codes\\try dotx template\\");
                if (outputDir.exists()) {
                    Desktop.getDesktop().open(outputDir);
                } else {
                    outputDir.mkdirs(); // create if not exists
                    Desktop.getDesktop().open(outputDir);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setTop(menuBox);

        // Initial display (static)
        renderTextFlow(false);

        // Handle "Enable Edit"
        enableEdit.setOnAction(e -> {
            editMode = true;
            renderTextFlow(true);
        });

        // Handle "Stop Edit"
        stopEdit.setOnAction(e -> {
            if (editMode) {
                placeholderValues.clear();
                for (TextField tf : placeholders) {
                    placeholderValues.add(tf.getText());
                }
                editMode = false;
                renderTextFlow(false);

                // Generate DOCX
                File template = new File("my_template.docx");
                File output = new File("output.docx");
                DocxTemplateUtil.fillTemplate(template, output, placeholderValues);
                System.out.println("DOCX generated!");
            }
        });

        textFlow.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        // Add textFlow to center
        scrollPane = new ScrollPane(textFlow);
        scrollPane.setFitToWidth(true);  // optional: make content width fit scrollpane width
        scrollPane.setFitToHeight(true); // optional
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPannable(true);
        scrollPane.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        setCenter(scrollPane);


    }

    private void renderTextFlow(boolean editable) {
        textFlow.getChildren().clear();
        placeholders.clear();

        String[] parts = template.split("\\.\\.\\.");

        for (int i = 0; i < parts.length; i++) {
            // Add static text
            Text t = new Text(parts[i]);
            textFlow.getChildren().add(t);

            // Add placeholder or input
            if (i < parts.length - 1) {
                if (editable) {
                    TextField tf = new TextField();
                    tf.setPromptText("اضغط للكتابة");
                    tf.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                    tf.setPrefWidth(100);
                    tf.setStyle("-fx-border-color: gray; -fx-background-color: lightyellow;");

                    // Pre-fill previous values if exist
                    if (i < placeholderValues.size()) {
                        tf.setText(placeholderValues.get(i));
                    }

                    tf.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
                        int currentIndex = placeholders.indexOf(tf);
                        if (e.getCode() == KeyCode.DOWN && currentIndex < placeholders.size() - 1) {
                            TextField next = placeholders.get(currentIndex + 1);
                            next.requestFocus();
                            scrollToNode(scrollPane, next);  // <<< scroll to it
                            e.consume();
                        }
                        if (e.getCode() == KeyCode.UP && currentIndex > 0) {
                            TextField prev = placeholders.get(currentIndex - 1);
                            prev.requestFocus();
                            scrollToNode(scrollPane, prev);  // <<< scroll to it
                            e.consume();
                        }
                    });


                    textFlow.getChildren().add(tf);
                    placeholders.add(tf);
                } else {
                    // Show filled value if exists, else default "..."
                    String value = i < placeholderValues.size() ? placeholderValues.get(i) : "...";
                    Text placeholderText = new Text(value);
                    placeholderText.setStyle("-fx-border-color: gray; -fx-background-color: lightyellow;");
                    textFlow.getChildren().add(placeholderText);
                }
            }
        }

        // Focus first placeholder automatically
        if (!placeholders.isEmpty()) {
            placeholders.get(0).requestFocus();
        }
    }


    private void scrollToNode(ScrollPane scrollPane, javafx.scene.Node node) {
        double contentHeight = scrollPane.getContent().getBoundsInLocal().getHeight();
        double viewportHeight = scrollPane.getViewportBounds().getHeight();

        // Node's position relative to ScrollPane
        double nodeMinY = node.getBoundsInParent().getMinY();
        double nodeMaxY = node.getBoundsInParent().getMaxY();
        // Current viewport top and bottom
        double vTop = scrollPane.getVvalue() * (contentHeight - viewportHeight);
        double vBottom = vTop + viewportHeight;

        // Only scroll if node is outside the visible area
        if (nodeMinY < vTop) {
            // Node is above viewport → scroll up
            scrollPane.setVvalue(nodeMinY / (contentHeight - viewportHeight));
        } else if (nodeMaxY > vBottom) {
            // Node is below viewport → scroll down
            scrollPane.setVvalue((nodeMaxY - viewportHeight) / (contentHeight - viewportHeight));
        }
    }


}
