package org.example;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.nio.file.*;
import java.time.*;
import java.util.*;
import org.example.ESPNLogin;
public class MainPageTest {
    private WebDriver driver;
    @BeforeClass
    public void getDriver(){
        driver = ESPNLogin.loginAndGetDriver();
        driver.get("https://www.espn.com");
    }
    @Test
    public void verfiyNavi(){
        String expectedURL = "https://www.espn.com/";
        String currentURL = driver.getCurrentUrl();

        if (currentURL.equals(expectedURL)) {
            System.out.println("Navigation to ESPN main page successful.");
        } else {
            System.out.println("Navigation to ESPN main page failed. Current URL: " + currentURL);
        }

    }
    @Test
    public void getDropdownCata(){
        WebElement dropdownButton = driver.findElement(By.cssSelector("button.button-filter"));

        // Click on the dropdown button
        dropdownButton.click();

        // Wait for the dropdown menu to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdownMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dropdown-menu")));

        // Retrieve all options from the dropdown
        List<WebElement> options = dropdownMenu.findElements(By.tagName("a"));

        // Print each option
        for (WebElement option : options) {
            System.out.println(option.getText());
        }
    }
    //@Test
    public void getHeadline(){
        WebElement titleWrapper = driver.findElement(By.xpath("//h2[contains(@class, 'contentItem__title')]"));

        // Find the title element within the wrapper
        WebElement titleElement = titleWrapper.findElement(By.className("contentItem__title"));

        // Get the text from the title element
        String titleText = titleElement.getText();

        // Find the subhead element within the wrapper
        WebElement subheadElement = titleWrapper.findElement(By.className("contentItem__subhead"));

        // Get the text from the subhead element
        String subheadText = subheadElement.getText();

        // Print the text
        System.out.println("Title: " + titleText);
        System.out.println("Subhead: " + subheadText);
    }

    @Test
    public void ClickHeadline(){
        WebElement titleElement = driver.findElement(By.xpath("//h2[contains(@class, 'contentItem__title')]"));

        // Click on the element
        titleElement.click();
    }

}
