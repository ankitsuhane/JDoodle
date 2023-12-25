package com.orgname.test.stepdefinitions.web;

import com.orgname.framework.web.WebDriverFactory;
import com.orgname.test.stepdefinitions.pagesactions.actions.CompilerPageAction;
import com.orgname.test.stepdefinitions.pagesactions.actions.LoginAction;
import com.orgname.test.stepdefinitions.pagesactions.pages.LoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;
import org.openqa.selenium.JavascriptExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.awt.*;
import java.io.IOException;


public class JavaStepDefintion implements En {
    @Autowired
    CompilerPageAction compilerPageAction;

    @Autowired
    WebDriverFactory webDriverFactory;

    @Value("${userid}")
    private String userName;

    @Value("${password}")
    private String password;

    @Autowired
    LoginAction loginAction;

    @When("Press execute button")
    public void pressExecuteButton() {
        compilerPageAction.executeButtonClick();
    }

    @Then("program should be executed successfully")
    public void programShouldBeExecutedSuccessfully() {
        System.out.println("Unable to automate fully due to captcha");
    }

    @Given("I enter my username and password")
    public void iEnterMyUsernameAndPassword() {
        loginAction.enterCreds(userName,password);

    }

    @When("press submit button")
    public void pressSubmitButton() {
        loginAction.login();
    }

    @Then("able to login successfully")
    public void ableToLoginSuccessfully() {
        System.out.println("Unable to automate fully due to captcha");
    }

    @Given("Create a new program")
    public void createANewProgram() throws AWTException, IOException {
        compilerPageAction.createNewProject();
        compilerPageAction.enterCode();
    }

    @Given("I am at java compiler site")
    public void iAmAtJavaCompilerSite() {
        compilerPageAction.clickFrame();
    }

    @When("press HowTo_FAQ link")
    public void pressHowToFAQLink() {
        compilerPageAction.howTo();

    }

    @Then("HowTo Page should be opened")
    public void howtoPageShouldBeOpened() {
        compilerPageAction.validateDocsPage();
    }

    @Given("I opened the Java compiler site")
    public void iOpenedTheJavaCompilerSite() {
    }

    @When("select language from Java to {string}")
    public void selectLanguageFromJavaTo(String language) {
        compilerPageAction.selectLanguage(language);

    }

    @Then("language {string} should be changed and page title and code should be changed")
    public void languageShouldBeChangedAndPageTitleAndCodeShouldBeChanged(String language) {
        compilerPageAction.validateLanguage(language);
    }

    @When("click on the pricing page")
    public void clickOnThePricingPage() {
        compilerPageAction.clickPricing();
    }

    @Then("pricing page should be opened")
    public void pricingPageShouldBeOpened() {
        compilerPageAction.validatePricingPage();
    }
}
