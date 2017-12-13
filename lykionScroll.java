package com.lykion.Assert;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class lykionScroll {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E:\\eclipse_jar\\selenium_jar\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.baidu.com/");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("kw")));
		
		driver.findElement(By.id("kw")).sendKeys("httpclient");
		Thread.sleep(2000);
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_A);
			Thread.sleep(1000);
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_C);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_C);
			Thread.sleep(1000);
			
			driver.findElement(By.id("kw")).clear();
			Thread.sleep(1000);
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			Thread.sleep(1000);
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("su")).click();
		Thread.sleep(1000);
		WebElement element = driver.findElement(By.id("8"));
//		WebElement linktext = driver.findElement(By.partialLinkText("使用详解"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);   //滑动到指定位置
//		int i = 8;
//		assertEquals(i, element.getText());
//		linktext.click();
		Thread.sleep(1000);
		
		//获取本地时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmsss");
		String time = dateFormat.format(Calendar.getInstance().getTime());
		//截取滑动后的屏幕并保存到指定位置
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("snapshot", time + ".png"));
		Thread.sleep(2000);
		
		driver.close();
	}

}
