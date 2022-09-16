package com.monika.productlist.data.remote.api

import com.monika.productlist.data.model.ProductResponse
import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    suspend fun getProductsResponse(): List<ProductResponse>

}