package com.jira.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIComponent {
	public static boolean selectDropDownBoxItemByIndex(WebElement dropDownBox,
			int index) {
		try {
			Select dropDownSelecter = new Select(dropDownBox);
			dropDownSelecter.selectByIndex(index);
			return true;
		} catch (Exception ex) {
			printLog("ERROR : have exception in selectDropDownBoxFirstItem funtion with WebElement : "
					+ dropDownBox.getAttribute("id")
					+ " / Error msg : "
					+ ex.getMessage());
			return false;
		}

	}

	public static boolean selectSuggestionItem(WebDriver driver,
			String parentDivId) {
		try {

			List<WebElement> suggestionList = driver.findElements(By
					.xpath("//div[@id='" + parentDivId + "']/div/ul/li"));
			printLog("Number of suggestion: " + +suggestionList.size());
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.id(parentDivId)));
			for (WebElement suggestion : suggestionList) {
				printLog("Choose suggestion : " + suggestion.getText());
				suggestion.click();
				break;
			}
			return true;
		} catch (Exception ex) {
			printLog("ERROR : have exception in selectSuggestionItem funtion with parentDivId : "
					+ parentDivId + " / Error msg : " + ex.getMessage());
			return false;
		}

	}

	public static void waitForClikable( WebDriver driver, WebElement element ){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitForVisible( WebDriver driver, WebElement element ){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitForPageLoadWithStartWithTitle( WebDriver driver, final String startTitle, int waitSeconds ){
		(new WebDriverWait(driver, waitSeconds)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().startsWith(startTitle.toLowerCase());
			}
		});
	}
	
	public static void waitForPageLoadWithContainsTitle( WebDriver driver, final String startTitle, int waitSeconds ){
		(new WebDriverWait(driver, waitSeconds)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().contains(startTitle.toLowerCase());
			}
		});
	}
	
	public static void waitForPageLoadWithComponentIdDisplay( WebDriver driver, final String id, int waitSeconds  ){
		(new WebDriverWait(driver, waitSeconds)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.id(id)).isDisplayed();
			}
		});
	}
	
	public static void waitForPageLoadWithComponentId( WebDriver driver, final String id, int waitSeconds  ){
		(new WebDriverWait(driver, waitSeconds)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.findElement(By.id(id)).isDisplayed();
			}
		});
	}
	
	public static boolean haveElementWithClassName( WebDriver driver, String className ){
		try{
			WebElement element = driver.findElement(By.className(className));
			return true;
		} catch(Exception ex) {
			return false;
		}
	}
	
	private static void printLog(String content) {
		Log.printLog(content);
	}
}
