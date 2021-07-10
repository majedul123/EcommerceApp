package com.itmedicious.spliff.activity.ui.Explore

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itmedicious.spliff.adapter.ExploreAdapter
import com.itmedicious.spliff.databinding.FragmentExploreBinding
import com.itmedicious.spliff.model.Product

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        prepareData()
        return root
    }

    private fun prepareData() {
        val products: List<Product> = listOf(
            Product(
                name = "Indica blend",
                subName = "PURE SUN FARMS",
                productPrice = "$20",
                productImage = 1
            ),
            Product(
                name = "Ginger blend",
                subName = "PURE GINGER ROOT",
                productPrice = "$30",
                productImage = 3
            ),
            Product(
                name = "Oryza sativa",
                subName = "PURE ORYZA SATIVA",
                productPrice = "$40",
                productImage = 2
            ),
            Product(
                name = "Indica blend",
                subName = "PURE SUN FARMS",
                productPrice = "$20",
                productImage = 4
            )
        )

        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding?.itemList?.layoutManager = layoutManager
        val adapter = ExploreAdapter()
        binding?.itemList?.adapter = adapter
        adapter.setValue(product = products)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}