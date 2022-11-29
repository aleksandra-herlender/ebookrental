package com.kodilla.ebookrental;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends AbstractPage {

    @FindBy(id = "register-btn")
    private WebElement registerBtn;

    @FindBy(id ="login-btn")
    private WebElement loginBtn;

    @FindBy(id = "login")
    private WebElement loginInput;

    @FindBy(id ="password")
    private WebElement passwordInput;

    @FindBy(id ="password-repeat")
    private WebElement passwordRepeatInput;


    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }

    public void registerAction(String login, String password, String passwordRepeat){
        this.loginInput.sendKeys(login);
        this.passwordInput.sendKeys(password);
        this.passwordRepeatInput.sendKeys(passwordRepeat);
        this.registerBtn.click();
    }

}
