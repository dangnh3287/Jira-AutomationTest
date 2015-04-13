package com.jira.util;

public class Config {
	
	public static final String loginPageUrl = "https://id.atlassian.com/login?continue=https%3A%2F%2Fjira.atlassian.com%2Fsecure%2FDashboard.jspa&application=jac";
	public static final String testUsername = "jira.atlassiantest1@gmail.com";
	public static final String testPassword = "RCMP69]murex";
	public static final String issueSummary = "This's a test issue, please ignore it.";
	public static final String issueDueDate = "30/Apr/15";
	public static final String issueEnvironment = "Environment for test issue.";
	public static final String issueDescription = "Description for test issue.";
	public static final String issueTimeTracking = "1w 2d 3h";
	public static final String issueComponentText = "c";
	public static final String issueAffectsVersionText = "a";
	public static final String issueFixVersionText = "t";
	public static final String issueLabelText = "label";
	public static final String issueAssignToText = "un";
	public static final String issueSuccessToSearch = "This's a test issue, please ignore it.1428866218943"; 
	public static final String issueFailToSearch = "24039247!W834823~"; 

	private static String newIssueTitle = "";
	private static String editIssueKeyword = "";
	
	public static String getEditIssueKeyword() {
		return editIssueKeyword;
	}

	public static void setEditIssueKeyword(String editIssueKeyword) {
		Config.editIssueKeyword = editIssueKeyword;
	}
	
	public static String getNewIssueTitle() {
		return newIssueTitle;
	}

	public static void setNewIssueTitle(String newIssueTitle){
		Config.newIssueTitle = newIssueTitle;
	}
}
