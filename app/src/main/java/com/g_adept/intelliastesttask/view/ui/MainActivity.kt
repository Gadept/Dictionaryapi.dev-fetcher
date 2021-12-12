package com.g_adept.intelliastesttask.view.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.g_adept.intelliastesttask.R
import com.g_adept.intelliastesttask.databinding.ActivityMainBinding
import com.g_adept.intelliastesttask.view.ui.adapters.MeaningsAdapter
import com.g_adept.intelliastesttask.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        binding.rvMeanings.layoutManager = LinearLayoutManager(this)
        binding.rvMeanings.setHasFixedSize(true)
        binding.rvMeanings.adapter = MeaningsAdapter(listOf())

        binding.btnSearch.setOnClickListener {

            lifecycleScope.launch {
                val result = viewModel.getWordDefinition(binding.etSearch.text.toString())
                result.data?.get(0)?.let {
                    binding.tvMeanings.visibility = View.VISIBLE
                    binding.dictionary = it
                    binding.rvMeanings.adapter = MeaningsAdapter(it.meanings)
                }
            }
        }
    }
}
