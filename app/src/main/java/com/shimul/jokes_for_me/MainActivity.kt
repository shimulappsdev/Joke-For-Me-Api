package com.shimul.jokes_for_me

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.shimul.jokes_for_me.view.favourite.FavouriteJokeFragment
import com.shimul.jokes_for_me.view.home.JokeHomeFragment
import com.shimul.jokes_for_me.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationView: ChipNavigationBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationView = binding.bottomNavigation


        setSupportActionBar(binding.toolbar)
        binding.toolbar.setBackgroundColor(getColor(R.color.colorSecondaryDark))
        binding.toolbar.setTitleTextColor(getColor(R.color.colorSurface))

        navigationView.setOnItemSelectedListener { itemId ->
            when (itemId) {
                R.id.navigation_home -> {
                    loadFragment(JokeHomeFragment())
                    supportActionBar?.title = "Joke for me"
                    true
                }
                R.id.navigation_favorite -> {
                    loadFragment(FavouriteJokeFragment())
                    supportActionBar?.title = "Favorites Joke"
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            navigationView.setItemSelected(R.id.navigation_home, true)
            supportActionBar?.title = "Joke for me"
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.bottom_nav_container, fragment)
            .commit()
    }
}