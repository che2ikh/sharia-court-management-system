package TemplateFill;

import TemplateFill.JsBridge;
import TemplateFill.TemplateFillView;
import javafx.concurrent.Worker;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.controlsfx.control.Notifications;

import java.io.File;

public class WebViewManager {

    private static JsBridge jsBridge;
    private static long start=-1;

    public static void setStart(long start) {
        WebViewManager.start = start;
    }

    public static void PrepareWebView(TemplateFillView templateFillView){

        //enable zooming
        templateFillView.getWebView().setOnScroll(event -> {
            if (event.isControlDown()) {
                double zoom = templateFillView.getWebView().getZoom();
                templateFillView.getWebView().setZoom(Math.max(0.1, zoom + (event.getDeltaY() > 0 ? 0.1 : -0.1)));
                event.consume();
            }
        });
        
        templateFillView.getWebView().getEngine().setOnAlert(event -> {

            Notifications.create()
                    .title("JavaScript Alert")
                    .text(event.getData())
                    .showInformation();
        });
// Expose a Java object named "javaApp"
        templateFillView.getWebView().getEngine().getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                System.out.println("Page loaded, attaching javaApp...");
                JSObject window = (JSObject) templateFillView.getWebView().getEngine().executeScript("window");
                jsBridge=new JsBridge(templateFillView);
                window.setMember("javaApp",jsBridge);

                templateFillView.getWebView().getEngine().executeScript("alert('javaApp attached');");

                if(start!=-1) {
                    long end = System.currentTimeMillis();
                    System.out.println("WebView finished loading in " + (end - start) + " ms");
                }
                templateFillView.getWebView().getEngine().executeScript("alert('Hello from JavaFX');");


                String content = (String)  templateFillView.getWebView().getEngine().executeScript("document.documentElement.outerHTML");
                System.out.println("=== WebView Content ===");
                System.out.println(content);
            }
        });

    }
}

