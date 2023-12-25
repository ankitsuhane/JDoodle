package com.orgname.test.stepdefinitions.pagesactions.actions;

import com.orgname.framework.web.BaseWebAction;
import com.orgname.framework.web.CommonUtilities;
import com.orgname.framework.web.WebDriverFactory;
import com.orgname.test.stepdefinitions.pagesactions.pages.CompilerPage;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

@Lazy
@Component
@Scope("cucumber-glue")
public class CompilerPageAction extends BaseWebAction {

    @Autowired
    WebDriverFactory webDriverFactory;

    @Autowired
    CompilerPage compilerPage;

    @Value("${JavaCode}")
    private String code;

    @Autowired
    CommonUtilities commonUtilities;

    public void clickFrame() {
        PageFactory.initElements(webDriverFactory.getWebDriver(),compilerPage);
        compilerPage.scrollToNewProject();
    }

    public void createNewProject() {
        PageFactory.initElements(webDriverFactory.getWebDriver(),compilerPage);
        commonUtilities.Click(compilerPage.GotItCookie);
        compilerPage.scrollToNewProject();
        compilerPage.clickNewProject();
        compilerPage.confirmNewProject();
    }
    public void enterCode() throws AWTException, IOException {
        String finalCode = commonUtilities.csvReader(new File("JavaCode.txt"), System.getProperty("user.dir")+"\\src\\test\\resources\\");
        commonUtilities.Click(compilerPage.editor);
        Robot robot = new Robot();
        robot.mouseMove(345, 350);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(finalCode);
        clipboard.setContents(stringSelection, null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    public void executeButtonClick() {
        JavascriptExecutor js = (JavascriptExecutor) webDriverFactory.getWebDriver();
        js.executeScript("arguments[0].scrollIntoView(true);",compilerPage.executeButton);
        FluentWait<WebDriver> wait = commonUtilities.waitForElement(120L, 250L);
        wait.until(ExpectedConditions.elementToBeClickable(compilerPage.executeButton));
        commonUtilities.Click(compilerPage.executeButton);
    }

    public void howTo() {
        JavascriptExecutor js = (JavascriptExecutor) webDriverFactory.getWebDriver();
        js.executeScript("arguments[0].scrollIntoView(true);",compilerPage.howTo);
        commonUtilities.moveToElementClick(compilerPage.panelHowTo);
        commonUtilities.moveToElementClick(compilerPage.panelHowTo);
        commonUtilities.Click(compilerPage.howTo);
    }

    public void validateDocsPage() {
        commonUtilities.switchToChildWindow(1);
        String actualDocsUrl= webDriverFactory.getWebDriver().getCurrentUrl();
        String expectedDocsUrl="https://www.jdoodle.com/docs";
        Assert.assertEquals("Expected not matches: ", expectedDocsUrl,actualDocsUrl);
    }

    public void selectLanguage(String language) {
        PageFactory.initElements(webDriverFactory.getWebDriver(),compilerPage);
        commonUtilities.Click(compilerPage.languageBar);
        commonUtilities.Click(compilerPage.searchLanguage);
        commonUtilities.sendKeys(compilerPage.searchLanguage, language);
        commonUtilities.Click(compilerPage.searchLanguage);
        commonUtilities.Click(compilerPage.python3);

    }

    public void validateLanguage(String language) {
        String actualTitle= compilerPage.title.getText();
        String expectedTitle="Online Python 3 IDE";
        Assert.assertEquals("Expected not matches: ", expectedTitle,actualTitle);

    }
    public void clickPricing() {
        PageFactory.initElements(webDriverFactory.getWebDriver(),compilerPage);
        commonUtilities.Click(compilerPage.pricingLink);
    }

    public void validatePricingPage() {
        String expectedPricingUrl="https://www.jdoodle.com/pricing";
        FluentWait<WebDriver> wait = commonUtilities.waitForElement(120L, 250L);
        wait.until(ExpectedConditions.titleIs("JDoodle - Online Compiler API IDE Pricing"));
        String actualPricingUrl= webDriverFactory.getWebDriver().getCurrentUrl();
        Assert.assertEquals("Expected not matches: ", expectedPricingUrl,actualPricingUrl);
    }
}


