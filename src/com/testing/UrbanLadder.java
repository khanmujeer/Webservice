package com.testing;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class UrbanLadder {
	
	static
	{
		System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
	}
	
	
	WebDriver driver;
	
	@Test
	public void openApp() throws InterruptedException
	{
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.urbanladder.com/");
		WebElement close = driver.findElement(By.xpath("//a[@class='close-reveal-modal hide-mobile']"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(close));
		close.click();
		
		List<WebElement> mainEle = driver.findElements(By.xpath("//div[@id='topnav_wrapper']/descendant::span[@class='topnav_itemname']"));
		 Actions act = new Actions(driver);
		
		for(int i=0;i<mainEle.size();i++)
		{
			WebElement sElemt = mainEle.get(i);
			String mText = sElemt.getText();
			Reporter.log(mText,true);
			act.moveToElement(sElemt).build().perform();
			Thread.sleep(4000);
			
			List<WebElement> sbEle = driver.findElements(By.xpath("//div[@id='topnav_wrapper']/"
					+ "descendant::span[contains(.,'"+mText+"')]"
					+ "/following-sibling::div[@class='subnavlist lt']/descendant::div[@class='taxontype']"));
			
			for(int j=0;j<sbEle.size();j++)
			{
				String ssText = sbEle.get(j).getText();
				Reporter.log(ssText,true);
				List<WebElement> ssEle = driver.findElements(By.xpath("//div[@id='topnav_wrapper']/"
						+ "descendant::span[contains(.,'"+mText+"')]"
						+ "/following-sibling::div[@class='subnavlist lt']/descendant::div[@class='taxontype']"
						+ "/child::a[contains(.,'"+ssText+"')]/parent::div/following-sibling::ul/descendant::span"));
				
				for(int k=0;k<ssEle.size();k++)
				{
					String ssstxt = ssEle.get(k).getText();
					Reporter.log(ssstxt,true);
					
				}	
			}
			
		}
		
	}

}
