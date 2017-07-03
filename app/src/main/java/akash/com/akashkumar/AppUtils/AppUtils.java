package akash.com.akashkumar.AppUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import akash.com.akashkumar.UserData;
import akash.com.akashkumar.UserDetatils;

/**
 * Created by Akash on 7/1/2017.
 */

public class AppUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showLoadingDialog(Context mContext, ProgressDialog dialog ){

        try{
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Loading...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void dissMissDailog(ProgressDialog dialog){
        if (dialog != null){
            dialog.dismiss();
        }
    }

    public static void showToast(Context context, String message){
        Toast toast =  Toast.makeText(context, message ,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     *  Method to conver data in json
     * @param context
     * @param cursor
     * @return
     */
    public static JSONArray convertCursorToJSON(Context context, Cursor cursor) {

        JSONArray result = new JSONArray();
        JSONObject row = new JSONObject();

        int columnCount = cursor.getColumnCount();

        while (cursor.moveToNext()) {

            for (int index = 0; index < columnCount; index++) {
                try {

                    row.put(cursor.getColumnName(index), cursor.getString(index));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            result.put(row);

        }
        return result;
    }

    /**
     *  Method to itterate data from sql to Pojo
     * @param context
     * @param cursor
     * @return
     */
    public static UserDetatils keepDataInPojo(Context context, Cursor cursor){

        UserData userdata;
        UserDetatils userDetatils = new UserDetatils();

        List<UserData> userDataList=new ArrayList();

        if (cursor.moveToFirst()) {
            do {
                userdata = new UserData();
                // get the data into array, or class variable
                userdata.setPassword(cursor.getString(cursor.getColumnIndex("pswd")));
                userdata.setName(cursor.getString(cursor.getColumnIndex("name")));
                userdata.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
                userdata.setDesignation(cursor.getString(cursor.getColumnIndex("designation")));
                userdata.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                userdata.setPhone(cursor.getString(cursor.getColumnIndex("phn")));
                userDataList.add(userdata);
            } while (cursor.moveToNext());

        }

        userDetatils.setUserList(userDataList);

        return userDetatils;
    }

}
