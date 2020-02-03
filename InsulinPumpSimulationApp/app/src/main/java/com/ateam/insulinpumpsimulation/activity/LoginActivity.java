package com.ateam.insulinpumpsimulation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.ateam.insulinpumpsimulation.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btn_login)
    Button buttonLogin;

    @BindView(R.id.phone_number)
    EditText phone;

    @BindView(R.id.password)
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //removing title and set full screen activity
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_login)
    public void onLoginButtonClick() {
        if (phone.getText().toString().trim().equals("")) {
            phone.setError("Please Enter your phone number here");
        } else if (password.getText().toString().trim().equals("")) {
            password.setError("Enter your password here");
        } else {
            String userPhone = phone.getText().toString().trim();
            String userPassword = password.getText().toString().trim();
            Log.d("User Phone: ", userPhone);
            Log.d("Password", userPassword);
            login(userPhone, userPassword);
        }

    }

    private void login(String userPhone, String userPassword) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra(
                "number", userPhone
        ));
        overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
    }

}
