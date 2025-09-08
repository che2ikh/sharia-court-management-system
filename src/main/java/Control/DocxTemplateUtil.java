package Control;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class DocxTemplateUtil {

    public static void fillTemplate(File templateFile, File outputFile, List<String> values) {
        try (XWPFDocument doc = new XWPFDocument(new FileInputStream(templateFile))) {
            int valueIndex = 0;

            for (XWPFParagraph p : doc.getParagraphs()) {
                for (XWPFRun run : p.getRuns()) {
                    String text = run.getText(0);
                    if (text != null && text.contains("...")) {
                        if (valueIndex < values.size()) {
                            text = text.replace("...", values.get(valueIndex));
                            valueIndex++;
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
