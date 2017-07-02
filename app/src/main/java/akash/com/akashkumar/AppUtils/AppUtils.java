package akash.com.akashkumar.AppUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

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
    public static JSONArray convertCursorToJSON(Context context, Cursor cursor) {
        JSONArray result = new JSONArray();

        int columnCount = cursor.getColumnCount();
        while (cursor.moveToNext()) {
            JSONObject row = new JSONObject();
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

}
