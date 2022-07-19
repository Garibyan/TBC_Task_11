package com.garibyan.armen.tbc_task_11

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.garibyan.armen.tbc_task_11.Constants.IS_EDITING
import com.garibyan.armen.tbc_task_11.Constants.ITEM_INDEX
import com.garibyan.armen.tbc_task_11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val gameAdapter by lazy { GameAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        onClickListeners()
    }

    override fun onResume() {
        super.onResume()
        gameAdapter.submitList(list.toList())
    }

    private fun init() {
        binding.rvView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = gameAdapter
        }
    }

    private fun onClickListeners() {
        binding.swipeContainer.setOnRefreshListener {
            val currentList = list
            gameAdapter.submitList(currentList)
            Toast.makeText(this, R.string.refreshed, Toast.LENGTH_SHORT).show()
            binding.swipeContainer.isRefreshing = false
        }

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, AddEditGameActivity::class.java))
        }

        gameAdapter.onDeleteClickListener = {
            list.remove(it)
            gameAdapter.submitList(list.toList())
        }

        gameAdapter.onEditClickListener = {
            val intent = Intent(this, AddEditGameActivity::class.java)
            intent.putExtra(IS_EDITING, true)
            intent.putExtra(ITEM_INDEX, it)
            startActivity(intent)
        }
    }
}