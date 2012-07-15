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
package chen.android.toolkit.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.text.TextUtils;
import android.util.Xml;

/**
 * </br><b>name : </b>		XMLParser
 * </br><b>description :</b>TODO
 * </br>@author : 			桥下一粒砂
 * </br><b>e-mail : </b>	chenyoca@gmail.com
 * </br><b>weibo : </b>		@桥下一粒砂
 * </br><b>date : </b>		2012-7-8 下午4:00:35
 *
 */
public class XMLParseEngine {

	/**
	 * </br><b>name : </b>		XMLElementParser
	 * </br><b>description :</b>节点元素解析接口
	 * </br>@author : 			桥下一粒砂
	 * </br><b>e-mail : </b>	chenyoca@gmail.com
	 * </br><b>weibo : </b>		@桥下一粒砂
	 * </br><b>date : </b>		2012-7-15 下午9:26:08
	 *
	 */
	public interface XMLElementParser{
		/**
		 * </br><b>title : </b>		标签起始
		 * </br><b>description :</b>解析引擎遇到起始标签时处理
		 * </br><b>time :</b>		2012-7-8 下午4:12:46
		 * @param uri
		 * @param localName
		 * @param qName
		 * @param attributes
		 */
		void onStartElement(String uri, String localName, String qName, Attributes attributes);
		
		/**
		 * </br><b>title : </b>		标签终止
		 * </br><b>description :</b>解析引擎遇到终止标签时处理
		 * </br><b>time :</b>		2012-7-8 下午4:13:30
		 * @param qName
		 * @param data
		 */
		void onEndElement(String qName,StringBuffer data);
	};
	
	/**
	 * 节点数据缓存
	 */
	private StringBuffer mBuffer = new StringBuffer();
	
	/**
	 * 节点元素解析
	 */
	private XMLElementParser mElementParser;
	
	/**
	 * </br><b>description : </b>	必须传入一个节点解析接口
	 * @param elementParser
	 */
	public XMLParseEngine(XMLElementParser elementParser){
		mElementParser = elementParser;
	}
	
	/**
	 * </br><b>title : </b>		对InputStream进行XML解析
	 * </br><b>description :</b>对InputStream进行XML解析
	 * </br><b>time :</b>		2012-7-8 下午4:18:13
	 * @param is				XML文件的InputStream
	 * @throws IOException
	 * @throws SAXException
	 */
	public void parse(InputStream is) throws IOException, SAXException{
		Xml.parse(is,Xml.Encoding.UTF_8,new InnerXmlParse());
	}
	
	/**
	 * </br><b>title : </b>		对XML文件进行XML解析
	 * </br><b>description :</b>对XML文件进行XML解析。
	 * </br><b>time :</b>		2012-7-8 下午4:20:18
	 * @param file
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws SAXException
	 */
	public void parse(File file) throws FileNotFoundException, IOException, SAXException{
		parse(new FileInputStream(file));
	}
	
	/**
	 * </br><b>name : </b>		InnerXmlParse
	 * </br><b>description :</b>XML 解析器
	 * </br>@author : 			桥下一粒砂
	 * </br><b>e-mail : </b>	chenyoca@gmail.com
	 * </br><b>weibo : </b>		@桥下一粒砂
	 * </br><b>date : </b>		2012-7-8 下午4:12:25
	 *
	 */
	private class InnerXmlParse extends DefaultHandler{
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) 
				throws SAXException {
			mBuffer.setLength(0);
			mElementParser.onStartElement(uri,localName,qName,attributes);
		}
		
		@Override
		public void characters(char[] ch, int start, int length)throws SAXException {
			String data = new String(ch,start,length);
			if (!TextUtils.isEmpty(data)){
				mBuffer.append(data);
			}
		}
		
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			mElementParser.onEndElement(qName, mBuffer);
		}
	}
	
}
