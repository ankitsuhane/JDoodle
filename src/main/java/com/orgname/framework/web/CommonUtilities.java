package com.orgname.framework.web;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.awt.event.KeyEvent;
import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope("cucumber-glue")
public class CommonUtilities {
	@Autowired
	WebDriverFactory webDriverFactory;
	private static final org.slf4j.Logger Log = LoggerFactory.getLogger(CommonUtilities.class);

	public void Click(WebElement locator) {
		Log.info("Click method has been executed for Webelement" + locator);
		if (isElementPresent(locator))
			locator.click();
		else
			Assert.fail("locator not found: " + locator);
	}

	public void sendKeys(WebElement locator, String value) {
		try {
			Log.info("Click method has been executed for Webelement" + locator);
			if (isElementPresent(locator)) {
				locator.sendKeys(value);
			}
		} catch (Exception e) {
			Log.error(locator + " : has got into exception", e);
			Assert.fail(e.getMessage());
		}

	}

	public void clear(WebElement locator) {

		try {
			Log.info("Click method has been executed for Webelement" + locator);
			if (isElementPresent(locator)) {
				locator.clear();
			}
		} catch (Exception e) {
			Log.error(locator + " : has got into exception", e);
		}
	}

	public String getText(WebElement locator) {
		String text = null;
		try {
			Log.info("Click method has been executed for Webelement" + locator);
			if (isElementPresent(locator)) {
				text = locator.getText();
			}
		} catch (Exception e) {
			Log.error(locator + " : has got into exception", e);
		}
		return text;
	}

	public void clearAndSendKeys(WebElement locator, String value) {

		try {
			Log.info("Click method has been executed for Webelement" + locator);
			if (isElementPresent(locator)) {
				locator.clear();
				locator.sendKeys(Keys.CONTROL + "a");
				locator.sendKeys(value);
			}
		} catch (Exception e) {
			Log.error(locator + " : has got into exception", e);
		}

	}

	public static boolean isElementPresent(WebElement locator) {
		try {
			Log.info("isElementPresent method has been executed for Webelement" + locator);
			boolean isPresent = locator.isDisplayed();
			return isPresent;
		} catch (NoSuchElementException e) {
			Log.error(locator + " : has got into exception");
			return false;
		}
	}

	public void moveToElement(WebElement element) {
		try{
		Actions action = new Actions(webDriverFactory.getWebDriver());
		action.moveToElement(element).pause(Duration.ofSeconds(2)).build().perform();
		Thread.sleep(5000);}
		catch (Exception e){
		System.out.println(e.getMessage());
	}
	}

	public void moveToElementClick(WebElement element) {
		try{
		Actions action = new Actions(webDriverFactory.getWebDriver());
		action.moveToElement(element).pause(Duration.ofSeconds(2)).click().build().perform();
		Thread.sleep(5000);}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private void waitForWindows(int index) {
		FluentWait<WebDriver> wait = waitForElement(120L, 250L);
		wait.until(ExpectedConditions.numberOfWindowsToBe(index));
	}

	private List<String> getWindowIds(){
		ArrayList<String> windowIds = new ArrayList<>(webDriverFactory.getWebDriver().getWindowHandles());
		return Collections.unmodifiableList(windowIds);
	}
	public void switchToChildWindow(int index){
		ArrayList<String> windowIds = new ArrayList<>(getWindowIds());
		waitForWindows(index+1);

		if(index < 0 || index > windowIds.size())
			throw new IllegalArgumentException("Index is not valid " + index);
		webDriverFactory.getWebDriver().switchTo().window(windowIds.get(index));
	}

	public FluentWait<WebDriver> waitForElement(long gFlunetWaitTimeoutSec, long gFlunetWaitPoolingTimeoutMili) {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriverFactory.getWebDriver());
		wait
				.ignoring(NoSuchElementException.class)
				.ignoring(ElementNotVisibleException.class)
				.ignoring(StaleElementReferenceException.class)
				.withTimeout(Duration.ofSeconds(gFlunetWaitTimeoutSec))
				.pollingEvery(Duration.ofMillis(gFlunetWaitPoolingTimeoutMili));
		return wait;
	}

	public String csvReader(File fileName, String location) throws IOException {
		File file = new File(location+fileName);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String getCode, finalCode = "";
		while((getCode= br.readLine())!=null){
			finalCode= finalCode.concat(getCode);
		}
		return finalCode;
	}

}
