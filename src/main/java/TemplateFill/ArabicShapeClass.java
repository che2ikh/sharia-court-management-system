package TemplateFill;

import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArabicShapeClass {
  public static ArabicShaping shaper = new ArabicShaping(ArabicShaping.LETTERS_SHAPE);

    public static String shapeArabicInHtml(String html) {
        try {
            Document doc = Jsoup.parse(html);

            // Traverse all text nodes
            doc.traverse((node,depth) -> {
                if (node instanceof TextNode textNode) {
                    String original = textNode.text();
                    try {
                        textNode.text(shapeArabicText(original));
                    } catch (ArabicShapingException e) {
                        e.printStackTrace();
                    }
                }
            });

            // Wrap in UTF-8 + font + RTL
            return """
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body {
                            font-family: 'Arial', 'Tahoma', 'Segoe UI', sans-serif;
                            direction: rtl;
                        }
                        .highlight { color: red; font-weight: bold; }
                    </style>
                </head>
                <body>
                    %s
                </body>
                </html>
                """.formatted(doc.body().html());

        } catch (Exception e) {
            e.printStackTrace();
            return html;
        }
    }

    public static String shapeArabicText(String text) throws ArabicShapingException {
        Pattern arabicPattern = Pattern.compile("[\\u0600-\\u06FF]+");
        Matcher matcher = arabicPattern.matcher(text);
        StringBuffer shaped = new StringBuffer();

        while (matcher.find()) {
            String arabic = matcher.group();
            matcher.appendReplacement(shaped, shaper.shape(arabic));
        }
        matcher.appendTail(shaped);
        return shaped.toString();
    }
}
