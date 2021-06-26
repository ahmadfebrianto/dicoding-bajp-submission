package com.ahmadfebrianto.moviecatalogue.ui.detail

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ahmadfebrianto.moviecatalogue.R
import com.ahmadfebrianto.moviecatalogue.data.MovieEntity
import com.ahmadfebrianto.moviecatalogue.databinding.ActivityDetailBinding
import com.ahmadfebrianto.moviecatalogue.databinding.ContentActivityDetailBinding
import com.ahmadfebrianto.moviecatalogue.utils.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
        const val MOVIE = "movie"
        const val TV_SHOW = "tv_show"
    }

    private lateinit var contentActivityDetailBinding: ContentActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        contentActivityDetailBinding = activityDetailBinding.detailContent

        setContentView(activityDetailBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {

            val itemId = extras.getString(EXTRA_ID)
            val itemTye = extras.getString(EXTRA_TYPE)
            if (itemId != null && itemTye != null) {
                viewModel.setSelectedItem(itemId, itemTye)
            }

            viewModel.getItem().observe(this, {
                activityDetailBinding.progressBar.visibility = View.GONE
                activityDetailBinding.content.visibility = View.VISIBLE
                populateView(it)

            })

        }

    }

    private fun populateView(item: MovieEntity) {
        Glide.with(this)
            .load(Uri.parse(item.poster_path))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(contentActivityDetailBinding.ivMoviePoster)

        contentActivityDetailBinding.tvMovieTitle.text = item.title
        contentActivityDetailBinding.tvMovieRating.text = item.rating
        contentActivityDetailBinding.tvMovieReleaseYear.text = item.release_year
        contentActivityDetailBinding.tvMovieDescription.text = item.description

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = item.title
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}