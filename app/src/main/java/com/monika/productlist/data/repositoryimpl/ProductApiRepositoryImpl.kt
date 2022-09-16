package com.monika.productlist.data.repositoryimpl


import com.monika.productlist.data.model.ProductResponse
import com.monika.productlist.data.remote.api.ProductApi
import com.monika.productlist.data.repository.ProductApiRepository
import javax.inject.Inject

class ProductApiRepositoryImpl @Inject constructor(private val apiService: ProductApi) :
    ProductApiRepository {

    override suspend fun getProducts(): List<ProductResponse> {
        return apiService.getProductsResponse()
    }
}