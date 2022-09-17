package com.monika.productlist.presentation.ui.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monika.productlist.data.model.ProductResponse
import com.monika.productlist.data.repository.ProductApiRepository
import com.monika.productlist.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productApiRepository: ProductApiRepository,
) : ViewModel() {

    private var productResponse = MutableLiveData<Resource<List<ProductResponse>>>()
    val products: LiveData<Resource<List<ProductResponse>>>
        get() = productResponse

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            productResponse.value = (Resource.loading(null))
            supervisorScope {
                val userResponseAsync =
                    async { productApiRepository.getProducts() }
                try {
                    productResponse.value = (Resource.success(userResponseAsync.await()))
                } catch (e: Exception) {
                    productResponse.value = (Resource.error(e.toString(), null))
                }

            }
        }
    }

}