package org.netlife.webTestScripts;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.netlife.Base.Base;
import org.netlife.assertions.Assertion;
import org.netlife.utilities.reportCapture;
import org.netlife.utilities.screenshotCapture;
import org.netlife.webPages.inicialPopupPage;
import org.netlife.webPages.loginPage;
import org.netlife.webPages.mainMenuPage;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC9 extends Base{
	
	Boolean result = true;
	
	@Test(groups = {"web"})
	public void TC9_definition() throws InterruptedException, IOException {
		
		// mainMenuPage: This case takes all options in main menu and compare with expected strings.
		
		List<String> expectedString = Arrays.asList("INICIO","CANALES", "MIS CONTENIDOS");

		String pathPicture = "/home/rufo/logs/netlifeWebAutomated/TC9.jpg";
		String className = "TC9";
		
		ExtentReports rep = reportCapture.handleReport();
		ExtentTest tc9 = rep.startTest(className);
		
		loginPage login = new loginPage(driver_chrome, webproperties);
		login.signIn(username, passwd);
		Thread.sleep(4000);
		
		inicialPopupPage clickaccept = new inicialPopupPage(driver_chrome, webproperties);
		clickaccept.clickAceptarTodo();
		
		mainMenuPage mainstr = new mainMenuPage(driver_chrome, webproperties);
		List<String> st = mainstr.getMainMenuOptions();
		
		Integer c = 0;
		 
		for (String i : st) {
			if (i.equals(expectedString.get(c))) {
				System.out.println(c.toString() + " -> OK");
			}
			else {
				System.out.println(c.toString() + " -> FAIL");
				this.result = false;
				break;
			}
			c++;
		}
		
		Assertion.assertion_2(result, className);
		if (result) {
			tc9.log(LogStatus.PASS, "TC4 is getting passed");
		}
		else {
			tc9.log(LogStatus.FAIL, "TC4 is getting failed");
		}
		screenshotCapture.takeScreenshot(driver_chrome, pathPicture);
		rep.endTest(tc9);
		rep.flush();
		
	}
}
