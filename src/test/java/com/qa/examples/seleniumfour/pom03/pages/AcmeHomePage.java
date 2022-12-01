package com.qa.examples.seleniumfour.pom03.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AcmeHomePage {

	private WebDriver driver;

	@FindBy(className = "logged-user-name")
	private WebElement username;

	public AcmeHomePage(WebDriver driver) {
		this.driver = driver;
	}

	public String getLoggedInUsername() {
		return username.getText();
	}
}
