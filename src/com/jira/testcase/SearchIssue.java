package com.jira.testcase;

import com.jira.page.DashBoardPage;
import com.jira.page.SearchResultPage;
import com.jira.util.Log;

public class SearchIssue {

	public static SearchResultPage runTest(DashBoardPage dashBoardPage, String title) {
		printLog("Begin search for issue : '" + title + "'");
		SearchResultPage searchResultPage = dashBoardPage.searchIssue(title);
		return searchResultPage;
	}

	private static void printLog(String content) {
		Log.printLog(content);
	}
}
