package org.iesfm.sqlexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Creando base de datos", Toast.LENGTH_SHORT).show();

        DBHelper dbHelper = new DBHelper(this);
        DBHelper dbHelperPersonalizable = new DBHelper(this, "personalizado.db", null, 2);

        Toast.makeText(this, "Creando la tabla alumnos...", Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "Tabla alumnos creada", Toast.LENGTH_SHORT).show();
        String create_alumnos = "CREATE TABLE IF NOT EXISTS alumnos(code INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR(50))";

        Toast.makeText(this, "Tabla comentarios creada", Toast.LENGTH_SHORT).show();
        String create_comments = "CREATE TABLE IF NOT EXISTs comments(_id INTEGER PRIMARY KEY AUTOINCREMENT, user VARCHAR(50), comment VARCHAR(100))";

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(create_alumnos);
        db.execSQL(create_comments);
        //Opcion 1: Insert mediante execSQL

        String insert_comment = "INSERT INTO comments(user, comment) VALUES ('admin', 'administrador de sistemas')";
        db.execSQL(insert_comment);

        //Opcion 2: Insert mediante contentValues
        ContentValues insertCommentsV2 = new ContentValues();
        insertCommentsV2.put("user", "usuario");
        insertCommentsV2.put("comment", "Usuario normal del sistema");

        db.insert("comments", null, insertCommentsV2);

        db.close();
    }
}