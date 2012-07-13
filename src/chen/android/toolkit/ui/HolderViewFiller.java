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
	
	public HolderViewFiller(LayoutInflater inflater,AbsListView view,ViewCreator<T> creator){
		mAdapter = new HolderAdapter<T>(inflater,creator);
		view.setAdapter(mAdapter);
	}
	
	/**
	 * </br><b>title : </b>		TODO
	 * </br><b>description :</b>TODO
	 * </br><b>time :</b>		2012-7-14 上午1:39:42
	 * @param data
	 */
	public void update(List<T> data){
		mAdapter.update(data);
	}
}
