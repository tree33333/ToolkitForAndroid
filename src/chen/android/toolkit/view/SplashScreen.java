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
package chen.android.toolkit.view;

/**
 * </br><b>name : </b>		SplashScreen
 * </br><b>description :</b>启动界面动画
 * </br>@author : 			桥下一粒砂
 * </br><b>e-mail : </b>	chenyoca@gmail.com
 * </br><b>weibo : </b>		@桥下一粒砂
 * </br><b>date : </b>		2012-7-15 下午9:28:25
 *
 */
public class SplashScreen {

	/**
	 * </br><b>name : </b>		SplashListener
	 * </br><b>description :</b>回调接口
	 * </br>@author : 			桥下一粒砂
	 * </br><b>e-mail : </b>	chenyoca@gmail.com
	 * </br><b>weibo : </b>		@桥下一粒砂
	 * </br><b>date : </b>		2012-7-15 下午10:24:47
	 *
	 */
	public interface SplashListener{
		void onStart();
		void onEnd();
	};
	
	public SplashListener mListener;
	
	public void start(){
		
	}
	
	public void addSplash(String fileInAssert,int duration){
		
	}
	
	public void addSplash(int resId,int duration){
		
	}
	
	public void setSplashListener(SplashListener listener){
		mListener = listener;
	}
}
