package com.jira.testcase;

import org.openqa.selenium.WebDriver;

import com.jira.page.EditIssueForm;
import com.jira.page.SearchResultPage;
import com.jira.util.Log;

public class UpdateIssue {
	public static void runTest(WebDriver driver, SearchResultPage searchResultPage) {
		printLog("Begin update for issue");
		EditIssueForm editIssueForm = searchResultPage.loadEditForm(driver);
		editIssueForm.fillIssueData();
		editIssueForm.editIssue();
	}

	private static void printLog(String content) {
		Log.printLog(content);
	}
}
