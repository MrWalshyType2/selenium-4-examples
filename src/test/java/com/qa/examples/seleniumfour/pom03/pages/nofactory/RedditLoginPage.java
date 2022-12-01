package com.qa.examples.seleniumfour.pom03.pages.nofactory;

import org.openqa.selenium.WebDriver;

public class RedditLoginPage {
	
	private WebDriver driver;

	public RedditLoginPage(WebDriver driver) {
		this.driver = driver;

		if (!driver.getTitle().equals("Reddit - Dive into anything")) {
			throw new IllegalStateException("Page did not load");
		}
	}
	
	public void login(String username, String password) {
		
	}
}