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

import java.io.UnsupportedEncodingException;

/**
 * </br><b>name : </b>	ChineseUtil </br>
 * <b>description :</b>	TODO </br>
 * @author : 			桥下一粒砂 </br>
 * <b>e-mail : </b> 	chenyoca@gmail.com </br>
 * <b>weibo : </b> 		@桥下一粒砂
 * </br><b>date : </b> 	2012-7-17 下午10:26:03
 * 
 */
public class ChineseUtil {

	public static final char[] CHINESE_FIRST_LETTER_TABLE = { 'a', 'b', 'c',
			'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'w', 'x', 'y', 'z' };

	// 存放国标一级汉字不同读音的起始区位码
	private static final int[] GB_AREA_CODE = { 1601, 1637, 1833, 2078, 2274,
			2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858,
			4027, 4086, 4390, 4558, 4684, 4925, 5249, 5600 };

	/**
	 * </br><b>title : </b>		判断是否是中文字符
	 * </br><b>description :</b>判断是否是中文字符
	 * </br><b>time :</b>		2012-7-17 下午10:27:17
	 * @param ch
	 * @return
	 */
	public static boolean isChineseChar(char ch) {
		// 如果左移7不为0是中文
		return (ch >> 7) != 0;
	}

	/**
	 * @title: getFirstLetter
	 * @Description: 取得首字母
	 * @Param chinesChar 中文
	 * @return
	 * @throws UnsupportedEncodingException
	 *             不是GBK编码时抛出异常
	 */
	public static Character getFirstLetter(char chinesChar)
			throws UnsupportedEncodingException {
		byte[] uniCode = String.valueOf(chinesChar).getBytes("GBK");
		return (0 < uniCode[0] && uniCode[0] < 128) ? null : convert(uniCode);
	}

	/**
	 * </br><b>title : </b>		转换成字符
	 * </br><b>description :</b>转换成字符
	 * </br><b>time :</b>		2012-7-17 下午10:28:57
	 * @param bytes
	 * @return
	 */
	private static char convert(byte[] bytes) {
		char result = '-';
		int secPosValue = 0;
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] -= 160;
		}
		secPosValue = bytes[0] * 100 + bytes[1];
		for (int i = 0; i < CHINESE_FIRST_LETTER_TABLE.length; i++) {
			if (secPosValue >= GB_AREA_CODE[i]
					&& secPosValue < GB_AREA_CODE[i + 1]) {
				result = CHINESE_FIRST_LETTER_TABLE[i];
				break;
			}
		}
		return result;
	}
}
