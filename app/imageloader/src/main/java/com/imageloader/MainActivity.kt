package com.imageloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.net.URL


open class MainActivity : AppCompatActivity() {

    var imgUrl:String? = ""
    var imageView : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // imgUrl = "https://clipartart.com/images/small-size-clipart-images-6.jpg"

      /*  if (imgUrl.isNullOrBlank()){
            Toast.makeText(applicationContext, "Image url not found", Toast.LENGTH_SHORT).show()
        }else{
            ImageLoad(image).execute(imgUrl)
        }*/
        ImageLoader(imgUrl!!,imageView)
    }

    fun ImageLoader(url: String?, imageView: ImageView?) {
        ImageLoad(imageView!!).execute(url)
    }

    class ImageLoad(var image: ImageView) : AsyncTask<String?, Int?, Bitmap>() {
        var bitmap: Bitmap? = null
        override fun doInBackground(vararg url: String?): Bitmap? {
            val urldisplay: String? = url[0]
            try {
                val inputStream  = URL(urldisplay).openStream()
                bitmap = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
                e.printStackTrace()
            }
            return bitmap!!
        }

        override fun onPostExecute(result: Bitmap) {
            image.setImageBitmap(result)
        }
    }
}