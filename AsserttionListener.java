package com.lykion.Assert;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AsserttionListener {
	
	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "E:\\eclipse_jar\\selenium_jar\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.baidu.com/");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("kw")));
		/**
		 * 添加断言 判断Title是否正确
		 */
//		try {
//			String baidu_title = "百度一下，你就知道";
//			assertEquals(baidu_title, driver.getTitle());
//			System.out.println("Test pass !");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		String baidu_title = "百度一下，你就知道";
		assertEquals(baidu_title, driver.getTitle());
		System.out.println("Test successes *-*");
		
		driver.close();
	}
}
