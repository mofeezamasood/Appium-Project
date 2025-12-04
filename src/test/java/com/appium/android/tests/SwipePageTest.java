package com.appium.android.tests;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import static java.time.Duration.ofMillis;

/**
 * Test class for swipe operations
 * Follows Page Object Model pattern similar to SwipePage example
 */
public class SwipePageTest extends BaseTest {

    private WebElement swipeMenuButton() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"󰤼\")"));
    }

    private void performSwipe(Point start, Point end, int durationMillis) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(ofMillis(0),
            PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(ofMillis(durationMillis),
            PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(new Pause(finger, ofMillis(600)));
        driver.perform(Collections.singletonList(swipe));
    }

    private void performFirstSwipe() {
        Point start = new Point(946, 1339);
        Point end = new Point(529, 1352);
        performSwipe(start, end, 1000);
    }

    private void performSecondSwipe() {
        Point start = new Point(946, 1346);
        Point end = new Point(436, 1346);
        performSwipe(start, end, 1000);
    }

    private void performThirdSwipe() {
        Point start = new Point(942, 1352);
        Point end = new Point(510, 1346);
        performSwipe(start, end, 1000);
    }

    private void performFourthSwipe() {
        Point start = new Point(946, 1365);
        Point end = new Point(536, 1365);
        performSwipe(start, end, 1000);
    }

    private void performFifthSwipe() {
        Point start = new Point(936, 1365);
        Point end = new Point(513, 1365);
        performSwipe(start, end, 1000);
    }
    private void performSixthSwipe() {
        Point start = new Point(936, 1365);
        Point end = new Point(513, 1365);
        performSwipe(start, end, 1000);
    }

    public void performAllSwipes() {
        performFirstSwipe();
        performSecondSwipe();
        performThirdSwipe();
        performFourthSwipe();
        performFifthSwipe();
        performSixthSwipe();
    }

    @Test
    public void sampleTest() {
        // Navigate to swipe menu
        swipeMenuButton().click();

        // Perform all swipe operations
        performAllSwipes();
    }
}
