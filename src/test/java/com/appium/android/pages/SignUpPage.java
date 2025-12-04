package com.appium.android.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.appium.android.utils.AppiumDriverManager;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;

/**
 * Page Object Model for Sign Up Page
 * Contains sign up form elements and actions
 */
public class SignUpPage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    public SignUpPage() {
        this.driver = AppiumDriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private WebElement signUpLink() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Sign up\")")));
    }

    private WebElement emailField() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.accessibilityId("input-email")));
    }

    private WebElement passwordField() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.accessibilityId("input-password")));
    }

    private WebElement confirmPasswordField() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.accessibilityId("input-repeat-password")));
    }

    private WebElement signUpBtn() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(16)")));
    }

    private WebElement okBtn() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            AppiumBy.id("android:id/button1")));
    }


    private WebElement loginEmailField() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.accessibilityId("input-email")));
    }

    private WebElement loginPasswordField() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.accessibilityId("input-password")));
    }

    private WebElement loginSubmitBtn() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(15)")));
    }

    public String getSuccessMessageTitle() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.id("android:id/alertTitle"))).getText();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            AppiumBy.id("android:id/message"))).getText();
    }

    private void openSignUpForm() {
        signUpLink().click();
    }

    public void signUp(String email, String password) {
        HomePage homePage = new HomePage();
        homePage.openMenu("Login");
        openSignUpForm();
        emailField().clear();
        emailField().sendKeys(email);
        passwordField().clear();
        passwordField().sendKeys(password);
        confirmPasswordField().clear();
        confirmPasswordField().sendKeys(password);
        signUpBtn().click();
    }

    public void login(String email, String password) {
        HomePage homePage = new HomePage();
        homePage.openMenu("Login");
        loginEmailField().clear();
        loginEmailField().sendKeys(email);
        loginPasswordField().clear();
        loginPasswordField().sendKeys(password);
        loginSubmitBtn().click();
    }

    public void closeSuccessMessage() {
        okBtn().click();
    }
}

