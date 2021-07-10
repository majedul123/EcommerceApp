package com.itmedicious.spliff.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.state.State;

public class ProductDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "product_database";
    public static final String TABLE_NAME = "product_table";
    public static final int VERSION_NUMBER = 6;
    Context context;


    public ProductDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Toast.makeText(context, "table created ", Toast.LENGTH_SHORT).show();
            db.execSQL("CREATE table "+TABLE_NAME+" (name VARCHAR(200),  subName VARCHAR(200),productPrice VARCHAR(200),productImage INTEGER);");
        } catch (Exception e) {
            Toast.makeText(context, "exception " + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
