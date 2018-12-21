package com.luxoft.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavafxApplication extends Application {


    private static final String MAIN = "/main.fxml";

    private ConfigurableApplicationContext context;

    private Parent rootNode;

    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(JavafxApplication.class);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));

        FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN));
        loader.setControllerFactory(context::getBean);
        rootNode = loader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();

        Scene scene = new Scene(rootNode, visualBounds.getWidth(), visualBounds.getHeight());

        primaryStage.setScene(scene);

        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        primaryStage.setMaxWidth(700);
        primaryStage.setMaxHeight(500);

        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }
}

