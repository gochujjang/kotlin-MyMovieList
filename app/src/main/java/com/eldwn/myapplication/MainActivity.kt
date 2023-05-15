package com.eldwn.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvMovies: RecyclerView
    private val list = ArrayList<Movies>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMovies   = findViewById(R.id.rv_movies)
        rvMovies.setHasFixedSize(true)

        list.addAll(MoviesData.listData)
        showRecyclerList()
    }



    private fun showRecyclerList() {
        rvMovies.layoutManager = LinearLayoutManager(this)
        val listMoviesAdapter = ListMoviesAdapter(list)
        rvMovies.adapter = listMoviesAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        chooseMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun chooseMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.about_page -> {
                val moveAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveAbout)
            }
        }
    }


    private fun showSelectedMovies(movies: Movies){
        Toast.makeText(this, "Kamu memilih " + movies.name, Toast.LENGTH_SHORT).show()
    }
}
