package com.itmedicious.spliff.activity.Bag

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itmedicious.spliff.adapter.BagAdapter
import com.itmedicious.spliff.adapter.ExploreAdapter
import com.itmedicious.spliff.database.ProductDatabaseHelper
import com.itmedicious.spliff.database.ProductDatabaseHelper.Companion.TABLE_NAME
import com.itmedicious.spliff.databinding.ActivityYourBagBinding
import com.itmedicious.spliff.model.BagProduct
import com.itmedicious.spliff.model.Product

class BagActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityYourBagBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityYourBagBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        var productDatabaseHelper: ProductDatabaseHelper? =
            ProductDatabaseHelper(context = applicationContext)
        val sqLiteDatabase: SQLiteDatabase? = productDatabaseHelper?.writableDatabase
        var cursor: Cursor? = sqLiteDatabase?.rawQuery("SELECT * FROM " + TABLE_NAME, null)

        var productList: MutableList<BagProduct>? = ArrayList()
        while (cursor?.moveToNext() == true) {

            var product = BagProduct(
                name = cursor.getString(0),
                subName = cursor.getString(1),
                productPrice = cursor.getString(2),
                productImage = cursor.getInt(3),
                productQuantity = cursor.getInt(4)
            )
            productList?.add(product)

        }

        Log.e("SD", productList.toString())

        val layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        binding?.contentLayout?.bagItemList?.layoutManager = layoutManager
        val adapter = BagAdapter()
        binding?.contentLayout?.bagItemList?.adapter = adapter
        productList?.let { adapter.setValue(product = it) }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


}