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
import org.netlife.webPages.settingsPage;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TC10 extends Base{
	
	Boolean result = true;
	
	@Test(groups = {"web"})
	public void TC10_definition() throws InterruptedException, IOException {

		// settingsPage: This case takes Acerca del Dispositivo data and compares with expected data.
		
		List<String> expectedString = Arrays.asList("Versión actual","24.30.030","Versión principal","24.30.030");
		
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\TC10.jpg";
		String className = "TC10";
		
		ExtentReports rep = reportCapture.handleReport();
		ExtentTest tc10 = rep.startTest(className);
		
		loginPage login = new loginPage(driver_chrome, webproperties);
		login.signIn(username, passwd);
		Thread.sleep(2000);
		
		inicialPopupPage clickaccept = new inicialPopupPage(driver_chrome, webproperties);
		clickaccept.clickAceptarTodo();
		
		settingsPage setop = new settingsPage(driver_chrome, webproperties);
		setop.selectSettingsIcon();
		//Thread.sleep(2000);
		setop.selectDeviceInfo();
		//Thread.sleep(2000);
		String t1 = setop.getActualVersionTitle();
		String t2 = setop.getActualVersionNum();
		String t3 = setop.getPrincipalVersionTitle();
		String t4 = setop.getPrincipalVersionNum();
		
		List<String> st = Arrays.asList(t1,t2,t3,t4);
		
		Integer c = 0;
		
		for (String i : st) {
			String mesg = expectedString.get(c) + " -> is Expected; It is Receiving -> " + i ;
			if (i.equals(expectedString.get(c))) {
				System.out.println(i + " -> OK");
				tc10.log(LogStatus.PASS, mesg );
			}
			else {
				System.out.println(i + " -> FAIL");
				tc10.log(LogStatus.FAIL, mesg );
				this.result = false;
			}
			c++;
		}
		
		Assertion.assertion_2(result, className);
		if (result) {
			tc10.log(LogStatus.PASS, "TC10 - settingsPage: This case takes Acerca del Dispositivo data and compares with expected data. - is getting passed");
		}
		else {
			tc10.log(LogStatus.FAIL, "TC10 - settingsPage: This case takes Acerca del Dispositivo data and compares with expected data. - is getting failed");
		}
		
		screenshotCapture.takeScreenshot(driver_chrome, pathPicture);
		rep.endTest(tc10);
		rep.flush();
				
	}
}

