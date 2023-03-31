package com.example.moviecatalog.view.home

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.core.data.Resource
import com.example.moviecatalog.core.ui.MovieAdapter
import com.example.moviecatalog.databinding.FragmentHomeBinding
import com.example.moviecatalog.view.detail.DetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


@Suppress("RedundantWith")
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app: ApplicationInfo = requireActivity().packageManager
            .getApplicationInfo(requireActivity().packageName, PackageManager.GET_META_DATA)
        val bundle = app.metaData
        val apiKey = bundle.getString("apiKey")

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { movie ->
            val mBundle = Bundle()
            mBundle.putParcelable(DetailFragment.EXTRA_DATA, movie)
            Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_detailFragment, mBundle)
        }

        viewModel.getAllPopularMovie(apiKey!!).observe(viewLifecycleOwner){ movie ->
            if (movie != null){
                when(movie){
                    is Resource.Loading -> {
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        binding.progressCircular.visibility = View.GONE
                        Toast.makeText(requireContext(), movie.message, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Success -> {
                        binding.progressCircular.visibility = View.GONE
                        movieAdapter.setData(movie.data)
                        Log.d("datamovie", movie.data.toString())
                    }
                }
            }

            with(binding.rvMovie){
                layoutManager = GridLayoutManager(requireContext(), 2)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}