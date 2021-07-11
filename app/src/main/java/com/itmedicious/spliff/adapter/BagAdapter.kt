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
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.itmedicious.spliff.R
import com.itmedicious.spliff.activity.Bag.AddToBagActivity
import com.itmedicious.spliff.database.ProductDatabaseHelper
import com.itmedicious.spliff.database.ProductDatabaseHelper.Companion.TABLE_NAME
import com.itmedicious.spliff.model.BagProduct
import com.itmedicious.spliff.model.Product


class BagAdapter : RecyclerView.Adapter<BagAdapter.ItemViewHolder>() {

    private var data = ArrayList<BagProduct>()


    fun setValue(product: List<BagProduct>) {
        this.data.clear()
        this.data.addAll(product)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemVIew =
            LayoutInflater.from(parent.context).inflate(R.layout.bag_item, parent, false)

        return ItemViewHolder(itemVIew)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val productCard: CardView? = itemView.findViewById(R.id.product_card)
        val productPhoto: AppCompatImageView? = itemView.findViewById(R.id.product_photo)
        private val productSubName: AppCompatTextView? =
            itemView.findViewById(R.id.product_sub_name)
        private val productName: AppCompatTextView? = itemView.findViewById(R.id.product_name)
        private val productPrice: AppCompatTextView? = itemView.findViewById(R.id.product_price)
        fun bind(product: BagProduct) {

            productName?.text = product.name
            productSubName?.text = product.subName
            productPrice?.text = "$" + product.productPrice


        }


    }
}