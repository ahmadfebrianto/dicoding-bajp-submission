package com.ahmadfebrianto.moviecatalogue.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmadfebrianto.moviecatalogue.R
import com.ahmadfebrianto.moviecatalogue.utils.ViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvMovie: RecyclerView = view.findViewById(R.id.rv_movie)
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        val movieAdapter = MovieAdapter()

        viewModel.getMovies().observe(viewLifecycleOwner, {
            progressBar.visibility = View.GONE
            movieAdapter.setMovies(it)
            movieAdapter.notifyDataSetChanged()
        })

        rvMovie.layoutManager = LinearLayoutManager(context)
        rvMovie.adapter = movieAdapter
        rvMovie.setHasFixedSize(true)

        val dividerItemDecoration =
            DividerItemDecoration(rvMovie.context, DividerItemDecoration.VERTICAL)
        rvMovie.addItemDecoration(dividerItemDecoration)

    }
}