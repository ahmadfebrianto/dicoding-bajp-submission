package com.ahmadfebrianto.moviecatalogue.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmadfebrianto.moviecatalogue.R

class MovieFragment : Fragment() {

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
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieViewModel::class.java]

            val movies = viewModel.getMovies()
            val movieAdapter = MovieAdapter()
            movieAdapter.setMovies(movies)

            rvMovie.layoutManager = LinearLayoutManager(context)
            rvMovie.adapter = movieAdapter
            rvMovie.setHasFixedSize(true)

            val dividerItemDecoration =
                DividerItemDecoration(rvMovie.context, DividerItemDecoration.VERTICAL)
            rvMovie.addItemDecoration(dividerItemDecoration)
        }
    }
}