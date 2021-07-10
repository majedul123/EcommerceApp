package com.itmedicious.spliff.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class ProductDatabaseHelper(var context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION_NUMBER) {

    companion object {
        const val DATABASE_NAME = "product_database"
        const val TABLE_NAME = "product_table"
        const val VERSION_NUMBER = 8
        const val CREATE_TABLE_QUERY =
            "CREATE table " + TABLE_NAME + " (name VARCHAR(200),  subName VARCHAR(200),productPrice VARCHAR(200),productImage INTEGER,productQuantity INTEGER);"
    }

    override fun onCreate(db: SQLiteDatabase) {
        try {
            Toast.makeText(context, "table created ", Toast.LENGTH_SHORT).show()
            db.execSQL(CREATE_TABLE_QUERY)
        } catch (e: Exception) {
            Toast.makeText(context, "exception " + e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

}