package com.rizfan.moviews.favorite.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rizfan.moviews.di.FavoriteModuleDependencies
import com.rizfan.moviews.favorite.adapter.FavoriteAdapter
import com.rizfan.moviews.favorite.databinding.ActivityFavoriteBinding
import com.rizfan.moviews.favorite.di.DaggerFavoriteComponent
import com.rizfan.moviews.favorite.di.ViewModelFactory
import com.rizfan.moviews.ui.detail.DetailMovieActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class FavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)

        supportActionBar?.title = "Favorite Movies"
        setContentView(binding.root)

        val favoriteAdapter = FavoriteAdapter()
        with(binding.rvFavorite) {
            layoutManager = GridLayoutManager(this@FavoriteActivity, 2)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
        favoriteAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }
        viewModel.favoriteMovies.observe(this) {movie ->
            favoriteAdapter.setData(movie)
        }
    }
}