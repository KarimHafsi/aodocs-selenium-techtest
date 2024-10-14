package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

public class DemoRequestPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators for the form fields
    private static final By FORM_IFRAME = By.id("hs-form-iframe-0");
    private static final By FIRST_NAME_FIELD = By.xpath("//*[@id='firstname-99eb06c6-add3-4424-9e29-7daf7b7897aa']");
    private static final By LAST_NAME_FIELD = By.name("lastname");
    private static final By EMAIL_FIELD = By.name("email");
    private static final By COMPANY_SIZE_DROPDOWN = By.name("company_size__c");
    private static final By LAST_NAME_ERROR_MESSAGE = By
            .xpath("//input[@name='lastname']//ancestor::div[1]//following-sibling::ul[@role='alert']");
    private static final By EMAIL_ERROR_MESSAGE = By
            .xpath("//input[@name='email']//ancestor::div[1]//following-sibling::ul[@role='alert']");

    // Constructor
    public DemoRequestPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Methods

    private void switchToIframe() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FORM_IFRAME));
    }

    public void fillDemoRequestForm(String firstName, String email, String option) {
        switchToIframe();
        waitUntilVisible(FIRST_NAME_FIELD).sendKeys(firstName);
        waitUntilVisible(LAST_NAME_FIELD).click();
        waitUntilVisible(LAST_NAME_FIELD).clear();
        waitUntilVisible(EMAIL_FIELD).sendKeys(email);
        selectCompanySize(option);
        driver.switchTo().defaultContent();
    }

    public void selectCompanySize(String option) {
        WebElement companySizeDropdown = waitUntilClickable(COMPANY_SIZE_DROPDOWN);
        Select dropdown = new Select(companySizeDropdown);
        dropdown.selectByVisibleText(option);
    }

    public boolean isErrorMessageDisplayed(By errorMessageLocator) {
        switchToIframe();
        boolean isDisplayed = waitUntilVisible(errorMessageLocator).isDisplayed();
        driver.switchTo().defaultContent();
        return isDisplayed;
    }

    public boolean isErrorMessageLastNameDisplayed() {
        return isErrorMessageDisplayed(LAST_NAME_ERROR_MESSAGE);
    }

    public boolean isErrorMessageEmailDisplayed() {
        return isErrorMessageDisplayed(EMAIL_ERROR_MESSAGE);
    }

    // Helper Methods
    private WebElement waitUntilVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
