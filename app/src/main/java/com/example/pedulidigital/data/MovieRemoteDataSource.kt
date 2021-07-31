package com.example.pedulidigital.data

import com.example.pedulidigital.module.Movie

class MovieRemoteDataSource {

    private val apiEndpoint: ApiEndpoint = ApiRetrofit.getRetrofitInstance().create(ApiEndpoint::class.java)

    suspend fun fetchNowPlayingMovies(): Result<List<Movie>> {
        val response = apiEndpoint.fetchNowPlayingMovies()

        return if (response.isSuccessful) {
            Result.success(response.body()?.results ?: emptyList())
        } else {
            Result.failure(Throwable(response.message()))
        }
    }

    suspend fun searchMovies(query: String): Result<List<Movie>> {
        val response = apiEndpoint.searchMovie(query = query)

        return if (response.isSuccessful) {
            Result.success(response.body()?.results ?: emptyList())
        } else {
            Result.failure(Throwable(response.message()))
        }
    }
}