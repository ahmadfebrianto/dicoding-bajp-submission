package com.ahmadfebrianto.moviecatalogue.ui.tvshows

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

class TvShowFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvTvShow: RecyclerView = view.findViewById(R.id.rv_tv_show)
        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvShowViewModel::class.java]

            val tvShows = viewModel.getTvShows()
            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.setMovies(tvShows)

            rvTvShow.layoutManager = LinearLayoutManager(context)
            rvTvShow.adapter = tvShowAdapter
            rvTvShow.setHasFixedSize(true)

            val dividerItemDecoration =
                DividerItemDecoration(rvTvShow.context, DividerItemDecoration.VERTICAL)
            rvTvShow.addItemDecoration(dividerItemDecoration)
        }
    }
}