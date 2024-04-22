package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ArticleTesting {
    WebDriver driver;
    @BeforeClass
    public void initializeDriver() {
        driver = ESPNLogin.loginAndGetDriver();
        driver.get("https://www.espn.com/wnba/story/_/id/39945196/wnba-mock-draft-2024-clark-iowa-brink-reese-cardoso-edwards");
    }

    @Test
    public void validateArticle() {
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.espn.com/wnba/story/_/id/39945196/wnba-mock-draft-2024-clark-iowa-brink-reese-cardoso-edwards";
        if (actualUrl.equals(expectedUrl)) {
            System.out.println("Test Passed: The current URL '" + actualUrl + "' matches the expected URL.");

        } else {
            System.out.println("Test Failed: Expected URL '" + expectedUrl + "', but got '" + actualUrl + "'.");

        }
    }

    @Test
    public void checkHeader() {
        String className = "article-header";
        WebElement headerElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
        String headerText = headerElement.getText().toLowerCase();
        if (headerText.contains("sparks")) {
            System.out.println("Test Passed: The header talks about the Sparks.");
            //return true;
        } else {
            System.out.println("Test Failed: The header does not mention the Sparks.");
            //return false;
        }
    }

    // checks if the author works for espn
    @Test
    public void checkAuthor() {
        String xpath = "//div[@class='article-body']//div[@class='author has-bio']";
        WebElement authorElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        String authorText = authorElement.getText();
        String[] parts = authorText.split(",");
        if (parts.length > 1 && parts[1].trim().equals("ESPN.com")) {
            System.out.println("Test Passed: The author works for ESPN.com.");
            //return true;
        } else {
            System.out.println("Test Failed: The author does not work for ESPN.com or the format is unexpected.");
           // return false;
        }
    }

    // checks how many reactions are on a article
    @Test
    public void checkReactionsCount() {
        String className = "reactions-count-button";
        WebElement reactionsButton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
        String reactionsText = reactionsButton.findElement(By.tagName("span")).getText();
        double numberOfReactions;
        if (reactionsText.contains("K")) {
            numberOfReactions = Double.parseDouble(reactionsText.replace("K", "")) * 1000;
        } else {
            numberOfReactions = Double.parseDouble(reactionsText);
        }
        if (numberOfReactions > 1000) {
            System.out.println("Test Passed: Reactions are more than 1K.");

        } else {
            System.out.println("Test Failed: Reactions are not more than 1K.");
        }
    }
    @Test
    public void checkArticleLength() {
        String className = "article-body";
        WebElement articleElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
        String articleText = articleElement.getText();
        int lengthOfArticle = articleText.length();
        if (lengthOfArticle < 20000) {
            System.out.println("Test Passed: The length of the article is " + lengthOfArticle + " characters, which is less than 20,000.");
            //return true;
        } else {
            System.out.println("Test Failed: The length of the article is " + lengthOfArticle + " characters, which exceeds the expected limit of 20,000.");
            //return false;
        }
    }

}

