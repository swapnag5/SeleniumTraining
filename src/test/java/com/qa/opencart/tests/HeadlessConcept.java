package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HeadlessConcept {

	public static void main(String[] args) {

     //headless -- no launching browser
		//faster than reguler browser
		//incognito-- does not maintain browsing history
		
		ChromeOptions co = new ChromeOptions();
		//co.addArguments("--headless");
		co.setHeadless(true);
		
		//co.addArguments("--incognito");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(co);
		driver.get("https://google.com");
		System.out.println(driver.getTitle());
		
		
	}

}
