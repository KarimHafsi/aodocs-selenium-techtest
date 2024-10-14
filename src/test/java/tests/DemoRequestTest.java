package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.GoogleSearchPage;
import pages.*;
import selenium.driver.*;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoRequestTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private GoogleSearchPage googleSearchPage;
    private LandingPage landingPage;
    private DemoRequestPage demoRequestPage;

    @BeforeEach
    public void setup() {
        driver = WebDriverUtility.getWebDriver(Browser.CHROME);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        googleSearchPage = new GoogleSearchPage(driver);
        landingPage = new LandingPage(driver, wait);
        demoRequestPage = new DemoRequestPage(driver, wait);
    }

    @Test
    public void testAODocsDemoForm() {
        // Step 1: Search AODocs in Google
        driver.get("https://www.google.com");
        googleSearchPage.refuseCookies();
        googleSearchPage.search("AODocs");

        // Step 2: Open the AODocs website from the search results
        googleSearchPage.clickFirstResult();

        // Step 3: Click on "Request a demo"
        landingPage.clickRequestDemo();

        // Step 4: Fill out the form
        demoRequestPage.fillDemoRequestForm("Karim", "jesuisunemail@invalid", "51-350 employees");

        // Step 5: Check for error messages
        assertTrue(demoRequestPage.isErrorMessageLastNameDisplayed(),
                "Error messages for last name field not displayed!");
        assertTrue(demoRequestPage.isErrorMessageEmailDisplayed(),
                "Error messages for email field not displayed!");
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}