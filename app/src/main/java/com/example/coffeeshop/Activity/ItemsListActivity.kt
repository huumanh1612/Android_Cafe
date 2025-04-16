package com.example.coffeeshop.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshop.Adapter.ItemsListCategoryAdapter
import com.example.coffeeshop.ViewModel.MainViewModel
import com.example.coffeeshop.databinding.ActivityItemsListBinding

class ItemsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemsListBinding
    private val viewModel = MainViewModel()
    private var categoryId: String = ""
    private var categoryTitle: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityItemsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentData()
        setupViews()
        loadData()
    }

    private fun getIntentData() {
        categoryId = intent.getStringExtra("id") ?: ""
        categoryTitle = intent.getStringExtra("title") ?: ""
        binding.categoryTxt.text = categoryTitle
    }

    private fun setupViews() {
        binding.backBtn.setOnClickListener { finish() }
        binding.listView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun loadData() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.loadItems(categoryId).observe(this, Observer { itemList ->
            if (itemList.isNullOrEmpty()) {
                binding.listView.visibility = View.GONE
                binding.emptyStateText.visibility = View.VISIBLE
            } else {
                binding.listView.adapter = ItemsListCategoryAdapter(itemList)
                binding.listView.visibility = View.VISIBLE
                binding.emptyStateText.visibility = View.GONE
            }
            binding.progressBar.visibility = View.GONE
        })
    }
}