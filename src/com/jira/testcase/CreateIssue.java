package com.jira.testcase;

import com.jira.page.CreateIssueForm;
import com.jira.page.DashBoardPage;
import com.jira.util.Log;

public class CreateIssue {
	public static DashBoardPage runTest(DashBoardPage dashboardPage) {
		printLog("Begin create new issue");
		CreateIssueForm createIssueForm = dashboardPage.loadCreateIssueForm();
		createIssueForm.fillIssueData();
		dashboardPage = createIssueForm.createIssue();
		return dashboardPage;
	}

	private static void printLog(String content) {
		Log.printLog(content);
	}
}
