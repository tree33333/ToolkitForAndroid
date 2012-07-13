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
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * </br><b>name : </b>		HolderAdapter
 * </br><b>description :</b>TODO
 * </br>@author : 			桥下一粒砂
 * </br><b>e-mail : </b>	chenyoca@gmail.com
 * </br><b>weibo : </b>		@桥下一粒砂
 * </br><b>date : </b>		2012-7-10 下午10:56:11
 *
 */
public abstract class HolderAdapter<E> extends BaseAdapter {

	private static class ViewHolder{
		public View view;
	}
	
	private List<E> mDataCache;
	
	private LayoutInflater mInflater;
	
	public HolderAdapter(LayoutInflater inflater){
		mInflater = inflater;
	}

	/**
	 * </br><b>title : </b>		更新数据集
	 * </br><b>description :</b>更新数据集
	 * </br><b>time :</b>		2012-7-10 下午11:06:40
	 * @param data
	 */
	public void update(List<E> data){
		mDataCache = data;
		notifyDataSetChanged();
	}
	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	public int getCount() {
		return null == mDataCache ? 0 : mDataCache.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	public E getItem(int position) {
		return null == mDataCache ? null : mDataCache.get(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	public long getItemId(int position) {
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
		    holder = new ViewHolder();
		    convertView = createView(mInflater, position, getItem(position),parent);
		    convertView.setTag(holder);
		    holder.view = convertView;
		} else {
		    holder = (ViewHolder)convertView.getTag();
		}
		fillDataToView(holder.view,position, getItem(position));
		return convertView;
	}
	
	/**
	 * </br><b>title : </b>		创建View
	 * </br><b>description :</b>创建View,HolderAdapter需要创建View时，会调用此方法创建View。
	 * </br><b>time :</b>		2012-7-10 下午11:03:47
	 * @param inflater
	 * @param position
	 * @param data
	 * @param parent
	 * @return
	 */
	protected abstract View createView(LayoutInflater inflater,int position,E data,ViewGroup parent);
	
	/**
	 * </br><b>title : </b>		对View进行填充数据
	 * </br><b>description :</b>对View进行填充数据
	 * </br><b>time :</b>		2012-7-10 下午11:04:30
	 * @param view
	 * @param position
	 * @param data
	 */
	protected abstract void fillDataToView(View view,int position,E data);

}
