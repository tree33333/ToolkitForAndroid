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
package chen.android.toolkit.res;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

/**
 * </br><b>name : </b>		AssetsReader
 * </br><b>description :</b>读取Assets文件夹内的
 * </br>@author : 			桥下一粒砂
 * </br><b>e-mail : </b>	chenyoca@163.com
 * </br><b>weibo : </b>		@桥下一粒砂
 * </br><b>date : </b>		2012-7-8
 *
 */
public class AssetsReader {

	/**
	 * </br><b>title : </b>		以InputStream方式读取文件
	 * </br><b>description :</b>以InputStream方式读取Assets文件夹内的文件。
	 * @param c Android环境上下文
	 * @param fileInAssets Assets内的文件
	 * @return IntpuStream对象
	 * @throws IOException 
	 */
	public static InputStream readAsInputStream(Context c,String fileInAssets) throws IOException{
		return c.getAssets().open(fileInAssets);
	}
}
