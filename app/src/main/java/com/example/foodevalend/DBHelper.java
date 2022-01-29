package com.example.foodevalend;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import VO.ProductoVO;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table usuarios(correo TEXT primary key, password TEXT,name TEXT,lastname TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE producto(id INTEGER NOT NULL primary key AUTOINCREMENT, nombre TEXT NOT NULL, description TEXT, precio DOUBLE NOT NULL, categoria TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void abrir(){
        this.getWritableDatabase();
    }
    public void cerrar(){
        this.close();
    }

    public void insertUser(String cor,String pas, String nam, String last){
        ContentValues valores = new ContentValues();
        valores.put("correo",cor);
        valores.put("password",pas);
        valores.put("name",nam);
        valores.put("lastname",last);
        this.getWritableDatabase().insert("usuarios",null,valores);
    }

    public void insertProducto(String nom,String des, Double prec, String cat){
        ContentValues valores = new ContentValues();
        valores.put("nombre",nom);
        valores.put("description",des);
        valores.put("precio",prec);
        valores.put("categoria",cat);
        this.getWritableDatabase().insert("producto",null,valores);
    }


    public Cursor consultarUserpass(String cor, String pass){
        Cursor cursor = null;
        cursor=this.getReadableDatabase().query("usuarios",new String[]{"correo","password","name","lastname"},
                "correo like '"+cor+"' and password like '"+pass+"' ",null,null,null,null);
        return cursor;
    }

    public ProductoVO getfindByIdProduc(Integer idP){
        ProductoVO vo=null;
        String selectQuery = "SELECT * FROM producto where id=?"+idP;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        vo =new ProductoVO();
        vo.setId(cursor.getInt(0));
        vo.setNombre(cursor.getString(1));
        vo.setDescription(cursor.getString(2));
        vo.setPrecio(cursor.getDouble(3));
        vo.setCategoria(cursor.getString(4));

        return vo;
    }

    public List<ProductoVO> getAllProducto() {
        List<ProductoVO> productoList = new ArrayList<ProductoVO>();
        String selectQuery = "SELECT * FROM producto";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {

            ProductoVO producto = new ProductoVO();

            producto.setId(Integer.parseInt(cursor.getString(0)));
            producto.setNombre(cursor.getString(1));
            producto.setDescription(cursor.getString(2));
            producto.setPrecio(cursor.getDouble(3));
            producto.setCategoria(cursor.getString(4));

            productoList.add(producto);
        }

        return productoList;
    }
}


/*
public class SQLiteView extends Activity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        tv = (TextView) findViewById(R.id.tvDBdisplay);
        DbHelper d = new DbHelper(this);

        List<Person> person = d.getAllPerson();
        for (Person p: person) {
            String data =
            p.getId() + " " + p.getName() + " " + p.getHotness() + " " + p.getAge();
            tv.setText(data);
        }
        }
    }
 */