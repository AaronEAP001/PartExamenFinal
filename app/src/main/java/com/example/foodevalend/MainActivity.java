package com.example.foodevalend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button registerButton,recuperarbtn,loginStar;
    EditText username,pass;
    DBHelper db =new DBHelper(this,"DB1",null,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerButton = findViewById(R.id.btn_create_user);
        recuperarbtn = findViewById(R.id.btn_password_reset);
        loginStar = findViewById(R.id.btn_start_login);



        loginStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = findViewById(R.id.username_edit_text_lg);
                pass = findViewById(R.id.password_edit_text_lg);

                Cursor cursor = db.consultarUserpass(username.getText().toString(), pass.getText().toString());

                if (cursor.getCount()>0){
                    Intent i = new Intent(MainActivity.this,ActivityHome.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,"Password o User Incorrectos", Toast.LENGTH_SHORT).show();
                }
                username.setText("");
                pass.setText("");
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        recuperarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ActivityResetPass.class);
                startActivity(i);
            }
        });

    }
}