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
package chen.android.toolkit.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * </br><b>name : </b>		SQLiteHelper
 * </br><b>description :</b>创建数据库辅助工具，用于创建，打开和管理一个数据库。
 * </br>@author : 			桥下一粒砂
 * </br><b>e-mail : </b>	chenyoca@163.com
 * </br><b>weibo : </b>		@桥下一粒砂
 * </br><b>date : </b>		2012-7-8 下午2:33:56
 *
 */
public class SQLiteHelper extends SQLiteOpenHelper  {

	private static final String TAG = "SQLiteHelper";
	
	/**
	 * SQLite数据库对象
	 */
	private SQLiteDatabase mDatabase;
	
	/**
	 * </br><b>description : </b>	数据库在没有调用getWritableDatabase() 或者 getReadableDatabase()
	 * 其中的一个方法前，是不会创建或者打开的。
	 * @param context 				Android应用环境变量引用,用于创建或者打开数据库
	 * @param dbName 				数据库名称，即数据库文件名。如果为null则创建一个内存数据库。
	 * @param factory 				用于创建Cursor游标对象组，默认为null。
	 * @param version 				数据库版本，从1开始。如果已经存在的数据库版本比version要小，则会调用 
	 * 			  			onUpgrade(SQLiteDatabase, int, int)升级数据库。否则调用 onDowngrade(SQLiteDatabase, int, int)
	 * 			  			降级数据库。
	 */
	public SQLiteHelper(Context context, String dbName, CursorFactory factory,
			int version) {
		super(context, dbName, factory, version);
	}
	
	/**
	 * </br><b>description : </b>	数据库在没有调用getWritableDatabase() 或者 getReadableDatabase()
	 * 其中的一个方法前，是不会创建或者打开的。
	 * @param context 				Android应用环境变量引用,用于创建或者打开数据库
	 * @param dbName 				数据库名称，即数据库文件名。如果为null则创建一个内存数据库。
	 * @param version 				数据库版本，从1开始。如果已经存在的数据库版本比version要小，则会调用 
	 * 			  onUpgrade(SQLiteDatabase, int, int)升级数据库。否则调用 onDowngrade(SQLiteDatabase, int, int)
	 * 			  降级数据库。
	 */
	public SQLiteHelper(Context context, String dbName,int version) {
		this(context, dbName, null, version);
	}
	
	/**
	 * </br><b>title : </b>		校验数据库
	 * </br><b>description :</b>如果数据库没有被创建，调用此方法将会触发OnCreate()方法创建数据库。
	 * 需要 onCreateSQLFile() 返回创建数据库时执行的SQL文件。
	 * </br><b>time :</b>		2012-7-8 下午2:30:13
	 */
	final public void verify(){
		mDatabase = this.getReadableDatabase();
		mDatabase.rawQuery("SELECT * FROM sqlite_master;",null).close();
	}
	
	/**
	 * </br><b>title : </b>		插入数据
	 * </br><b>description :</b>插入数据
	 * </br><b>time :</b>		2012-7-8 下午2:29:37
	 * @param table 			需要插入数据的表名
	 * @param values 			需要插入的Key-Value键值对。Map对象包含数据行的初始值。
	 *            			Key必须是表中的列名，Value必须是表中的列值。
	 * @return 				返回最后插入的行ID。如果发生异常，返回 -1 。
	 */
	public long insert(String table, ContentValues values) {
		mDatabase = this.getWritableDatabase();
		return mDatabase.insert(table, null, values);
	}
	
	/**
	 * </br><b>title : </b>		删除数据
	 * </br><b>description :</b>删除数据
	 * </br><b>time :</b>		2012-7-8 下午2:29:10
	 * @param table 			表名
	 * @param whereClause 		执行条件，用?置换
	 * @param whereArgs 		需要置换?的参数
	 * @return 				影响行数
	 */
	public int delete(String table, String whereClause, String[] whereArgs) {
		mDatabase = this.getWritableDatabase();
		return mDatabase.delete(table, whereClause, whereArgs);
	}
	
	/**
	 * </br><b>title : </b>		更新数据
	 * </br><b>description :</b>更新数据
	 * </br><b>time :</b>		2012-7-8 下午2:28:28
	 * @param table 			表名
	 * @param values 			需要更新Key-Value键值对
	 * @param whereClause 		更新条件，用?置换
	 * @param whereArgs 		需要置换?的参数
	 * @return 				影响行数
	 */
	public int update(String table, ContentValues values, String whereClause,
			String[] whereArgs) {
		mDatabase = this.getWritableDatabase();
		return mDatabase.update(table, values, whereClause, whereArgs);
	}
	
	/**
	 * </br><b>title : </b>		查询数据
	 * </br><b>description :</b>查询数据
	 * </br><b>time :</b>		2012-7-8 下午2:27:58
	 * @param sqlStatement 		SQL语句
	 * @return 				查询结果游标对象
	 */
	public Cursor query(String sqlStatement) {
		mDatabase = this.getReadableDatabase();
		return mDatabase.rawQuery(sqlStatement, null);
	}

	/**
	 * </br><b>title : </b>		查询数据
	 * </br><b>description :</b>查询数据
	 * </br><b>time :</b>		2012-7-8 下午2:27:14
	 * @param table 			表名
	 * @param columns 			要获取的字段名
	 * @param selection 		查询条件
	 * @param selectionArgs 	条件参数
	 * @return 				返回查询结果游标对象
	 */
	public Cursor query(String table, String[] columns, String selection,
			String[] selectionArgs) {
		mDatabase = this.getReadableDatabase();
		return mDatabase.query(table, columns, selection, selectionArgs, null, null,null); 
	}
	
	/**
	 * </br><b>title : </b>		执行单句SQL语句
	 * </br><b>description :</b>执行单句SQL语句，不能是SELECT或者其它带返回数据的SQL语句。
	 * </br><b>time :</b>		2012-7-8 下午2:26:45
	 * @param sql 				需要被执行的SQL语句。不支持用分号（;）分隔的多行语句。
	 */
	public void executeSQL(String sql){
		mDatabase = this.getWritableDatabase();
		mDatabase.execSQL(sql);
	}
	
	/**
	 * </br><b>title : </b>		执行单句SQL语句
	 * </br><b>description :</b>执行单句SQL语句，不能是SELECT/INSERT/UPDATE/DELETE等。
	 * </br><b>time :</b>		2012-7-8 下午2:26:04
	 * @param sql 				需要被执行的SQL语句。不支持用分号（;）分隔的多行语句。
	 * @param bindArgs 			只能是byte[], String, Long 和 Double 等类型
	 */
	public void executeSQL(String sql,Object[] bindArgs){
		mDatabase = this.getWritableDatabase();
		mDatabase.execSQL(sql, bindArgs);
	}
	
	/**
	 * 关闭数据库
	 * 在使用完数据库后，必须手动关闭。
	 */
	final public void close(){
		if(mDatabase != null) mDatabase.close();
	}

	/**
	 * SQ语句组
	 */
	private String mSQLStatements;
	
	/**
	 * SQL注释前缀
	 */
	private static final String COMMENT_PREFIX = "--";
	
	private static final String EMPTY_SQL_TIP = "Empty SQL statements for database create/upgrade !";
	
	/**
	 * </br><b>title : </b>		设置SQL语句组
	 * </br><b>description :</b>设置SQL语句组，以\n或者\r\n和;分行。
	 * </br><b>time :</b>		2012-7-8 下午2:25:30
	 * @param sqlStatements 	SQL语句组
	 */
	public void setSQLStatement(String sqlStatements){
		mSQLStatements = sqlStatements;
	}
	
	/**
	 * 当数据库被第一次创建时调用。
	 * 在此方法中可创建表和初始化表数据。
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		if( null == mSQLStatements){
			Log.w(TAG,EMPTY_SQL_TIP);
		}else{
			execSQLStatements(db,mSQLStatements.replace("\r\n", ";").replace("\n", ";").split(";"));
		}
	}
	
	/**
	 * 升级数据库
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);
	}
	
	/**
	 * </br><b>title : </b>		执行一组SQL语句
	 * </br><b>description :</b>执行一组SQL语句
	 * </br><b>time :</b>		2012-7-8 下午2:24:27
	 * @param db 				数据库对象
	 * @param sqlStatements 	SQL语句组
	 */
	public void execSQLStatements(SQLiteDatabase db,String[] sqlStatements){
		for(String sql : sqlStatements){
			if(sql.startsWith(COMMENT_PREFIX)) continue;
			db.execSQL(sql);
		}
	}

}
