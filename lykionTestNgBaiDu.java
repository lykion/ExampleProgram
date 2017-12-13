package com.lykion.selenium;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class lykionTestNgBaiDu {

	private WebDriver driver;
//	private String url;
//	private boolean acceptNextAlert = true;
//	private StringBuffer verificationErrors = new StringBuffer();
	
	@BeforeClass
	public void setUp() throws Exception{
		System.setProperty("webdriver.chrome.driver", "E:\\eclipse_jar\\selenium_jar\\chromedriver.exe");  //chromeû����Ĭ�ϰ�װĿ¼����Ҫ����System.setProperty����
		driver = new ChromeDriver();  //����Chrome
		driver.get("https://www.baidu.com/");  //���ʵ�Ŀ���ַ
		
		/**
		 * ���õȴ�ʱ�䣺ֱ�����������Ϊֹ(���ȴ�ʱ��10s)
		 */
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(new ExpectedCondition<WebElement>() {

			@Override
			public WebElement apply(WebDriver d) {
				// TODO Auto-generated method stub
				return driver.findElement(By.id("kw"));  
			}
		});
		Thread.sleep(1000);
	}
	
	@Test
	public void searchAndBack() throws Exception{
		driver.manage().window().maximize();  //�������
		Thread.sleep(1000);
		
		driver.findElement(By.id("kw")).sendKeys("java");  //������������ؼ��֡�java��
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#su")).click();  //���������ť��ʼ����
		Thread.sleep(1000);
		
		driver.findElement(By.id("kw")).clear();  //�������������
		Thread.sleep(1000);
		driver.findElement(By.id("kw")).sendKeys("�й�");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#su")).click();
		Thread.sleep(1000);
		
		driver.navigate().back();  //������һ��ҳ��
		Thread.sleep(2000);
		
		driver.navigate().refresh();  //ˢ��ҳ��
		Thread.sleep(3000);
		
		driver.navigate().forward();  //�������ǰ��һ��
		Thread.sleep(2000);
		
//		WebElement element = driver.findElement(By.xpath("//*[@id=\"7\"]/h3/a"));
		WebElement element = driver.findElement(By.linkText("Adobe:���⡢Ӫ�����ĵ�����������"));  //�ҵ���Ҫ��λ��Ԫ��element
		Thread.sleep(2000);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);  //��������λԪ��element��λ��
		Thread.sleep(2000);
		
		//��ȡ����ʱ��
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(Calendar.getInstance().getTime());
		
		//ָ����OutputType.FILE��Ϊ�������ݸ�getScreenshotAs()�������京���ǽ���ȡ����Ļ���ļ���ʽ����
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		/**
		 * ����FileUtils�������copyFile()��������getScreenshotAs()���ص��ļ�����
		 * new File()��һ��������ʾ��ͼ����·����������ļ��ڱ�����Ŀ¼�£�ֻ��Ҫ��д�ļ������ּ��ɣ�������ļ���û���ڸù���Ŀ¼�£�����Ҫ��д��ɵ�·��
		 * new File()�ڶ���������ʾ��ͼ������ļ���
		 */
//		FileUtils.copyFile(srcFile, new File("snapshot", System.currentTimeMillis() + ".png"));
		FileUtils.copyFile(srcFile, new File("snapshot", time + ".png"));
	}
	
	@AfterClass
	public void tearDown() throws Exception{
		driver.close();
	}
}
