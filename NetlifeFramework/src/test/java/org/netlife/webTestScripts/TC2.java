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

public class TC2 extends Base{

	@Test(groups = {"web"})
	public void TC2_definition() throws InterruptedException, IOException {
		
		// loginPage: This test case clicks on "¿Olvidó usuario o contraseña?" and verifies proper url is opened.	
		
		String expectedUrl = "https://extranet.telconet.ec/reiniciarContrasenia/changePasswordNetlifePlay";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\TC2.jpg";
		String className = "TC2";
		
		ExtentReports rep = reportCapture.handleReport();
		ExtentTest tc2 = rep.startTest(className);
		
		loginPage login = new loginPage(driver_chrome, webproperties);
		login.forgotten();
		Thread.sleep(1000);
		
		Boolean result = Assertion.assertion_1(driver_chrome.getCurrentUrl(), expectedUrl, className);
		if (result) {
			tc2.log(LogStatus.PASS, "TC2 - loginPage: This test case clicks on \"¿Olvidó usuario o contraseña?\" and verifies proper url is opened. - is getting passed");
		}
		else {
			tc2.log(LogStatus.FAIL, "TC2 - loginPage: This test case clicks on \\\"¿Olvidó usuario o contraseña?\\\" and verifies proper url is opened. - is getting failed");
		}
		screenshotCapture.takeScreenshot(driver_chrome, pathPicture);
		rep.endTest(tc2);
		rep.flush();
		
				
	}
	
}