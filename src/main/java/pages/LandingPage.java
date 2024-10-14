package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

public class LandingPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators for the form fields
    private static final By REQUEST_DEMO_BUTTON = By.xpath("//span[text()='Request a demo']");

    // Constructor
    public LandingPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Methods
    public void clickRequestDemo() {
        waitUntilClickable(REQUEST_DEMO_BUTTON).click();
    }

    private WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
