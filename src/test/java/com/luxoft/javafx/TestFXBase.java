package com.luxoft.javafx;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import org.junit.After;
import org.junit.BeforeClass;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.concurrent.TimeoutException;

public abstract class TestFXBase extends ApplicationTest {

    @BeforeClass
    public static void setupHeadlessMode() {
        if (Boolean.getBoolean("headless")) {
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("java.awt.headless", "true");
        }
    }

    @After
    public void afterEachTest() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[0]);
        release(new MouseButton[0]);
    }

    @SuppressWarnings("unchecked")
    public <T extends Node> T find(String query, Class<T> clazz) {
        return (T) lookup(query).queryAll().iterator().next();
    }
}
