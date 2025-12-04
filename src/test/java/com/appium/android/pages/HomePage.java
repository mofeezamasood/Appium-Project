package com.appium.android.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.appium.android.utils.AppiumDriverManager;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;

/**
 * Page Object Model for Home Page
 * Contains navigation and menu-related actions
 */
public class HomePage {
    
    private AndroidDriver driver;
    private WebDriverWait wait;
    
    public HomePage() {
        this.driver = AppiumDriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
    public void openMenu(String menuName) {
        // Wait for menu item and click
        WebElement menuItem = wait.until(ExpectedConditions.elementToBeClickable(
            AppiumBy.androidUIAutomator("new UiSelector().text(\"" + menuName + "\")")));
        menuItem.click();
    }
    
    public void clickMenuIcon() {
        // Click the menu icon (hamburger menu)
        WebElement menuIcon = wait.until(ExpectedConditions.elementToBeClickable(
            AppiumBy.androidUIAutomator("new UiSelector().text(\"󰏫\")")));
        menuIcon.click();
    }
}

