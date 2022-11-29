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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TitlesPageTest {
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
    }

    @AfterEach
    public void cleanUpTest(){
        driver.close();
    }

    @Test
    public void shouldAddNewBook(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-title-button")));
        TitlesPage titlesPage = new TitlesPage(driver);
        titlesPage.addTitleBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fog__content")));
        AddEditTitlePage addTitle = new AddEditTitlePage(driver);
        Integer books = titlesPage.titles.size();
        addTitle.addOrEdit("Author", "title", "2013");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("titles-list__item"),books));
        titlesPage = new TitlesPage(driver);
        assertEquals(books+1,titlesPage.titles.size());
    }

    public void assertAddFailed(String expected){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert--error")));
        WebElement error = driver.findElement(By.cssSelector(".alert--error"));
        assertEquals(expected, error.getText());
    }

    @Test
    public void shouldFailedOnWrongFormAuthor(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-title-button")));
        TitlesPage titlesPage = new TitlesPage(driver);
        titlesPage.addTitleBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fog__content")));
        AddEditTitlePage addTitle = new AddEditTitlePage(driver);
        addTitle.addOrEdit("", "title", "2013");
        assertAddFailed("\"author\" field shouldn't be empty...");
    }

    @Test
    public void shouldFailedOnWrongFormTitle(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-title-button")));
        TitlesPage titlesPage = new TitlesPage(driver);
        titlesPage.addTitleBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fog__content")));
        AddEditTitlePage addTitle = new AddEditTitlePage(driver);
        addTitle.addOrEdit("author", "", "2013");
        assertAddFailed("\"title\" field shouldn't be empty...");
    }


    @Test
    public void shouldFailedOnWrongFormYear(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-title-button")));
        TitlesPage titlesPage = new TitlesPage(driver);
        titlesPage.addTitleBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fog__content")));
        AddEditTitlePage addTitle = new AddEditTitlePage(driver);
        addTitle.addOrEdit("author", "title", "");
        assertAddFailed("\"year\" field shouldn't be empty...");
    }

    @Test
    public void shouldRemoveBook() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-title-button")));
        TitlesPage titlesPage = new TitlesPage(driver);
        Integer books = titlesPage.titles.size();
        titlesPage.removeTitle(titlesPage.titles.get(books-1));
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className("titles-list__item"),books));
        titlesPage = new TitlesPage(driver);
        assertEquals(books-1,titlesPage.titles.size());
    }

}
