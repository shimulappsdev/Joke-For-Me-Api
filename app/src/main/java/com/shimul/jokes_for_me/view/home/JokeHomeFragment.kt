package com.shimul.jokes_for_me.view.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shimul.jokes_for_me.R
import com.shimul.jokes_for_me.R.drawable
import com.shimul.jokes_for_me.databinding.FragmentJokeHomeBinding

class JokeHomeFragment : Fragment() {

    private lateinit var binding: FragmentJokeHomeBinding
    private lateinit var viewModel : JokesHomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJokeHomeBinding.inflate(inflater, container, false)


        viewModel = ViewModelProvider(this)[JokesHomeViewModel::class.java]



        viewModel._jokesData.observe(viewLifecycleOwner, Observer { data ->

            binding.jokesCategory.text = data.category
            binding.jokeLanguage.text = data.lang

            if (data.type.toString().equals("twopart")){
                binding.jokesType.text = "Two Part"
                binding.setup.text = data.setup
                binding.delivery.text = data.delivery
            }else {
                binding.jokesType.text = "Single"
                binding.delivery.text = data.joke
                binding.setup.text = null
            }

            if (data.lang.toString().equals("en")){
                binding.jokeLanguage.text = "English"
            }else{
                binding.jokeLanguage.text = "Other"
            }

            if (data.safe.toString().equals("true")){
                binding.jokeMode.text = "Safe"
                binding.jokeMode.setTextColor(Color.GREEN)
            }else{
                binding.jokeMode.text = "Unsafe"
                binding.jokeMode.setTextColor(Color.RED)
            }

            data.flags?.apply {
                val flagsText = StringBuilder()
                if (explicit == true) flagsText.append("Explicit, ")
                if (nsfw == true) flagsText.append("NSFW, ")
                if (political == true) flagsText.append("Political, ")
                if (racist == true) flagsText.append("Racist, ")
                if (religious == true) flagsText.append("Religious, ")
                if (sexist == true) flagsText.append("Sexist, ")

                if (flagsText.isNotEmpty()) {
                    flagsText.delete(flagsText.length - 2, flagsText.length)
                }else {
                    flagsText.append("No tag")
                }

                binding.jokeTag.text = flagsText.toString()
            }


            showProgressBar(false)


        })

        binding.getJokeBtn.setOnClickListener(View.OnClickListener {
            showProgressBar(true)
            viewModel.getRandomJokes()
            binding.addFavouriteBtn.setImageResource(drawable.favourite_bottom)

        })

        binding.addFavouriteBtn.setOnClickListener(View.OnClickListener {
            binding.addFavouriteBtn.setImageResource(drawable.favourite_icon)
        })

        return binding.root
    }

    private fun showProgressBar(show: Boolean) {
        if (show) {
            binding.getJokeProgressBar.visibility = View.VISIBLE
        } else {
            binding.getJokeProgressBar.visibility = View.GONE
        }
    }


}