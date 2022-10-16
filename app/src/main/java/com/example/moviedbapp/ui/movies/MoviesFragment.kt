package com.example.moviedbapp.ui.movies
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbapp.BuildConfig
import com.example.moviedbapp.adapter.MovieListAdapter
import com.example.moviedbapp.adapter.MediaListAdapter
import com.example.moviedbapp.base.BaseFragment
import com.example.moviedbapp.data.response.movies.MoviesModel
import com.example.moviedbapp.databinding.FragmentMovieSearchBinding
import com.example.moviedbapp.datasource.MoviesDataSource
import com.example.moviedbapp.network.Resource
import com.example.moviedbapp.repository.MoviesRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMovieSearchBinding, MoviesRepository>() , MovieListAdapter.ClickListener, MediaListAdapter.ClickListener {
    @Inject
    lateinit var dataSource: MoviesDataSource
    private lateinit var viewModel: MoviesViewModel
    private var mediaArrayList=ArrayList<MoviesModel>()
    private var fragmentCallBinding:FragmentMovieSearchBinding ? =null
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMovieSearchBinding {
        fragmentCallBinding= FragmentMovieSearchBinding.inflate(inflater, container, false)
        return fragmentCallBinding as FragmentMovieSearchBinding
    }

    override fun getRepository(): MoviesRepository {
        return MoviesRepository(dataSource)
    }

    private fun init() {
        fragmentCallBinding?.searchEditText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                searchMovie(s.toString())
            }
        })

    }

    override fun onPostInit() {
        viewModel = ViewModelProvider(this, factory).get(MoviesViewModel::class.java)
        init()
        setAdapter()
    }

    private fun searchMovie(query:String){
        val hashMap = HashMap<String, String>()
        hashMap["api_key"] = BuildConfig.API_KEY
        hashMap["query"] = query
        progressBar.show(requireContext())
        viewModel.fetchMoviesList(hashMap)
        setUpObserver()
    }

    override fun onOptionsSelected(item: MenuItem) {
    }

    override fun onActivityCreation() {
    }

    private fun setUpObserver() {
        viewModel._moviesMutableList.observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progressBar.dialog.dismiss()
                    mediaArrayList.clear()
                  val result=  it.data?.results?.groupBy { it -> it.media_type }
                    result?.forEach { it ->
                        mediaArrayList.add(MoviesModel(it.key,ArrayList(it.value)))
                    }
                    fragmentCallBinding?.callRecyclerView?.adapter?.notifyDataSetChanged()
                }
                Resource.Status.ERROR -> {
                    progressBar.dialog.dismiss()
                    Toast.makeText(requireContext(), it.responseError?.errorMessage, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                }
            }
        }
    }
    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    private fun setAdapter() {
        // initialize  layout manager
        LinearLayoutManager(requireActivity(),  RecyclerView.VERTICAL, false).apply {
            // specify the layout manager for recycler view
            fragmentCallBinding?.callRecyclerView?.layoutManager = this
        }
        // finally, data bind the recycler view with adapter
        fragmentCallBinding?.callRecyclerView?.adapter = MovieListAdapter(mediaArrayList, this)
    }

    override fun onItemClick(position: Int) {
    }

    override fun setNestedList(position: Int,recyclerView: RecyclerView) {
        setNestedListAdapter(recyclerView, position)
    }

    private fun setNestedListAdapter(recyclerView: RecyclerView?,position: Int) {
        // initialize  layout manager
        LinearLayoutManager(context,  RecyclerView.HORIZONTAL, false).apply {
            // specify the layout manager for recycler view
            recyclerView?.layoutManager = this
        }
        // finally, data bind the recycler view with adapter
        recyclerView?.adapter = MediaListAdapter(mediaArrayList.get(position).mediaArrayList, this,requireContext())
    }

    override fun onCarouselItemClick(result: com.example.moviedbapp.data.response.movies.Result) {
        val action = MoviesFragmentDirections.actionToMovieDetailFragment(result)
        findNavController().navigate(action)
    }
}