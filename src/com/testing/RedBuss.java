package com.testing;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.TimeUnit;

import javax.swing.KeyStroke;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

public class RedBuss {

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
		
		
		WebElement from = driver.findElement(By.id("src"));
		from.sendKeys("Bengaluru");
		Thread.sleep(2000);
		from.sendKeys(Keys.ENTER);
		
		WebElement dest = driver.findElement(By.id("dest"));
		dest.sendKeys("Mumbai");
		Thread.sleep(2000);
		dest.sendKeys(Keys.ENTER);
		
		
		LocalDate date = LocalDate.now();
		System.out.println(date);
		
		
		LocalDate plDays = date.plusDays(286);
		int pday = plDays.getDayOfMonth();
		String pMonth = plDays.getMonth().toString().substring(0, 3);
		String actPMon = pMonth.substring(0, 1)+pMonth.substring(1,3).toLowerCase();
		int pYear = date.getYear();
		
		String pActualM = pMonth+" "+pYear;
		
		
		Thread.sleep(3000);
		WebElement onward = driver.findElement(By.id("onward_cal"));
		onward.sendKeys(Keys.ENTER);
		System.out.println("===============");
		System.out.println(pday);
		try {
		for(int i=0;;i++)
		{
		WebElement monthTitle = driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']/"
				+ "descendant::td[@class='monthTitle']"));
		String exptedM = monthTitle.getText();
		
		WebElement nextBtn = driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']/"
				+ "descendant::td[@class='next']"));
	
		if(pActualM.equalsIgnoreCase(exptedM))
		{
			System.out.println("in if loop");
			WebElement currentdate = driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']"
					+ "/descendant::td[contains(.,'"+pActualM+"')]/following::td[.='"+pday+"']"));
			
			System.out.println(currentdate.getText());		
			 currentdate.click();
			 break;
		}
		
		else
		{
			nextBtn.click();
		}
		

		} 
		}
		catch(Exception e)
		{
			
		}
	
		
		
		
		
		
		
		
		
		
		
		

	}

}
