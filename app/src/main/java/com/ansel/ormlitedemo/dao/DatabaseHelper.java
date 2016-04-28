package com.ansel.ormlitedemo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ansel.ormlitedemo.bean.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 *
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "sqlite-test.db";

    /**
     * userDao ，每张表对于一个
     */
    private Dao<User, Integer> userDao;

    private DatabaseHelper(Context context) {
        /**
         * context：Associated content from the application. This is needed to locate the database.
         *      应用的上下文对象，用于定位database
         * databaseName：Name of the database we are opening.
         *      要打开的数据库名称
         * factory：Cursor factory or null if none.
         *      游标工程，如果没有，传null(一般都是穿null)
         * databaseVersion：Version of the database we are opening. This causes {@link #onUpgrade(SQLiteDatabase, int, int)} to be called if the stored database is a different version.
         *      要打开数据库的版本号，如果版本号与之前的不一样，会调用 onUpgrade方法。
         */
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        try {
            //使用 OrmLite 提供的方法创建表，
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            //更新数据库，删除旧表，创建新表
            TableUtils.dropTable(connectionSource, User.class, true);
            //删除完成后，需要执行创建操作
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;

    /**
     * 单例获取该DatabaseHelper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }
        return instance;
    }

    /**
     * 获得userDao
     *
     * @return
     * @throws SQLException
     */
    public Dao<User, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        return userDao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        userDao = null;
    }

}
