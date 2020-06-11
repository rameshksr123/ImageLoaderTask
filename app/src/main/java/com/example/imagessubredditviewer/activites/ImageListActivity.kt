package com.example.imagessubredditviewer.activites

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagessubredditviewer.R
import com.example.imagessubredditviewer.`interface`.OnClickEvent
import com.example.imagessubredditviewer.adapter.ImageViewAdapter
import com.example.imagessubredditviewer.apicall.APIInterface
import com.example.imagessubredditviewer.model.ImageListResponse
import com.example.imagessubredditviewer.model.Images
import com.example.imagessubredditviewer.webservice.APIClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageListActivity : AppCompatActivity(), OnClickEvent {

    var apiInterface: APIInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMethod()
    }

    private fun initMethod() {
        apiInterface = APIClient.client?.create(APIInterface::class.java)
        if (isNetworkAvailable()){
            getImageList()
        }else{
            Toast.makeText(applicationContext, "Check network connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getImageList() {
        progress.isVisible = true
        val call: Call<ImageListResponse?>? = apiInterface?.doGetImageList()
        call?.enqueue(object : Callback<ImageListResponse?> {

            override fun onResponse(call: Call<ImageListResponse?>?, response: Response<ImageListResponse?>) {
                progress.isVisible = false
                if (response.isSuccessful){
                    setAdapter(response.body()?.images)
                }
            }

            override fun onFailure(call: Call<ImageListResponse?>, t: Throwable?) {
                progress.isVisible = false
                Log.e("Error", t?.message.toString())
            }
        })
    }

    private fun setAdapter(imgList: ArrayList<Images>?) {
        imgRecyclerview.adapter = ImageViewAdapter(imgList,this)
        imgRecyclerview.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        imgRecyclerview.setHasFixedSize(true)
    }

    override fun onClick(imgUrl: String?) {
        val intent = Intent(applicationContext,ImageViewActivity::class.java)
        intent.putExtra("imageUrl",imgUrl)
        startActivity(intent)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}