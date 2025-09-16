package TemplateFill;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com.ibm.icu.text.ArabicShaping;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class DocxToHtmlViewerFX extends Application {

    private WebView webView;


    @Override
    public void start(Stage stage) throws IOException {
        /*stage.setTitle("DOCX to HTML Viewer");

        webView = new WebView();
        WebViewManager.PrepareWebView(webView);


        Button openButton = new Button("Open DOCX File");
        openButton.setOnAction(e -> openDocxFile(stage));

        BorderPane root = new BorderPane();
        root.setTop(openButton);
        root.setCenter(webView);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

        String sample=new String(
                getClass().getResourceAsStream("/test.html").readAllBytes(),
                StandardCharsets.UTF_8
        );
        webView.getEngine().loadContent(ArabicShapeClass.shapeArabicInHtml(sample));
*/
    }

 /*  private void openDocxFile(Stage stage) {
        String finalHtml="";
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("DOCX files", "*.docx"));
            File docxFile = fileChooser.showOpenDialog(stage);

            if (docxFile != null) {
                System.out.println("open a file");
try {
    long startConv = System.currentTimeMillis();

    finalHtml = DocxConverter.convertDocxToHtml(docxFile);

    long endConv = System.currentTimeMillis();
    System.out.println("DOCX conversion took " + (endConv - startConv) + " ms");
}catch (IOException io){
    io.printStackTrace();
            }
                String addedButtonEdit=new String(
                        getClass().getResourceAsStream("/buttons.html").readAllBytes(),
                        StandardCharsets.UTF_8
                );

                String jsUrl = getClass().getResource("/functions.js").toExternalForm();

                String addedFunctionalities = "<script src=\"" + jsUrl + "\"></script>";

                finalHtml = finalHtml.replaceFirst("(?i)<body>", "<body>" + addedButtonEdit + addedFunctionalities);


                WebViewManager.setStart(System.currentTimeMillis());
                webView.getEngine().loadContent(finalHtml);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/


}