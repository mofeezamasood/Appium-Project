package com.appium.android.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.appium.android.utils.AppiumDriverManager;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;

/**
 * Page Object Model for Form Page
 * Contains all form-related elements and actions
 */
public class FormPage {
    
    private AndroidDriver driver;
    private WebDriverWait wait;
    
    public FormPage() {
        this.driver = AppiumDriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    
    // Element finders (private methods)
    private WebElement inputField() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.accessibilityId("text-input")));
    }
    
    private WebElement switchBtn() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            AppiumBy.accessibilityId("switch")));
    }
    
    private WebElement dropdownField() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            AppiumBy.accessibilityId("Dropdown")));
    }
    
    private WebElement activeBtn() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            AppiumBy.accessibilityId("button-Active")));
    }
    
    private WebElement inActiveBtn() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.accessibilityId("button-Inactive")));
    }
    
    private WebElement okBtn() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            AppiumBy.id("android:id/button1")));
    }
    
    // Public action methods
    public void enterText(String text) {
        inputField().clear();
        inputField().sendKeys(text);
    }
    
    public void toggleSwitch() {
        switchBtn().click();
    }
    
    public void toggleSwitchTwice() {
        switchBtn().click();
        switchBtn().click();
    }
    
    public void openDropdown() {
        dropdownField().click();
    }
    
    public void selectDropdownOption(int optionIndex) {
        driver.findElements(AppiumBy.id("android:id/text1")).get(optionIndex).click();
    }
    
    public void selectDropdownOptionByText(String optionText) {
        driver.findElement(AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"" + optionText + "\")")).click();
    }
    
    public void clickActiveButton() {
        activeBtn().click();
    }
    
    public void clickInactiveButton() {
        inActiveBtn().click();
    }
    
    public void closeAlert() {
        okBtn().click();
    }
    
    // Getter methods for assertions
    public String getSwitchText() {
        return driver.findElement(AppiumBy.accessibilityId("switch-text")).getText();
    }
    
    public String getSelectedDropdownValue() {
        return driver.findElements(AppiumBy.className("android.widget.EditText")).get(1).getText();
    }
    
    public String getActiveMessageTitle() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.id("android:id/alertTitle"))).getText();
    }
    
    public String getActiveMessage() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.id("android:id/message"))).getText();
    }
    
    public String getInputText() {
        return driver.findElement(AppiumBy.accessibilityId("input-text-result")).getText();
    }
    
    public String getInactiveButtonAttribute(String attribute) {
        return inActiveBtn().getAttribute(attribute);
    }
    
    // Combined workflow methods
    public void fillForm(String input, int dropdownOption) {
        enterText(input);
        toggleSwitch();
        openDropdown();
        selectDropdownOption(dropdownOption);
    }
    
    public void fillForm(String input, String dropdownOptionText) {
        enterText(input);
        toggleSwitch();
        openDropdown();
        selectDropdownOptionByText(dropdownOptionText);
    }
    
    public void submitForm() {
        clickActiveButton();
    }
    
    public void handleActiveAlert() {
        clickActiveButton();
        closeAlert();
    }
}

