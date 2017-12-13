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
		String currentHandle = driver.getWindowHandle();  //��ȡ��ǰ���ڵľ��
		System.out.println("��ǰ����������" + driver.getWindowHandles().size());
		Thread.sleep(1000);
	}
	@AfterClass
	public void tearDown() throws Exception{
		
	}
	@Test
	public void testCase() throws Exception{
		driver.findElement(By.xpath("/html/body/form/input")).click();  //���µĴ���
		windowChange();  //����windowChange()�л�����
//		switchToWindow("", driver);  //����switchToWindow()�л�����
		System.out.println("��ǰ����������" + driver.getWindowHandles().size());
		System.out.println("��ǰ���ڱ��⣺" + driver.getTitle());
		
		WebElement element = driver.findElement(By.partialLinkText("LESS IS MORE"));  //�ٴδ�����ҳ��
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		Thread.sleep(1000);
		String href = element.getAttribute("href");  //��ȡĿ������
		((JavascriptExecutor)driver).executeScript("window.open('"+href+"')");  //��Ŀ������
		element.click();  //��Ŀ������(��һ�ִ����ӵķ�ʽ)
		windowChange();  //����windowChange()�л�����
//		switchToWindow("new", driver);  //����switchToWindow()�л����ڣ������һ���л����ڵ�ʱ���һ���������ܿգ�������Զ��л�����һ������
		System.out.println("��ǰ����������" + driver.getWindowHandles().size());
		System.out.println("��ǰ���ڱ��⣺" + driver.getTitle());
		Thread.sleep(1000);
		
	}
	
	/**
	 * ��װ�л����ں����Է������
	 */
	public void windowChange() {
		String currentHandle = driver.getWindowHandle();  //��ȡ��ǰ���ھ��
		Set<String> allHandle = driver.getWindowHandles();  //��ȡ���д��ھ��
		Iterator<String> it = allHandle.iterator();  //����allhandle����ľ��
		while(it.hasNext()) {                        //��it.hasNext()�ж�ʱ������һ������,����о��л�����һ������
			driver.switchTo().window(it.next());     //�л����´���
		}
	}
	
//	//��װ�л�����
//    public static boolean switchToWindow(String windowTitle, WebDriver driver) {
//    	boolean flag = false;
//		try {
//			//��ҳ�������е�windowshandle������set���ϵ���
//			String currentHandle = driver.getWindowHandle();
//			Set<String> handles = driver.getWindowHandles();
//			for(String s : handles) {
//				if(s.equals(currentHandle))
//					continue;
//				else {
//					driver.switchTo().window(s);
//					//�͵�ǰ�Ĵ��ڽ��бȽ������ͬ���л���windowhandle  
//			        //�ж�title�Ƿ��handles��ǰ�Ĵ�����ͬ
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
