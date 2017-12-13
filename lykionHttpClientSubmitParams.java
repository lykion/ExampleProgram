package com.lykion.Appliction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 下面实例是网站安全检测接口调用示例
 * @author Uker
 *
 */
public class lykionHttpClientSubmitParams {
	
	public static void main(String[] args) {
//		String url = "http://apis.juhe.cn/webscan/?domain=juhe.cn&key=e8653d8956536b7ee9fdc538be7bb707";
//		String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js";
//		String url = "http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?scope=103&format=json&appid=379020&bk_key=关键字&bk_length=600";
		String url = "http://baike.baidu.com/api/openapi/BaikeLemmaCardApi";
		String str = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		/**
		 * 通过GET请求方式提交参数
		 */
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("scope", "103"));
		param.add(new BasicNameValuePair("format", "json"));
		param.add(new BasicNameValuePair("appid", "379020"));
		param.add(new BasicNameValuePair("bk_key", "马化腾"));
		param.add(new BasicNameValuePair("bk_length", "600"));
//		param.add(new BasicNameValuePair("key", "appkey"));
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(param, "UTF-8");  //拼接参数
			str = EntityUtils.toString(entity);  //将拼接好的参数通过EntityUtils.toString方法转换成String类型
			System.out.println("------------------------------------------------------");
			System.out.println(str);
			System.out.println("------------------------------------------------------");
			HttpGet get = new HttpGet(url + "?" + str);  //将参数拼接到目标url
			System.out.println("目标地址：" + get.getURI());  //打印目标地址
			System.out.println("------------------------------------------------------");
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity2 = response.getEntity();  //获取响应实体
			System.out.println(convert(EntityUtils.toString(entity2)));  //调用convert方法，将Unicode编码转换成中文输出
//			System.out.println("返回消息：" + EntityUtils.toString(entity2));  //将响应实体转换成soString再打印
//			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			/**
			 * 下面表示POST请求提交参数的方式
			 */
//			HttpPost post = new HttpPost(url);
//			post.setEntity(entity);
//			CloseableHttpResponse response = httpClient.execute(post)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//关闭连接，释放资源
				httpClient.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	/**
	 * 以下方法是将Unicode编码转换成中文
	 * @param utfString
	 * @return
	 */
	public static String convert(String utfString){
	    StringBuilder sb = new StringBuilder();
	    int i = -1;
	    int pos = 0;
	    while((i=utfString.indexOf("\\u", pos)) != -1){
	        sb.append(utfString.substring(pos, i));
	        if(i+5 < utfString.length()){  
	            pos = i+6;  
	            sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));  
	        }
	    }
	    return sb.toString();
	}
}
