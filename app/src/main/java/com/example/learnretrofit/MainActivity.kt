package com.example.learnretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var memeImageView: ImageView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memeImageView = findViewById(R.id.memeImageView)
        progressBar = findViewById(R.id.progressBar)

        loadMemeUsingRetrofit()
    }

    fun nextMeme(view: View) {
        loadMemeUsingRetrofit()
    }

    fun shareMeme(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Checkout this funny meme")
        val chooser = Intent.createChooser(intent, "Share this meme using")
        startActivity(chooser)
    }

    private fun loadMemeUsingRetrofit() {
        progressBar.visibility = View.VISIBLE
        RetrofitInstance.apiInterface.getMeme().enqueue(object : Callback<MemeData?> {
            override fun onResponse(call: Call<MemeData?>, response: Response<MemeData?>) {

                if (response.isSuccessful) {
                    val memeData = response.body()

                    //get the url of the image from the memedata
                    val imageurl = memeData?.preview?.get(2)

                    //glide
                    Glide.with(this@MainActivity).load(imageurl).into(memeImageView)
                    progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<MemeData?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: $t", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            }
        })
    }
}