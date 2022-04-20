package com.ida.challengechapter5.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ida.challengechapter5.R
import com.ida.challengechapter5.activities.MainActivity.Companion.ID
import com.ida.challengechapter5.adapter.PopularAdapter
import com.ida.challengechapter5.databinding.ActivityDetailBinding
import com.ida.challengechapter5.model.BelongsToCollection
import com.ida.challengechapter5.model.GetAllDetail
import com.ida.challengechapter5.model.GetAllMoviePopular
import com.ida.challengechapter5.model.Result
import com.ida.challengechapter5.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val id = intent.getIntExtra(ID,0)
        getDetail(id)
    }

    private val IMAGE_BASE ="https://image.tmdb.org/t/p/w500/"

    private fun getDetail(id: Int) {
        ApiClient.instance.getALLDetail(id)
            .enqueue(object: Callback<GetAllDetail> {
                override fun onResponse(
                    call: Call<GetAllDetail>,
                    response: Response<GetAllDetail>
                ) {
                    val body = response.body()
                    val code = response.code()
                    if (code == 200) {
                        binding.tvTittle.text = body!!.originalTitle
                        binding.tvDesc.text = body!!.overview
                        Glide.with(binding.root).load(IMAGE_BASE + body.backdropPath)
                            .into(binding.ivImage)
                        Toast.makeText(this@DetailActivity, "Success", Toast.LENGTH_SHORT).show()
                    }
                }
                    override fun onFailure(call: Call<GetAllDetail>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
            })
    }

}