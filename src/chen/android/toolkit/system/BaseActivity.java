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
package chen.android.toolkit.system;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * </br><b>name : </b>		BaseActivity
 * </br><b>description :</b>BaseActivity
 * </br>@author : 			桥下一粒砂
 * </br><b>e-mail : </b>	chenyoca@gmail.com
 * </br><b>weibo : </b>		@桥下一粒砂
 * </br><b>date : </b>		2012-7-18 下午9:20:41
 *
 */
public class BaseActivity extends Activity {

	/**
	 * Activity引用
	 */
	protected Activity mActivity;
	
	/**
	 * Context引用
	 */
	protected Context mContext;
	
	protected DoubleClickExit mDClickExit;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = this;
		mContext = this;
	}
	
	/**
	 * </br><b>title : </b>		启用双击返回键退出程序
	 * </br><b>description :</b>启用双击返回键退出程序
	 * </br><b>time :</b>		2012-7-18 下午9:24:41
	 */
	protected void enabledDClickExit(){
		mDClickExit = new DoubleClickExit(mActivity);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if( null != mDClickExit ){
			return mDClickExit.doubleClickExit(keyCode);
		}else{
			return super.onKeyDown(keyCode, event);
		}
		
	}
	
	
	
}
