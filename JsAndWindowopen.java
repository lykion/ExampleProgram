package com.lykion.lianxi;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JsAndWindowopen {

	private static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E:\\eclipse_jar\\selenium_jar\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://www.baidu.com/");
		Thread.sleep(1000);
		
		WebElement element = driver.findElement(By.partialLinkText("新闻"));
		String url = element.getAttribute("href");
		((JavascriptExecutor)driver).executeScript("window.open('"+url+"')");  //使用window.open()点击当前页面链接，浏览器会new一个tab，即打开一个新的窗口
//		element.click();  //使用click()点击当前页面链接，浏览器会在当前窗口跳转到新的页面，而不会new一个tab，即不会打开新的窗口
		Thread.sleep(1000);
		change();
		System.out.println(driver.getTitle());
		System.out.println("---------------------------------------------------------------------");
		
		((JavascriptExecutor)driver).executeScript("window.open('"+driver.findElement(By.partialLinkText("许其亮、张又侠任中央军委副主席")).getAttribute("href")+"')");
//		driver.findElement(By.partialLinkText("国内")).click();
		Thread.sleep(1000);
		change();
		System.out.println(driver.getTitle());
		
		driver.quit();
	}

	public static void change() {
//		String currentwin = driver.getWindowHandle();
		Set<String> allwin = driver.getWindowHandles();
		Iterator<String> it = allwin.iterator();
		while(it.hasNext()) {
			driver.switchTo().window(it.next());
		}
	}
}
