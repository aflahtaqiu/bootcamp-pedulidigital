package com.example.pedulidigital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    val moviesAdapter by lazy { MovieListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupObserver()
        setupListener()

        viewModel.getNowPlayingMovies()

        moviesRecylerView.adapter = moviesAdapter
    }

    private fun setupListener() {
        searchEditText.doAfterTextChanged {
            viewModel.searchMovies(it.toString())
        }
    }

    private fun setupObserver() {
        viewModel.moviesResultLiveData.observe(this) {
            viewModel.handleMovieListResponse(it)
        }
        viewModel.moviesLiveData.observe(this) {
            moviesAdapter.replaceData(it)
        }
        viewModel.errorMessageLiveData.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
}