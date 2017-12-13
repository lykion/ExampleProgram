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
 * ����ʵ������վ��ȫ���ӿڵ���ʾ��
 * @author Uker
 *
 */
public class lykionHttpClientSubmitParams {
	
	public static void main(String[] args) {
//		String url = "http://apis.juhe.cn/webscan/?domain=juhe.cn&key=e8653d8956536b7ee9fdc538be7bb707";
//		String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js";
//		String url = "http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?scope=103&format=json&appid=379020&bk_key=�ؼ���&bk_length=600";
		String url = "http://baike.baidu.com/api/openapi/BaikeLemmaCardApi";
		String str = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		/**
		 * ͨ��GET����ʽ�ύ����
		 */
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("scope", "103"));
		param.add(new BasicNameValuePair("format", "json"));
		param.add(new BasicNameValuePair("appid", "379020"));
		param.add(new BasicNameValuePair("bk_key", "����"));
		param.add(new BasicNameValuePair("bk_length", "600"));
//		param.add(new BasicNameValuePair("key", "appkey"));
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(param, "UTF-8");  //ƴ�Ӳ���
			str = EntityUtils.toString(entity);  //��ƴ�ӺõĲ���ͨ��EntityUtils.toString����ת����String����
			System.out.println("------------------------------------------------------");
			System.out.println(str);
			System.out.println("------------------------------------------------------");
			HttpGet get = new HttpGet(url + "?" + str);  //������ƴ�ӵ�Ŀ��url
			System.out.println("Ŀ���ַ��" + get.getURI());  //��ӡĿ���ַ
			System.out.println("------------------------------------------------------");
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity2 = response.getEntity();  //��ȡ��Ӧʵ��
			System.out.println(convert(EntityUtils.toString(entity2)));  //����convert��������Unicode����ת�����������
//			System.out.println("������Ϣ��" + EntityUtils.toString(entity2));  //����Ӧʵ��ת����soString�ٴ�ӡ
//			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			/**
			 * �����ʾPOST�����ύ�����ķ�ʽ
			 */
//			HttpPost post = new HttpPost(url);
//			post.setEntity(entity);
//			CloseableHttpResponse response = httpClient.execute(post)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//�ر����ӣ��ͷ���Դ
				httpClient.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	/**
	 * ���·����ǽ�Unicode����ת��������
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
