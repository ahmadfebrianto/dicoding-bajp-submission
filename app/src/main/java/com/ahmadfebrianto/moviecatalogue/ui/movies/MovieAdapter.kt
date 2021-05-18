package com.ahmadfebrianto.moviecatalogue.ui.movies

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

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val listMovies = ArrayList<MovieEntity>()

    fun setMovies(movies: List<MovieEntity>) {
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    class MovieViewHolder(private val binding: BaseRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(movie.poster_path)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(binding.ivMoviePoster)

                tvMovieTitle.text = movie.title
                tvMovieRating.text =
                    itemView.resources.getString(R.string.rating_placeholder, movie.rating)
                tvMovieReleaseYear.text =
                    itemView.resources.getString(R.string.release_placeholder, movie.release_year)

                itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, movie.movieId)
                    intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.MOVIE)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val baseRowBinding =
            BaseRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(baseRowBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }
}