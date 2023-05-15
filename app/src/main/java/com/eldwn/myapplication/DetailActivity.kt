package com.eldwn.myapplication

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_RATING = "extra_rating"
        const val EXTRA_REL = "extra_rel"
        const val EXTRA_GEN = "extra_gen"
        const val EXTRA_DUR = "extra_dur"
        const val EXTRA_DIR = "extra_dir"
        const val EXTRA_WRI = "extra_wri"
        const val EXTRA_STARS = "extra_stars"
        const val EXTRA_SYNOP = "extra_synop"
        const val EXTRA_COVER = "extra_cover"
        const val EXTRA_LINK = "extra_link"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val tvTitle:TextView = findViewById(R.id.tv_detail_title)
        val tvRating:TextView = findViewById(R.id.tv_detail_rating)
        val tvRelease:TextView = findViewById(R.id.tv_detail_release)
        val tvGenre:TextView = findViewById(R.id.tv_detail_genre)
        val tvDuration:TextView = findViewById(R.id.tv_detail_duration)
        val tvDirector:TextView = findViewById(R.id.tv_detail_director)
        val tvWriter:TextView = findViewById(R.id.tv_detail_writer)
        val tvStars:TextView = findViewById(R.id.tv_detail_stars)
        val tvSynopsis:TextView = findViewById(R.id.tv_detail_synopsis)
        val tvCover:ImageView = findViewById(R.id.iv_detail_cover)
        val btnShare:Button = findViewById(R.id.action_share)

        val getTitle = intent.getStringExtra(EXTRA_NAME)
        val getRating = intent.getStringExtra(EXTRA_RATING)
        val getRelease = intent.getStringExtra(EXTRA_REL)
        val getGenre = intent.getStringExtra(EXTRA_GEN)
        val getDuration = intent.getStringExtra(EXTRA_DUR)
        val getDirector = intent.getStringExtra(EXTRA_DIR)
        val getWriter = intent.getStringExtra(EXTRA_WRI)
        val getStars = intent.getStringExtra(EXTRA_STARS)
        val getSynop = intent.getStringExtra(EXTRA_SYNOP)
        val getCover = intent.getStringExtra(EXTRA_COVER)
        val getLink = intent.getStringExtra(EXTRA_LINK)

        val actionBar = supportActionBar
        actionBar!!.title = "$getTitle"
        actionBar.setDisplayHomeAsUpEnabled(true)


        tvTitle.text = getTitle
        tvRating.text = getRating
        tvRelease.text = getRelease
        tvGenre.text = getGenre
        tvDuration.text = getDuration
        tvDirector.text = getDirector
        tvWriter.text = getWriter
        tvStars.text = getStars
        tvSynopsis.text = getSynop
        Glide.with(this)
            .load(getCover)
            .apply(RequestOptions())
            .into(tvCover)


        // Share Button
        btnShare.setOnClickListener{
            val actShare = Intent()
            //Implicit Intent Share Text with Images
            val bitmapDrawable = tvCover!!.drawable as BitmapDrawable
            val bitmap = bitmapDrawable.bitmap
            val bitmapPath = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "$getTitle",
            null)
            val bitmapUri = Uri.parse(bitmapPath)

            actShare.action = Intent.ACTION_SEND
            actShare.putExtra(Intent.EXTRA_TEXT, "Hai! coba cek film ini deh,\n$getTitle\n$getLink")
            actShare.putExtra(Intent.EXTRA_STREAM, bitmapUri)
            actShare.type = "*/*"
            actShare.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            startActivity(Intent.createChooser(actShare, "Share Image"))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}