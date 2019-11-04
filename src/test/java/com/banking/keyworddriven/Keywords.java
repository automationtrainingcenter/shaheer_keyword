package com.banking.keyworddriven;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Keywords {
	private WebDriver driver;
	private Alert alert;

	// openBrowser
	public void openBrowser(String locType, String locValue, String testData) {
		if (testData.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (testData.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
	}

//	navigate
	public void navigate(String locType, String locValue, String testData) {
		driver.get(testData);
	}

//	fillTextField
	public void fillTextField(String locType, String locValue, String testData) {
		driver.findElement(LocatorHelper.locate(locType, locValue)).sendKeys(testData);
	}

//	click
	public void click(String locType, String locValue, String testData) {
		driver.findElement(LocatorHelper.locate(locType, locValue)).click();
	}

//	wait
	public void wait(String locType, String locValue, String testData) {
		try {
			Thread.sleep(Long.parseLong(testData));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

//	selectByText
	public void selectByText(String locType, String locValue, String testData) {
		Select eleSelect = new Select(driver.findElement(LocatorHelper.locate(locType, locValue)));
		eleSelect.selectByVisibleText(testData);
	}

//	switchToAlert 
	public void switchToAlert(String locType, String locValue, String testData) {
		alert = driver.switchTo().alert();
	}

//	acceptAlert
	public void acceptAlert(String locType, String locValue, String testData) {
		alert.accept();
	}
	
	
}
