/**
 * Copyright (C) 2012 ToolkitForAndroid Project
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

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

/**
 * </br><b>name : </b>		NetworkUtil
 * </br><b>description :</b>网络检测
 * </br>@author : 			桥下一粒砂
 * </br><b>e-mail : </b>	chenyoca@gmail.com
 * </br><b>weibo : </b>		@桥下一粒砂
 * </br><b>date : </b>		2012-7-18 下午7:43:52
 *
 */
public class NetworkUtil {

	/**
	 * </br><b>title : </b>		网络是否可用
	 * </br><b>description :</b>网络是否可用
	 * </br><b>time :</b>		2012-7-18 下午7:44:36
	 * @param context
	 * @return
	 */
	public static boolean isAvailable(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = manager.getActiveNetworkInfo();
		return !(networkinfo == null || !networkinfo.isAvailable());
	}
	
	/**
	 * </br><b>title : </b>		Wifi是否启用
	 * </br><b>description :</b>Wifi是否启用
	 * </br><b>time :</b>		2012-7-18 下午7:45:20
	 * @param c
	 * @return
	 */
	public static boolean isWIFIActivate(Context c) {
		return ((WifiManager) c.getSystemService(Context.WIFI_SERVICE)).isWifiEnabled();
	}
	
	/**
	 * </br><b>title : </b>		修改WIFI状态
	 * </br><b>description :</b>修改WIFI状态
	 * </br><b>time :</b>		2012-7-18 下午7:46:13
	 * @param c
	 * @param status
	 */
	public static void changeWIFIStatus(Context c, boolean status) {
		((WifiManager) c.getSystemService(Context.WIFI_SERVICE)).setWifiEnabled(status);
	}
}
