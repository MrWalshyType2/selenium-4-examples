package com.qa.examples.seleniumfour.elementinteraction02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;

import com.qa.examples.seleniumfour.utilities.ScreenshotManager;
import com.qa.examples.seleniumfour.utilities.WebDriverFactory;

public class FindingElements {

	private WebDriver driver;
	private ScreenshotManager screenshotManager;

	@Before
	public void setup() throws Exception {
		driver = WebDriverFactory.getDriver();
		screenshotManager = new ScreenshotManager();
		Window window = driver.manage().window();
		Timeouts timeouts = driver.manage().timeouts();
		window.maximize();
		timeouts.pageLoadTimeout(Duration.ofSeconds(30));
	}

	@Ignore
	@Test
	public void findingWithCss() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php");

		WebElement editorialBlockTitle = driver.findElement(By.cssSelector("div[id=editorial_block_center] > h1"));
		assertEquals(editorialBlockTitle.getText(), "Automation Practice Website");
	}

	@Ignore
	@Test
	public void findingWithBelowRelativeLocator() {
		driver.get("http://automationpractice.com/index.php");

		WebElement editorialBlockTitle = driver
				.findElement(RelativeLocator.with(By.tagName("h1")).below(By.id("editorial_image_legend")));

		assertEquals(editorialBlockTitle.getText(), "Automation Practice Website");
	}

	@Ignore
	@Test
	public void findingWithNearRelativeLocator() {
		driver.get("http://automationpractice.com/index.php");

		WebElement editorialBlockTitle = driver
				.findElement(RelativeLocator.with(By.tagName("h1")).near(By.id("editorial_image_legend")));

		assertEquals(editorialBlockTitle.getText(), "Automation Practice Website");
	}

	@Ignore
	@Test
	public void testingImageSource() {
		driver.get("http://automationpractice.com/index.php");
		WebElement logo = driver.findElement(By.cssSelector("#header_logo > a > img.logo"));

		assertEquals("http://automationpractice.com/img/logo.jpg", logo.getAttribute("src"));
	}

	@Test
	public void createAccount() {
		By usernameBy = By.id("username");
		By passwordBy = By.id("password");
		By signinBy = By.id("log-in");

		driver.get("https://demo.applitools.com/");

		if (!driver.getTitle().equals("ACME demo app")) {
			throw new IllegalStateException("Page did not load");
		}
		
		// login page
		driver.findElement(usernameBy).sendKeys("bob");
		driver.findElement(passwordBy).sendKeys("test");
		driver.findElement(signinBy).click();
		
		// verify logged in
		assertEquals("ACME", driver.findElement(By.cssSelector("a > div.logo-label")).getText());
	}

	@After
	public void teardown() {
		// prevents driver resources from being left open when the test finishes
		driver.quit();
	}
}
