package com.itmedicious.spliff.activity.ui.Explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itmedicious.spliff.adapter.ExploreAdapter
import com.itmedicious.spliff.databinding.FragmentExploreBinding
import com.itmedicious.spliff.model.Product

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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
            Product("Tom"),
            Product("John"),
            Product("John"),
            Product("John"),
            Product("John")
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