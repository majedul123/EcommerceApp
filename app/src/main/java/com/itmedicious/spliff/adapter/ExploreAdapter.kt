package com.itmedicious.spliff.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.itmedicious.spliff.R
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
        var productPhoto: AppCompatImageView? = itemView.findViewById(R.id.product_photo)
        var productSubName: AppCompatTextView? = itemView.findViewById(R.id.product_sub_name)
        var productName: AppCompatTextView? = itemView.findViewById(R.id.product_name)
        var productPrice: AppCompatTextView? = itemView.findViewById(R.id.product_price)
        fun bind(product: Product) {

            productName?.text = product.name
            productSubName?.text = product.subName
            productPrice?.text = product.productPrice
         //   productPhoto?.setImageResource(productPhotoArray[product.productImage!!])

        }

    }
}