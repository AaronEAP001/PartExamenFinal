package com.example.foodevalend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

    Button loginReset,btnRegistrar,retornaBtn;

    EditText userName,password,name,lastname;

    DBHelper db =new DBHelper(this,"DB1",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_register_activity);

        loginReset = findViewById(R.id.true_user);
        retornaBtn = findViewById(R.id.return_home);
        btnRegistrar = findViewById(R.id.btn_register);

        userName= findViewById(R.id.register_email_edit_text);
        password= findViewById(R.id.register_password_edit_text);
        name= findViewById(R.id.register_name_edit_text);
        lastname= findViewById(R.id.register_lastname_edit_text);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cor = userName.getText().toString();
                String pass = password.getText().toString();
                String nom = name.getText().toString();
                String last = lastname.getText().toString();

                if(cor.length()>0 || pass.length()>0 || nom.length()>0 || last.length()>0){
                    db.abrir();
                    db.insertUser(cor,pass,nom,last);
                    db.cerrar();
                    Intent i = new Intent(RegisterActivity.this, ActivityHome.class);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(RegisterActivity.this,"No se permiten campos 0", Toast.LENGTH_SHORT).show();
                }

            }
        });

        loginReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        retornaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

}
