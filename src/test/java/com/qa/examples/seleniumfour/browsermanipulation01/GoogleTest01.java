package com.qa.examples.seleniumfour.browsermanipulation01;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import com.qa.examples.seleniumfour.utilities.WebDriverFactory;

public class GoogleTest01 {

	// WebDriver interface
	private WebDriver driver;
	
	@Before
	public void setup() throws Exception {
		// provide WebDriver implementation
		driver = WebDriverFactory.getDriver();
		
		// get Window and Timeouts objects for configuration
		Window window = driver.manage()
							  .window();
		
		// maximise the browser window
		window.maximize();
		
		Timeouts timeouts = driver.manage()
				  				  .timeouts();
		// set the amount of time until a timeout error is thrown, i.e. the amount of time to wait
		// for a page to load before throwing an error
		timeouts.pageLoadTimeout(Duration.ofSeconds(30));
	}
	
	/**
	 * Use to agree to the terms and conditions form when first navigating to the Google search page.
	 */
	public void agreeToTerms() {
		WebElement agreeBtn = driver.findElement(By.xpath("//button[div[text()='Accept all']]"));
		agreeBtn.click();
	}
	
	@Test
	public void googleSearchTest() {
		driver.get("https://google.com");
		agreeToTerms();
		WebElement searchInputField = driver.findElement(By.name("q"));
		searchInputField.sendKeys("kittens");
		searchInputField.submit();
		
		assertEquals("kittens - Google Search", driver.getTitle());
	}
	
	@After
	public void teardown() {
		// prevents driver resources from being left open when the test finishes
		driver.quit();
	}
}
