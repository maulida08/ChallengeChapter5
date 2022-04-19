package com.ida.challengechapter5.service

import com.ida.challengechapter5.model.GetAllDetail
import com.ida.challengechapter5.model.GetAllMoviePopular
import com.ida.challengechapter5.model.GetAllMovieTop
import com.ida.challengechapter5.model.GetAllMovieUpcoming
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular?api_key=7fd85d1ae16130aa2bbe3d705027b5be")
    fun getAllMoviePopular() : Call<GetAllMoviePopular>

    @GET("movie/upcoming?api_key=7fd85d1ae16130aa2bbe3d705027b5be")
    fun getAllMovieUpcoming() : Call<GetAllMovieUpcoming>

    @GET("movie/top_rated?api_key=7fd85d1ae16130aa2bbe3d705027b5be")
    fun getAllMovieTop() : Call<GetAllMovieTop>

    @GET("movie/:movie_id")
    fun getDetail() : Call<GetAllDetail>
}