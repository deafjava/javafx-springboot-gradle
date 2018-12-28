package com.luxoft.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static com.luxoft.javafx.assembler.ResistanceInputElement.RESIS_SELECTOR;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MainTest extends TestFXBase {

    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        loader.setControllerFactory(context::getBean);
        Parent rootNode = loader.load();

        stage.setScene(new Scene(rootNode, 800, 600));
        stage.centerOnScreen();
        stage.show();
    }

    @Test
    public void simpleParallelCalculationTest() {

        VBox vBox = find("#refBox", VBox.class);

        Set<Node> txs = vBox.lookupAll(RESIS_SELECTOR);

        txs.forEach(n -> {
            clickOn(n, MouseButton.PRIMARY);
            write("12");
        });

        Label l = find("#resultResistance", Label.class);

        String result = l.getText();

        assertEquals("6.00", result);
    }

    @Test
    public void addAndRemoveResistorFieldTest() {

        VBox vBox = find("#refBox", VBox.class);

        Button addR = find("#addR", Button.class);

        Button removeR = find("#removeR", Button.class);

        clickOn(addR, MouseButton.PRIMARY);

        Set<Node> txs = vBox.lookupAll(RESIS_SELECTOR);

        txs.forEach(n -> {
            clickOn(n, MouseButton.PRIMARY);
            write("12");
        });

        Label l = find("#resultResistance", Label.class);

        String result = l.getText();

        assertEquals("4.00", result);

        clickOn(removeR, MouseButton.PRIMARY);

        result = l.getText();

        assertEquals("6.00", result);

    }

    @Test
    public void saveResultTest() {

        VBox refBox = find("#refBox", VBox.class);

        Button saveR = find("#saveR", Button.class);

        Set<Node> txs = refBox.lookupAll(RESIS_SELECTOR);

        txs.forEach(n -> {
            clickOn(n, MouseButton.PRIMARY);
            write("12");
        });

        clickOn(saveR, MouseButton.PRIMARY);

        VBox historyCalculation = find("#historyCalculation", VBox.class);

        Set<Node> lbs = historyCalculation.lookupAll(".resultLabel");

        lbs.forEach(lb -> assertEquals("12.00Ω, 12.00Ω, turns to 6.00Ω", ((Label) lb).getText()));
    }
}