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
package chen.android.toolkit.ui;

import java.util.List;

import android.view.LayoutInflater;
import android.widget.AbsListView;
import chen.android.toolkit.ui.HolderAdapter.ViewCreator;

/**
 * </br><b>name : </b>		HolderViewFiller
 * </br><b>description :</b>TODO
 * </br>@author : 			桥下一粒砂
 * </br><b>e-mail : </b>	chenyoca@gmail.com
 * </br><b>weibo : </b>		@桥下一粒砂
 * </br><b>date : </b>		2012-7-14 上午1:33:59
 *
 */
public class HolderViewFiller<T> {

	private HolderAdapter<T> mAdapter;
	
	public HolderViewFiller(LayoutInflater inflater,ViewCreator<T> creator){
		mAdapter = new HolderAdapter<T>(inflater,creator);
		
	}
	
	/**
	 * </br><b>title : </b>		将数据更新到View中
	 * </br><b>description :</b>将数据更新到View中
	 * </br><b>time :</b>		2012-7-18 下午7:41:55
	 * @param view
	 * @param data
	 */
	public void update(AbsListView view,List<T> data){
		if( !mAdapter.equals(view.getAdapter()) ){
			view.setAdapter(mAdapter);
		}
		mAdapter.update(data);
	}
	
	/**
	 * </br><b>title : </b>		取得内置Adapter实例
	 * </br><b>description :</b>取得内置Adapter实例
	 * </br><b>time :</b>		2012-7-18 下午7:39:21
	 * @return
	 */
	public HolderAdapter<T> getAdapter(){
		return mAdapter;
	}
}
