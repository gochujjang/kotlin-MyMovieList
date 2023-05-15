package com.eldwn.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListMoviesAdapter(private val listMovies: ArrayList<Movies>) : RecyclerView.Adapter<ListMoviesAdapter.ListViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_movies, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, rating, release, genre, duration, director, writer, stars, synopsis, movLink, photo) = listMovies[position]
        val context = holder.itemView.context

        Glide.with(holder.itemView.context)
            .load(photo) // url gambar
            .into(holder.imgPhoto) // imageView mana yang akan diterapkan
        holder.tvName.text = name
        holder.tvRating.text = rating
        holder.tvSynopsis.text = synopsis

        holder.itemView.setOnClickListener {
            val moveDetail = Intent(context, DetailActivity::class.java)
            moveDetail.putExtra(DetailActivity.EXTRA_NAME, name)
            moveDetail.putExtra(DetailActivity.EXTRA_RATING, rating)
            moveDetail.putExtra(DetailActivity.EXTRA_REL, release)
            moveDetail.putExtra(DetailActivity.EXTRA_GEN, genre)
            moveDetail.putExtra(DetailActivity.EXTRA_DUR, duration)
            moveDetail.putExtra(DetailActivity.EXTRA_DIR, director)
            moveDetail.putExtra(DetailActivity.EXTRA_WRI, writer)
            moveDetail.putExtra(DetailActivity.EXTRA_STARS, stars)
            moveDetail.putExtra(DetailActivity.EXTRA_SYNOP, synopsis)
            moveDetail.putExtra(DetailActivity.EXTRA_COVER, photo)
            moveDetail.putExtra(DetailActivity.EXTRA_LINK, movLink)
            context.startActivity(moveDetail)
        }
    }

    override fun getItemCount(): Int = listMovies.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.iv_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvRating: TextView = itemView.findViewById(R.id.tv_item_rating)
        val tvSynopsis: TextView = itemView.findViewById(R.id.tv_item_synopsis)

    }

}