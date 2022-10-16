package com.example.moviedbapp.ui.player

import android.net.Uri
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.devbrackets.android.exomedia.listener.OnPreparedListener
import com.example.moviedbapp.base.BaseFragment
import com.example.moviedbapp.databinding.FragmentVideoPlayerBinding
import com.example.moviedbapp.repository.EmptyRepository


class PlayerFragment : BaseFragment<FragmentVideoPlayerBinding, EmptyRepository>(),
    OnPreparedListener {

    var fragmentVideoPlayerBinding: FragmentVideoPlayerBinding?= null
    override fun getFragmentBinding(inflater: LayoutInflater,
                                    container: ViewGroup?): FragmentVideoPlayerBinding {
        fragmentVideoPlayerBinding= FragmentVideoPlayerBinding.inflate(inflater, container, false)
        return fragmentVideoPlayerBinding as FragmentVideoPlayerBinding
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
        setupVideoView()
    }
    private fun setupVideoView() {
        // Make sure to use the correct VideoView import
        fragmentVideoPlayerBinding?.videoView?.setOnPreparedListener(this)

        //For now we just picked an arbitrary item to play
        fragmentVideoPlayerBinding?.videoView?.setVideoURI(Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"))
    }

    override fun onPrepared() {
        fragmentVideoPlayerBinding?.videoView?.start()
    }

    override fun onStop() {
        super.onStop()
        if(fragmentVideoPlayerBinding?.videoView?.isPlaying?:false){
            fragmentVideoPlayerBinding?.videoView?.stopPlayback()
        }
    }
}