package org.netlife.mobTestScripts;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.netlife.Base.Base;
import org.netlife.assertions.Assertion;
import org.netlife.mobPages.loginPage;
import org.netlife.utilities.ElementUtils;
import org.netlife.utilities.reportCapture;
import org.netlife.utilities.screenshotCapture;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTestCases extends Base{
	
	@Test(groups = {"mobile"})
	public void getText() throws IOException {
		
		// LoginPage: This test case verifies some strings on Sign in menu.	
		
		Boolean result = true;
		String testCaseName = "Loginscreen_getText";
		
		String list = mobileproperties.getProperty("list_string_login");
		String[] expectedString = list.split(",");
													
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportMob();
		ExtentTest tc1 = rep.startTest(testCaseName);
		
		loginPage login = new loginPage(driver_android, mobileproperties);
		String t1 = login.usuario().getText();
		String t2 = login.password().getText();
		String t3 = login.inicio_sesion().getText();
		String t4 = login.olvido_pass().getText();
		String t5 = login.terminos_politicas().getText();
		
		List<String> st = Arrays.asList(t1,t2,t3,t4,t5);
		
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
				result = false;
			}
			c++;
		}
		
		//Assertion.assertion_2(result, testCaseName);
			
		screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
		
		rep.endTest(tc1);
		rep.flush();
		
		
	}
	
	@Test(groups = {"mobile"})
	public void loginSuccess() throws InterruptedException, IOException {
		
		//  LoginPage: This test case login with correct account.
	
		String testCaseName = "Loginscreen_loginSuccess";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportMob();
		ExtentTest tc1 = rep.startTest(testCaseName);
		
		loginPage login = new loginPage(driver_android, mobileproperties);
		login.usuario().sendKeys(username);
		login.password().sendKeys(passwd);
		login.inicio_sesion().click();;
		
		Duration timeout = Duration.ofSeconds(30);
		WebElement con = ElementUtils.waitForElement(driver_android, timeout, driver -> login.idioma_seleccion());
		
		if (con != null) {
		    System.out.println("Element found and visible.");
		    tc1.log(LogStatus.PASS, "Loginscreen_login - LoginPage: This test case login with correct account - PASSED");
		} else {
		    System.out.println("Element not found within 20 seconds.");
		    tc1.log(LogStatus.FAIL, "Loginscreen_login - LoginPage: This test case login with correct account - FAILED");
		}

		screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
		
		rep.endTest(tc1);
		rep.flush();
			
	}
	
	@Test(groups = {"mobile"})
	public void loginError() throws InterruptedException, IOException {
		
		// LoginPage: This test login with Incorrect account.
		
		String testCaseName = "Loginscreen_loginError";

		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportMob();
		ExtentTest tc1 = rep.startTest(testCaseName);
		
		loginPage login = new loginPage(driver_android, mobileproperties);
		login.usuario().sendKeys(wronguser);
		login.password().sendKeys(wrongpass);
		login.inicio_sesion().click();;
		
		Duration timeout = Duration.ofSeconds(5);
		WebElement con = ElementUtils.waitForElement(driver_android, timeout, driver -> login.sign_error());
				
		if(con != null) {
			System.out.println("Element found and visible.");
			tc1.log(LogStatus.PASS, "Loginscreen_loginError - LoginPage: This test login with Incorrect account. - PASSED");
		}
		else {
			System.out.println("Element not found within 20 seconds.");
			tc1.log(LogStatus.FAIL, "Loginscreen_loginError - LoginPage: This test login with Incorrect account. - FAILED");
		}
		
		screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
		
		rep.endTest(tc1);
		rep.flush();
	}
	
	@Test(groups = {"mobile"})
	public void forgetPass() throws IOException {
		
		// LoginPage: This test clicks on "Olvidó Contraseña" and selects "OK".
		
		String testCaseName = "Loginscreen_forgetPass";

		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportMob();
		ExtentTest tc1 = rep.startTest(testCaseName);
		
		loginPage login = new loginPage(driver_android, mobileproperties);
		login.olvido_pass().click();
		
		Duration timeout = Duration.ofSeconds(10);
		WebElement con_1 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.rest_contra_msg_2());
		WebElement con_2 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.rest_contra_ok());
		
		if(con_1 != null && con_2 != null) {
			System.out.println("Elements found and visible.");
			tc1.log(LogStatus.PASS, "Loginscreen_forgetPass - LoginPage: This test clicking Olvidó Contraseña. - PASSED");
		}
		else {
			System.out.println("Element not found within 5 seconds.");
			tc1.log(LogStatus.FAIL, "Loginscreen_forgetPass - LoginPage: This test clicking Olvidó Contraseña. - FAILED");
		}
		
		screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
		
		con_2.click();
		
		Boolean con_3 = login.inicio_sesion().isDisplayed();
		
		if(con_3 != false) {
			System.out.println("Elements found and visible.");
			tc1.log(LogStatus.PASS, "Loginscreen_forgetPass - LoginPage: Press OK in Olvidó Contraseña menu. - PASSED");
		}
		else {
			System.out.println("Element not found within 5 seconds.");
			tc1.log(LogStatus.FAIL, "Loginscreen_forgetPass - LoginPage: Press OK in Olvidó Contraseña menu. - FAILED");
		}
		
		rep.endTest(tc1);
		rep.flush();
		
	}
	
	@Test(groups = {"mobile"})
	public void termPoliticas() throws IOException {
	
		// LoginPage: This test clicks on "Términos y Políticas" and selects "OK".
		
				String testCaseName = "Loginscreen_termPoliticas";

				String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
				
				ExtentReports rep = reportCapture.handleReportMob();
				ExtentTest tc1 = rep.startTest(testCaseName);
				
				loginPage login = new loginPage(driver_android, mobileproperties);
				login.terminos_politicas().click();
				
				Duration timeout = Duration.ofSeconds(10);
				WebElement con_2 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.term_politica__msg_2());
				WebElement con_3 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.term_politica_msg_1());
				WebElement con_1 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.term_politica_ok());
				
				if(con_2 != null && con_3 != null && con_1 != null ) {
					System.out.println("Elements found and visible.");
					tc1.log(LogStatus.PASS, "Loginscreen_termPoliticas - LoginPage: This test clicking Términos y Políticas. - PASSED");
				}
				else {
					System.out.println("Element not found within 5 seconds.");
					tc1.log(LogStatus.FAIL, "Loginscreen_termPoliticas - LoginPage:  This test clicking Términos y Políticas - FAILED");
				}
				
				screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
				
				con_1.click();
				
				Boolean con_4 = login.inicio_sesion().isDisplayed();
				
				if(con_4 != false) {
					System.out.println("Elements found and visible.");
					tc1.log(LogStatus.PASS, "Loginscreen_termPoliticas - LoginPage: Press OK in Términos y Políticas menu. - PASSED");
				}
				else {
					System.out.println("Element not found within 5 seconds.");
					tc1.log(LogStatus.FAIL, "Loginscreen_termPoliticas - LoginPage: Press OK in Términos y Políticas menu. - FAILED");
				}
				
				rep.endTest(tc1);
				rep.flush();
	}
	
	@Test(groups = {"mobile"})
	public void selecIdioma() throws IOException {
		
		//  LoginPage: This test case verifies some strings on Idioma menu.
		
		Boolean result = true;
		String testCaseName = "Loginscreen_selectLanguage";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\MOB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportMob();
		ExtentTest tc1 = rep.startTest(testCaseName);
		
		String list = mobileproperties.getProperty("list_string_idioma");
		String[] expectedString = list.split(";");
		
		loginPage login = new loginPage(driver_android, mobileproperties);
		login.usuario().sendKeys(username);
		login.password().sendKeys(passwd);
		login.inicio_sesion().click();;
		
		Duration timeout = Duration.ofSeconds(30);
		WebElement con_1 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.idioma_seleccion());
		WebElement con_2 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.idioma_eng());
		WebElement con_3 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.idioma_esp());
		WebElement con_4 = ElementUtils.waitForElement(driver_android, timeout, driver -> login.idioma_siguiente());
//		
		String t1 = con_1.getText();
		String t2 = con_2.getText();
		String t3 = con_3.getText();
		String t4 = con_4.getText();
		
		List<String> st = Arrays.asList(t1,t2,t3,t4);
		
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
				result = false;
			}
			c++;
		}
		
		screenshotCapture.takeScreenshotMob(driver_android, pathPicture);
		
		rep.endTest(tc1);
		rep.flush();
		
		
	}
}
