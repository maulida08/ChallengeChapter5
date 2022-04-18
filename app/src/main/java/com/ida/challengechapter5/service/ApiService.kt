package com.ida.challengechapter5.service

import com.ida.challengechapter5.model.GetAllMoviePopular
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular?api_key=7fd85d1ae16130aa2bbe3d705027b5be")
    fun getAllMoviePopular() : Call<GetAllMoviePopular>
}