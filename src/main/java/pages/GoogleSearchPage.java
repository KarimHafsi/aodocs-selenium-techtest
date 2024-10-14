package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchPage {
    private WebDriver driver;

    // Locators
    private By refuseCookies = By.id("W0wltc");
    private By searchBox = By.name("q");
    private By firstResult = By.xpath("//div[@class='g']//a");

    // Constructor
    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods
    public void refuseCookies() {
        driver.findElement(refuseCookies).click();
    }

    public void search(String searchText) {
        driver.findElement(searchBox).sendKeys(searchText);
        driver.findElement(searchBox).submit();
    }

    public void clickFirstResult() {
        driver.findElement(firstResult).click();
    }
}
