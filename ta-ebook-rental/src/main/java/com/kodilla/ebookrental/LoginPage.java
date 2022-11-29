package com.kodilla.ebookrental;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends AbstractPage {

    @FindBy(id = "register-btn")
    private WebElement registerBtn;

    @FindBy(id ="login-btn")
    private WebElement loginBtn;

    @FindBy(id = "login")
    private WebElement loginInput;

    @FindBy(id ="password")
    private WebElement passwordInput;



    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }

    public void loginAction(String login, String password){
        this.loginInput.sendKeys(login);
        this.passwordInput.sendKeys(password);
        this.loginBtn.click();
    }

}
