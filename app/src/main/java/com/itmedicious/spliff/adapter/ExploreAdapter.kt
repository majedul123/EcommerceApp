package com.itmedicious.spliff.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        (holder as ItemViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(product: Product) {


        }

    }
}