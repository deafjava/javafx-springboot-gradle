package com.luxoft.javafx.utils;

import com.luxoft.javafx.assembler.ResistanceInputElement;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import java.util.Locale;
import java.util.Set;

@UtilityClass
public class ReadAndCalculateUtils {
    public void asParallel(VBox refBox, Label resultRef) {
        Set<Node> rs = refBox.lookupAll(ResistanceInputElement.RESIS_SELECTOR);
        double result = 0.0;
        for (Node t : rs) {
            String value = ((TextField) t).getText();

            if (StringUtils.isEmpty(value)) {
                result = 0.0;
                break;
            }

            double r = Double.parseDouble(value);

            if (result == 0) {
                result = r;
                continue;
            }

            result = ResistanceCalculatorUtils.parallel(result, r);
        }
        resultRef.setText(String.format(Locale.ENGLISH, "%.2f", result));
    }
}
