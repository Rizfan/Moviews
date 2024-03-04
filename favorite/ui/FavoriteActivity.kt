package com.rizfan.moview.favorite.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rizfan.moview.di.FavoriteModuleDependencies
import com.rizfan.moview.favorite.adapter.FavoriteAdapter
import com.rizfan.moview.favorite.databinding.ActivityFavoriteBinding
import com.rizfan.moview.favorite.di.DaggerFavoriteComponent
import com.rizfan.moview.favorite.di.ViewModelFactory
import com.rizfan.moview.ui.detail.DetailMovieActivity
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

        setContentView(binding.root)

        val favoriteAdapter = FavoriteAdapter()
        with(binding.rvFavorite) {
            layoutManager = GridLayoutManager(context, 2)
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