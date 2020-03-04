package com.testing;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBussSenario {
	
	static
	{
		System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		FirefoxOptions option = new FirefoxOptions();
		option.addArguments("--disable-notifications");
		
		
		WebDriver driver=new FirefoxDriver(option);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.redbus.in/");
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		
		WebElement from = driver.findElement(By.id("src"));
		from.sendKeys("Bengaluru");
		Thread.sleep(2000);
		from.sendKeys(Keys.ENTER);
		
		WebElement dest = driver.findElement(By.id("dest"));
		dest.sendKeys("Mumbai");
		Thread.sleep(2000);
		dest.sendKeys(Keys.ENTER);
		
		LocalDate currentdate = LocalDate.now();
		System.out.println(currentdate);
		
		LocalDate onwardDATE = currentdate.plusDays(50);
		int onwardDay = onwardDATE.getDayOfMonth();
		String month = onwardDATE.getMonth().toString().substring(0, 3);
		String onwardMonth= month.substring(0, 1)+month.substring(1,3).toLowerCase();
		int onwardYear = onwardDATE.getYear();
		
		String actOnwardMYear = onwardMonth+" "+onwardYear;
		//System.out.println(actOnwardMYear);
		
		Thread.sleep(3000);
		WebElement onwardCalender = driver.findElement(By.id("onward_cal"));
		onwardCalender.sendKeys(Keys.ENTER);
		
		while(true) {
		WebElement calMonth = driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']/descendant::td[@class='monthTitle']"));
		String aCalMonth = calMonth.getText();
		 
		WebElement nxtBtn = driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']/descendant::td[@class='next']"));
		
		if(actOnwardMYear.equals(aCalMonth))
		{
			WebElement dateEle = driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']"
					+ "/descendant::td[contains(.,'"+actOnwardMYear+"')]"
					+ "/following::td[text()='"+onwardDay+"']"));
		dateEle.click();
			break;
		}
		
		else
		{
			nxtBtn.click();
		}
		}
		
		WebElement returnCal = driver.findElement(By.id("return_cal"));
		
		
		
		returnCal.sendKeys(Keys.ENTER);
		//driver.switchTo().frame(returnCal);
		
		LocalDate returnDate = onwardDATE.plusDays(55);
		int returnDay = returnDate.getDayOfMonth();
		String returnMonth = returnDate.getMonth().toString().substring(0, 3);
		String actRmon = returnMonth.substring(0, 1)+returnMonth.substring(1,3).toLowerCase();
		int returnYear = returnDate.getYear();
		
		String actReturnMy = actRmon+" "+returnYear;
		
		//System.out.println(actReturnMy);
		while(true) {
		Thread.sleep(6000);
		WebElement returnM = driver.findElement(By.xpath("//div[@id='rb-calendar_return_cal']/descendant::td[@class='monthTitle']"));								  	
		String actRMy = returnM.getText();
		System.out.println(actRMy);
		
		WebElement renext = driver.findElement(By.xpath("//div[@id='rb-calendar_return_cal']/descendant::td[@class='next']"));
		
		if(actReturnMy.equals(actRMy))
		{
			WebElement rDate = driver.findElement(By.xpath("//div[@id='rb-calendar_return_cal']/"
					+ "descendant::td[contains(.,'"+actReturnMy+"')]/following::td[text()='"+returnDay+"']"));
			rDate.click();
			break;
		}
		else
		{
			Thread.sleep(1000);
		  renext.click();
			
		}
		
		}
		
		driver.findElement(By.xpath("//button[@id='search_btn']")).click();
		
		
	}

}
