package org.netlife.webPages;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.collections.Lists;

public class loginPage {

	ChromeDriver driver;
	Properties pr;
	
	public loginPage(ChromeDriver driver, Properties pr) {
		this.driver = driver;
		this.pr = pr;
	}
	
	public void signIn(String userName, String password) throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath(pr.getProperty("usuario"))).sendKeys(userName);
		driver.findElement(By.xpath(pr.getProperty("contrasena"))).sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.xpath(pr.getProperty("iniciarbutton"))).click();
	}
	
	public void forgotten () throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath(pr.getProperty("olvidobutton"))).click();
		List<String> browserTabs = Lists.newArrayList(driver.getWindowHandles());
		driver.switchTo().window(browserTabs.get(1)); // Switch to second tab		
	}
	
	public void termandcond() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath(pr.getProperty("terminospoliticas"))).click();
		List<String> browserTabs = Lists.newArrayList(driver.getWindowHandles());
		driver.switchTo().window(browserTabs.get(1)); // Switch to second tab	
	}
	
	public String signonerror(String wronguser, String wrongpass) throws InterruptedException{
		Thread.sleep(5000);
		driver.findElement(By.xpath(pr.getProperty("usuario"))).sendKeys(wronguser);
		driver.findElement(By.xpath(pr.getProperty("contrasena"))).sendKeys(wrongpass);
		Thread.sleep(2000);
		driver.findElement(By.xpath(pr.getProperty("iniciarbutton"))).click();
		Thread.sleep(2000);
		String txt = driver.findElement(By.xpath(pr.getProperty("signwrong"))).getText();
		return txt;
	}
	
	
	
	public List<String> getStrings() throws InterruptedException {
		Thread.sleep(5000);
		String txt1 = driver.findElement(By.xpath(pr.getProperty("olvidobutton"))).getText();
		String txt2 = driver.findElement(By.xpath(pr.getProperty("terminospoliticas"))).getText();
		String txt3 = driver.findElement(By.xpath(pr.getProperty("conocercontenido"))).getText();
		return Arrays.asList(txt1, txt2, txt3);
	}
}

//WebElement forgotten = driver.findElement(By.xpath(pr.getProperty("olvidobutton")));
//Actions ac = new Actions(driver);
//ac.keyDown(Keys.CONTROL).click(forgotten).keyUp(Keys.CONTROL).build().perform();