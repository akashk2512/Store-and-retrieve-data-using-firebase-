package akash.com.akashkumar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import akash.com.akashkumar.AppUtils.AppUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.btn_signinn)
    Button btn_signinn;
    @Bind(R.id.ed_username)
    EditText ed_username;
    @Bind(R.id.ed_password)
    EditText ed_password;
    DbHandler dbHandler;
    boolean validate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        btn_signinn.setOnClickListener(this);
        dbHandler = new DbHandler(LoginActivity.this);
        dbHandler.open();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_signinn:
               validation();
                Log.d("Test","getLogin_Act "+ validate);
                if (validate == false){
                    Log.d("Test","getLogin_Act");
                    dbHandler.getLogin(ed_username.getText().toString().trim(),
                            ed_password.getText().toString().trim());
                }
                break;
            default:
                break;
        }
    }

    boolean validation() {

        validate = false;
        if (ed_username.getText().length() == 0) {
            validate = true;
            ed_username.setError("Enter Email Id");

        } else if (ed_password.getText().length() == 0) {
            validate = true;
            ed_password.setError("Enter Password");

        }
        return validate;
    }
}
