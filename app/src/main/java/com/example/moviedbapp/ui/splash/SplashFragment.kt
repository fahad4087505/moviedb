package com.example.moviedbapp.ui.splash

import android.os.Handler
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.moviedbapp.R
import com.example.moviedbapp.base.BaseFragment
import com.example.moviedbapp.databinding.FragmentSplashBinding
import com.example.moviedbapp.repository.EmptyRepository

class SplashFragment : BaseFragment<FragmentSplashBinding, EmptyRepository>() {
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun getRepository(): EmptyRepository {
        return EmptyRepository()
    }

    override fun onPostInit() {
        goToNextScreen()
    }

    override fun onOptionsSelected(item: MenuItem) {
    }

    override fun onActivityCreation() {
    }

    private fun goToNextScreen() {
        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_quizModeFragment)

        }, 3000)

    }
}