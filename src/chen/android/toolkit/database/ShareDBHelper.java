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
package chen.android.toolkit.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * </br><b>name : </b>		ShareHelper
 * </br><b>description :</b>ShareDatabase辅助工具类
 * </br>@author : 			桥下一粒砂
 * </br><b>e-mail : </b>	chenyoca@gmail.com
 * </br><b>weibo : </b>		@桥下一粒砂
 * </br><b>date : </b>		2012-7-8 下午4:50:50
 *
 */
public class ShareDBHelper {

	private SharedPreferences db;
	
	/**
	 * </br><b>description : </b>	初始化，建立一个ShareDatabase
	 * @param context				Android环境上下文
	 * @param dbName				Database名字
	 */
	public ShareDBHelper(Context context, String dbName) {
		db = context.getSharedPreferences(dbName, 0);
	}

	/**
	 * </br><b>title : </b>		将值保存到ShareDatabase中
	 * </br><b>description :</b>将值保存到ShareDatabase中。只支持基本类型
	 * </br><b>time :</b>		2012-7-8 下午4:52:04
	 * @param key				KEY
	 * @param val				VALUE
	 */
	public void put(String key, Object val) {
		Editor editor = db.edit();
		if(val instanceof String){
			editor.putString(key, val.toString());
		}else if(val instanceof Integer){
			editor.putInt(key, (Integer)val);
		}else if(val instanceof Float){
			editor.putFloat(key, (Float)val);
		}else if(val instanceof Long){
			editor.putLong(key, (Long)val);
		}else if(val instanceof Boolean){
			editor.putBoolean(key, (Boolean)val);
		}
		editor.commit();
	}
	
	public int getInt(String key){
		return db.getInt(key, 0); 
	}
	
	public int getInt(String key,int deftVal){
		return db.getInt(key, deftVal);
	}
	
	public boolean getBoolean(String key){
		return db.getBoolean(key, false);
	}

	public boolean getBoolean(String key,boolean defValue){
		return db.getBoolean(key, defValue);
	}
	
	public String getString(String key){
		return db.getString(key, null);
	}
	
	public String getString(String key,String defValue){
		return db.getString(key, defValue);
	}
	
	public long getLong(String key){
		return db.getLong(key, 0L);
	}
	
	public long getLong(String key,Long defValue){
		return db.getLong(key, defValue);
	}
	
	public float getFloat(String key){
		return db.getFloat(key, 0.0f);
	}
	
	public float getFloat(String key,Float defValue){
		return db.getFloat(key, defValue);
	}
}
