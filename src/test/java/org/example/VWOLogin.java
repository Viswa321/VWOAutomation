package org.example;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class VWOLogin {
    ChromeOptions options;
    WebDriver driver;

    @BeforeSuite
    public void setOptions() {
        options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

    }

    @Test(priority = 1, groups = {"Negative TestCase,Sanity Test"})
    @Description("Verified that In-valid username and valid password ,Login successful")
    public void NegativeTest()  {
        driver.get("https://app.vwo.com/#/login");
        driver.findElement(By.id("login-username")).sendKeys("93npu2yyb0@esiix.co");
        driver.findElement(By.id("login-password")).sendKeys("Wingify@123");
        driver.findElement(By.id("js-login-btn")).click();


        WebElement Alertmessage = driver.findElement(By.id("js-notification-box-msg"));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(Alertmessage));
        System.out.println(Alertmessage.getText());
         driver.findElement(By.className("notification-box-description")).getText();
        Assert.assertEquals(Alertmessage.getText(), "Your email, password, IP address or location did not match");
    }

    @Test( priority = 2, groups = {"positive", "sanity", "stage"})
    @Description("Verify that with Valid username and Valid password, Login is successful !!")
    public void testValidLogin() {

        // ID, Name, ClassName -> CssSelector, Xpath - Firefox or Chrome
        // xpath. CSS Selector -> Chrome, will not work Firefox.


        driver.get("https://app.vwo.com/#/login");
        driver.findElement(By.id("login-username")).sendKeys("93npu2yyb0@esiix.com");
        driver.findElement(By.id("login-password")).sendKeys("Wingify@123");
        driver.findElement(By.id("js-login-btn")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector(".page-heading"))));

        // Assertion
        // Expected Result == Actual Result

        Assert.assertEquals(driver.getTitle(), "Dashboard");
        Assert.assertEquals(driver.getCurrentUrl(), "https://app.vwo.com/#/dashboard");

        driver.get("https://app.vwo.com/logout");



    }

    @AfterSuite
    public void TearDown () {

    }
}
