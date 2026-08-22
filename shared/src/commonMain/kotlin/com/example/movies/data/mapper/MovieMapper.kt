package com.example.movies.data.mapper

import com.example.movies.data.network.IMAGE_BASE_URL
import com.example.movies.data.network.model.CastMemberResponse
import com.example.movies.data.network.model.MovieResponse
import com.example.movies.domain.model.ImageSize
import com.example.movies.domain.model.Movie
import com.example.movies.utils.formatRating
import kotlin.math.roundToInt

fun MovieResponse.toModel(
    castMembersResponse: List<CastMemberResponse>? = null,
    imageSize: ImageSize = ImageSize.SMALL
) = Movie(
    id = this.id,
    title = this.title,
    overview = this.overview,
    posterUrl = "$IMAGE_BASE_URL/${imageSize.size}/${this.posterPath}",
    genres = this.genres?.map { it.toModel() },
    year = this.getYearFromReleaseDate(),
    duration = this.getDurationInHoursAndMinutes(),
    rating = this.voteAverage.formatRating(),
    castMembers = castMembersResponse
        ?.filter { it.department == "Acting" }
        ?.take(20)
        ?.map { it.toModel() }
)

private fun MovieResponse.getYearFromReleaseDate(): Int {
    return this.releaseDate.year
}

private fun MovieResponse.getDurationInHoursAndMinutes(): String? {
    return this.runTime?.let { runTimeMinutes ->
        val hours = runTimeMinutes / 60
        val minutes = runTimeMinutes % 60

        buildString {
            if (hours > 0) {
                append("${hours}h ")
            }

            append("${minutes}min")
        }
    }
}