package org.netlife.webTestScripts;

import java.io.IOException;

import org.netlife.Base.Base;
import org.netlife.assertions.Assertion;
import org.netlife.utilities.reportCapture;
import org.netlife.utilities.screenshotCapture;
import org.netlife.webPages.loginPage;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC3 extends Base{

	@Test(groups = {"web"})
	public void TC3_definition() throws InterruptedException, IOException {
		
		// 	loginPage: This test case clicks on "Términos de servicio y Política de privacidad." and verifies proper url is opened.	
		
		String expectedUrl = "https://www.netlife.ec/netlife-play-tyc/";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\TC3.jpg";
		String className = "TC3";
		
		ExtentReports rep = reportCapture.handleReport();
		ExtentTest tc3 = rep.startTest(className);
		
		
		loginPage login = new loginPage(driver_chrome, webproperties);
		login.termandcond();
		Thread.sleep(2000);
		
		Boolean result = Assertion.assertion_1(driver_chrome.getCurrentUrl(), expectedUrl, className);
		if (result) {
			tc3.log(LogStatus.PASS, "TC3 - loginPage: This test case clicks on \"Términos de servicio y Política de Cookies y Política de Privacidad.\" and verifies proper url is opened. - is getting passed");
		}
		else {
			tc3.log(LogStatus.FAIL, "TC3 - loginPage: This test case clicks on \"Términos de servicio y Política de Cookies y Política de Privacidad.\" and verifies proper url is opened. - is getting failed");
		}
		screenshotCapture.takeScreenshot(driver_chrome, pathPicture);
		rep.endTest(tc3);
		rep.flush();
	}	
}