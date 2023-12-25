package com.orgname.test.stepdefinitions.pagesactions.pages;

import com.orgname.framework.web.CommonUtilities;
import com.orgname.framework.web.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class CompilerPage {

    @Autowired
    WebDriverFactory webDriverFactory;

    @FindBy(xpath = "//span[text()='New Project']")
    public WebElement newProjectOnFrame;
    @FindBy(css = "div[class*='z-10 w-[60px] sm:w-[75px] transition-all print:hidden']")
    public WebElement panelFrame;

    @FindBy(xpath = "//span[contains(text(),'How To')]")
    public WebElement howTo;

    @FindBy(xpath = "//button[contains(text(), 'Yes')]")
    public WebElement yesButton;
    @FindBy(xpath ="//div[@id='code'] [contains(@class,'editor')]")
    public WebElement editor;

    @FindBy(xpath = "//button[contains(text(), 'Execute')]")
    public WebElement executeButton;

    @FindBy(css = "div#navbar-collapse-basic .h-full.print\\:hidden.relative.w-fit > .h-full.relative  img[alt='language logo']")
    public WebElement languageBar;

    @FindBy(css = "input[placeholder='Search Language'][id='searchBar']")
    public WebElement searchLanguage;

    @FindBy(xpath = "//span[text()='Python3'] [contains(@class,'inline')]")
    public WebElement python3;

    @FindBy(xpath = "//h1[@class='text-md font-medium xl:text-lg']")
    public WebElement title;

    @FindBy(css = "button[class='btn-secondary btn-rounded-full sm:w-auto']")
    public WebElement GotItCookie;
    @FindBy(css = ".flex.flex-row.flex-start.h-full.print\\:hidden.px-\\[17\\.5px\\].section-quinary.sm\\:px-\\[25px\\].static.w-full")
    public WebElement panelHowTo;

    @FindBy(linkText = "Pricing")
    public WebElement pricingLink;
    @Autowired
    CommonUtilities commonUtilities;

    public void scrollToNewProject(){
        commonUtilities.moveToElement(panelFrame);
    }

    public void clickNewProject() {
        commonUtilities.moveToElementClick(newProjectOnFrame);
    }

    public void confirmNewProject() {
        commonUtilities.Click(yesButton);
    }
}
