package com.ida.challengechapter5.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ida.challengechapter5.adapter.PopularAdapter
import com.ida.challengechapter5.adapter.TopAdapter
import com.ida.challengechapter5.adapter.UpcomingAdapter
import com.ida.challengechapter5.databinding.ActivityMainBinding
import com.ida.challengechapter5.model.*
import com.ida.challengechapter5.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchAllMoviePopular()
        fetchAllMovieUpcoming()
        fetchAllMovieTop()
    }

    private fun fetchAllMoviePopular(){
        ApiClient.instance.getAllMoviePopular()
            .enqueue(object : Callback<GetAllMoviePopular> {

                override fun onResponse(
                    call: Call<GetAllMoviePopular>,
                    response: Response<GetAllMoviePopular>
                ) {
                    val body = response.body()
                    val code = response.code()
                    if (code == 200){
                        body?.let { showListMoviePopular(it.results)
                    }

                    }

                }

                override fun onFailure(call: Call<GetAllMoviePopular>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun fetchAllMovieUpcoming(){
        ApiClient.instance.getAllMovieUpcoming()
            .enqueue(object : Callback<GetAllMovieUpcoming> {

                override fun onResponse(
                    call: Call<GetAllMovieUpcoming>,
                    response: Response<GetAllMovieUpcoming>
                ) {
                    val body = response.body()
                    val code = response.code()
                    if (code == 200){
                        body?.let { showListMovieUpcoming(it.results)
                        }

                    }

                }

                override fun onFailure(call: Call<GetAllMovieUpcoming>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun fetchAllMovieTop(){
        ApiClient.instance.getAllMovieTop()
            .enqueue(object : Callback<GetAllMovieTop> {

                override fun onResponse(
                    call: Call<GetAllMovieTop>,
                    response: Response<GetAllMovieTop>
                ) {
                    val body = response.body()
                    val code = response.code()
                    if (code == 200){
                        body?.let { showListMovieTop(it.results)
                        }

                    }

                }

                override fun onFailure(call: Call<GetAllMovieTop>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }


    private fun showListMoviePopular(data: List<Result>) {
        val adapter = PopularAdapter(object : PopularAdapter.OnClickListener{
            override fun onClickItem(data: Result) {
                val id = data.id
            }
        })
        adapter.submitData(data)
        binding.rvListPopular.adapter = adapter
    }

    private fun showListMovieUpcoming(data: List<ResultX>) {
        val adapter = UpcomingAdapter(object : UpcomingAdapter.OnClickListener{
            override fun onClickItem(data: ResultX) {
                val id = data.id
            }
        })
        adapter.submitData(data)
        binding.rvUpcoming.adapter = adapter
    }


    private fun showListMovieTop(data: List<ResultXX>) {
        val adapter = TopAdapter(object : TopAdapter.OnClickListener{
            override fun onClickItem(data: ResultXX) {
                val id = data.id
            }
        })
        adapter.submitData(data)
        binding.rvListTopRated.adapter = adapter
    }


}
