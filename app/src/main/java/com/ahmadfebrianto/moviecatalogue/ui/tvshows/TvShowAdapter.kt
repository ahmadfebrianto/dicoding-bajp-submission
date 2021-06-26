package com.ahmadfebrianto.moviecatalogue.ui.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmadfebrianto.moviecatalogue.R
import com.ahmadfebrianto.moviecatalogue.data.MovieEntity
import com.ahmadfebrianto.moviecatalogue.databinding.BaseRowBinding
import com.ahmadfebrianto.moviecatalogue.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listTvShows = ArrayList<MovieEntity>()

    fun setMovies(tvShows: List<MovieEntity>) {
        this.listTvShows.clear()
        this.listTvShows.addAll(tvShows)
    }

    class TvShowViewHolder(private val binding: BaseRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv_show: MovieEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(tv_show.poster_path)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(binding.ivMoviePoster)

                tvMovieTitle.text = tv_show.title
                tvMovieRating.text =
                    itemView.resources.getString(R.string.rating_placeholder, tv_show.rating)
                tvMovieReleaseYear.text =
                    itemView.resources.getString(R.string.release_placeholder, tv_show.release_year)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, tv_show.movieId)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.TV_SHOW)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowViewHolder {
        val baseRowBinding =
            BaseRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(baseRowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShows[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int {
        return listTvShows.size
    }
}