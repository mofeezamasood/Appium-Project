# How to Paste Your Recorded Code from Appium Inspector

## Quick Guide

### For Form Components:
**File:** `src/test/java/com/appium/tests/FormTest.java`
- Open `FormTest.java`
- Find the `testFormComponents()` method
- Replace the example code with your recorded code from Appium Inspector

### For General App Tests:
**File:** `src/test/java/com/appium/tests/AppTest.java`
- Open `AppTest.java`
- Find the `sampleTest()` method
- Replace the example code with your recorded code

## Step-by-Step Instructions

### 1. Get Your Code from Appium Inspector
- Open Appium Inspector
- Record your test steps (interact with form elements)
- Click "Copy Code" or "Export" → Select "Java"
- Copy the generated Java code

### 2. Convert the Code (if needed)

Appium Inspector might generate code like this:
```java
driver.findElement(By.id("com.example:id/button")).click();
```

**Change it to:**
```java
driver.findElement(AppiumBy.id("com.example:id/button")).click();
```

Or if using WebElement:
```java
WebElement button = driver.findElement(AppiumBy.id("com.example:id/button"));
button.click();
```

### 3. Paste into Test Method

**Example - Form Test:**
```java
@Test
public void testFormComponents() {
    // Paste your code here
    driver.findElement(AppiumBy.id("com.example:id/username")).sendKeys("testuser");
    driver.findElement(AppiumBy.id("com.example:id/password")).sendKeys("password123");
    driver.findElement(AppiumBy.id("com.example:id/loginButton")).click();
}
```

## Common Element Locators

### By ID (Resource ID):
```java
driver.findElement(AppiumBy.id("com.example:id/button")).click();
```

### By Accessibility ID:
```java
driver.findElement(AppiumBy.accessibilityId("Login")).click();
```

### By XPath:
```java
driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='Submit']")).click();
```

### By Class Name:
```java
driver.findElement(AppiumBy.className("android.widget.Button")).click();
```

### By Android UIAutomator:
```java
driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Click\")")).click();
```

## Form Component Examples

### Text Input:
```java
WebElement textField = driver.findElement(AppiumBy.id("com.example:id/username"));
textField.clear();
textField.sendKeys("testuser");
```

### Button Click:
```java
driver.findElement(AppiumBy.id("com.example:id/submit")).click();
```

### Checkbox:
```java
WebElement checkbox = driver.findElement(AppiumBy.id("com.example:id/agree"));
if (!checkbox.isSelected()) {
    checkbox.click();
}
```

### Dropdown/Spinner:
```java
driver.findElement(AppiumBy.id("com.example:id/dropdown")).click();
driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Option 1']")).click();
```

### Radio Button:
```java
driver.findElement(AppiumBy.id("com.example:id/radioOption1")).click();
```

## Tips

1. **Always use `AppiumBy`** instead of `By` for better Appium compatibility
2. **Add waits** if elements take time to load:
   ```java
   WebDriverWait wait = AppiumDriverManager.getWait();
   WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
       AppiumBy.id("com.example:id/button")));
   element.click();
   ```
3. **Use clear()** before sending keys to text fields:
   ```java
   textField.clear();
   textField.sendKeys("new text");
   ```
4. **Add assertions** to verify results:
   ```java
   WebElement result = driver.findElement(AppiumBy.id("com.example:id/result"));
   assertEquals("Success", result.getText());
   ```

## Running Your Tests

After pasting your code:
```bash
mvn test
```

Or run specific test:
```bash
mvn test -Dtest=FormTest
```

