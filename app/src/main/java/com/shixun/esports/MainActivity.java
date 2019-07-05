package com.shixun.esports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.shixun.esports.Activity.HomeActivity;
import com.shixun.esports.Activity.RegisterActivity;
import com.shixun.esports.Tools.Verification;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton login;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initView();
        initEvent();
    }

    private void initEvent() {
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void initView() {
        login = (ImageButton) findViewById(R.id.login);
        register = (Button)findViewById(R.id.register);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login:
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, HomeActivity.class);
                    MainActivity.this.startActivity(intent);
                    finish();
                break;
            case R.id.register:
                Intent in = new Intent();
                in.setClass(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(in);
                break;
        }
    }

}
