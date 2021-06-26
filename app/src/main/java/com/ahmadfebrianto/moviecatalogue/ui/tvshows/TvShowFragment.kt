package com.ahmadfebrianto.moviecatalogue.ui.tvshows

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
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        val tvShowAdapter = TvShowAdapter()
        viewModel.getTvShows().observe(viewLifecycleOwner, {
            progressBar.visibility = View.GONE
            tvShowAdapter.setMovies(it)
            tvShowAdapter.notifyDataSetChanged()
        })

        rvTvShow.layoutManager = LinearLayoutManager(context)
        rvTvShow.adapter = tvShowAdapter
        rvTvShow.setHasFixedSize(true)

        val dividerItemDecoration =
            DividerItemDecoration(rvTvShow.context, DividerItemDecoration.VERTICAL)
        rvTvShow.addItemDecoration(dividerItemDecoration)
    }
}