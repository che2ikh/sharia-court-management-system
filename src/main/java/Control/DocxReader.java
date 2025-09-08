package Control;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class DocxReader {

    public static String readTemplate(File file) {
        StringBuilder sb = new StringBuilder();
        try (XWPFDocument doc = new XWPFDocument(new FileInputStream(file))) {
            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            for (XWPFParagraph p : paragraphs) {
                sb.append(p.getText());
                sb.append("\n"); // preserve line breaks
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

