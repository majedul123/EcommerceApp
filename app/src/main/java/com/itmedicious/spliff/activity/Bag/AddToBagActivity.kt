package com.itmedicious.spliff.activity.Bag

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.itmedicious.spliff.database.ProductDatabaseHelper
import com.itmedicious.spliff.databinding.ActivityAddToBagBinding

class AddToBagActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAddToBagBinding


//
//    private val productPhotoArray = arrayOf(
//        R.drawable.ic_product_photo_one,
//        R.drawable.ic_product_photo_four,
//        R.drawable.ic_product_photo_three,
//        R.drawable.ic_product_photo_four,
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddToBagBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        var productDatabaseHelper: ProductDatabaseHelper? = ProductDatabaseHelper(context =applicationContext)
        val sqLiteDatabase: SQLiteDatabase? = productDatabaseHelper?.writableDatabase

        val bundle = intent.extras

        val name = bundle?.getString("name", "")
        val subName = bundle?.getString("subName", "")
        val productPrice = bundle?.getString("productPrice", "0")
        val productImage = bundle?.getInt("productImage", 0)
        var productQuantity = bundle?.getInt("productQuantity", 0)


        binding?.contentLayout?.productSubName?.text = subName
        binding?.contentLayout?.productName?.text = name
        binding?.contentLayout?.productQuantity?.text = productQuantity.toString()
        binding?.contentLayout?.productPrice?.text = productPrice.toString()

        var price: Int = productPrice?.toInt() ?: 0
        var total: Int? = (price) * (productQuantity!!)
        binding?.contentLayout?.totalPrice?.text = total.toString()


        binding?.contentLayout?.plusIcon?.setOnClickListener {
            productQuantity++
            binding?.contentLayout?.productQuantity.text = productQuantity.toString()
            total = (price) * (productQuantity!!)
            binding?.contentLayout?.totalPrice?.text = total.toString()
        }
        binding?.contentLayout?.minusIcon?.setOnClickListener {
            if (productQuantity > 0) {
                productQuantity--
                binding?.contentLayout?.productQuantity.text = productQuantity.toString()
                total = (price) * (productQuantity!!)
                binding?.contentLayout?.totalPrice?.text = total.toString()

            }
        }


        binding?.contentLayout?.addToBagButton?.setOnClickListener {
            val contentValue = ContentValues()
            contentValue.put("name", name)
            contentValue.put("subName", subName)
            contentValue.put("productPrice", total.toString())
            contentValue.put("productImage", productImage)
            contentValue.put("productQuantity", productQuantity)
            sqLiteDatabase?.insert(ProductDatabaseHelper.TABLE_NAME, null, contentValue)
            startActivity(Intent(this, BagActivity::class.java))

        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}