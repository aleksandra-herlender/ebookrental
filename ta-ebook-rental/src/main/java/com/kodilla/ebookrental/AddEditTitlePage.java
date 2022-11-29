package com.kodilla.ebookrental;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEditTitlePage extends AbstractPage{

    @FindBy(name = "title")
    WebElement titleInput;

    @FindBy(name = "author")
    WebElement authorInput;

    @FindBy(name = "year")
    WebElement yearInput;


    public AddEditTitlePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void addOrEdit(String author, String title, String year) {
        this.authorInput.sendKeys(author);
        this.titleInput.sendKeys(title);
        this.yearInput.sendKeys(year);
        this.titleInput.submit();
    }

    public void resetFields(){
        this.authorInput.clear();
        this.titleInput.clear();
        this.yearInput.clear();
    }
}
