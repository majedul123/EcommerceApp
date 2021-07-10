package com.itmedicious.spliff.adapter

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.itmedicious.spliff.R
import com.itmedicious.spliff.activity.Bag.AddToBagActivity
import com.itmedicious.spliff.database.ProductDatabaseHelper
import com.itmedicious.spliff.database.ProductDatabaseHelper.Companion.TABLE_NAME
import com.itmedicious.spliff.model.Product


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

        private val productCard: CardView? = itemView.findViewById(R.id.product_card)
        val productPhoto: AppCompatImageView? = itemView.findViewById(R.id.product_photo)
        private val productSubName: AppCompatTextView? =
            itemView.findViewById(R.id.product_sub_name)
        private val productName: AppCompatTextView? = itemView.findViewById(R.id.product_name)
        private val productPrice: AppCompatTextView? = itemView.findViewById(R.id.product_price)
        fun bind(product: Product) {

            productName?.text = product.name
            productSubName?.text = product.subName
            productPrice?.text = product.productPrice


            productCard?.setOnClickListener {
                showDialogue(itemView.context, product)

            }

        }

        private fun showDialogue(context: Context, product: Product) {
            var productQuantity: Int = 1
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.product_quantity_dialogue)

            val plusIcon: AppCompatImageView? = dialog.findViewById(R.id.plus_icon)
            val minusIcon: AppCompatImageView? = dialog.findViewById(R.id.minus_icon)
            val productQuantityText: AppCompatTextView? = dialog.findViewById(R.id.product_quantity)
            val addToBagButton: MaterialButton? = dialog.findViewById(R.id.add_to_bag_text)

            plusIcon?.setOnClickListener {
                productQuantity++
                productQuantityText?.text = productQuantity.toString()
            }
            minusIcon?.setOnClickListener {
                if (productQuantity > 0) {
                    productQuantity--
                    productQuantityText?.text = productQuantity.toString()

                }
            }
            addToBagButton?.setOnClickListener {
                // saveData(product, productQuantity)

                dialog.dismiss()
                val bundle = Bundle()
                bundle.putString("name", product.name)
                bundle.putString("subName", product.subName)
                bundle.putString("productPrice", product.productPrice)
                bundle.putInt("productImage", product.productImage)
                bundle.putInt("productQuantity", productQuantity)
                val i = Intent(context, AddToBagActivity::class.java)
                i.putExtras(bundle)
                context.startActivity(i)


            }

            dialog.show()
        }

        private fun saveData(product: Product, productQuantity: Int) {
            val contentValue = ContentValues()
            contentValue.put("name", product.name)
            contentValue.put("subName", product.subName)
            contentValue.put("productPrice", product.productPrice)
            contentValue.put("productImage", product.productImage)
            contentValue.put("productQuantity", productQuantity)
            val rowId = sqLiteDatabase.insert(TABLE_NAME, null, contentValue)
            Log.e("SD", "Succefully data interted")

        }

    }
}