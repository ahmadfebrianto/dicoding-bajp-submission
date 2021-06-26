package com.ahmadfebrianto.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ahmadfebrianto.moviecatalogue.R
import com.ahmadfebrianto.moviecatalogue.data.MovieEntity
import com.ahmadfebrianto.moviecatalogue.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
        const val MOVIE = "movie"
        const val TV_SHOW = "tv_show"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val itemId = extras.getString(EXTRA_ID)
            if (itemId != null) {
                viewModel.setSelectedItem(itemId)
                when (extras.getString(EXTRA_TYPE)) {
                    MOVIE -> {
                        val movie = viewModel.getMovie()
                        populateView(movie)
                    }
                    TV_SHOW -> {
                        val tvShow = viewModel.getTvShow()
                        populateView(tvShow)
                    }

                }
            }

        }

    }

    private fun populateView(item: MovieEntity) {
        Glide.with(this)
            .load(item.poster_path)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(binding.ivMoviePoster)

        binding.tvMovieTitle.text = item.title
        binding.tvMovieRating.text = item.rating
        binding.tvMovieReleaseYear.text = item.release_year
        binding.tvMovieDescription.text = item.description

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = item.title
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}