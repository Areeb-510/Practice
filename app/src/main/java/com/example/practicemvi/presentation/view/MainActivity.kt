package com.example.practicemvi.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicemvi.R
import com.example.practicemvi.presentation.adapter.ItemViewAdapter
import com.example.practicemvi.presentation.intent.ItemIntent
import com.example.practicemvi.presentation.state.ItemViewState
import com.example.practicemvi.presentation.viewmodel.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ItemViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorMessage: TextView
    private lateinit var itemAdapter: ItemViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recycler_view)
        progressBar = findViewById(R.id.progress_bar)
        errorMessage = findViewById(R.id.error_message)

        itemAdapter = ItemViewAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemAdapter
        }
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is ItemViewState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    errorMessage.visibility = View.GONE
                }
                is ItemViewState.Success -> {
                    progressBar.visibility = View.GONE
                    errorMessage.visibility = View.GONE
                    itemAdapter.setItems(state.items)
                }
                is ItemViewState.Error -> {
                    progressBar.visibility = View.GONE
                    errorMessage.visibility = View.VISIBLE
                }
            }
        })

        viewModel.processIntent(ItemIntent.FetchItems)
    }
}