package com.monika.productlist.presentation.ui.productlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.monika.productlist.R
import com.monika.productlist.databinding.FragmentProductsBinding
import com.monika.productlist.presentation.adapter.ProductsAdapter
import com.monika.productlist.presentation.ui.base.BaseFragment
import com.monika.productlist.util.NetworkHelper
import com.monika.productlist.util.Status
import com.monika.productlist.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductsFragment : BaseFragment<FragmentProductsBinding>(R.layout.fragment_products) {

    private val viewModel: ProductsViewModel by activityViewModels()
    private var adapter: ProductsAdapter = ProductsAdapter()

    @Inject
    lateinit var networkHelper: NetworkHelper

    override val defineBindingVariables: (FragmentProductsBinding) -> Unit
        get() = { binding ->
            binding.fragment = this
            binding.lifecycleOwner = viewLifecycleOwner
            binding.viewModel = viewModel
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.products.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    binding.swipeRefresh.isRefreshing = true
                }
                Status.SUCCESS -> {
                    adapter.setUserList(emptyList())
                    binding.recyclerView.adapter = adapter
                    binding.swipeRefresh.isRefreshing = false
                    it.data?.let { data ->
                        adapter.setUserList(data)
                    }
                }
                Status.ERROR -> {
                    binding.swipeRefresh.isRefreshing = false
                    if (!networkHelper.isNetworkConnected()) {
                        showToast("Check your network connection", requireContext())
                    } else {
                        showToast(it.message.toString(), requireContext())
                    }
                }
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchUsers()
        }
    }
}