package TemplateFill;


import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DocxFileChooser {

    public static void openDocxFile(TemplateFillView templateFillView) {
        Stage stage = templateFillView.getStage();
        String finalHtml="";
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("DOCX files", "*.docx"));
           File docxFile = fileChooser.showOpenDialog(stage);
           templateFillView.setCurrentDocxFile(docxFile);
            System.out.println(docxFile);
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


                String jsUrl = DocxFileChooser.class.getResource("/functions.js").toExternalForm();

                String addedFunctionalities = "<script src=\"" + jsUrl + "\"></script>";

                finalHtml = finalHtml.replaceFirst("(?i)<body>", "<body>"  + addedFunctionalities);

                System.out.println(finalHtml);
                WebViewManager.setStart(System.currentTimeMillis());
                templateFillView.getWebView().getEngine().loadContent(finalHtml);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
