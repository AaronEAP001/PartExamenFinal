package com.example.foodevalend;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodevalend.Recycle.ProductosAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import VO.ProductoVO;

public class ActivityHome extends AppCompatActivity {

    List<ProductoVO> elements;
    FloatingActionButton btnAgregar;
    DBHelper db =new DBHelper(this,"DB1",null,1);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(myToolbar);

        ArrayList<String> informacionLIst;
        ArrayList<ProductoVO> listaProduc;

        btnAgregar = findViewById(R.id.floatingActionButton);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityHome.this,NuevoProductoActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void setSupportActionBar(androidx.appcompat.widget.Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.producto_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle item selection
        switch (item.getItemId()) {
            case R.id.edit_item:
                Toast.makeText(ActivityHome.this,"Usted apreto editar", Toast.LENGTH_SHORT).show();
                //edit_item();
                return true;
            case R.id.delete_item:
                Toast.makeText(ActivityHome.this,"Usted apreto eliminar", Toast.LENGTH_SHORT).show();
                //delete_item();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void init(){
        elements = new ArrayList<>();
        db.abrir();
        elements = db.getAllProducto();

        if (elements.size()>0){
            ProductosAdapter productosAdapter= new ProductosAdapter(elements);

            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(productosAdapter);
        }else{
            Toast.makeText(ActivityHome.this,"No hya datos aun :'v", Toast.LENGTH_SHORT).show();
        }

        db.cerrar();

    }

}

