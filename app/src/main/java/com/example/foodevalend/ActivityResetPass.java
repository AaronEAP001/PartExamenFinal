package com.example.foodevalend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import VO.ProductoVO;

public class ActivityResetPass extends Activity {

    Button btnRetornar,btnLista;
    DBHelper db =new DBHelper(this,"DB1",null,1);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        btnRetornar = findViewById(R.id.return_home_xd);
        btnLista = findViewById(R.id.btn_enviar_pass);

        btnRetornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityResetPass.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ProductoVO productoVO = new ProductoVO();
                db.abrir();
                //db.getAllProducto();
                for(ProductoVO vo:db.getAllProducto()){
                    Toast.makeText(ActivityResetPass.this,"VO = "+vo.toString(), Toast.LENGTH_SHORT).show();
                }
                db.cerrar();
            }
        });

    }
}
