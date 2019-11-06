package com.banking.keyworddriven;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Keywords {
	private WebDriver driver;
	private Alert alert;
	private Actions actions;

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
			Thread.sleep(4000);
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

	// moveToElement
	public void moveToElement(String locType, String locValue, String testData) {
		actions = new Actions(driver);
		actions.moveToElement(driver.findElement(LocatorHelper.locate(locType, locValue))).build().perform();
	}

	// moveToEleAndClick
	public void moveToEleAndClick(String locType, String locValue, String testData) {
		actions = new Actions(driver);
		actions.moveToElement(driver.findElement(LocatorHelper.locate(locType, locValue))).click().build().perform();
	}

	// switchToFrame
	public void switchToFrame(String locType, String locValue, String testData) {
		WebElement frame = driver.findElement(LocatorHelper.locate(locType, locValue));
		driver.switchTo().frame(frame);
	}

	// switchToParentFrame
	public void swithToParentFrame(String locType, String locValue, String testData) {
		driver.switchTo().parentFrame();
	}

	// switchToMainPage
	public void switchToMainPage(String locType, String locValue, String testData) {
		driver.switchTo().defaultContent();
	}

	// switchToWindow
	public void switchToWindow(String locType, String locValue, String windowID) {
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowIDs = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowIDs.get(Integer.parseInt(windowID)));
	}

	// closeBrowser
	public void closeBrowser(String locType, String locValue, String testData) {
		if (driver.getWindowHandles().size() > 1) {
			driver.quit();
		} else {
			driver.close();
		}
	}

//	public static void main(String[] args) {
//		Keywords keywords = new Keywords();
//		Method[] methods = keywords.getClass().getMethods();
//		for (Method method : methods) {
//			System.out.println(method.getName());
//		}
//
//	}

}
