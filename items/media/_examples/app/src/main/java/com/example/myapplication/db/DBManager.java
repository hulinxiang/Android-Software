package com.example.myapplication.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.example.myapplication.entity.LoginNameBean;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static DBManager manager;
    private DBHelper mHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context mContext;

    public DBManager(Context mContext) {
        this.mContext = mContext;
        mHelper = new DBHelper(mContext, Constant.DB_NAME,null,1);
        sqLiteDatabase = mHelper.getWritableDatabase();
    }

    public static DBManager getInstance(Context context){
        if(manager == null){
            synchronized (DBManager.class) {
                if (manager == null)
                    manager = new DBManager(context);
            }
        }
        return manager;

    }


    public void insertMessage(String loginName,String passWord){
        if(sqLiteDatabase == null)  return;
        if(TextUtils.isEmpty(loginName) || TextUtils.isEmpty(passWord)) return;
        try {
            String sql = "insert into "+Constant.TABLE_NAME+" (loginName,passWord) values (?,?)";
            Log.d("sql===","sql 语句："+sql);
            sqLiteDatabase.execSQL(sql,new String[]{loginName,passWord});
        }catch (Exception e){
            e.printStackTrace();
            updateMessage(loginName,passWord);
        }

    }


    public void deleteMessage(String loginName){
        if(sqLiteDatabase == null)  return;
        if(TextUtils.isEmpty(loginName)) return;
        String sql = "delete from "+Constant.TABLE_NAME+" where loginName = "+loginName;
        Log.d("sql===","sql 语句："+sql);
        sqLiteDatabase.execSQL(sql);

    }

    public void updateMessage(String loginName,String passWord){
        if(sqLiteDatabase == null)  return;
        if(TextUtils.isEmpty(loginName) || TextUtils.isEmpty(passWord)) return;

        String sql = "update "+Constant.TABLE_NAME+" set passWord=? where loginName=?";
        Log.d("sql===","sql 语句："+sql);
        sqLiteDatabase.execSQL(sql,new String[]{loginName,passWord});

    }

    @SuppressLint("Range")
    public List<LoginNameBean> queryAllData() {
        List<LoginNameBean> list = new ArrayList<>();
        if (sqLiteDatabase == null) return list;
        String sql = "SELECT * FROM " + Constant.TABLE_NAME;
        Log.d("DBManager", "Executing SQL: " + sql);
        try (Cursor cursor = sqLiteDatabase.rawQuery(sql, null)) {
            while (cursor != null && cursor.moveToNext()) {
                LoginNameBean bean = new LoginNameBean();
                bean.loginName = cursor.getString(cursor.getColumnIndex("loginName"));
                bean.pwd = cursor.getString(cursor.getColumnIndex("passWord"));
                list.add(bean);
            }
        } catch (Exception e) {
            Log.e("DBManager", "Error querying all data: " + e.getMessage());
        }
        return list;
    }
}
