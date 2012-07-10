/**
 * Copyright (C) 2012 TookitForAndroid Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package chen.android.toolkit.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

/**
 * </br><b>name : </b>		HttpHelper
 * </br><b>description :</b>HTTP连接辅助
 * </br>@author : 			桥下一粒砂
 * </br><b>e-mail : </b>	chenyoca@gmail.com
 * </br><b>weibo : </b>		@桥下一粒砂
 * </br><b>date : </b>		2012-7-8 下午4:29:41
 *
 */
public class HttpConnection {
	
	private static final String USER_AGENT = "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;MIUI v4 Build.CHEN) "
            +"AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1";
	
	/**
	 * </br><b>title : </b>		创建一个HttpClient连接对象
	 * </br><b>description :</b>TODO
	 * </br><b>time :</b>		2012-7-10 下午10:49:35
	 * @param charset
	 * @return
	 */
	public static HttpClient createHttpClient(String charset){
		HttpParams params =new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params,charset);
        HttpProtocolParams.setUseExpectContinue(params, true);
        HttpProtocolParams.setUserAgent( params,USER_AGENT);
        ConnManagerParams.setTimeout(params, 1 * 1000);
        HttpConnectionParams.setConnectionTimeout(params, 2 * 1000);
        HttpConnectionParams.setSoTimeout(params, 4 * 1000);
        SchemeRegistry schReg =new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        // 使用线程安全的连接管理来创建HttpClient
        ClientConnectionManager conMgr =new ThreadSafeClientConnManager(params, schReg);
        return new DefaultHttpClient(conMgr, params);
	}
	
	/**
	 * </br><b>title : </b>		TODO
	 * </br><b>description :</b>TODO
	 * </br><b>time :</b>		2012-7-10 下午10:52:15
	 * @return
	 */
	public static synchronized HttpClient getHttpClient(){
		return createHttpClient(HTTP.UTF_8);
	}
	
	public static synchronized HttpClient getHttpClientWithGBK(){
		return createHttpClient("GBK");
	}

	/**
	 * </br><b>title : </b>		向服务器发送POST请求，并接收服务器输出流
	 * </br><b>description :</b>向服务器发送POST请求，并接收服务器输出流
	 * </br><b>time :</b>		2012-7-8 下午4:30:06
	 * @param url				POST请求URL
	 * @param params			POST请求URL
	 * @return					服务器响应流
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static InputStream post(String url,Map<String,String> params) throws MalformedURLException,IOException{  
        return post(url,params,null);
    }
	
	/**
	 * </br><b>title : </b>		向服务器发送POST请求，并接收服务器输出流
	 * </br><b>description :</b>向服务器发送POST请求，并接收服务器输出流
	 * </br><b>time :</b>		2012-7-8 下午4:33:20
	 * @param url				POST请求URL
	 * @param params			POST请求参数
	 * @param data				发送到服务器的位数组
	 * @return					服务器响应流
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static InputStream post(String url,Map<String,String> params,byte[] data) throws MalformedURLException, IOException{
		StringBuffer sb = new StringBuffer();  
        for(Map.Entry<String,String> entry:params.entrySet()){  
            sb.append(entry.getKey()).append("=").append(entry.getValue());  
            sb.append("&");  
        }  
        String param = sb.deleteCharAt(sb.length()-1).toString();  
        return connect("POST",new URL(url),param.getBytes(),data);
	}

	/**
	 * </br><b>title : </b>		向服务器发送POST请求，并接收服务器输出流
	 * </br><b>description :</b>向服务器发送POST请求，并接收服务器输出流
	 * </br><b>time :</b>		2012-7-8 下午4:34:08
	 * @param method			请求方法名
	 * @param url				POST请求URL
	 * @param datas				发送到服务器的位数组列表
	 * @return					服务器响应流
	 * @throws IOException
	 */
	private static InputStream connect(String method,URL url,byte[]... datas) throws IOException{
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod(method);
		conn.setUseCaches(false);
		conn.setInstanceFollowRedirects(true);
		conn.setConnectTimeout(6 * 1000);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Charset", "UTF-8");
		OutputStream outputStream = conn.getOutputStream();
		for (byte[] data : datas) {
			outputStream.write(data);
		}
		outputStream.close();
		return conn.getInputStream();
	}
	
	/**
	 * </br><b>title : </b>		向服务器发送GET请求，并接收服务器输出流
	 * </br><b>description :</b>向服务器发送GET请求，并接收服务器输出流
	 * </br><b>time :</b>		2012-7-8 下午4:35:23
	 * @param url				GET请求URL
	 * @param params			发送到服务器的参数
	 * @return					服务器响应流
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static InputStream get(String url,Map<String,String> params) throws MalformedURLException, IOException{
		StringBuffer sb = new StringBuffer();  
        for(Map.Entry<String,String> entry:params.entrySet()){  
            sb.append(entry.getKey()).append("=").append(entry.getValue());  
            sb.append("&");  
        }  
        String param = sb.deleteCharAt(sb.length()-1).toString();  
        return connect("GET",new URL(url),param.getBytes());
	}
	
	/**
	 * </br><b>title : </b>		向服务器发送GET请求，并接收服务器输出流
	 * </br><b>description :</b>向服务器发送GET请求，并接收服务器输出流
	 * </br><b>time :</b>		2012-7-8 下午4:35:58
	 * @param url				GET请求URL
	 * @return					服务器响应流
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static InputStream get(String url) throws MalformedURLException, IOException{
		return get(url,null);
	}
}