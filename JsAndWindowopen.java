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
		
		WebElement element = driver.findElement(By.partialLinkText("����"));
		String url = element.getAttribute("href");
		((JavascriptExecutor)driver).executeScript("window.open('"+url+"')");  //ʹ��window.open()�����ǰҳ�����ӣ��������newһ��tab������һ���µĴ���
//		element.click();  //ʹ��click()�����ǰҳ�����ӣ���������ڵ�ǰ������ת���µ�ҳ�棬������newһ��tab����������µĴ���
		Thread.sleep(1000);
		change();
		System.out.println(driver.getTitle());
		System.out.println("---------------------------------------------------------------------");
		
		((JavascriptExecutor)driver).executeScript("window.open('"+driver.findElement(By.partialLinkText("���������������������ί����ϯ")).getAttribute("href")+"')");
//		driver.findElement(By.partialLinkText("����")).click();
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
