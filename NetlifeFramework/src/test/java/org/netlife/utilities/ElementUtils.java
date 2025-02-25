package org.netlife.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.function.Function;

public class ElementUtils {

	public static WebElement waitForElement(WebDriver driver, Duration timeout, Function<WebDriver, WebElement> elementLocator) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement element = null;

        try {
            element = wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    WebElement element = elementLocator.apply(driver);
                    if (element != null && element.isDisplayed()) {
                        return element;
                    } else {
                        return null;
                    }
                }
            });
        } catch (TimeoutException e) {
            // Handle the case where the element is not found
            System.out.println("Element not found within the timeout period.");
        }

        return element;
    }
}

