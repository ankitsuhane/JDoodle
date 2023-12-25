package com.orgname.test.stepdefinitions;

import com.orgname.framework.web.WebDriverFactory;
import com.orgname.test.testRunner.TestRunner;
import cucumber.api.Result;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import lombok.var;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.net.MalformedURLException;
import java.util.*;

public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Autowired
    WebDriverFactory webDriverFactory;
    @Value("${webui}")
    private String webUI;

    @Before("@web")
    public void setupWeb(Scenario scenario) {
        logger.info("Setting up Web Driver!");
        webDriverFactory.setUpWebDriverLocal();
        webDriverFactory.getWebDriver().get(webUI);
        webDriverFactory.getWebDriver().manage().deleteAllCookies();
    }

    @After("@web")
    public void tearDownWeb(Scenario scenario) {
        logger.info("Tear down Web Driver!");
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) webDriverFactory.getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "img/png");
            } catch (WebDriverException somePlatformsDontSupportScreenShots) {
                logger.error(somePlatformsDontSupportScreenShots.getMessage());
            }
        }
        webDriverFactory.getWebDriver().close();
        webDriverFactory.getWebDriver().quit();
    }
}