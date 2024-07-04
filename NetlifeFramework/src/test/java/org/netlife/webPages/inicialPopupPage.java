package org.netlife.webPages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class inicialPopupPage {

	ChromeDriver driver;
	Properties pr;
	
	public inicialPopupPage(ChromeDriver driver, Properties pr) {
		this.driver = driver;
		this.pr = pr;
	}
	
	public void signInfrominicialPopupPage (String userName, String password) throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath(pr.getProperty("usuario"))).sendKeys("NC1");
		driver.findElement(By.xpath(pr.getProperty("contrasena"))).sendKeys("123");
		Thread.sleep(2000);
		driver.findElement(By.xpath(pr.getProperty("iniciarbutton"))).click();
	}
	
	public String getPopupHeader() throws InterruptedException{
		Thread.sleep(4000);
		String txt = driver.findElement(By.xpath(pr.getProperty("headmainpopup"))).getText();
		return txt;
	}
	
	public void clickClose() throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.xpath(pr.getProperty("closemainpopup"))).click();
	}
		
	
	public void clickAceptarTodo() throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.xpath(pr.getProperty("acceptmainpopup"))).click();
	}
	
}
