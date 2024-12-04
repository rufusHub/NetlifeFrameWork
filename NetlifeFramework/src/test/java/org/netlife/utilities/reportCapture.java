package org.netlife.utilities;


import com.relevantcodes.extentreports.ExtentReports;

public class reportCapture {

	public static ExtentReports handleReport() {	
		ExtentReports rep = new ExtentReports("C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\Report_demo.html", false);
		return rep;
	}
}
