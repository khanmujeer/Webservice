package com.testing;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropDown {
	static
	{
	
		//System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}

	public static void main(String[] args) {
		
	//WebDriver driver=new FirefoxDriver();
	
	WebDriver driver = new ChromeDriver();
	
	driver.manage().window().maximize();
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get("file:///C:/Users/HP/Desktop/html/dropdown.html");
	
	WebElement drop = driver.findElement(By.id("mtr"));
	
	Select sl = new Select(drop);
	System.out.println(sl.isMultiple());
	
	sl.selectByIndex(0);
	sl.selectByIndex(2);
	
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.visibilityOf(drop));
	
	sl.selectByIndex(4);
	sl.getFirstSelectedOption();
	
	WebElement el = sl.getWrappedElement();
	System.out.println(el.getText());
	List<WebElement> al = sl.getOptions();
	
	System.out.println("===========================");
	for(WebElement web:al)
	{
		if(!web.isSelected())
		{
			System.out.println(web.getText());
		}
	}
	
	
	
	System.out.println("++++++++++++++++++++++++++");
	for(WebElement ele:al)
	{
		System.out.println(ele.getText());
	}
	
		
	driver.close();	
		

	}

}
