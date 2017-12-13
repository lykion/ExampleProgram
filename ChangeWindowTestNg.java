package com.lykion.lianxi;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChangeWindowTestNg {

	private static WebDriver driver;
	@BeforeClass
	public void setUp() throws Exception{
		System.setProperty("webdriver.chrome.driver", "E:\\eclipse_jar\\selenium_jar\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///C:/Users/Uker/Desktop/seleniumHTML/newWindow.html");
		String currentHandle = driver.getWindowHandle();  //获取当前窗口的句柄
		System.out.println("当前窗口数量：" + driver.getWindowHandles().size());
		Thread.sleep(1000);
	}
	@AfterClass
	public void tearDown() throws Exception{
		
	}
	@Test
	public void testCase() throws Exception{
		driver.findElement(By.xpath("/html/body/form/input")).click();  //打开新的窗口
		windowChange();  //调用windowChange()切换窗口
//		switchToWindow("", driver);  //调用switchToWindow()切换窗口
		System.out.println("当前窗口数量：" + driver.getWindowHandles().size());
		System.out.println("当前窗口标题：" + driver.getTitle());
		
		WebElement element = driver.findElement(By.partialLinkText("LESS IS MORE"));  //再次打开其他页面
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		String href = element.getAttribute("href");  //获取目标链接
		((JavascriptExecutor)driver).executeScript("window.open('"+href+"')");  //打开目标链接
		element.click();  //打开目标链接(另一种打开链接的方式)
		windowChange();  //调用windowChange()切换窗口
//		switchToWindow("new", driver);  //调用switchToWindow()切换窗口；在最后一个切换窗口的时候第一个参数不能空，否则会自动切换到第一个窗口
		System.out.println("当前窗口数量：" + driver.getWindowHandles().size());
		System.out.println("当前窗口标题：" + driver.getTitle());
		Thread.sleep(1000);
		
	}
	
	/**
	 * 封装切换窗口函数以方便调用
	 */
	public void windowChange() {
		String currentHandle = driver.getWindowHandle();  //获取当前窗口句柄
		Set<String> allHandle = driver.getWindowHandles();  //获取所有窗口句柄
		Iterator<String> it = allHandle.iterator();  //迭代allhandle里面的句柄
		while(it.hasNext()) {                        //用it.hasNext()判断时候有下一个窗口,如果有就切换到下一个窗口
			driver.switchTo().window(it.next());     //切换到新窗口
		}
	}
	
//	//封装切换窗口
//    public static boolean switchToWindow(String windowTitle, WebDriver driver) {
//    	boolean flag = false;
//		try {
//			//将页面上所有的windowshandle放在如set集合当中
//			String currentHandle = driver.getWindowHandle();
//			Set<String> handles = driver.getWindowHandles();
//			for(String s : handles) {
//				if(s.equals(currentHandle))
//					continue;
//				else {
//					driver.switchTo().window(s);
//					//和当前的窗口进行比较如果相同就切换到windowhandle  
//			        //判断title是否和handles当前的窗口相同
//					if(driver.getTitle().contains(windowTitle)) {
//						flag = true;
//						System.out.println("Switch to window successfully!");
//						break;
//					}else
//						continue;
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.printf("Window cound not found!", e.fillInStackTrace());
//			flag= false;
//		}
//		return flag;
//	}
}
