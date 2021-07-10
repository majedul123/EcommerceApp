package com.itmedicious.spliff.activity.Bag

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.itmedicious.spliff.R
import com.itmedicious.spliff.databinding.ActivityAddToBagBinding

class AddToBagActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAddToBagBinding

    private val productPhotoArray = arrayOf(
        R.drawable.ic_product_photo_one,
        R.drawable.ic_product_photo_four,
        R.drawable.ic_product_photo_three,
        R.drawable.ic_product_photo_four,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddToBagBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

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



        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}