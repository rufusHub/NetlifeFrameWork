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

public class TC11 extends Base{
	
	Boolean result = true;
	
	@Test(groups = {"web"})
	public void TC11_definition() throws InterruptedException, IOException { 

	// settingsPage: This case takes Control parental data and compares with expected data.
	
	List<String> expectedString = Arrays.asList("Habilitar control parental", "Contenido permitido", "Cambiar PIN");
	
	String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\TC11.jpg";
	String className = "TC11";
	
	ExtentReports rep = reportCapture.handleReport();
	ExtentTest tc11 = rep.startTest(className);
	
	loginPage login = new loginPage(driver_chrome, webproperties);
	login.signIn(username, passwd);
	Thread.sleep(2000);
	
	inicialPopupPage clickaccept = new inicialPopupPage(driver_chrome, webproperties);
	clickaccept.clickAceptarTodo();
	
	settingsPage setop = new settingsPage(driver_chrome, webproperties);
	setop.selectSettingsIcon();
	//Thread.sleep(1000);
	setop.selectDeviceInfo();
	//Thread.sleep(1000);
	setop.selectControlParental();
	//Thread.sleep(1000);
	List<String> st = setop.getParentalControlOptions();
	
	Integer c = 0;
	
	for (String i : st) {
		String mesg = expectedString.get(c) + " -> is Expected; It is Receiving -> " + i ;
		if (i.equals(expectedString.get(c))) {
			System.out.println(i + " -> OK");
			tc11.log(LogStatus.PASS, mesg );
		}
		else {
			System.out.println(i + " -> FAIL");
			tc11.log(LogStatus.FAIL, mesg );
			this.result = false;
		}
		c++;
	}
	
	Assertion.assertion_2(result, className);
	if (result) {
		tc11.log(LogStatus.PASS, "TC11 - settingsPage: This case takes Control parental data and compares with expected data. - is getting passed");
	}
	else {
		tc11.log(LogStatus.FAIL, "TC11 - settingsPage: This case takes Control parental data and compares with expected data. - is getting failed");
	}
	
	screenshotCapture.takeScreenshot(driver_chrome, pathPicture);
	rep.endTest(tc11);
	rep.flush();
	}
	
}
