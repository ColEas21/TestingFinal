package org.example;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
public class WatchEspn {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = ESPNLogin.loginAndGetDriver();
        driver.get("https://www.espn.com");
    }

    @Test
    public void clickOnWatchDropdown(){
        WebElement watchElement = driver.findElement(By.xpath("//span[@class='link-text' and text()='Watch']"));


        watchElement.click();
    }

    @Test(dependsOnMethods = "clickOnWatchDropdown")
    public void goToWatchHome(){
        WebElement homeLink = driver.findElement(By.xpath("//a[@href='/watch/' and descendant::span[@class='link-text' and text()='Home']]"));

        // Click on the element
        homeLink.click();
    }

    @Test(dependsOnMethods = "goToWatchHome")
    public  void getVideoTitle(){
        WebElement titleElement = driver.findElement(By.cssSelector("#fittPageContainer > section > div:nth-child(1) > section > div.Carousel__Wrapper.relative.Carousel__Wrapper--canScrollRight > div > div > ul > li.CarouselSlide.relative.pointer.CarouselSlide--active.CarouselSlide--inView > div > div > a > div.WatchTile__Content > div > div:nth-child(1) > h2"));

        // Gets the text content of the element
        String title = titleElement.getText().trim();

        System.out.println("Title: " + title);

    }

    @Test(dependsOnMethods = "getVideoTitle")
    public void clickOnVideo(){
        //driver.get("https://www.espn.com/watch/");
        WebElement videoElement = driver.findElement(By.cssSelector("#fittPageContainer > section > div:nth-child(1) > section > div.Carousel__Wrapper.relative.Carousel__Wrapper--canScrollRight > div > div > ul > li.CarouselSlide.relative.pointer.CarouselSlide--active.CarouselSlide--inView > div > div > a"));

        videoElement.click();

    }
    @Test(dependsOnMethods = "clickOnVideo")
    public void clickOnThreeDots(){
        WebElement svgElement = driver.findElement(By.cssSelector("#fittPageContainer > section > div:nth-child(1) > section > div.Carousel__Wrapper.relative.Carousel__Wrapper--canScrollRight > div > div > ul > li.CarouselSlide.relative.pointer.CarouselSlide--active.CarouselSlide--inView > div > div > a > div.WatchTile__Content > div > div.WatchTile_ContinueWatching > button > svg > use"));

        svgElement.click();

    }
    //@Test(dependsOnMethods = "clickOnThreeDots")
    public void goToBrowse(){
        WebElement navLinkElement = driver.findElement(By.cssSelector("#nav-link-nav-menu-item-9419 > span"));

        navLinkElement.click();
    }

    //@Test(dependsOnMethods = "clickOnVideo")
    public void WatchPopupAppears(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Attempt to click on the element with retry mechanism
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#fittPortal_0 > div > div > section > header > button > div > svg")));
                element.click();
                break;
            } catch (Exception e) {
                System.out.println("Attempt " + (attempts + 1) + " failed. Retrying...");
            }
            attempts++;
        }
    }

    //@Test(dependsOnMethods = "clickOnVideo")
    public void closePopup(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#fittPortal_0 > div > div > section > header > button > div > svg")));


        element.click();
    }




}
