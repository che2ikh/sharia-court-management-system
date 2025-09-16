package TemplateFill;

import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TemplateFillView extends VBox {
    private Stage stage;
    private WebView webView;
    private File currentDocxFile,outputDocxFile;
    private List<String> filledValues;
    private Button openBtn,editBtn, saveBtn;
    private HBox toolbar,webViewBox;

    public TemplateFillView(Stage stage) throws IOException {

        filledValues = new ArrayList<>();
        this.stage = stage;
        this.prepareButtons(stage);

        // Toolbar at the top
        toolbar = new HBox(20);
        toolbar.getStyleClass().add("toolbar");
        toolbar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        toolbar.getChildren().addAll(editBtn, saveBtn);

        // WebView filling remaining space
        webView = new WebView();
        WebViewManager.PrepareWebView(this);

        // Make WebView grow to fill VBox
        VBox.setVgrow(webView, javafx.scene.layout.Priority.ALWAYS);
        webView.setMinHeight(0); // allow shrinking
        webView.setMinWidth(0); // allow shrinking

        // Center WebView horizontally
        webView.setMaxWidth(Double.MAX_VALUE);
        webView.setPrefWidth(Double.MAX_VALUE);

        getChildren().addAll(toolbar, webView); // no need for extra HBox
        setContent("ok.html");
        setCurrentDocxFile("khula_template");
    }

    public void setContent(String htmlFile) {
        try {
            // Load JS file from resources
            String jsUrl = getClass().getResource("/functions.js").toExternalForm();
            String addedFunctionalities = "<script src=\"" + jsUrl + "\"></script>";

            // Read HTML content
            String htmlContent = new String(
                    getClass().getResourceAsStream("/" + htmlFile).readAllBytes(),
                    StandardCharsets.UTF_8
            );

            // Inject <script> before </body>
            htmlContent = htmlContent.replaceFirst("(?i)</body>", addedFunctionalities + "</body>");

            // Load modified HTML into WebView
            webView.getEngine().loadContent(htmlContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void prepareButtons(Stage stage) {
        //openBtn = new Button("Open Docx File");
      //  openBtn.setOnAction(e->DocxFileChooser.openDocxFile(this));

        editBtn = new Button("تعديل");
        editBtn.setOnAction(e->{webView.getEngine().executeScript("replaceInputs()");});

        saveBtn = new Button("حفظ");
        saveBtn.setOnAction(e->{
            outputDocxFile = new File("out.docx");
            webView.getEngine().executeScript("submitValues()");
            System.out.println("before fill"+ filledValues.toString());
        });



        editBtn.getStyleClass().add("toolbar-button");
        saveBtn.getStyleClass().add("toolbar-button");
    }


    public void setFilledValues(List<String> filledValues) {
        this.filledValues = filledValues;
    }

    public void setCurrentDocxFile(String fileName ){

        InputStream is = getClass().getResourceAsStream("/"+fileName+".docx");
        if (is == null){
            System.out.println("Template not found!");
            return;
        }

        File tempFile = null;
        try {
            tempFile = File.createTempFile(fileName, ".docx");

        tempFile.deleteOnExit(); // optional: delete after program exit

        Files.copy(is, tempFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            this.currentDocxFile=tempFile;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         }
    public void setCurrentDocxFile(File currentDocxFile ){

     this.currentDocxFile=currentDocxFile;
    }


    public Stage getStage() {
        return stage;
    }

    public WebView getWebView(){ return webView;}

    public File getCurrentDocxFile() {
        return currentDocxFile;
    }

    public File getOutputDocxFile() {
        return outputDocxFile;
    }

    public List<String> getFilledValues() {
        return filledValues;
    }
}
