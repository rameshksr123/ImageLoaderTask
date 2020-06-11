package com.example.imagessubredditviewer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagessubredditviewer.R
import com.example.imagessubredditviewer.`interface`.OnClickEvent
import com.example.imagessubredditviewer.model.Images
import com.imageloader.MainActivity
import kotlinx.android.synthetic.main.item_image.view.*


class ImageViewAdapter(var imgList: ArrayList<Images>?, var click:OnClickEvent) : RecyclerView.Adapter<ImageViewAdapter.ViewHolder>() {

    var imgLoaderLibrary : MainActivity? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return imgList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        imgLoaderLibrary = MainActivity()
        imgLoaderLibrary?.ImageLoader(imgList!![position].url,holder.itemView.natureImage)
        holder.itemView.natureImage.setOnClickListener {
            click.onClick(imgList!![position].url)
        }
    }

    class ViewHolder (v:View) : RecyclerView.ViewHolder(v){

    }
}