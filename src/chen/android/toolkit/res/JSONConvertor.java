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
package chen.android.toolkit.res;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

/**
 * </br><b>name : </b>		JSONConvertor
 * </br><b>description :</b>JSON对象转换为其它Java对象
 * </br>@author : 			桥下一粒砂
 * </br><b>e-mail : </b>	chenyoca@163.com
 * </br><b>weibo : </b>		@桥下一粒砂
 * </br><b>date : </b>		2012-7-8 下午2:37:33
 *
 */
public abstract class JSONConvertor<T> {

	/**
	 * Android环境上下文
	 */
	private Context mContext;
	
	private static final String TAG = "JSONConvertor";
	
	/**
	 * </br><b>description : </b>	JSON转换器
	 * @param context
	 */
	public JSONConvertor(Context context){
		mContext = context;
	}
	
	/**
	 * </br><b>title : </b>		从res读取字符串资源组，并解析成对象
	 * </br><b>description :</b>从res的XML文件中读取字符串资源，将其解析成JAVA对象
	 * </br><b>time :</b>		2012-7-8 下午2:53:53
	 * @param stringArrayResId	res中的string-array资源ID
	 * @return					解析后的Java对象组
	 */
	public List<T> loadInRes(int stringArrayResId){
		String[] dataCache = mContext.getResources().getStringArray(stringArrayResId);
		List<T> result = null;
		if( 0 < dataCache.length ){
			result = new ArrayList<T>();
			for(String jsonContent : dataCache){
				try {
					result.add(convertTo(convertToJSON(jsonContent)));
				} catch (JSONException e) {
					Log.e(TAG,String.format("Convert to JSONObject ERROR ! Cause by :%s",e.getCause()));
					continue;
				}
			}
		}
		return result;
	}
	
	/**
	 * </br><b>title : </b>		将JSON对象转换成Java对象
	 * </br><b>description :</b>将JSON对象转换成Java对象
	 * </br><b>time :</b>		2012-7-8 下午2:56:05
	 * @param jsonObj			JSONObject对象
	 * @return					Java对象
	 */
	public abstract T convertTo(JSONObject jsonObj);
	
	/**
	 * </br><b>title : </b>		将字符串转换成JSON对象
	 * </br><b>description :</b>字符串必须按JSON格式编写
	 * </br><b>time :</b>		2012-7-8 下午2:45:18
	 * @param jsonContent		字符串内容
	 * @return 				JSON对象
	 * @throws JSONException	解析JSON出错时，抛出异常。
	 */
	public static JSONObject convertToJSON(String jsonContent) throws JSONException{
		return new JSONObject(jsonContent);
	}
}
