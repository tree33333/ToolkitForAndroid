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
package chen.android.toolkit.sys;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Window;
import android.view.WindowManager;

/**
 * </br><b>name : </b>		ActivityUtil
 * </br><b>description :</b>Activity特性辅助工具
 * </br>@author : 			桥下一粒砂
 * </br><b>e-mail : </b>	chenyoca@163.com
 * </br><b>weibo : </b>		@桥下一粒砂
 * </br><b>date : </b>		2012-7-8
 *
 */
public class ActivityUtil {

	/**
	 * </br><b>title : </b>		设置Activity全屏显示
	 * </br><b>description :</b>设置Activity全屏显示。
	 * @param activity Activity引用
	 * @param isFull true为全屏，false为非全屏
	 */
	public static void setFullScreen(Activity activity,boolean isFull){
		Window window = activity.getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		if (isFull) {
			params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
			window.setAttributes(params);
			window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		} else {
			params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
			window.setAttributes(params);
			window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		}
	}
	
	/**
	 * </br><b>title : </b>		隐藏系统标题栏
	 * </br><b>description :</b>隐藏Activity的系统默认标题栏
	 * @param activity Activity对象
	 */
	public static void hideTitleBar(Activity activity){
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
	/**
	 * </br><b>title : </b>		设置Activity的显示方向为垂直方向
	 * </br><b>description :</b>强制设置Actiity的显示方向为垂直方向。
	 * @param activity Activity对象
	 */
	public static void setScreenVertical(Activity activity){
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	/**
	 * </br><b>title : </b>		设置Activity的显示方向为横向
	 * </br><b>description :</b>强制设置Actiity的显示方向为横向。
	 * @param activity Activity对象
	 */
	public static void setScreenHorizontal(Activity activity){
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}
	
}
