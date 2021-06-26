package com.ahmadfebrianto.moviecatalogue.data

data class MovieEntity(
    var movieId: String = "",
    var poster_path: String = "",
    var title: String = "",
    var description: String = "",
    var rating: String = "",
    var release_year: String = "",
    var stars: String = "",
    var director: String = ""
)