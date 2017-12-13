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
		System.setProperty("webdriver.chrome.driver", "E:\\eclipse_jar\\selenium_jar\\chromedriver.exe");  //chrome没有在默认安装目录，需要设置System.setProperty变量
		driver = new ChromeDriver();  //启动Chrome
		driver.get("https://www.baidu.com/");  //访问的目标地址
		
		/**
		 * 设置等待时间：直到搜索框出现为止(最大等待时间10s)
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
		driver.manage().window().maximize();  //窗口最大化
		Thread.sleep(1000);
		
		driver.findElement(By.id("kw")).sendKeys("java");  //在搜索框输入关键字“java”
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#su")).click();  //点击搜索按钮开始搜索
		Thread.sleep(1000);
		
		driver.findElement(By.id("kw")).clear();  //清除搜索框内容
		Thread.sleep(1000);
		driver.findElement(By.id("kw")).sendKeys("中国");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#su")).click();
		Thread.sleep(1000);
		
		driver.navigate().back();  //返回上一个页面
		Thread.sleep(2000);
		
		driver.navigate().refresh();  //刷新页面
		Thread.sleep(3000);
		
		driver.navigate().forward();  //浏览器向前进一步
		Thread.sleep(2000);
		
//		WebElement element = driver.findElement(By.xpath("//*[@id=\"7\"]/h3/a"));
		WebElement element = driver.findElement(By.linkText("Adobe:创意、营销和文档管理解决方案"));  //找到需要定位的元素element
		Thread.sleep(2000);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);  //滑动到定位元素element的位置
		Thread.sleep(2000);
		
		//获取本地时间
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(Calendar.getInstance().getTime());
		
		//指定了OutputType.FILE做为参数传递给getScreenshotAs()方法，其含义是将截取的屏幕以文件形式返回
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		/**
		 * 利用FileUtils工具类的copyFile()方法保存getScreenshotAs()返回的文件对象
		 * new File()第一个参数表示截图储存路径；如果该文件在本工程目录下，只需要填写文件夹名字即可；如果该文件夹没有在该工程目录下，则需要填写完成的路径
		 * new File()第二个参数表示截图保存的文件名
		 */
//		FileUtils.copyFile(srcFile, new File("snapshot", System.currentTimeMillis() + ".png"));
		FileUtils.copyFile(srcFile, new File("snapshot", time + ".png"));
	}
	
	@AfterClass
	public void tearDown() throws Exception{
		driver.close();
	}
}
