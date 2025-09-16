package TemplateFill;

import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DocxConverter {

    public static String convertDocxToHtml(File docxFile) throws IOException {
        ByteArrayOutputStream out=null;
        try (FileInputStream in = new FileInputStream(docxFile)) {
            XWPFDocument document = new XWPFDocument(in);

             out = new ByteArrayOutputStream();
            XHTMLOptions options = XHTMLOptions.create();
            XHTMLConverter.getInstance().convert(document, out, options);

            String html = out.toString(StandardCharsets.UTF_8);

            // If you have Arabic shaping logic
            return ArabicShapeClass.shapeArabicInHtml(html);
        }finally {

                if (out != null) out.close();

        }
    }
}