package TemplateFill;

import netscape.javascript.JSObject;

import java.util.ArrayList;
import java.util.List;

public class JsBridge {
    private static List<String> inputs;
    TemplateFillView templateFillView;
    JsBridge(TemplateFillView templateFillView){
        this.templateFillView = templateFillView;
        inputs = templateFillView.getFilledValues();
    }

    public void handleInputs(Object values) {
        System.out.println("handleInputs called!"); // debug line


        if (values instanceof JSObject jsArray) {
            int length = (int) jsArray.getMember("length");
            for (int i = 0; i < length; i++) {
                Object val = jsArray.getSlot(i);
                if (val != null) {
                    inputs.add(val.toString()); // store in list
                }
            }
        } else {
            System.out.println("warning");
        }
        System.out.println(inputs.toString());
        templateFillView.setFilledValues(new ArrayList<>(inputs));
        DocxTemplateUtil.fillTemplate(templateFillView);

    }

}