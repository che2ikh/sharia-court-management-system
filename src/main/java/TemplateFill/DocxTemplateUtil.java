package TemplateFill;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class DocxTemplateUtil {

    public static void fillTemplate(TemplateFillView templateFillView) {

        File templateFile,outputFile;
        List<String> values;

        templateFile = templateFillView.getCurrentDocxFile();
        outputFile = templateFillView.getOutputDocxFile();
        values = templateFillView.getFilledValues();

        templateFillView.getWebView().getEngine().executeScript("alert('"+templateFile+"');");
        try (XWPFDocument doc = new XWPFDocument(new FileInputStream(templateFile))) {
            int valueIndex = 0;

            for (XWPFParagraph p : doc.getParagraphs()) {
                for (XWPFRun run : p.getRuns()) {
                    String text = run.getText(0);
                    if (text != null && text.contains("...")) {
                        if (valueIndex < values.size()) {
                            text = text.replace("...", values.get(valueIndex));
                            valueIndex++;
                            System.out.println("yippie");
                        }
                        run.setText(text, 0);
                    }
                }
            }

            try (FileOutputStream out = new FileOutputStream(outputFile)) {
                doc.write(out);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

