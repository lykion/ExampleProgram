package com.lykion.lianxi;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class lykionAlert {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E:\\eclipse_jar\\selenium_jar\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///C:/Users/Uker/Desktop/duoxuan.html");
		
		driver.findElement(By.id("alert")).click();  //点击第一个按钮alert
		Alert alert = driver.switchTo().alert();  //使用switchTo()方法切换到弹窗
		Thread.sleep(1000);
		String text = alert.getText();  //获取弹窗的文本
		System.out.println(text);  //输出弹窗的文本
		alert.accept();  //点击确认
		Thread.sleep(1000);
		
		driver.findElement(By.id("confirm")).click();  //点击第一个按钮confirm
		Alert confirm = driver.switchTo().alert();  //使用switchTo()方法切换到弹窗
		Thread.sleep(1000);
		String text1 = confirm.getText();  //获取弹窗的文本
		System.out.println(text1);  //输出弹窗的文本
//		confirm.accept();  //点击确认
		confirm.dismiss();  //点击取消
		Thread.sleep(1000);
		
		driver.findElement(By.id("prompt")).click();  //点击第一个按钮prompt
		Alert prompt = driver.switchTo().alert();  //使用switchTo()方法切换到弹窗
		Thread.sleep(1000);
		String text2 = prompt.getText();  //获取弹窗的文本
		System.out.println(text2);  //输出弹窗的文本
		prompt.sendKeys("jerry");  //输入文本内容
		Thread.sleep(2000);
//		prompt.accept();  //点击确认
		prompt.dismiss();  //点击取消
		Thread.sleep(1000);
		
		driver.close();
	}

}
