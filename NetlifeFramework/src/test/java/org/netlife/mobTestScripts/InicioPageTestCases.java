package org.netlife.mobTestScripts;

import java.io.IOException;
import java.time.Duration;

import org.netlife.Base.Base;
import org.netlife.mobPages.loginPage;
import org.netlife.mobPages.mainMenuPage;
import org.netlife.utilities.ElementUtils;
import org.netlife.utilities.reportCapture;
import org.netlife.utilities.screenshotCapture;
import org.netlife.utilities.scrollToElement;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class InicioPageTestCases extends Base{

	@Test(groups = {"mobile"})
	public void inicioScrolling() throws IOException {
		
		// MainMenu: This test case verifies "INICIO" strips screen.
		
		String testCaseName = "MainMenu__Inicio_Validate_Strips";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportMob();
		ExtentTest tc1 = rep.startTest(testCaseName);
		
		try {
		
			String list = mobileproperties.getProperty("list_string_inicio");
			String[] expectedString = list.split(";");
			
			loginPage login = new loginPage(driver_android, mobileproperties);
			login.usuario().sendKeys(username);
			login.password().sendKeys(passwd);
			login.inicio_sesion().click();
			
			Duration timeout = Duration.ofSeconds(30);
			ElementUtils.waitForElement(driver_android, timeout, driver -> login.idioma_siguiente()).click();
			
			Duration timeout_2 = Duration.ofSeconds(3);
			ElementUtils.waitForElement(driver_android, timeout_2, driver -> login.control_omitir()).click();
			
			mainMenuPage main = new mainMenuPage(driver_android, mobileproperties);
			
			if (ElementUtils.waitForElement(driver_android, timeout_2, driver -> main.inicio_label()).isDisplayed()) {
				tc1.log(LogStatus.PASS, "INICIO screen is displayed." );
				scrollToElement scroll = new scrollToElement();
				boolean canScroll = true;
				int i = 0;
				do {
					try {
						final int index = i;
						scroll.scrollToElement_1(driver_android, index);
						String ti = ElementUtils.waitForElement(driver_android, timeout_2, driver -> main.inicio_strip(index)).getText();
						String mesg = "[Expected] :: " + expectedString[index] + " --> " + ti + " :: [Received]" ;
						if (ti.equals(expectedString[index])) {
							System.out.println(ti + " -> OK");
							tc1.log(LogStatus.PASS, mesg );
						}
						else {
							System.out.println(i + " -> FAIL");
							tc1.log(LogStatus.FAIL, mesg );
						}
						i++;
					} catch(Exception  e) {
						canScroll = false;
					}
				}while(canScroll);
				
			}else {
				tc1.log(LogStatus.PASS, "INICIO screen is NOT displayed." );
			}
		
		} catch(Exception e) {
			String msn = "THE TEST \"" + testCaseName + "\" DID NOT EXCECUTE!";
			System.out.println(msn);
			tc1.log(LogStatus.FATAL, msn );
			e.printStackTrace();
		}
		screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
		
		rep.endTest(tc1);
		rep.flush();
	}
	
}
