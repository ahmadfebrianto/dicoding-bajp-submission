package com.ahmadfebrianto.moviecatalogue.utils

import android.app.Application
import android.content.Context
import android.net.Uri
import com.ahmadfebrianto.moviecatalogue.data.MovieEntity
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

object CatalogHelper {

    lateinit var application: Application

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getMovies(): ArrayList<MovieEntity>{
        val results = ArrayList<MovieEntity>()
        val fileName = "movies.json"
        val jsArray = JSONArray(getJsonDataFromAsset(application, fileName))

        for (i in 0 until jsArray.length()) {
            val jsonObject = jsArray.getJSONObject(i)
            val posterUri = Uri.parse("file:///android_asset/poster_movie/mv${i + 1}.png")
            val movie = jsonToDataClass(jsonObject, posterUri)
            results.add(movie)
        }

        return results
    }

    fun getTvShows(): ArrayList<MovieEntity> {
        val results = ArrayList<MovieEntity>()
        val fileName = "tvshows.json"
        val jsArray = JSONArray(getJsonDataFromAsset(application, fileName))

        for (i in 0 until jsArray.length()) {
            val jsonObject = jsArray.getJSONObject(i)
            val posterUri = Uri.parse("file:///android_asset/poster_tv_show/tv${i + 1}.png")
            val movie = jsonToDataClass(jsonObject, posterUri)
            results.add(movie)
        }

        return results
    }

    private fun jsonToDataClass(jsonObject: JSONObject, posterUri: Uri): MovieEntity {
        val movie = MovieEntity()
        movie.movieId = jsonObject.getString("movieId")
        movie.poster_path = posterUri
        movie.title = jsonObject.getString("title")
        movie.description = jsonObject.getString("description")
        movie.rating = jsonObject.getString("rating")
        movie.release_year = jsonObject.getString("release_year")
        movie.stars = jsonObject.getString("stars")
        movie.director = jsonObject.getString("director")
        return movie
    }
}