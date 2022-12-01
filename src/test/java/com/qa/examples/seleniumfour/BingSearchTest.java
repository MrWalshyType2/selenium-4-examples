package com.qa.examples.seleniumfour;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.examples.seleniumfour.utilities.WebDriverFactory;

public class BingSearchTest {

	WebDriver driver;

	@Before
	public void setup() throws Exception {
		driver = WebDriverFactory.getDriver();
		
		Options options = driver.manage();
		Timeouts timeouts = options.timeouts();
		timeouts.implicitlyWait(Duration.ofSeconds(15));
		timeouts.pageLoadTimeout(Duration.ofSeconds(15));
	}

	@After
	public void teardown() {
		// When we use a WebDriver, the resource must be closed when we are done
		driver.quit();
	}

	@Test
	public void searchOnBing() throws InterruptedException {
		// given the user is on the Bing search page
		driver.get("https://www.bing.com");

		// when the user searches for kittens
		// - find the element
		// - driver.findElement() takes a selector strategy as input
		WebElement element = driver.findElement(By.name("q"));
		// - send input to the element
		element.sendKeys("kittens");
		// - submit the search
		element.submit();

		// then the page title should be kittens - Search
		assertEquals("kittens - Search", driver.getTitle());

	}

}
