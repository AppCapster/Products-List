package com.monika.productlist.presentation.ui.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.monika.productlist.R
import com.monika.productlist.databinding.FragmentProductDetailsBinding
import com.monika.productlist.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentProductDetailsBinding>(R.layout.fragment_product_details) {

    private val viewModel: ProductDetailsViewModel by activityViewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override val defineBindingVariables: (FragmentProductDetailsBinding) -> Unit
        get() = { binding ->
            binding.fragment = this
            binding.lifecycleOwner = viewLifecycleOwner
            binding.viewModel = viewModel
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title.text = args.productDetails.title
        val price = "₹ ${args.productDetails.price}/-"
        binding.price.text = price
        binding.description.text = args.productDetails.description
        binding.category.text = args.productDetails.category
        Glide.with(requireContext())
            .load(args.productDetails.image)
            .into(binding.productImage)

    }
}