package com.qa.examples.seleniumfour.pom03;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;

import com.qa.examples.seleniumfour.pom03.pages.nofactory.RedditHomePage;
import com.qa.examples.seleniumfour.utilities.ScreenshotManager;
import com.qa.examples.seleniumfour.utilities.WebDriverFactory;

public class RedditTests {

	private WebDriver driver;
	private RedditHomePage redditHomePage;

	@Before
	public void setup() throws Exception {
		driver = WebDriverFactory.getDriver();
		Window window = driver.manage().window();
		Timeouts timeouts = driver.manage().timeouts();
		window.maximize();
		timeouts.pageLoadTimeout(Duration.ofSeconds(30));
		timeouts.implicitlyWait(Duration.ofSeconds(3));
		redditHomePage = new RedditHomePage(driver);
	}
	
	@Test
	public void openReddit() throws InterruptedException {
		redditHomePage.search("hello");
		
		Thread.sleep(5000);
	}
	
	@After
	public void teardown() {
		// prevents driver resources from being left open when the test finishes
		driver.quit();
	}
}
