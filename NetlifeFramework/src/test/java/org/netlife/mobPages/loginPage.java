package org.netlife.mobPages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;


public class loginPage {

	AndroidDriver driver;
	Properties pr;
	
	public loginPage(AndroidDriver driver, Properties pr) {
		this.driver = driver;
		this.pr = pr;
	}
	
	public WebElement usuario() {
		return driver.findElement(By.xpath(pr.getProperty("usuario")));
	}
	
	public WebElement password() {
		return driver.findElement(By.xpath(pr.getProperty("password")));
	}
	
	public WebElement inicio_sesion() {
		return driver.findElement(By.xpath(pr.getProperty("inicio_sesion")));
	}
	
	public WebElement olvido_pass() {
		return driver.findElement(By.xpath(pr.getProperty("olvido_pass")));
	}
	
	public WebElement terminos_politicas() {
		return driver.findElement(By.xpath(pr.getProperty("terminos_politicas")));
	}
	
	public WebElement sign_error() {
		return driver.findElement(By.xpath(pr.getProperty("sign_error")));
	}
	
	public WebElement rest_contra_msg_2() {
		return driver.findElement(By.xpath(pr.getProperty("rest_contra_2")));
	}
	
	public WebElement rest_contra_ok() {
		return driver.findElement(By.xpath(pr.getProperty("rest_contra_ok")));
	}
	
	public WebElement term_politica_msg_1() {
		return driver.findElement(By.xpath(pr.getProperty("term_politica_1")));
	}
	
	public WebElement term_politica__msg_2() {
		return driver.findElement(By.xpath(pr.getProperty("term_politica_2")));
	}
	
	public WebElement term_politica_ok() {
		return driver.findElement(By.xpath(pr.getProperty("term_politica_ok")));
	}
	
	public WebElement idioma_seleccion() {
		return driver.findElement(By.xpath(pr.getProperty("idioma_seleccion")));
	}
	
	public WebElement idioma_eng() {
		return driver.findElement(By.xpath(pr.getProperty("idioma_eng")));
	}
	
	public WebElement idioma_esp() {
		return driver.findElement(By.xpath(pr.getProperty("idioma_esp")));
	}
	
	@SuppressWarnings("deprecation")
	public WebElement idioma_esp_2() {
		return driver.findElement(MobileBy.AndroidUIAutomator("idioma_esp_3"));	
	}
	
	public WebElement idioma_siguiente() {
		return driver.findElement(By.xpath(pr.getProperty("idioma_siguiente")));
	}
	
	
	
}
