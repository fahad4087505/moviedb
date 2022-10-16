package com.example.moviedbapp
import android.app.Application
import android.content.Context
import com.example.moviedbapp.helper.SharedPrefrencesHelper
import com.example.moviedbapp.util.Util.startNetworkCallback
import dagger.hilt.android.HiltAndroidApp
@HiltAndroidApp
class MyApplication: Application() {
  companion object{
    lateinit var context: Context
  }
  override fun onCreate() {
    super.onCreate()

    context = this
    SharedPrefrencesHelper.init(this)
    /*
    * Initiate Internet Availability check
    * */
    startNetworkCallback()
  }
}