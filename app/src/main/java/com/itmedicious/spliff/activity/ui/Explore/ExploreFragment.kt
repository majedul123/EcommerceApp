package com.itmedicious.spliff.activity.ui.Explore

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
            Product("Indica blend", "PURE SUN FARMS", "$20", 1),
            Product("Ginger", "GINGER ROOT", "$30", 2),
            Product("Indica blend", "PURE SUN FARMS", "$40", 3),
            Product("Ginger", "GINGER ROOT BIG", "$60", 4),
            Product("Ginger", "GINGER ROOT BIG", "$60", 4),
            Product("Ginger", "GINGER ROOT BIG", "$60", 4)
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