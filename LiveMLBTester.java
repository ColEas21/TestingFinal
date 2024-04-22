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
public class LiveMLBTester {
    private WebDriver driver;

    @BeforeClass
    public void initializeDriver() {

        driver = ESPNLogin.loginAndGetDriver();

    }

    public void handleContinueButton(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("BtnSubmit")));
        continueButton.click();
    }

    @Test
    public void main() {
        try {
            driver.manage().window().maximize();
            driver.get("https://www.espn.com");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement navBar = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("global-nav")));

            WebElement liElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.menu-mlb")));

            WebElement aElement = liElement.findElement(By.cssSelector("a[href=\"/mlb/\"]"));

            WebElement spanElement = aElement.findElement(By.cssSelector("span > span.link-text"));

            Actions actions = new Actions(driver);
            actions.moveToElement(spanElement).perform();

            Thread.sleep(5000);

            WebElement submenuWrapper = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submenu-sportsmenumlb")));

            WebElement teamsDiv = submenuWrapper.findElement(By.xpath(".//li[@class='teams mlb']"));

            String divText = teamsDiv.getText();

            List<String> fileTeams = Files.readAllLines(Paths.get("C:\\Users\\colli\\Downloads\\teams.txt"));

            boolean result1 = checkTeams(fileTeams, divText);

            fileTeams.add("FLORIDA GIANTS");
            boolean result2 = checkTeams(fileTeams, divText);

            //input("Press Enter to Continue...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception happened");
        } finally {
            //driver.quit();
        }
    }

    public boolean checkTeams(List<String> fileTeams, String divText) {
        List<String> missingTeams = fileTeams.stream()
                .filter(team -> !divText.contains(team))
                .toList();
        if (missingTeams.isEmpty()) {
            System.out.println("All teams from the text file are present in the div's text.");
            return true;
        } else {
            System.out.println("The following teams are missing in the div's text: " + missingTeams);
            return false;
        }
    }

    @Test(dependsOnMethods = "main")
    public void navigateToScores() {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"submenu-sportsmenumlb\"]/ul[1]/li[2]/a"));
        element.click();
    }

    @Test(dependsOnMethods = "navigateToScores")
    public void getDayOfGames(){
        WebElement dayContainer = driver.findElement(By.cssSelector("#fittPageContainer > div:nth-child(3) > div > div > div:nth-child(1) > div > section > div > section > div > div > div > div > div > div.Week.currentWeek > div > div:nth-child(7) > div"));
        dayContainer.click();

    }
}




