package com.sample.android.tmdb.ui.detail

import android.os.Bundle
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import com.sample.android.tmdb.R

class DetailActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var detailFragment: DetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        if (savedInstanceState == null) {

            supportFragmentManager.findFragmentById(R.id.fragment_container)
                    as DetailFragment? ?: detailFragment.also {
                supportFragmentManager.beginTransaction()
                        .add(R.id.fragment_container, it)
                        .commit()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        const val EXTRA_MOVIE = "movie"
    }
}