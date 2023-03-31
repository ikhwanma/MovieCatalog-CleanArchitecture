package com.example.moviecatalog.view.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.moviecatalog.R
import com.example.moviecatalog.core.domain.model.Movie
import com.example.moviecatalog.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : Fragment(), View.OnClickListener {

    private val viewModel: DetailViewModel by viewModel()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var isFavorite = false
    private lateinit var data: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        data = requireArguments().getParcelable<Movie>(EXTRA_DATA) as Movie

        val baseUrl = "https://image.tmdb.org/t/p/w500/"
        val imgUrl = baseUrl + data.posterPath
        isFavorite = data.isFavorite

        setTextButton()

        with(binding) {
            tvName.text = data.originalTitle
            tvOverview.text = data.overview
            Glide.with(requireView()).load(imgUrl).into(ivMovie)
            btnFavorite.setOnClickListener(this@DetailFragment)
        }
    }

    private fun setTextButton() {
        if (isFavorite) {
            val txtButton = "Remove From Favorite"
            binding.btnFavorite.text = txtButton
        } else {
            val txtButton = "Add to Favorite"
            binding.btnFavorite.text = txtButton
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_favorite -> {
                isFavorite = if (isFavorite) {
                    viewModel.setFavorite(data, false)
                    Toast.makeText(requireContext(), "Removed From Favorite", Toast.LENGTH_SHORT)
                        .show()
                    setTextButton()
                    false
                } else {
                    viewModel.setFavorite(data, true)
                    Toast.makeText(requireContext(), "Added to Favorite", Toast.LENGTH_SHORT).show()
                    setTextButton()
                    true
                }
            }
        }
    }

}