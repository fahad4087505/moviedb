package com.example.moviedbapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbapp.BuildConfig
import com.example.moviedbapp.R
import com.example.moviedbapp.data.response.movies.Result
import kotlinx.android.synthetic.main.child_media_item.view.*

class MediaListAdapter(private val mResultList: List<Result>, private val click: ClickListener,private val context:Context) :
    RecyclerView.Adapter<MediaListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.child_media_item, parent, false)
           return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(!mResultList[position].media_type.equals("movie")){
            holder.mCarouselNameTextView.text = "${mResultList[position].name}"
        }else{
            holder.mCarouselNameTextView.text = "${mResultList[position].original_title}"
        }
        if(mResultList[position].media_type.equals("person")){
            Glide.with(context).load(BuildConfig.IMAGE_URL+mResultList[position].profile_path).into(holder.mCarouselImageView)
        }else{
            Glide.with(context).load(BuildConfig.IMAGE_URL+mResultList[position].poster_path).into(holder.mCarouselImageView)
        }
        holder.mCarouselCardView.setOnClickListener {
            click.onCarouselItemClick(mResultList[position])
        }
    }

    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return mResultList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mCarouselCardView: ConstraintLayout = itemView.container
        val mCarouselImageView: ImageView = itemView.media_image
        val mCarouselNameTextView: TextView = itemView.movie_name
    }

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface ClickListener {
        fun onCarouselItemClick(result: Result)
    }
}