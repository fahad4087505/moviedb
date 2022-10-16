package com.example.moviedbapp.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.R
import com.example.moviedbapp.data.response.movies.MoviesModel
import kotlinx.android.synthetic.main.item_movie_courasel.view.*

class MovieListAdapter(private val mMoviesList: ArrayList<MoviesModel>, private val click: ClickListener) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_courasel,parent,false)
        // return the view holder
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // display the current index
        holder.mNameTextView.text = "${mMoviesList[position].mediaType}"
        click.setNestedList(position,holder.mMediaItemRecyclerView)
        holder.linearLayout.setOnClickListener {
            click.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return mMoviesList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val mNameTextView: TextView = itemView.nameTextView
        val mMediaItemRecyclerView: RecyclerView = itemView.mediaItemRecyclerview
        val linearLayout: LinearLayout = itemView.linearLayout
    }

    // this two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }
    interface ClickListener {
        fun onItemClick(position: Int)
        fun setNestedList(position: Int,recyclerView: RecyclerView)
    }
}