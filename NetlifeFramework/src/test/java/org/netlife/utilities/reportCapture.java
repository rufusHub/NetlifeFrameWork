package org.netlife.utilities;


import com.relevantcodes.extentreports.ExtentReports;

public class reportCapture {

	public static ExtentReports handleReport() {	
		ExtentReports rep = new ExtentReports("/home/rufo/logs/netlifeWebAutomated/reportNetlife.html", false);
		return rep;
	}
}
