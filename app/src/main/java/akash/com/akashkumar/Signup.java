package akash.com.akashkumar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import akash.com.akashkumar.AppUtils.AppUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.btn_signupp)
    Button btn_signupp;

    @Bind(R.id.ed_name)
    EditText ed_name;
    @Bind(R.id.ed_password)
    EditText ed_password;
    @Bind(R.id.ed_username)
    EditText ed_username;
    @Bind(R.id.ed_email)
    EditText email;
    @Bind(R.id.ed_designation)
    EditText ed_designation;
    @Bind(R.id.ed_phn)
    EditText ed_phn;

    DbHandler dbHandler;
    boolean validate;
    Context mContext;


    DatabaseReference databaseReference;
    String mailpattren = "^([_A-Za-z0-9-\\+]{3,})+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        mContext = Signup.this;

        databaseReference = FirebaseDatabase.getInstance().getReference("employees");
        dbHandler = new DbHandler(mContext);
        dbHandler.open();
        btn_signupp.setOnClickListener(this);


    }


    boolean validation(){

        validate = false;
        if (ed_name.getText().length()==0){
            validate = true;
            ed_name.setError("Enter Name");
            ed_name.requestFocus();
        }else if (ed_username.getText().length()==0){
            validate = true;
            ed_username.requestFocus();
            ed_username.setError("Enter User Name");

        }else if (email.getText().length()==0){
            validate = true;
            email.requestFocus();
            email.setError("Enter Email");

        }else if (ed_password.getText().length()==0){
            validate = true;
            ed_password.requestFocus();
            ed_password.setError("Enter Password");

        }else if (ed_phn.getText().length()==0){
            validate = true;
            ed_phn.requestFocus();
            ed_phn.setError("Enter Phone Number");

        }
        else if (ed_designation.getText().length()==0){
            validate = true;
            ed_designation.requestFocus();
            ed_designation.setError("Enter Designation");

        }
        return validate;
    }

    @Override
    public void onClick(View v) {
        validation();
        Log.d("Test","validate "+ validate);
        if (validate== false){
            if (email.getText().toString().trim().matches(mailpattren)){
                Log.d("Test","getLogin_ActSignUp");

                dbHandler.insertUserInfo(ed_name.getText().toString().trim(),
                       ed_username.getText().toString().trim(),
                        email.getText().toString().trim(),
                        ed_password.getText().toString().trim(),
                        ed_phn.getText().toString().trim(),
                        ed_designation.getText().toString().trim());
                String pushId = databaseReference.push().getKey();
                UserData userData = new UserData();

                userData.setName(ed_name.getText().toString().trim());
                userData.setEmail(email.getText().toString().trim());
                userData.setDesignation(ed_designation.getText().toString().trim());
                userData.setPassword(ed_password.getText().toString().trim());
                userData.setPhone(ed_phn.getText().toString().trim());
                userData.setUserName(ed_username.getText().toString().trim());
                databaseReference.child(pushId).setValue(userData);
                AppUtils.showToast(mContext,"Data Saved in FireBase and Sqlite");
                Intent intent = new Intent(mContext,LoginActivity.class);
                startActivity(intent);
                finish();
            }else {
                email.setError("Enter valid email Id");
                email.requestFocus();
            }

        }
    }
}
