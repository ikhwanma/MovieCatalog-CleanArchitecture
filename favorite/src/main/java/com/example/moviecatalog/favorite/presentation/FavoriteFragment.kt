package com.example.moviecatalog.favorite.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviecatalog.R
import com.example.moviecatalog.core.ui.MovieAdapter
import com.example.moviecatalog.favorite.databinding.FragmentFavoriteBinding
import com.example.moviecatalog.favorite.di.favoriteModule
import com.example.moviecatalog.view.detail.DetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)
        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { movie ->
            val mBundle = Bundle()
            mBundle.putParcelable(DetailFragment.EXTRA_DATA, movie)
            Navigation.findNavController(requireView()).navigate(R.id.action_favoriteFragment_to_detailFragment, mBundle)
        }

        viewModel.favoriteMovie.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                movieAdapter.setData(it)
                with(binding.rvFavorite){
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter = movieAdapter
                }
            }
        }
    }

}