package ilife;

import org.testng.annotations.Test;
import java.io.File;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;

import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class ilifeTestCase {

	public AndroidDriver<WebElement> driver;
	private boolean isInstall = false;
	
	@BeforeSuite
	public void setUp() throws  Exception{
		/***
		 * 初始化
		 */
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");  //测试平台
		capabilities.setCapability("platformVersion", "4.4.2");  //版本
		capabilities.setCapability("deviceName", "7N2QXX144T017459");  //设备名称
		capabilities.setCapability("automationName", "Appium");  //automationName
//		capabilities.setCapability("noRest", true);  //不需要重新再次安装app
		capabilities.setCapability("noSign", true);  //安装时不对apk进行重签名，设置很有必要，否则有的apk在重签名之后无法正常使用
		capabilities.setCapability("unicodeKeyboard", true);  //允许输入中文
		capabilities.setCapability("resetKeyboard", true);  //隐藏键盘
		
		//启动APP
		capabilities.setCapability("appPackage", "cn.sccl.ilife.android");
		capabilities.setCapability("appActivity", "cn.sccl.ilife.android.uhealth.UhealthWelcomeActivity");
		
		//启动appium
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Thread.sleep(5000);
		
		/***
		 * 判断设备是否已经安装APP
		 */
		if(isInstall) {
			//set up appium设置apk路径
			File classpathRoot = new File(System.getProperty("usr.dir"));
			File appDir = new File(classpathRoot, "apps");
			File app = new File(appDir, "app-debug20170731(testplatform).apk");
			
			capabilities.setCapability("app", app.getAbsolutePath());  //设置apk路径
		}
	}
	
	@Test
	/***
	 * 登录
	 * @throws Exception
	 */
	public void a_ilifeLogin() throws Exception{
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_welcome_skip").click();  //跳过引导页直接到登录界面
		/***
		 * 滑动引导页到登录界面
		 */
		int width = driver.manage().window().getSize().width;  //获取屏幕的宽度
		int height = driver.manage().window().getSize().height;  //获取屏幕的高度
		driver.swipe(width*7/8, height/2, width/8, height/2, 1000);  //通过坐标的变化实现屏幕滑动
		Thread.sleep(1000);
		driver.swipe(width*7/8, height/2, width/8, height/2, 1000);  //使用几次swipe就表示滑动几次
		Thread.sleep(1000);
		/***
		 * 用户名、密码登录
		 */
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_welcome_bt").click();
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_login_phone_et").sendKeys("17761260775");
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_login_pw_et").sendKeys("aaaaaa");
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_login_btn").click();
//		Thread.sleep(3000);
//		driver.findElementById("android:id/button2").click();
		/***
		 * QQ登录
		 */
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_welcome_bt").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_login_qq").click();
//		Thread.sleep(5000);
//		driver.findElementByName("登录").click();
//		Thread.sleep(5000);
//		driver.findElementById("android:id/button2").click();

		/***
		 *功能：等待界面出现出现指定元素后才执行后面的操作
		 *WebDriverWait(driver, 10) ---10 表示超时时间为10s（此处单位：秒）
		 */
		WebDriverWait wait = new WebDriverWait(driver, 10); //设置超时时间
		WebElement e = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("确定更新吗?")); //指定出现的元素
			}
		});
		
		driver.findElementByName("取消").click();
	}
	
	@Test
	/***
	 * 修改个人信息
	 * @throws Exception
	 */
	public void z_EditInformation() throws Exception{
		driver.findElementByName("我的").click();
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_me_info_rl").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_me_info_rl").click();
//		Thread.sleep(1000);
		
		//修改头像
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_center_info_head_iv").click();
		driver.findElementByName("从相册选择图片").click();
		driver.findElementById("com.android.documentsui:id/icon_thumb").click();
//		driver.findElementByName("拍照").click();
//		Thread.sleep(2000);
//		driver.findElementById("com.huawei.camera:id/shutter_button").click();
//		Thread.sleep(2000);
//		driver.findElementById("com.huawei.camera:id/btn_done").click();
		//取消头像修改
//		driver.findElementByName("取消").click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement e = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("体重"));
			}
		});
		
		//修改姓名
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_info_name").click();
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_de_iv").click();
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_old_info").sendKeys("Jerry");
		driver.findElementByName("保存").click();
		WebElement f = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("体重"));
			}
		});
		
		//修改性别
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_info_xb").click();
//		driver.findElementByName("男").click();
		driver.findElementByName("女").click();
		driver.findElementByName("保存").click();
		WebElement g = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("体重"));
			}
		});
		
		//修改地址
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_info_address").click();
		
		/***
		 *分层定位元素：
		 *1.先获取需要定位元素的控件
		 *2.通过控件去定位元素
		 */
		 
//		MobileElement shengf_1 = (MobileElement) driver.findElementByAndroidUIAutomator("resourceId(\"cn.sccl.ilife.android:id/uhealth_lv_city\")");  //获取需要定位元素的控件
//		MobileElement shengf_2 = (MobileElement) ((FindsByAndroidUIAutomator<WebElement>) shengf_1).findElementByAndroidUIAutomator("text(\"河北省\")");  //通过控件去定位元素
//		shengf_2.click();
//		MobileElement city_1 = (MobileElement) driver.findElementByAndroidUIAutomator("resourceId(\"cn.sccl.ilife.android:id/uhealth_lv_city\")");
//		MobileElement city_2 = (MobileElement) ((FindsByAndroidUIAutomator<WebElement>) city_1).findElementByAndroidUIAutomator("text(\"石家庄市\")");
//		city_2.click();
        /***
		 *另一种简单的实现写法，原理是一样
		 */
		MobileElement city = (MobileElement) driver.findElementById("cn.sccl.ilife.android:id/uhealth_lv_city");
		city.findElementByName("河北省").click();
		city.findElementByName("石家庄市").click();
		driver.findElementByName("保存").click();
		
		WebElement s = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("体重"));
			}
		});
		
		//修改年龄   bounds---[220,480][500,786]
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_info_nl").click();
		/***
		 * 通过坐标点的变化来实现在控件内滑动
		 * 缺陷该方法布局有普遍性，应为手机屏幕的大小各不相同，需要重新计算坐标点
		 */
//		Thread.sleep(1000);
//		driver.swipe(360, 780, 360, 450, 1000);
		
		/***
		 * 以下做法适用于不同屏幕，不需要根据屏幕大小不同重新计算坐标
		 * 1.获取控件的起始坐标x、y
		 * 2.获取控件的宽度、高度
		 * 3.计算控件的中间坐标点
		 * 4.使用swipe方法再控件内滑动
		 */
		WebElement ageSelectDialog = driver.findElement(By.id("cn.sccl.ilife.android:id/list_select_dialog_wv"));
		//计算控件中间点坐标的x坐标
		int xStartPoint = ageSelectDialog.getLocation().getX();  //x的起始坐标
		int xEndPoint = xStartPoint + ageSelectDialog.getSize().width;  //x的结束坐标 ,ageDialog.getSize().width 获取控件的宽度
		int x = (xStartPoint+xEndPoint)/2;  //控件中间坐标点的x坐标
		
		//计算控件中间点坐标的y坐标
		int yStartPoint = ageSelectDialog.getLocation().getY();  //y的起始坐标
		int yEndPoint = yStartPoint + ageSelectDialog.getSize().height;  //y的结束坐标 ,ageDialog.getSize().height 获取控件的高度
		int y = (yStartPoint+yEndPoint)/2;  //控件中间坐标点的y坐标
		
		driver.swipe(x, y*7/8, x, y/2, 1000);
		
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_select_ok").click();
//		driver.findElementByName("保存").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_select_cancel").click();
//		driver.findElementByName("取消").click();
		WebElement h = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("体重"));
			}
		});
		
		//修改身高
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_info_sg").click();
		int xsgStartPoint = ageSelectDialog.getLocation().getX();  //此处使用ageSelectDialog，是因为选择框的属性是相同，以下同理
		int xsgEndPoint = xsgStartPoint + ageSelectDialog.getSize().width;
		int xsg = (xsgStartPoint+xsgEndPoint)/2;
		
		int ysgStartPoint = ageSelectDialog.getLocation().getY();
		int ysgEndPoint = ysgStartPoint + ageSelectDialog.getSize().getHeight();
		int ysg = (ysgStartPoint+ysgEndPoint)/2;
		
		driver.swipe(xsg, ysg*7/8, xsg, ysg/2, 1000);
		
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_select_ok").click();
//		driver.findElementByName("保存").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_select_cancel").click();
//		driver.findElementByName("取消").click();
		WebElement i = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("体重"));
			}
		});
		
		//修改体重
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_info_weight").click();
		int xtzStartPoint = ageSelectDialog.getLocation().getX();
		int xtzEndPoint = xtzStartPoint + ageSelectDialog.getSize().getWidth();
		int xtz = (xtzStartPoint+xtzEndPoint)/2;
		
		int ytzStartPoint = ageSelectDialog.getLocation().getY();
		int ytzEndPoint = ytzStartPoint + ageSelectDialog.getSize().getHeight();
		int ytz = (ytzStartPoint+ytzEndPoint)/2;
		
		driver.swipe(xtz, ytz*7/8, xtz, ytz/2, 1000);
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_select_ok").click();
//		driver.findElementByName("保存").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_select_cancel").click();
//		driver.findElementByName("取消").click();
		WebElement j = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("体重"));
			}
		});
		
		//星座
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_info_xz").click();
		int xxzStartPoint = ageSelectDialog.getLocation().getX();
		int xxzEndPoint = xxzStartPoint + ageSelectDialog.getSize().getWidth();
		int xxz = (xxzStartPoint+xxzEndPoint)/2;
		
		int yxzStartPoint = ageSelectDialog.getLocation().getY();
		int yxzEndPoint = yxzStartPoint + ageSelectDialog.getSize().getHeight();
		int yxz = (yxzStartPoint+yxzEndPoint)/2;
		
		driver.swipe(xxz, yxz*7/8, xxz, yxz/2, 1000);
		
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_select_ok").click();
//		driver.findElementByName("保存").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_select_cancel").click();
//		driver.findElementByName("取消").click();
		WebElement k = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("体重"));
			}
		});
		
		//修改血型
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_info_xx").click();
		int xxxStartPoint = ageSelectDialog.getLocation().getX();
		int xxxEndPoint = xxxStartPoint + ageSelectDialog.getSize().getWidth();
		int xxx = (xxxStartPoint+xxxEndPoint)/2;
		
		int yxxStartPoint = ageSelectDialog.getLocation().getY();
		int yxxEndPoint = yxxStartPoint + ageSelectDialog.getSize().getHeight();
		int yxx = (yxxStartPoint+yxxEndPoint)/2;
		
		driver.swipe(xxx, yxx*7/8, xxx, yxx/2, 1000);
		
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_select_ok").click();
//		driver.findElementByName("保存").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_select_cancel").click();
//		driver.findElementByName("取消").click();
		WebElement l = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("体重"));
			}
		});
		
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width/2, height*7/8, width/2, height/8, 1000);
		
		//修改职业   cn.sccl.ilife.android:id/uhealth_lv_city
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_info_zy").click();
//		MobileElement hangye_1 = (MobileElement) driver.findElementByAndroidUIAutomator("resourceId(\"cn.sccl.ilife.android:id/uhealth_lv_city\")");
//		MobileElement hangye_2 = (MobileElement) ((FindsByAndroidUIAutomator<WebElement>) hangye_1).findElementByAndroidUIAutomator("text(\"IT互联网\")");
//		hangye_2.click();
//		MobileElement zhiye_1 = (MobileElement) driver.findElementByAndroidUIAutomator("resourceId(\"cn.sccl.ilife.android:id/uhealth_lv_city\")");
//		MobileElement zhiye_2 = (MobileElement) ((FindsByAndroidUIAutomator<WebElement>) zhiye_1).findElementByAndroidUIAutomator("text(\"测试工程师\")");
//		zhiye_2.click();
		MobileElement zy = (MobileElement) driver.findElementById("cn.sccl.ilife.android:id/uhealth_lv_city");
		zy.findElementByName("IT互联网").click();
		zy.findElementByName("测试工程师").click();
		driver.findElementByName("保存").click();
		
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_base_tv_right").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_base_iv_back").click();
		WebElement m = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("体重"));
			}
		});
		
		//修改QQ
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_info_qq").click();
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_de_iv").click();
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_old_info").sendKeys("313371005");
		driver.findElementByName("保存").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_base_tv_right").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_base_iv_back").click();
		WebElement n = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("体重"));
			}
		});
		
		//修改微信
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_info_wx").click();
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_de_iv").click();
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_old_info").sendKeys("温暖的港湾");
		driver.findElementByName("保存").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_base_tv_right").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_base_iv_back").click();
		WebElement o = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("体重"));
			}
		});
		
		//修改兴趣爱好  
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_info_ah").click();
		driver.findElementByName("台球").click();
		driver.findElementByName("游泳").click();
		driver.findElementByName("国内游").click();
		driver.findElementByName("轮滑").click();
		driver.findElementByName("排球").click();
		driver.findElementByName("兵乓球").click();
		driver.findElementByName("爬山").click();
		driver.findElementByName("沙发客").click();
		driver.findElementByName("保存").click();
		WebElement p = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("体重"));
			}
		});
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_base_tv_right").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_base_iv_back").click();
		//返回个人中心
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_base_iv_back").click();
		
		driver.findElementByName("设置").click();
//		//修改密码
//		driver.findElementByName("修改密码").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_old_pw").sendKeys("123456");
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_new_pw").sendKeys("111111");
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_new_pw_2").sendKeys("111111");
//		driver.findElementByName("保存").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_base_tv_right").click();
//		driver.findElementById("cn.sccl.ilife.android:id/uhealth_base_iv_back").click();
		//关于uker健康
		driver.findElementByName("关于Uker健康").click();
		driver.findElementByName("版本说明").click();
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_base_iv_back").click();
		driver.findElementByName("服务协议").click();
		Thread.sleep(2000);
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_base_iv_back").click();
		
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_base_iv_back").click();
		
		//退出登录
		driver.findElementByName("退出当前账号").click();
	}
	
	@Test
	/***
	 * 定位
	 * @param el 
	 * @throws Exception
	 */
	public void b_location() throws Exception{
		
//		driver.findElementByName("未知").click();
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_tv_city").click();
//		/***
//		 * 滑动选择省、市
//		 */
//		int width = driver.manage().window().getSize().width;
//		int height = driver.manage().window().getSize().height;
//		driver.swipe(width/2, height*3/4, width/2, height/4, 1000);
//		Thread.sleep(1000);
//		driver.swipe(width/2, height*3/4, width/2, height/4, 1000);
//		Thread.sleep(1000);
//		driver.swipe(width/2, height*3/4, width/2, height/4, 1000);
//		Thread.sleep(1000);
//		driver.findElementByName("四川省").click();
//		driver.findElementByName("绵阳市").click();
		
		//通过搜索省来定位
		driver.findElementById("android:id/search_src_text").sendKeys("四川省");
//		MobileElement lv_city = (MobileElement) driver.findElementByAndroidUIAutomator("resourceId(\"cn.sccl.ilife.android:id/uhealth_lv_city\")");
//		MobileElement shengF = (MobileElement) ((FindsByAndroidUIAutomator<WebElement>) lv_city).findElementByAndroidUIAutomator("text(\"四川省\")");
//		shengF.click();
//		MobileElement shi = (MobileElement) ((FindsByAndroidUIAutomator<WebElement>) lv_city).findElementByAndroidUIAutomator("text(\"攀枝花市\")");
//		shi.click();
		MobileElement location = (MobileElement) driver.findElementById("cn.sccl.ilife.android:id/uhealth_lv_city");
		location.driver.findElementByName("四川省").click();
		location.driver.findElementByName("攀枝花市").click();
	}
	
	@Test
	/***
	 * 卡包滑动解锁
	 * resourceId---cn.sccl.ilife.android:id/uhealth_card_pw_patterview
	 * @throws Exception
	 */
	public void c_slideUnlock() throws Exception{
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_tv_card_package").click();
		Thread.sleep(1000);
		/***
		 * 为了适应不同屏幕的滑动解锁，实现思路：
		 * 1.获取九宫格patterview的起始坐标x、y
		 * 2.获取九宫格patterview的宽度(width)、高度(height)
		 * 3.九宫格的九个格子大约平均把patterview的长、宽平均分成了4个等分，宽度间隔为width/4、高度间隔为height/4
		 * 4.第一个格子的坐标为（x+width/4, y+height/4）
		 * 5.使用TouchAction、moveTo方法实现滑动即可
		 */
		WebElement patterview = driver.findElementById("cn.sccl.ilife.android:id/uhealth_card_pw_patterview");
		//获取控件起始坐标的x、y
		int xStartPoint = patterview.getLocation().getX();
		int yStartPoint = patterview.getLocation().getY();
		//获取控件的高度、宽度
		int width = patterview.getSize().getWidth();
		int height = patterview.getSize().getHeight();
		//宽度、高度之间的间隔
		int xStep = width/4;
		int yStep = height/4;
		//patterview起始坐标的x、y；第一个格子与控件起始坐标相差一个间隔
		int x = xStartPoint + xStep;  
		int y = yStartPoint + yStep;
		
		TouchAction slide = new TouchAction(driver).press(x, y).waitAction(500).moveTo(0, yStep).waitAction(500).moveTo(0, yStep).waitAction(500)
				.moveTo(xStep, 0).release();
		slide.perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement e = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.name("绑定卡"));
			}
		});
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_base_iv_back").click();
	}
	
	@Test
	/***
	 * 查看健康卡内容
	 * @throws Exception
	 */
	public void d_JianKangKa() throws Exception{
		driver.findElementByName("健康卡").click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement e = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(By.className("android.widget.ListView"));
			}
		});
	}
	
	@Test
	/***
	 * 查看健康记录
	 * @throws Exception
	 */
	public void e_JianKangJiLu() throws Exception{
		driver.findElementByName("健康记录").click();
		Thread.sleep(1000);
//		driver.findElementByName("全部").click();
//		Thread.sleep(1000);
		driver.findElementByName("挂号就医").click();
		Thread.sleep(1000);
		driver.findElementByName("体检").click();
		Thread.sleep(1000);
		driver.findElementByName("运动").click();
		Thread.sleep(1000);
	}
	
	public void f_help() throws Exception{
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_tv_me").click();
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_me_help_rl").click();
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_help_text").sendKeys("近日，国家发展改革委、国家能源局、财政部等十五部门联合印发了"
				+ "《关于扩大生物燃料乙醇生产和推广使用车用乙醇汽油的实施方案》。");
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_help_time").click();
		MobileElement datePicker = (MobileElement) driver.findElementById("cn.sccl.ilife.android:id/dateAndTimePicker_datePicker");  //获取年月日的控件
		MobileElement timePicker = (MobileElement) driver.findElementById("cn.sccl.ilife.android:id/dateAndTimePicker_timePicker");  //获取小时分钟的控件
		int xDateStartPoint = datePicker.getLocation().getX();
		int yDateStartPoint = datePicker.getLocation().getY();
		int widhtDate = datePicker.getSize().width;
		int heightDate = datePicker.getSize().height;
		//计算年月日滑动的坐标
		int x1DateEndPoint = (xDateStartPoint + widhtDate*1/4);
		int y1DateEndPoint = (yDateStartPoint + heightDate*1/4);
		
		int x2DateEndPoint = (xDateStartPoint + widhtDate*2/4);
		int y2DateEndPoint = (yDateStartPoint + heightDate*2/4);
		
		int x3DateEndPoint = (xDateStartPoint + widhtDate*3/4);
		int y3DateEndPoint = (yDateStartPoint + heightDate*3/4);
		
		int xTimeStartPoint = timePicker.getLocation().getX();
		int yTimeStartPoint = timePicker.getLocation().getY();
		int widthTime = timePicker.getSize().width;
		int heightTime = timePicker.getSize().height;
		//计算小时分钟滑动的坐标
		int x1TimeEndPoint = (xTimeStartPoint + widthTime*1/3);
		int y1TimeEndPoint = (yTimeStartPoint + heightTime*1/3);
		
		int x2TimeEndPoint = (xTimeStartPoint + widthTime*2/3);
		int y2TimeEndPoint = (yTimeStartPoint + heightTime*2/3);
		
		driver.swipe(x1DateEndPoint, y1DateEndPoint, x1DateEndPoint, y3DateEndPoint, 1000);  //选择年
		Thread.sleep(1000);
		driver.swipe(x2DateEndPoint, y1DateEndPoint, x2DateEndPoint, y3DateEndPoint, 1000);  //选择月
		Thread.sleep(1000);
		driver.swipe(x3DateEndPoint, y1DateEndPoint, x3DateEndPoint, y3DateEndPoint, 1000);  //选择日
		Thread.sleep(1000);
		
		driver.swipe(x1TimeEndPoint, y1TimeEndPoint, x1TimeEndPoint, y2TimeEndPoint, 1000);  //选择小时
		Thread.sleep(1000);
		driver.swipe(x2TimeEndPoint, y1TimeEndPoint, x2TimeEndPoint, y2TimeEndPoint, 1000);  //选择分钟
		Thread.sleep(1000);
		
		driver.findElementById("android:id/button1").click();
		Thread.sleep(1000);
		driver.findElementById("cn.sccl.ilife.android:id/uhealth_personal_center_help_btn").click();
		Thread.sleep(1000);
	}
	
	@AfterSuite
	public void tearDown() throws Exception{
		driver.quit();
	}
}
