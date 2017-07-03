package akash.com.akashkumar;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONArray;

import akash.com.akashkumar.AppUtils.AppUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ShowAllUser extends AppCompatActivity {

    @Bind(R.id.list_data)
    ListView listData;
    DbHandler  dbHandler;
    SimpleCursorAdapter simpleCursorAdapter;

    JSONArray jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_user);
        ButterKnife.bind(this);

        dbHandler = new DbHandler(ShowAllUser.this);
        dbHandler.open();

        Cursor cursor = dbHandler.getAllUserData();

        if (cursor != null){

            simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.row, cursor,new String[]{"name","userName","email","phn","designation"},new int[]{
                    R.id.txt_name,R.id.txt_username,R.id.txt_email,R.id.txt_number,R.id.txt_designation
            },0);

            listData.setAdapter(simpleCursorAdapter);
            jsonData = AppUtils.convertCursorToJSON(ShowAllUser.this,cursor);
//            AppUtils.showToast(ShowAllUser.this,jsonData.toString());
//            AppUtils.keepDataInPojo(ShowAllUser.this,cursor);

        }


    }


}
