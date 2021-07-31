package com.example.pedulidigital.data

class MovieRepository(
    private val remoteSource: MovieRemoteDataSource = MovieRemoteDataSource()
) {

    suspend fun fetchNowPlayingMovies() = remoteSource.fetchNowPlayingMovies()

    suspend fun searchMovies(query: String) = remoteSource.searchMovies(query)
}