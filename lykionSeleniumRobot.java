package com.lykion.selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class lykionSeleniumRobot {
	
	public static void main(String[] args) throws InterruptedException, AWTException {
		System.setProperty("webdriver.chrome.driver", "E:\\eclipse_jar\\selenium_jar\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.baidu.com/");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("kw")));  //显示等待
		/**
		 * 复制、粘贴搜索框的文本内容
		 */
		driver.findElement(By.id("kw")).sendKeys("selenium + java");
		Thread.sleep(1000);
		
		Robot robot = new Robot();
		/**
		 * 全选搜索框的文本内容
		 */
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_A);
		Thread.sleep(1000);
		/**
		 * 复制搜索框的文本内容
		 */
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_C);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_C);
		Thread.sleep(1000);
		
		driver.findElement(By.id("kw")).clear();
		Thread.sleep(1000);
		/**
		 * 粘贴文本内容到搜索框
		 */
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);
		
		driver.findElement(By.id("su")).click();
		Thread.sleep(1000);
		
		driver.quit();
	}
	
}
