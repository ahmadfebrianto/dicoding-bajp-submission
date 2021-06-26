package com.ahmadfebrianto.moviecatalogue.data

import android.net.Uri

data class MovieEntity(
    var movieId: String = "",
    var poster_path: Uri? = null,
    var title: String = "",
    var description: String = "",
    var rating: String = "",
    var release_year: String = "",
    var stars: String = "",
    var director: String = ""
)