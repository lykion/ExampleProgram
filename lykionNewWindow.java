package com.lykion.lianxi;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class lykionNewWindow {
	
	private static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "E:\\eclipse_jar\\selenium_jar\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///C:/Users/Uker/Desktop/seleniumHTML/newWindow.html");
		Thread.sleep(1000);;
		driver.manage().window().maximize();
		String currentwindow = driver.getWindowHandle();
		System.out.println("当前窗口的title：" + driver.getTitle());
		System.out.println("当前窗口数量:" + driver.getWindowHandles().size());
		
		driver.findElement(By.xpath("/html/body/form/input")).click();
		changeWindow();
		System.out.println("当前窗口的title：" + driver.getTitle());
		System.out.println("当前窗口数量:" + driver.getWindowHandles().size());
		
		WebElement element = driver.findElement(By.partialLinkText("LESS IS MORE"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		String url = element.getAttribute("href");
		((JavascriptExecutor)driver).executeScript("window.open('"+url+"')");
		changeWindow();
		System.out.println("当前窗口的title：" + driver.getTitle());
		System.out.println("当前窗口数量:" + driver.getWindowHandles().size());
	}
	public static void changeWindow() {
		String currentwin = driver.getWindowHandle();
		Set<String> allwin = driver.getWindowHandles();
		Iterator<String> it = allwin.iterator();
		while(it.hasNext()) {
			driver.switchTo().window(it.next());
		}
	}
	
}
