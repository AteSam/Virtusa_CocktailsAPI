package com.atessam.mycocktails.ui.favourite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.atessam.mycocktails.R
import com.atessam.mycocktails.core.Resource
import com.atessam.mycocktails.data.model.Cocktail
import com.atessam.mycocktails.databinding.FavouriteFragmentBinding
import com.atessam.mycocktails.presentation.MainViewModel
import com.atessam.mycocktails.utils.show
import com.atessam.mycocktails.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment(R.layout.favourite_fragment), FavouriteAdapter.OnCocktailClickListener {

    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var favouriteAdapter: FavouriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favouriteAdapter = FavouriteAdapter(requireContext(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val binding =FavouriteFragmentBinding.bind(view)

        binding.rvFavoritos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavoritos.adapter = favouriteAdapter

        viewModel.getFavoriteCocktails().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    if (result.data.isEmpty()) {
                        binding.emptyContainer.root.show()
                        return@Observer
                    }
                    favouriteAdapter.setCocktailList(result.data)
                }
                is Resource.Failure -> {
                    showToast("An error occurred ${result.exception}")
                }
            }
        })
    }
    override fun onCocktailClick(cocktail: Cocktail, position: Int) {
       findNavController().navigate(
           FavouriteFragmentDirections.actionFavoritosFragmentToDetailFragment(cocktail)

       )
    }

    override fun onCocktailLongClick(cocktail: Cocktail, position: Int) {
        viewModel.deleteFavoriteCocktail(cocktail)
    }
}