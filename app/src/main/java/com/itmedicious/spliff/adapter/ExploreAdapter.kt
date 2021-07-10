package com.itmedicious.spliff.adapter

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.itmedicious.spliff.R
import com.itmedicious.spliff.database.ProductDatabaseHelper
import com.itmedicious.spliff.database.ProductDatabaseHelper.TABLE_NAME
import com.itmedicious.spliff.model.Product
import kotlinx.coroutines.runBlocking

class ExploreAdapter : RecyclerView.Adapter<ExploreAdapter.ItemViewHolder>() {

    private var data = ArrayList<Product>()


    fun setValue(product: List<Product>) {
        this.data.clear()
        this.data.addAll(product)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemVIew =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)

        return ItemViewHolder(itemVIew)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productPhotoArray = arrayOf(
            R.drawable.ic_product_photo_one,
            R.drawable.ic_product_photo_two,
            R.drawable.ic_product_photo_three,
            R.drawable.ic_product_photo_four
        )


        var productDatabaseHelper: ProductDatabaseHelper = ProductDatabaseHelper(itemView.context)
        val sqLiteDatabase: SQLiteDatabase = productDatabaseHelper.writableDatabase

        var productPhoto: AppCompatImageView? = itemView.findViewById(R.id.product_photo)
        var productSubName: AppCompatTextView? = itemView.findViewById(R.id.product_sub_name)
        var productName: AppCompatTextView? = itemView.findViewById(R.id.product_name)
        var productPrice: AppCompatTextView? = itemView.findViewById(R.id.product_price)
        var plusIcon: AppCompatImageView? = itemView.findViewById(R.id.plus_icon)
        fun bind(product: Product) {

            productName?.text = product.name
            productSubName?.text = product.subName
            productPrice?.text = product.productPrice


            plusIcon?.setOnClickListener {
                val contentValue = ContentValues()
                contentValue.put("name", product.name)
                contentValue.put("subName", product.subName)
                contentValue.put("productPrice", product.productPrice)
                contentValue.put("productImage", product.productImage)
                val rowId = sqLiteDatabase.insert(TABLE_NAME, null, contentValue)
                Log.e("SD", "Succefully data interted")


            }

        }

    }
}