package com.qa.examples.seleniumfour.pom03.pages.nofactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RedditHomePage {

	private WebDriver driver;
	private By searchSelector = By.name("q");
	private By loginSignupContainerSelector = By.xpath("//div[a[text()='Log In']]");
	By loginSelector = By.name("login");
	
	public RedditHomePage(WebDriver driver) {
		this.driver = driver;
		
		driver.get("https://www.reddit.com");
		
		if (!driver.getTitle().equals("Reddit - Dive into anything")) {
			throw new IllegalStateException("Page did not load");
		}
	}
	
	public RedditLoginPage clickLogin() {
		driver.findElement(loginSignupContainerSelector)
			  .findElement(By.xpath("//a[text()='Log In']"))
			  .click();
		return new RedditLoginPage(driver);
	}
	
	public void search(String input) {
		WebElement searchBar = driver.findElement(searchSelector);
		searchBar.sendKeys(input);
		searchBar.submit();
	}
	
	public void closeLoginPrompt() {
		WebElement exit = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div[2]/div/button"));
		exit.click();
	}
}
