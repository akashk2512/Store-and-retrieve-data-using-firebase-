package akash.com.akashkumar;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import akash.com.akashkumar.AppUtils.AppUtils;

/**
 * Created by Akash on 7/1/2017.
 */

public class DbHandler {
    /**
     *  DataBase NAme = User Details
     */

    private DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    Context mContext;
    private static int VERSION_NUMBER = 1;
    static String TABLE_NAME = "Employee";
    Cursor cursor ;

    public DbHandler(Context mContext) {
        this.mContext = mContext;
        dbHelper = new DbHelper(mContext,"UserDetails",null,VERSION_NUMBER);

    }

    private class DbHelper extends SQLiteOpenHelper{

        public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table Employee(_id integer primary key, name text,userName text,email text,pswd text,phn text, designation text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public void open(){
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void insertUserInfo(String name,String userName,String email,String pswd,String phn,String designation ){

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("userName",userName);
        contentValues.put("email",email);
        contentValues.put("pswd",pswd);
        contentValues.put("phn",phn);
        contentValues.put("designation",designation);
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }

    public String getLogin(String email,String pswd){

        cursor = sqLiteDatabase.query(TABLE_NAME,null,"email = ?",new String[]{email},null,null,null);

        Log.d("Test","getLogin");
        if (cursor.getCount()<1){
            cursor.close();
            Log.d("Test","getLogin1");
            AppUtils.showToast(mContext,"User not register");

        }else {
            cursor.moveToFirst();
            Log.d("Test","getLogin2");
            String pwd = cursor.getString(cursor.getColumnIndex("pswd"));
            if (pswd.equals(pwd)){
                Log.d("Test","getLogin3");
                Intent intent = new Intent(mContext,ShowAllUser.class);
                mContext.startActivity(intent);
                cursor.close();
            }else {

                AppUtils.showToast(mContext,"Incorrect password");
            }
        }

        return "";
    }

    public Cursor getAllUserData(){
        cursor = sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
        return cursor;
    }
}
