package com.orgname.test.stepdefinitions.pagesactions.actions;

import com.orgname.framework.web.BaseWebAction;
import com.orgname.framework.web.WebDriverFactory;
import com.orgname.test.stepdefinitions.pagesactions.pages.CompilerPage;
import com.orgname.test.stepdefinitions.pagesactions.pages.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;

@Lazy
@Component
@Scope("cucumber-glue")
public class LoginAction extends BaseWebAction {

    @Autowired
    WebDriverFactory webDriverFactory;

    @Autowired
    LoginPage loginPage;

    public void enterCreds(String userName,String password) {
        PageFactory.initElements(webDriverFactory.getWebDriver(),loginPage);
        loginPage.clickLoginLink();
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
    }

    public void login() {
        loginPage.loginButton();
    }
}


