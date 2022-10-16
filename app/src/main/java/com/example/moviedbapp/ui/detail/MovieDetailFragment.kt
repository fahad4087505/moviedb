package com.example.moviedbapp.ui.detail

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviedbapp.BuildConfig
import com.example.moviedbapp.base.BaseFragment
import com.example.moviedbapp.databinding.MovieDetailBinding
import com.example.moviedbapp.repository.EmptyRepository

class MovieDetailFragment : BaseFragment<MovieDetailBinding, EmptyRepository>() {
    val args: MovieDetailFragmentArgs by navArgs()
    var movieDetailBinding:MovieDetailBinding ?= null
    override fun getFragmentBinding(inflater: LayoutInflater,
        container: ViewGroup?): MovieDetailBinding {
        movieDetailBinding= MovieDetailBinding.inflate(inflater, container, false)
        return movieDetailBinding as MovieDetailBinding
    }

    override fun getRepository(): EmptyRepository {
        return EmptyRepository()
    }

    override fun onPostInit() {
        initView()
    }

    override fun onOptionsSelected(item: MenuItem) {
    }

    override fun onActivityCreation() {
    }

    private fun initView() {
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                isEnabled = false
                requireActivity().onBackPressed()
            }
        })
        movieDetailBinding?.backBtn?.setOnClickListener {
            requireActivity().onBackPressed()
        }
        movieDetailBinding?.playButton?.setOnClickListener {
            val action = MovieDetailFragmentDirections.actionToMovieDetailToPlayerScreen()
            findNavController().navigate(action)
        }
        movieDetailBinding?.movieImage?.let {
            if(args.movieDetail.media_type.equals("person")) {
                Glide.with(this).load(BuildConfig.IMAGE_URL+args.movieDetail.profile_path).into(it)
            }else{
                Glide.with(this).load(BuildConfig.IMAGE_URL+args.movieDetail.poster_path).into(it)
            }
        }
        if(!args.movieDetail.media_type.equals("movie")){
            movieDetailBinding?.content?.tvTitle?.text=args.movieDetail.name
        }else{
            movieDetailBinding?.content?.tvTitle?.text=args.movieDetail.title
        }
        if(args.movieDetail.media_type.equals("movie")||args.movieDetail.media_type.equals("tv")){
            movieDetailBinding?.playButton?.visibility= View.VISIBLE
        }
        movieDetailBinding?.content?.tvDescription?.text=args.movieDetail.overview
    }
}