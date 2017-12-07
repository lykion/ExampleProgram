# -*- coding:utf-8 -*-

import os
import unittest
import time
from selenium import webdriver
from appium import webdriver
from lib2to3.tests.support import driver
from lib2to3.pgen2.driver import Driver
from appium.webdriver.common.touch_action import TouchAction

# 设置路径信息
PATH = lambda p: os.path.abspath(os.path.join(os.path.dirname(__file__)), p)

class ukerTestCase(unittest.TestCase):
    
    def setUp(self):
        desired_caps = {}
        desired_caps['platformName'] = 'Android'   #被测设备的系统
        desired_caps['platformVersion'] = '4.4.2'  #系统版本号
        desired_caps['deviceName'] = '7N2QXX144T017459'  #被测的设备
        desired_caps['automationName'] = 'Appium'  #自动化测试引擎  
        desired_caps['unicodeKeyboard'] = True  #允许输入中文
        desired_caps['resetKeyboard'] = True  #隐藏键盘
        
        desired_caps['appPackage'] = 'cn.sccl.ilife.android'  #appPackage
        desired_caps['appActivity'] = 'cn.sccl.ilife.android.uhealth.UhealthWelcomeActivity'  #appActivity
        
        self.driver = webdriver.Remote('http://127.0.0.1:4723/wd/hub', desired_caps)  #启动Appium
        time.sleep(5)
        print("启动成功了！^_^")
    
    def test_aLogin(self):
        """开始滑动引导页到登录界面"""
        x = self.driver.get_window_size()['width']  #获取手机的宽度
        y = self.driver.get_window_size()['height']  #获取手机的高度
        x1 = x * 0.8
        y = y * 0.5
        x2 = x * 0.125
        self.driver.swipe(x1, y, x2, y, 1000)  #通过坐标的变化实现屏幕滑动
        time.sleep(1)
        self.driver.swipe(x1, y, x2, y, 1000)  #使用几次swipe就表示滑动几次
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_welcome_bt').click()
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_login_phone_et').send_keys('17761260775')
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_login_pw_et').send_keys('aaaaaa')
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_login_btn').click()
        print('test_aLogin 测试成功')
        time.sleep(3)
        
    def test_bLoaction(self):
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_tv_city').click()
#        x = self.driver.get_window_size()['width']
#        y = self.driver.get_window_size()['height']
#        x1 = x * 0.5
#        y1 = y * 0.8
#        y2 = y * 0.3
#        self.driver.swipe(x1, y1, x1, y2, 1000)
#        time.sleep(1)
#        self.driver.swipe(x1, y1, x1, y2, 1000)
#        time.sleep(1)
#        self.driver.swipe(x1, y1, x1, y2, 1000)
#        time.sleep(1)
#        self.driver.find_element_by_name('四川省').click()
#        self.driver.find_element_by_name('南充市').click()
        self.driver.find_element_by_id('android:id/search_src_text').send_keys('四川省')
        city = self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_lv_city')
        city.find_element_by_name('四川省').click()
        city.find_element_by_name('绵阳市').click()
		print('test_bLoaction 测试成功')
        time.sleep(3)
        
    def test_cKabao(self):
	    '''
		为了适应不同屏幕的滑动解锁，实现思路：
		 * 1.获取九宫格patterview的起始坐标x、y
		 * 2.获取九宫格patterview的宽度(width)、高度(height)
		 * 3.九宫格的九个格子大约平均把patterview的长、宽平均分成了4个等分，宽度间隔为width/4、高度间隔为height/4
		 * 4.一个格子的坐标为（x+width/4, y+height/4）
		 * 5.使用TouchAction、moveTo方法实现滑动即可
		'''
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_tv_card_package').click()
        time.sleep(1)
        patterview = self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_card_pw_patterview')  #获取九宫格控件
        x = patterview.location.get('x')  #起始坐标x
        y = patterview.location.get('y')  #起始坐标y
        
        width = patterview.size.get('width')  #控件宽度
        height = patterview.size.get('height')  #控件高度
#         print(x, y, width, height)
        xstep = width/4  #宽度间隔
        ystep = height/4  #高度间隔
        
		#九宫格第一个格子的坐标
        xStartPoint = x + xstep
        yStartPoint = y + ystep
        
        TouchAction(self.driver).press(x=xStartPoint, y=yStartPoint).wait(1000).move_to(x=0, y=ystep).wait(1000).move_to(x=0, y=ystep).wait(1000).move_to(x=xstep, y=0).release().perform()
        time.sleep(5)
        
        RelativeLayout = self.driver.find_element_by_class_name('android.widget.RelativeLayout')
        RelativeLayout.find_element_by_id('cn.sccl.ilife.android:id/uhealth_base_iv_back').click()
#         self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_base_iv_back')
        print('test_cKabao 测试成功')
        time.sleep(3)
        
    def test_dTittle(self):
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_tv_card').click()
        time.sleep(5)
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_tv_record').click()
        self.driver.find_element_by_name('挂号就医').click()
        self.driver.find_element_by_name('体检').click()
        self.driver.find_element_by_name('运动').click()
        print('test_dTittle 测试成功')
        time.sleep(3)
        
	def test_ehelp(self):
	    self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_tv_me').click()
#         self.driver.find_element_by_name('我的').click()
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_me_help_rl').click()
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_help_text').send_keys('巴黎洛杉矶获2024/28奥运举andy Jerry办权这次申奥特别在哪？')
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_help_time').click()
        datePicker = self.driver.find_element_by_id('cn.sccl.ilife.android:id/dateAndTimePicker_datePicker')  #获取年月日的控件
        timePicker = self.driver.find_element_by_id('cn.sccl.ilife.android:id/dateAndTimePicker_timePicker')  #获取时分秒的控件
        
        '''获取年月日滑动点的坐标'''
        xDateStartPoint = datePicker.location.get('x')  #起始x坐标
        yDateStartPoint = datePicker.location.get('y')  #起始y坐标
        xwidthDate = datePicker.size.get('width')  #控件的宽度
        yheightDate = datePicker.size.get('height')  #控件的高度
        
        '''计算需要滑动的点的坐标'''
        x1DateEndPoint = (xDateStartPoint + xwidthDate*1/4)
        y1DateEndPoint = (yDateStartPoint + yheightDate*1/3)
        
        x2DateEndPoint = (xDateStartPoint + xwidthDate*2/4)
        y2DateEndPoint = (yDateStartPoint + yheightDate*2/3)
        
        x3DateEndPoint = (xDateStartPoint + xwidthDate*3/4)
#         y3DateEndPoint = (yDateStartPoint + yheightDate*3*4)
        
        '''获取小时、分钟滑动点的坐标'''
        xTimeStartPoint = timePicker.location.get('x')  #起始x坐标
        yTimeStartPoint = timePicker.location.get('y')  #起始y坐标
        xwidthTime = timePicker.size.get('width')  #控件的宽度
        yheightTime = timePicker.size.get('height')  #控件的高度
        
        '''计算需要滑动的点的坐标'''
        x1TimeEndPoint = (xTimeStartPoint + xwidthTime*1/3)
        y1TimeEndPoint = (yTimeStartPoint + yheightTime*1/3)
        
        x2TimeEndPoint = (xTimeStartPoint + xwidthTime*2/3)
        y2TimeEndPoint = (yTimeStartPoint + yheightTime*2/3)
        
        '''滑动选择年、月、日'''
        self.driver.swipe(x1DateEndPoint, y1DateEndPoint, x1DateEndPoint, y2DateEndPoint, 1000)
        time.sleep(1)
        self.driver.swipe(x1DateEndPoint, y1DateEndPoint, x1DateEndPoint, y2DateEndPoint, 1000)
        time.sleep(1)
        self.driver.swipe(x2DateEndPoint, y1DateEndPoint, x2DateEndPoint, y2DateEndPoint, 1000)
        time.sleep(1)
        self.driver.swipe(x2DateEndPoint, y1DateEndPoint, x2DateEndPoint, y2DateEndPoint, 1000)
        time.sleep(1)
        self.driver.swipe(x3DateEndPoint, y1DateEndPoint, x3DateEndPoint, y2DateEndPoint, 1000)
        time.sleep(1)
        self.driver.swipe(x3DateEndPoint, y1DateEndPoint, x3DateEndPoint, y2DateEndPoint, 1000)
        time.sleep(1)
        
        '''滑动选择小时、分钟'''
        self.driver.swipe(x1TimeEndPoint, y1TimeEndPoint, x1TimeEndPoint, y2TimeEndPoint, 1000)
        time.sleep(1)
        self.driver.swipe(x1TimeEndPoint, y1TimeEndPoint, x1TimeEndPoint, y2TimeEndPoint, 1000)
        time.sleep(1)
        self.driver.swipe(x2TimeEndPoint, y1TimeEndPoint, x2TimeEndPoint, y2TimeEndPoint, 1000)
        time.sleep(1)
        self.driver.swipe(x2TimeEndPoint, y1TimeEndPoint, x2TimeEndPoint, y2TimeEndPoint, 1000)
        time.sleep(1)
        self.driver.find_element_by_id('android:id/button1').click()
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_help_btn').click()
        print('test_eHelp 测试成功')
        time.sleep(3)
		
    def test_fInformationEdit(self):
#        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_tv_me').click()
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_me_info_rl').click()
        
        #编辑头像
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_center_info_head_iv').click()
        self.driver.find_element_by_name('从相册选择图片').click()
        self.driver.find_element_by_id('com.android.documentsui:id/icon_thumb').click()
        time.sleep(5)
        
        #编辑姓名
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_info_name').click()
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_de_iv').click()
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_old_info').send_keys('Jerry')
        self.driver.find_element_by_name('保存').click()
        time.sleep(3)
        
        #编辑性别
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_info_xb').click()
        self.driver.find_element_by_name('男').click()
        self.driver.find_element_by_name('保存').click()
        time.sleep(3)
        
        #编辑地址
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_info_address').click()
        cityDialog = self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_lv_city')  #获取元素所在的控件
        cityDialog.find_element_by_name('河北省').click()  #通过控件去定位元素并执行click
        cityDialog.find_element_by_name('邯郸市').click()  #通过控件去定位元素并执行click
        self.driver.find_element_by_name('保存').click()
        time.sleep(3)
        
        #编辑年龄
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_info_nl').click()
		
		'''
		以下做法适用于不同屏幕，不需要根据屏幕大小不同重新计算坐标
		 * 1.获取控件的起始坐标x、y
		 * 2.获取控件的宽度、高度
		 * 3.计算控件的中间坐标点
		 * 4.使用swipe方法再控件内滑动
		'''
		
        dialog = self.driver.find_element_by_id('cn.sccl.ilife.android:id/list_select_dialog_wv')  #获取控件
        xStartPoint = dialog.location.get('x')  #起始坐标x
        yStartPoint = dialog.location.get('y')  #起始坐标y

		width = dialog.size.get('width')  #控件宽度
        height = dialog.size.get('height')  #控件高度
        
        xEndPoint = xStartPoint + width  #控件终止坐标x
        yEndPoint = yStartPoint + height  #控件终止坐标y
        
        x = (xStartPoint + xEndPoint)/2  #控件的中间点坐标x
        y = (yStartPoint + yEndPoint)/2  #控件的中间点坐标y
        
        self.driver.swipe(x, y*0.8, x, y*0.4, 1000)
        time.sleep(1)
        self.driver.find_element_by_name('保存').click()
        time.sleep(3)
        
        #编辑身高
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_info_sg').click()
        time.sleep(1)
        self.driver.swipe(x, y*0.8, x, y*0.4, 1000)  #因为该空间的属性与年龄中的控件属性完全相同，因此可以直接调用；以下类似
        time.sleep(1)
        self.driver.find_element_by_name('保存').click()
        time.sleep(3)
        
        #编辑体重
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_info_weight').click()
        time.sleep(1)
        self.driver.swipe(x, y*0.8, x, y*0.4, 1000)
        time.sleep(1)
        self.driver.find_element_by_name('保存').click()
        time.sleep(3)
        
        #编辑星座
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_info_xz').click()
        time.sleep(1)
        self.driver.swipe(x, y*0.8, x, y*0.4, 1000)
        time.sleep(1)
        self.driver.find_element_by_name('保存').click()
        time.sleep(3)
        
        #编辑血型
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_info_xx').click()
        time.sleep(1)
        self.driver.swipe(x, y*0.8, x, y*0.4, 1000)
        time.sleep(1)
        self.driver.find_element_by_name('保存').click()
        time.sleep(3)
        
        x = self.driver.get_window_size()['width']
        y = self.driver.get_window_size()['height']
        self.driver.swipe(x/2, y*0.8, x/2, y*0.4, 1000)
        
        #编辑职业
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_info_zy').click()
        cityDialog.find_element_by_name('IT互联网').click()
        cityDialog.find_element_by_name('开发工程师').click()
        self.driver.find_element_by_name('保存').click()
        time.sleep(3)
        
        #编辑QQ号码
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_info_qq').click()
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_de_iv').click()
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_old_info').send_keys('546523124')
        self.driver.find_element_by_name('保存').click()
        time.sleep(3)
        
        #编辑微信号
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_info_wx').click()
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_de_iv').click()
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_old_info').send_keys('午后的太阳')
        self.driver.find_element_by_name('保存').click()
        time.sleep(3)
        
        #编辑兴趣爱好
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_personal_center_info_ah').click()
        self.driver.find_element_by_name('台球').click()
        self.driver.find_element_by_name('游泳').click()
        self.driver.find_element_by_name('国内游').click()
        self.driver.find_element_by_name('轮滑').click()
        self.driver.find_element_by_name('排球').click()
        self.driver.find_element_by_name('兵乓球').click()
        self.driver.find_element_by_name('爬山').click()
        self.driver.find_element_by_name('沙发客').click()
        self.driver.find_element_by_name('保存').click()
        time.sleep(3)
        #返回个人中心
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_base_iv_back').click()
        self.driver.find_element_by_name('设置').click()
        self.driver.find_element_by_name('关于Uker健康').click()
        self.driver.find_element_by_name('版本说明').click()
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_base_iv_back').click()
        self.driver.find_element_by_name('服务协议').click()
        time.sleep(1)
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_base_iv_back').click()
        self.driver.find_element_by_id('cn.sccl.ilife.android:id/uhealth_base_iv_back').click()
        #退出登录
        self.driver.find_element_by_name('退出当前账号').click()
        
        print('test_fInformationEdit 测试成功')
        time.sleep(3)
        
    def tearDown(self):
        self.driver.quit()  #测试完毕后，退出Appium    
        
if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(ukerTestCase)
    unittest.TextTestRunner(verbosity=2).run(suite)
