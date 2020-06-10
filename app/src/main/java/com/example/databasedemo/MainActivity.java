package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //create a new database
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            //create a table
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR, age INT(3))");

            myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Faith',19)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES('Purity',18)");

            //pull stuff from the database
            Cursor c = myDatabase.rawQuery("SELECT * FROM users",null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");

            c.moveToFirst();
            while (c != null){
                Log.i("name", c.getString(nameIndex));
                Log.i("age", Integer.toString(c.getInt(ageIndex)));
                c.moveToNext();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }
}