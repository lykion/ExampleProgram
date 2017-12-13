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
		
		driver.findElement(By.id("alert")).click();  //�����һ����ťalert
		Alert alert = driver.switchTo().alert();  //ʹ��switchTo()�����л�������
		Thread.sleep(1000);
		String text = alert.getText();  //��ȡ�������ı�
		System.out.println(text);  //����������ı�
		alert.accept();  //���ȷ��
		Thread.sleep(1000);
		
		driver.findElement(By.id("confirm")).click();  //�����һ����ťconfirm
		Alert confirm = driver.switchTo().alert();  //ʹ��switchTo()�����л�������
		Thread.sleep(1000);
		String text1 = confirm.getText();  //��ȡ�������ı�
		System.out.println(text1);  //����������ı�
//		confirm.accept();  //���ȷ��
		confirm.dismiss();  //���ȡ��
		Thread.sleep(1000);
		
		driver.findElement(By.id("prompt")).click();  //�����һ����ťprompt
		Alert prompt = driver.switchTo().alert();  //ʹ��switchTo()�����л�������
		Thread.sleep(1000);
		String text2 = prompt.getText();  //��ȡ�������ı�
		System.out.println(text2);  //����������ı�
		prompt.sendKeys("jerry");  //�����ı�����
		Thread.sleep(2000);
//		prompt.accept();  //���ȷ��
		prompt.dismiss();  //���ȡ��
		Thread.sleep(1000);
		
		driver.close();
	}

}
