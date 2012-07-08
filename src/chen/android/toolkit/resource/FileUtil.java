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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

import android.util.Log;

/**
 * </br><b>name : </b>		FileUtil
 * </br><b>description :</b>通用的文件处理类
 * </br>@author : 			桥下一粒砂
 * </br><b>e-mail : </b>	chenyoca@gmail.com
 * </br><b>weibo : </b>		@桥下一粒砂
 * </br><b>date : </b>		2012-7-8 下午4:56:28
 *
 */
public class FileUtil {
	
	private static final String TAG = "FileUtil";
	
	/**
	 * </br><b>title : </b>		把源文件复制到目标文件中。
	 * </br><b>description :</b>把源文件复制到目标文件中。
	 * </br><b>time :</b>		2012-7-8 下午5:03:38
	 * @param source			源文件
	 * @param dest				目标文件
	 * @throws IOException		如果源文件不存在或者目标文件不可写入，抛出IO异常。
	 */
	public static void copy(File source, File dest) throws IOException {
		FileInputStream fileIS = null;
		FileOutputStream fileOS = null;
		try{
			fileIS = new FileInputStream(source);
			fileOS = new FileOutputStream(dest);
		}catch(FileNotFoundException ex){
			Log.e(TAG,"Source File not exist !");
		}
		FileChannel fic = fileIS.getChannel();
		MappedByteBuffer mbuf = fic.map(FileChannel.MapMode.READ_ONLY, 0,source.length());
		fic.close();
		fileIS.close();
		if (!dest.exists()) {
			String destPath = dest.getPath();
			String destDir = destPath.substring(0,destPath.lastIndexOf(File.separatorChar));
			File dir = new File(destDir);
			if (!dir.exists()) {
				if (dir.mkdirs()) {
					Log.i(TAG,"Directory created");
				} else {
					Log.e(TAG,"Directory not created");
				}
			}
		}
		FileChannel foc = fileOS.getChannel();
		foc.write(mbuf);
		foc.close();
		fileOS.close();
		mbuf.clear();
	}

	
	/**
	 * </br><b>title : </b>		复制文件
	 * </br><b>description :</b>复制文件
	 * </br><b>time :</b>		2012-7-8 下午5:04:23
	 * @param source			源文件路径
	 * @param dest				目标文件路径
	 * @throws IOException		如果源文件不存在或者目标文件不可写入，抛出IO异常。
	 */
	public static void copy(String source, String dest) throws IOException {
		copy(new File(source), new File(dest));
	}

	/**
	 * 移动文件
	 * @param source 
	 * 				源文件路径 
	 * @param dest 
	 * 				目标文件路径 
	 * @throws IOException
	 */
	
	/**
	 * </br><b>title : </b>		移动文件
	 * </br><b>description :</b>移动文件
	 * </br><b>time :</b>		2012-7-8 下午5:05:00
	 * @param source			源文件路径
	 * @param dest				目标文件路径
	 * @throws IOException
	 */
	public static void moveFile(String source, String dest) throws IOException {
		copy(source, dest);
		File src = new File(source);
		if (src.exists() && src.canRead()) {
			if (src.delete()) {
				Log.i(TAG,"Source file was deleted");
			} else {
				src.deleteOnExit();
			}
		} else {
			Log.w(TAG,"Source file could not be accessed for removal");
		}
	}

	/**
	 * </br><b>title : </b>		删除文件夹及其下内容
	 * </br><b>description :</b>删除文件夹及其下内容。如果文件夹被系统锁定或者文件夹不能被清空，将返回false。
	 * </br><b>time :</b>		2012-7-8 下午5:06:13
	 * @param directory			要被删除的文件夹
	 * @return					文件夹删除成功则返回true，文件夹不存在则返回false。
	 * @throws IOException		如果文件夹不能被删除，则抛出异常。
	 */
	public static boolean deleteDirectory(String directory) throws IOException {
		return deleteDirectory(directory, false);
	}
	
	/**
	 * </br><b>title : </b>		删除文件夹及其下内容。
	 * </br><b>description :</b>如果文件夹被系统锁定或者文件夹不能被清空，将返回false。
	 * </br><b>time :</b>		2012-7-8 下午5:07:00
	 * @param directory			
	 * @param useOSNativeDelete	 标识是否使用系统命令进行删除操作。
	 * @return					文件夹删除成功则返回true，文件夹不存在则返回false。
	 * @throws IOException		如果文件夹不能被删除，则抛出异常。
	 */
	public static boolean deleteDirectory(String directory,
			boolean useOSNativeDelete) throws IOException {
		boolean result = false;
		if (!useOSNativeDelete) {
			File dir = new File(directory);
			for (File file : dir.listFiles()) {
				if (!file.delete()) {
					file.deleteOnExit();
				} 
			}
			if (dir.delete()) {
				result = true;
			} else {
				dir.deleteOnExit();
			}
		} else {
			Process process = null;
			Thread std = null;
			try {
				Runtime runTime = Runtime.getRuntime();
				if (File.separatorChar == '\\') {
					process = runTime.exec("CMD /D /C \"RMDIR /Q /S "+ directory.replace('/', '\\') + "\"");
				} else {
					process = runTime.exec("rm -rf "+ directory.replace('\\', File.separatorChar));
				}
				std = stdOut(process);
				while (std.isAlive()) {
					try {
						Thread.sleep(250);
					} catch (Exception e) {
					}
				}
				result = true;
			} catch (Exception e) {
				Log.e(TAG,"Error running delete script");
			} finally {
				if (null != process) {
					process.destroy();
					process = null;
				}
				std = null;
			}
		}
		return result;
	}

	/**
	 * </br><b>title : </b>		使用本地系统命令重命名一个文件。
	 * </br><b>description :</b>使用本地系统命令重命名一个文件。
	 * </br><b>time :</b>		2012-7-8 下午5:12:23
	 * @param from				原文件名
	 * @param to				新文件名
	 */
	public static void rename(String from, String to) {
		Process process = null;
		Thread std = null;
		try {
			Runtime runTime = Runtime.getRuntime();
			if (File.separatorChar == '\\') {
				process = runTime.exec("CMD /D /C \"REN " + from + ' ' + to + "\"");
			} else {
				process = runTime.exec("mv -f " + from + ' ' + to);
			}
			std = stdOut(process);
			while (std.isAlive()) {
				try {
					Thread.sleep(250);
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			Log.e(TAG,"Error running delete script");
		} finally {
			if (null != process) {
				process.destroy();
				process = null;
				std = null;
			}
		}
	}
	
	/**
	 * </br><b>title : </b>		创建一个文件夹。
	 * </br><b>description :</b>创建一个文件夹。
	 * </br><b>time :</b>		2012-7-8 下午5:13:25
	 * @param directory			需要被创建的文件夹
	 * @return					创建成功则返回true，否则返回false。
	 * @throws IOException
	 */
	public static boolean makeDirectory(String directory) throws IOException {
		return makeDirectory(directory, false);
	}

	/**
	 * </br><b>title : </b>		创建一个文件夹
	 * </br><b>description :</b>如果<i>createParents</i> 被标记为true，则父级文件夹不存在将会被自动创建。
	 * </br><b>time :</b>		2012-7-8 下午5:14:07
	 * @param directory			需要被创建的文件夹
	 * @param createParents		是否创建父级文件夹标识
	 * @return					如果文件夹创建成功，返回true。如果文件夹已经存在，返回false。
	 * @throws IOException		如果文件夹不能被创建，则抛出异常
	 */
	public static boolean makeDirectory(String directory, boolean createParents)
			throws IOException {
		boolean created = false;
		File dir = new File(directory);
		if (createParents) {
			created = dir.mkdirs();
		} else {
			created = dir.mkdir();
		}
		return created;
	}


	/**
	 * Special method for capture of StdOut.
	 * @return
	 */
	private final static Thread stdOut(final Process p) {
		final byte[] empty = new byte[128];
		for (int b = 0; b < empty.length; b++) {
			empty[b] = (byte) 0;
		}
		Thread std = new Thread() {
			public void run() {
				StringBuilder sb = new StringBuilder(1024);
				byte[] buf = new byte[128];
				BufferedInputStream bis = new BufferedInputStream(
						p.getInputStream());
				try {
					while (bis.read(buf) != -1) {
						sb.append(new String(buf).trim());
						System.arraycopy(empty, 0, buf, 0, buf.length);
					}
					bis.close();
				} catch (Exception e) {
					Log.e(TAG,String.format("%1$s", e));
				}
			}
		};
		std.setDaemon(true);
		std.start();
		return std;
	}

	
	/**
	 * 生成一个文件名。类似 282818_00023 。这个名字由于当前秒数加随机数组成。
	 * @return 生成的文件名
	 */
	public static String generateCustomName() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		sb.append(System.nanoTime());
		sb.append('_');
		int i = random.nextInt(99999);
		if (i < 10) {
			sb.append("0000");
		} else if (i < 100) {
			sb.append("000");
		} else if (i < 1000) {
			sb.append("00");
		} else if (i < 10000) {
			sb.append("0");
		}
		sb.append(i);
		return sb.toString();
	}

}