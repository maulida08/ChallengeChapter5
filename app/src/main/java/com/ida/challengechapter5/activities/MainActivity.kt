package com.ida.challengechapter5.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ida.challengechapter5.R
import com.ida.challengechapter5.model.Result
import com.ida.challengechapter5.adapter.MainAdapter
import com.ida.challengechapter5.databinding.ActivityMainBinding
import com.ida.challengechapter5.model.GetAllMoviePopular
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

    private fun showListMoviePopular(data: List<Result>) {
        val adapter = MainAdapter(object : MainAdapter.OnClickListener{
            override fun onClickItem(data: Result) {
                val id = data.id
            }
        })
        adapter.submitData(data)
        binding.rvListPopuler.adapter = adapter
    }

}
