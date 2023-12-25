package com.orgname.test.stepdefinitions.pagesactions.pages;

import com.orgname.framework.web.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class LoginPage {

    @Autowired
    WebDriverFactory webDriverFactory;

    @FindBy(xpath = "/html//div[@id='navbar-collapse-basic']/div/div[3]/div/button[2]/div/div")
    private WebElement loginLink;

    @FindBy(css = "input[id='Email Addressjoe@example.com']")
    private WebElement userName;
    @FindBy(css = "#login_pwd")
    private WebElement password;

    @FindBy(css = "form > .btn-primary.btn-rounded-md.mt-6.text-base")
    private WebElement loginButton;

    public void setUserName(String userNamevalue){
        userName.sendKeys(userNamevalue);
    }

    public void setPassword(String passwordValue){
        password.sendKeys(passwordValue);
    }

    public void loginButton(){
        loginButton.click();
    }

    public void clickLoginLink() {
        loginLink.click();
    }
}
