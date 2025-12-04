package com.appium.android.tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.time.Duration.ofMillis;

public class WebViewPageTest extends BaseTest {
    
    @BeforeEach
    public void setUpWebView() throws Exception {
        // Driver is already initialized by BaseTest
        // Wait for app to be ready
        Thread.sleep(2000);
    }
    
    // Helper method for swipe gestures
    private void performSwipe(Point start, Point end, int durationMillis) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        
        swipe.addAction(finger.createPointerMove(ofMillis(0),
            PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(ofMillis(durationMillis),
            PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        
        driver.perform(Arrays.asList(swipe));
    }
    
    // Helper method for tap gestures
    private void performTap(Point point, int holdDuration) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        
        tap.addAction(finger.createPointerMove(ofMillis(0),
            PointerInput.Origin.viewport(), point.getX(), point.getY()));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, ofMillis(holdDuration)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        
        driver.perform(Arrays.asList(tap));
    }
    
    // Method to click the WebView menu button
    private void clickWebViewMenu() {
        WebElement webViewButton = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"󰖟\")"));
        webViewButton.click();
    }
    
    // Method to perform all the vertical swipes from your original code
    private void performVerticalSwipes() {
        // Group 1: Initial vertical swipes
        performSwipe(new Point(817, 1582), new Point(823, 1007), 1000);
        performSwipe(new Point(729, 1724), new Point(746, 797), 1000);
        performSwipe(new Point(678, 1669), new Point(713, 758), 1000);
        performSwipe(new Point(568, 1753), new Point(636, 575), 1000);
        performSwipe(new Point(520, 1943), new Point(552, 465), 1000);
        performSwipe(new Point(442, 1924), new Point(484, 923), 1000);
        
        // Horizontal swipe
        performSwipe(new Point(784, 1494), new Point(339, 1472), 1000);
        
        // Tap action
        performTap(new Point(581, 529), 50);
        
        // Group 2: More vertical swipes
        performSwipe(new Point(565, 1517), new Point(623, 413), 1000);
        performSwipe(new Point(474, 1730), new Point(510, 681), 1000);
        performSwipe(new Point(400, 1807), new Point(445, 613), 1000);
        performSwipe(new Point(384, 1875), new Point(500, 620), 1000);
        performSwipe(new Point(510, 1636), new Point(529, 549), 1000);
        performSwipe(new Point(426, 1946), new Point(484, 742), 1000);
        performSwipe(new Point(487, 1866), new Point(558, 542), 1000);
        performSwipe(new Point(449, 1807), new Point(452, 1194), 1000);
        
        // Another horizontal swipe
        performSwipe(new Point(697, 1256), new Point(252, 1252), 1000);
        
        // Group 3: Final vertical swipes
        performSwipe(new Point(303, 1782), new Point(433, 665), 1000);
        performSwipe(new Point(545, 1675), new Point(555, 810), 1000);
        performSwipe(new Point(497, 1730), new Point(562, 455), 1000);
    }
    
    // Method with percentage-based swipes (more reliable across devices)
    private void performPercentageBasedSwipes() {
        var screenSize = driver.manage().window().getSize();
        int screenWidth = screenSize.getWidth();
        int screenHeight = screenSize.getHeight();
        
        // Vertical swipe from 70% height to 30% height at 50% width
        performSwipe(
            new Point((int)(screenWidth * 0.5), (int)(screenHeight * 0.7)),
            new Point((int)(screenWidth * 0.5), (int)(screenHeight * 0.3)),
            1000
        );
        
        // Horizontal swipe from 80% width to 20% width at 50% height
        performSwipe(
            new Point((int)(screenWidth * 0.8), (int)(screenHeight * 0.5)),
            new Point((int)(screenWidth * 0.2), (int)(screenHeight * 0.5)),
            1000
        );
    }
    
    // Method to check if WebView content is accessible
    private boolean isWebViewAccessible() {
        try {
            // Try to get page source - if it returns something, WebView might be accessible
            String pageSource = driver.getPageSource();
            return pageSource != null && !pageSource.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
    
    // Method to list all available contexts
    private void listContexts() {
        try {
            var contexts = driver.getContextHandles();
            System.out.println("Available contexts:");
            for (String context : contexts) {
                System.out.println("  - " + context);
            }
        } catch (Exception e) {
            System.out.println("Could not get contexts: " + e.getMessage());
        }
    }
    
 // In WebViewPageTest.java, update the scrollUsingAppium method:
private void scrollUsingAppium() {
    try {
        var screenSize = driver.manage().window().getSize();
        
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("percent", 0.75);
        params.put("left", 100);
        params.put("top", 200);
        params.put("width", screenSize.getWidth() - 200);
        params.put("height", screenSize.getHeight() - 400);
        
        driver.executeScript("mobile: scrollGesture", params);
    } catch (Exception e) {
        System.out.println("Scroll gesture failed, using fallback: " + e.getMessage());
        // Fallback to manual swipe
        performPercentageBasedSwipes();
    }
}
    
    @Test
    public void webViewNavigationTest() {
        System.out.println("Starting WebView navigation test...");
        
        // Click WebView menu
        clickWebViewMenu();
        System.out.println("Clicked WebView menu button");
        
        // Wait for content to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Check if WebView is accessible
        boolean isAccessible = isWebViewAccessible();
        System.out.println("WebView accessible: " + isAccessible);
        
        // List available contexts
        listContexts();
        
        // Perform swipes (original hardcoded coordinates)
        System.out.println("Performing swipes...");
        performVerticalSwipes();
        
        System.out.println("Test completed successfully");
    }
    
    @Test
    public void alternativeSwipeTest() {
        System.out.println("Starting alternative swipe test...");
        
        // Click WebView menu
        clickWebViewMenu();
        
        // Wait
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Use percentage-based swipes (more reliable)
        System.out.println("Performing percentage-based swipes...");
        performPercentageBasedSwipes();
        
        // Try Appium's built-in scroll
        System.out.println("Using Appium's scroll gesture...");
        scrollUsingAppium();
        
        System.out.println("Alternative test completed");
    }
    
    @Test
    public void simpleWebViewTest() {
        // Simple test that just navigates and checks basic functionality
        System.out.println("Starting simple WebView test...");
        
        clickWebViewMenu();
        
        // Wait for page to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Get current activity for debugging
        String currentActivity = driver.currentActivity();
        System.out.println("Current activity: " + currentActivity);
        
        // Get page source (first 500 chars)
        String pageSource = driver.getPageSource();
        if (pageSource.length() > 500) {
            pageSource = pageSource.substring(0, 500) + "...";
        }
        System.out.println("Page source preview: " + pageSource);
        
        System.out.println("Simple test completed");
    }
    
    @AfterEach
    public void tearDown() {
        // Don't quit driver here, let TestSuiteRunner handle it
        // We can reset the app if needed
        // driver.resetApp();
    }
}