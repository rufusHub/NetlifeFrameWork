package org.netlife.mobTestScripts;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

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

import io.appium.java_client.AppiumBy;

public class MainPageTestCases extends Base{

	
	@Test(groups = {"mobile"})
	public void mainMenu() throws IOException {
			
			// MainMenu: This test case verifies "INICIO" screen and "Main" menu strings.
			
			String testCaseName = "MainMenu__Validate_Strings";
			String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
			
			ExtentReports rep = reportCapture.handleReportMob();
			ExtentTest tc1 = rep.startTest(testCaseName);
			
			String list = mobileproperties.getProperty("list_string_main");
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
			
			Duration timeout_3 = Duration.ofSeconds(3);
			Boolean b1 = ElementUtils.waitForElement(driver_android, timeout_3, driver -> main.inicio_button()).isDisplayed();
			Boolean b2 = ElementUtils.waitForElement(driver_android, timeout_3, driver -> main.inicio_search()).isDisplayed();
			Boolean b3 = ElementUtils.waitForElement(driver_android, timeout_3, driver -> main.inicio_label()).isDisplayed();
			
			if (b1 && b2 && b3) {
				tc1.log(LogStatus.PASS, "INICIO screen is displayed. - PASS." );
			}
			else {
				tc1.log(LogStatus.PASS, "INICIO screen is displayed. - FAIL." );
			}
			
			ElementUtils.waitForElement(driver_android, timeout_3, driver -> main.inicio_button()).click();
			
			Duration timeout_4 = Duration.ofSeconds(2);
			String t1 = ElementUtils.waitForElement(driver_android, timeout_4, driver -> main.main_inicio()).getText();
			String t2 = ElementUtils.waitForElement(driver_android, timeout_4, driver -> main.main_vivo()).getText();
			String t3 = ElementUtils.waitForElement(driver_android, timeout_4, driver -> main.main_guia()).getText();
			String t4 = ElementUtils.waitForElement(driver_android, timeout_4, driver -> main.main_contenidos()).getText();
			String t5 = ElementUtils.waitForElement(driver_android, timeout_4, driver -> main.main_buscar()).getText();
			String t6 = ElementUtils.waitForElement(driver_android, timeout_4, driver -> main.main_config()).getText();

			List<String> st = Arrays.asList(t1,t2,t3,t4,t5,t6);
			
			Integer c = 0;
			
			for (String i : st) {
				String mesg = "[Expected] :: " + expectedString[c] + " --> " + i + " :: [Received]" ;
				if (i.equals(expectedString[c])) {
					System.out.println(i + " -> OK");
					tc1.log(LogStatus.PASS, mesg );
				}
				else {
					System.out.println(i + " -> FAIL");
					tc1.log(LogStatus.FAIL, mesg );
				}
				c++;
			}
				
			screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
			
			rep.endTest(tc1);
			rep.flush();

	}
		
		
	@Test(groups = {"mobile"})
	public void inicioScrolling() throws IOException {
		String testCaseName = "MainMenu__Validate_Strings";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportMob();
		ExtentTest tc1 = rep.startTest(testCaseName);
		
		String list = mobileproperties.getProperty("list_string_main");
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
		
		scrollToElement scroll = new scrollToElement();
		scroll.scrollToElement_1(driver_android, 3);
		
//		boolean canScroll = true;
//		int i = 0;
//		do {
//			try {
//			
//			String j = Integer.toString(i);
//			scroll.scrollToElement_1(driver_android, "live_strip_" + j +"_heading_text");
//			String 
//			String tj = ElementUtils.waitForElement(driver_android, timeout_2, driver -> main.inicio_strip_j()).getText();
//			System.out.println(tj);
//			i++;
//			} catch(Exception  e) {
//				canScroll = false;
//			}
//		}while(canScroll);
		
		
		
		String t3 = ElementUtils.waitForElement(driver_android, timeout_2, driver -> main.inicio_strip(3)).getText();
		System.out.println(t3);
		
		screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
		
		rep.endTest(tc1);
		rep.flush();
	}
	
}
