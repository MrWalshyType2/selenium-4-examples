package com.qa.examples.seleniumfour.utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * A simple utility class for taking screenshots via a Selenium WebDriver.
 *
 * @author Morgan Walsh
 */
public class ScreenshotManager {

    /**
     * Holds the current screenshot, or null if none have been taken
     */
    private File currentScreenshot;

    /**
     * Takes a screenshot of the current browser tab
     *
     * @param driver - a WebDriver implementation representing the browser instance
     */
    public void takeScreenshot(WebDriver driver) {
        currentScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

    /**
     * Takes a screenshot of the element at the specified selector.
     *
     * @param driver - a WebDriver implementation representing the browser instance
     * @param selector - a By element selector to select the element to screenshot
     */
    public void takeElementScreenshot(WebDriver driver, By selector) {
        WebElement element = driver.findElement(selector);
        currentScreenshot = element.getScreenshotAs(OutputType.FILE);
    }

    /**
     * Takes a screenshot of the current browser tab.
     *
     * @param driver - a WebDriver implementation representing the browser instance
     * @param path - the string representing the path to save the image, "./images/test-image.png" would be a valid path as a string
     * @throws IOException - thrown if the file couldn't be saved
     */
    public void takeAndSaveScreenshot(WebDriver driver, String path) throws IOException {
        takeScreenshot(driver);
        saveScreenshot(path);
    }

    /**
     * Takes a screenshot of the element at the specified selector.
     *
     * @param driver - a WebDriver implementation representing the browser instance
     * @param selector - a By element selector to select the element to screenshot
     * @param path - the path to save the image, "./images/test-image.png" would be a valid path as a string
     * @throws IOException - thrown if the file couldn't be saved
     */
    public void takeAndSaveElementScreenshot(WebDriver driver, By selector, String path) throws IOException {
        takeElementScreenshot(driver, selector);
        saveScreenshot(path);
    }

    /**
     * Saves the current screenshot in the File object to the specified path, the last portion of the path is the filename.
     * <br />
     * <br />
     *
     * @param path - the path to save the image, "./images/test-image.png" would be a valid path as a string
     * @throws IOException - thrown if the file couldn't be saved
     */
    public void saveScreenshot(String path) throws IOException {
        currentScreenshot.renameTo(new File(path));
        currentScreenshot.createNewFile();
    }
}
