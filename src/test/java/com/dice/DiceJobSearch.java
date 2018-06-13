package com.dice;

import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
public static void main(String[] args) {
	//Set up chrome driver path
	WebDriverManager.chromedriver().setup();
	//invoke selenium webdriver
	WebDriver driver = new ChromeDriver();
	
	
	
	//full screen
	driver.manage().window().fullscreen();
	//set universal wait time in case web page is slow
	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	
	String url = "https://dice.com";
	driver.get(url);
	
	
	String actualTitle = driver.getTitle();
			String expectedTitle="Job Search for Technology Professionals | Dice.com";
	
	if(actualTitle.equals(expectedTitle)) {
		System.out.println("Step pass. Dice homepage succesfully loaded");
	}else {
		System.out.println("Step fail. Dice homepage did not load succesfully ");
		throw new RuntimeException("Step fail. Dice homepage did not load succesfully ");
	}
	
	String keyword = "automated tester";
	driver.findElement(By.id("search-field-keyword")).clear();
	driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
	
	String location = "60656";
	driver.findElement(By.id("search-field-location")).clear();
	driver.findElement(By.id("search-field-location")).sendKeys(location);
	
	driver.findElement(By.id("findTechJobs")).click();
	
	String count = driver.findElement(By.id("posiCountMobileId")).getText();
	System.out.println(count);
	//ensure count is more than 0
	int countResult = Integer.parseInt(count.replace(",", ""));
	
	if(countResult>0) {
		System.out.println("Step PASS: keyword :"+keyword+" search returned " + countResult+ " result in "+ location);
	}else {
		System.out.println("Step FAIL: keyword :"+keyword+" search returned " + countResult+ " result in "+ location);
	}
	
	driver.close();
}
}
