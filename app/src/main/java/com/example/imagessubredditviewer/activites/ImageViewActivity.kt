package com.example.imagessubredditviewer.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.imagessubredditviewer.R
import com.imageloader.MainActivity
import kotlinx.android.synthetic.main.activity_image_view.*

class ImageViewActivity : AppCompatActivity(), View.OnClickListener {

    var imgLoaderLibrary : MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)

        initMethod()
    }

    private fun initMethod() {
        imgLoaderLibrary = MainActivity()
        val url = intent.getStringExtra("imageUrl")
        setImage(url)
        imgBack.setOnClickListener(this)
    }

    private fun setImage(url: String?) {
        if (url.isNullOrEmpty()){
            Toast.makeText(applicationContext, "Image url not found", Toast.LENGTH_SHORT).show()
        }else{
            imgLoaderLibrary?.ImageLoader(url,imgFullView)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.imgBack -> onBackPressed()
        }
    }
}