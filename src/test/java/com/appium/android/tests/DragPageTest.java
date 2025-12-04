package com.appium.android.tests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

import static java.time.Duration.ofMillis;

/**
 * Test class for drag and gesture operations
 * Executes 9 gestures between el1 and el2 clicks
 */
public class DragPageTest extends BaseTest {

    private WebElement el1() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"󰇛\")"));
    }

    private WebElement el2() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(8)"));
    }

    private String getGesturesFolderPath() {
        String projectRoot = System.getProperty("user.dir");
        return projectRoot + File.separator + "apps" + File.separator + "Gestures";
    }

    private Dimension getScreenSize() {
        return driver.manage().window().getSize();
    }

    private void executeGestureFromJson(String gestureFilePath) {
        try {
            File gestureFile = new File(gestureFilePath);
            if (!gestureFile.exists()) {
                throw new RuntimeException("Gesture file not found: " + gestureFilePath);
            }

            Gson gson = new Gson();
            JsonObject gestureJson = gson.fromJson(new FileReader(gestureFile), JsonObject.class);
            JsonArray actions = gestureJson.getAsJsonArray("actions");

            if (actions.size() == 0) {
                throw new RuntimeException("No actions found in gesture file");
            }

            Dimension screenSize = getScreenSize();
            JsonObject action = actions.get(0).getAsJsonObject();
            JsonArray ticks = action.getAsJsonArray("ticks");

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence sequence = new Sequence(finger, 0);

            for (int i = 0; i < ticks.size(); i++) {
                JsonObject tick = ticks.get(i).getAsJsonObject();
                String type = tick.get("type").getAsString();

                switch (type) {
                    case "pointerMove":
                        double xPercent = tick.get("x").getAsDouble();
                        double yPercent = tick.get("y").getAsDouble();
                        int duration = tick.has("duration") ? tick.get("duration").getAsInt() : 0;

                        int x = (int) (screenSize.width * xPercent / 100.0);
                        int y = (int) (screenSize.height * yPercent / 100.0);

                        sequence.addAction(finger.createPointerMove(
                            ofMillis(duration),
                            PointerInput.Origin.viewport(), x, y));
                        break;

                    case "pointerDown":
                        int button = tick.has("button") ? tick.get("button").getAsInt() : 0;
                        sequence.addAction(finger.createPointerDown(
                            button == 0 ? PointerInput.MouseButton.LEFT.asArg() : PointerInput.MouseButton.RIGHT.asArg()));
                        break;

                    case "pointerUp":
                        int buttonUp = tick.has("button") ? tick.get("button").getAsInt() : 0;
                        sequence.addAction(finger.createPointerUp(
                            buttonUp == 0 ? PointerInput.MouseButton.LEFT.asArg() : PointerInput.MouseButton.RIGHT.asArg()));
                        break;
                }
            }

            driver.perform(Collections.singletonList(sequence));

        } catch (IOException e) {
            throw new RuntimeException("Failed to read gesture file: " + gestureFilePath, e);
        }
    }

    private void executeFirstGesture() {
        executeGestureFromJson(getGesturesFolderPath() + File.separator + "gesture-1st-Piece.json");
    }

    private void executeSecondGesture() {
        executeGestureFromJson(getGesturesFolderPath() + File.separator + "gesture-2nd-Piece.json");
    }

    private void executeThirdGesture() {
        executeGestureFromJson(getGesturesFolderPath() + File.separator + "gesture-3rd-Piece.json");
    }

    private void executeFourthGesture() {
        executeGestureFromJson(getGesturesFolderPath() + File.separator + "gesture-4th-Piece.json");
    }

    private void executeFifthGesture() {
        executeGestureFromJson(getGesturesFolderPath() + File.separator + "gesture-5th-Piece.json");
    }

    private void executeSixthGesture() {
        executeGestureFromJson(getGesturesFolderPath() + File.separator + "gesture-6th-Piece.json");
    }

    private void executeSeventhGesture() {
        executeGestureFromJson(getGesturesFolderPath() + File.separator + "gesture-7th-Piece.json");
    }

    private void executeEighthGesture() {
        executeGestureFromJson(getGesturesFolderPath() + File.separator + "gesture-8th-Piece.json");
    }

    private void executeNinthGesture() {
        executeGestureFromJson(getGesturesFolderPath() + File.separator + "gesture-9th-Piece.json");
    }

    public void executeAllGestures() {
        executeFirstGesture();
        executeSecondGesture();
        executeThirdGesture();
        executeFourthGesture();
        executeFifthGesture();
        executeSixthGesture();
        executeSeventhGesture();
        executeEighthGesture();
        executeNinthGesture();
    }

    @Test
    public void sampleTest() {
        // Click el1
        el1().click();

        // Execute all 9 gestures in sequence
        System.out.println("Executing 9 gestures...");
        executeAllGestures();
        System.out.println("All gestures executed successfully!");

        // Click el2
        el2().click();
    }
}
