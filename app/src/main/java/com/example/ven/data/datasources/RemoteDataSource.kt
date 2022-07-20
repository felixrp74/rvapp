package com.example.ven.data.datasources

import com.example.ven.domain.Movie

interface RemoteDataSource{
    fun getPopularMovie():List<Movie>
}