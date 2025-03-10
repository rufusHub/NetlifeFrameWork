package org.netlife.webTestScripts;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.netlife.Base.Base;
import org.netlife.assertions.Assertion;
import org.netlife.assertions.MobReportAssertions;
import org.netlife.utilities.reportCapture;
import org.netlife.utilities.screenshotCapture;
import org.netlife.webPages.loginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class WebLoginPageTestCases extends Base{

	@Test(groups = {"web"}, dataProvider = "dp1")
	public void loginSuccess(String s1, String s2) throws IOException {
		
		// LoginPage: This test case login with two accounts(runs twice).
		
		String testCaseName = "LoginMenu__LoginScreenSuccessful";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportWeb();
		ExtentTest tc = rep.startTest(testCaseName);
		
		try {
			String expUrl = webproperties.getProperty("expectedUrl_1");
			
			loginPage login = new loginPage(driver_chrome, webproperties);
			login.signIn(s1, s2);
			Thread.sleep(2000);
			String curUrl = driver_chrome.getCurrentUrl();
		
			MobReportAssertions as = new MobReportAssertions();
			as.assertion_1(expUrl, curUrl, tc);
				
		} catch(Exception e){
			String msn = testCaseName + " GOT A CRITICAL ERROR, REVIEW ASAP!";
			System.out.println(msn);
			tc.log(LogStatus.FATAL, msn );
			e.printStackTrace();
		}
		
		screenshotCapture.takeScreenshotMob(driver_chrome, pathPicture);
		rep.endTest(tc);
		rep.flush();	
	}
	
	@DataProvider
	public Object[][] dp1(){
		Object[][] ob = new Object[2][2];
		ob[0][0] = "NC1";
		ob[0][1] = "123";
		ob[1][0] = "NS1";
		ob[1][1] = "123";
		return ob;
	}
	
	@Test(groups = {"web"})
	public void loginUnsuccess() throws IOException {
		
		// loginPage: This test case try to login with WRONG account.	
		
		String testCaseName = "LoginMenu__LoginScreenUnsuccessful";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportWeb();
		ExtentTest tc = rep.startTest(testCaseName);
		
		try {
			String expString = webproperties.getProperty("expectedString_2");
			
			loginPage loginPagerror = new loginPage(driver_chrome, webproperties);
			String currString = loginPagerror.signonerror(wronguser, wrongpass);
			Thread.sleep(2000);
			
			MobReportAssertions as = new MobReportAssertions();
			as.assertion_1(expString, currString, tc);
			
		} catch(Exception e) {
			String msn = testCaseName + " GOT A CRITICAL ERROR, REVIEW ASAP!";
			System.out.println(msn);
			tc.log(LogStatus.FATAL, msn );
			e.printStackTrace();
		}
		screenshotCapture.takeScreenshotMob(driver_chrome, pathPicture);
		rep.endTest(tc);
		rep.flush();
	}
	
	@Test(groups = {"web"})
	public void getStrings() throws IOException {
		
		// LoginPage: This test case verifies some strings on Sign in menu.
		
		String testCaseName = "LoginMenu__EvaluateLoginMenuStrings";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\"+testCaseName+".jpg";

		ExtentReports rep = reportCapture.handleReportWeb();
		ExtentTest tc = rep.startTest(testCaseName);
		
		try {
			
			String[] expectedString = webproperties.getProperty("expectedString_1").split(";");

			loginPage login = new loginPage(driver_chrome, webproperties);
			List<String> st = login.getStrings();
			
			MobReportAssertions as = new MobReportAssertions();
			as.assertion_2(expectedString, st, tc);
					
		} catch(Exception e) {
			String msn = testCaseName + " GOT A CRITICAL ERROR, REVIEW ASAP!";
			System.out.println(msn);
			tc.log(LogStatus.FATAL, msn );
			e.printStackTrace();
		}
		
		screenshotCapture.takeScreenshot(driver_chrome, pathPicture);
		rep.endTest(tc);
		rep.flush();
	}
	
	@Test(groups = {"web"})
	public void forgotUserPass() throws IOException {
		
		// LoginPage: This test case clicks on "¿Olvidó usuario o contraseña?" and verifies proper url is opened.
		
		String testCaseName = "LoginMenu__VerifyForgotUserPassword";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportWeb();
		ExtentTest tc = rep.startTest(testCaseName);
		
		try {
			
			String expUrl = webproperties.getProperty("expectedUrl_2");
			
			loginPage login = new loginPage(driver_chrome, webproperties);
			login.forgotten();
			Thread.sleep(1000);
			String curUrl = driver_chrome.getCurrentUrl();
			
			MobReportAssertions as = new MobReportAssertions();
			as.assertion_1(expUrl, curUrl, tc);
			
			
		} catch(Exception e) {
			String msn = testCaseName + " GOT A CRITICAL ERROR, REVIEW ASAP!";
			System.out.println(msn);
			tc.log(LogStatus.FATAL, msn );
			e.printStackTrace();
		}
		
		screenshotCapture.takeScreenshot(driver_chrome, pathPicture);
		rep.endTest(tc);
		rep.flush();
	}
	
	@Test(groups = {"web"})
	public void termAndPolicies() throws IOException {
		
		// 	loginPage: This test case clicks on "Términos de servicio y Política de privacidad." and verifies proper url is opened.	

		String testCaseName = "LoginMenu__VerifyTermsAndPolicies";
		String pathPicture = "C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\WEB_demo\\"+testCaseName+".jpg";
		
		ExtentReports rep = reportCapture.handleReportWeb();
		ExtentTest tc = rep.startTest(testCaseName);
		
		try {
			
			String expUrl = webproperties.getProperty("expectedUrl_3");
			
			loginPage login = new loginPage(driver_chrome, webproperties);
			login.termandcond();
			Thread.sleep(2000);
			String curUrl = driver_chrome.getCurrentUrl();	
			
			MobReportAssertions as = new MobReportAssertions();
			as.assertion_1(expUrl, curUrl, tc);
			
			
		} catch(Exception e) {
			String msn = testCaseName + " GOT A CRITICAL ERROR, REVIEW ASAP!";
			System.out.println(msn);
			tc.log(LogStatus.FATAL, msn );
			e.printStackTrace();
		}
		
		screenshotCapture.takeScreenshot(driver_chrome, pathPicture);
		rep.endTest(tc);
		rep.flush();
	}
	
}
	
	
	
