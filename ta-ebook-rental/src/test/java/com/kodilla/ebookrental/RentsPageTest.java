package com.kodilla.ebookrental;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class RentsPageTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void testSetup(){
        System.setProperty("webdriver.chrome.driver", "c:\\selenium-drivers\\chrome\\chromedriver.exe");
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.navigate().to("https://ta-bookrental-fe.onrender.com/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAction("aleksandra-herlender","123");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-title-button")));
        TitlesPage titlesPage = new TitlesPage(driver);
        titlesPage.openItems(titlesPage.titles.get(titlesPage.titles.size()-1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title-copies")));
        ItemsPage itemsPage = new ItemsPage(driver);
        itemsPage.openHistory(itemsPage.items.get(itemsPage.items.size()-1));
    }

    @AfterEach
    public void cleanUpTest(){
        driver.close();
    }

    @Test
    public void shouldAddRent(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rents")));
        RentsPage rentsPage = new RentsPage(driver);
        Integer currentRents = rentsPage.rents.size();
        rentsPage.addRent("Ola");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("rents-list__rent"),currentRents));
        rentsPage = new RentsPage(driver);
        assertEquals(currentRents+1,rentsPage.rents.size());
    }



    @Test
    public void shouldRemoveRent(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rents")));
        RentsPage rentsPage = new RentsPage(driver);
        Integer currentRents = rentsPage.rents.size();
        rentsPage.addRent("Ola");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("rents-list__rent"),currentRents));
        rentsPage = new RentsPage(driver);
        currentRents = rentsPage.rents.size();
        rentsPage.removeRent(rentsPage.rents.get(currentRents-1));
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className("rents-list__rent"),currentRents));
        rentsPage = new RentsPage(driver);
        assertEquals(currentRents-1,rentsPage.rents.size());
    }


}