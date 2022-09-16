package com.monika.productlist.data.repository

import com.monika.productlist.data.model.ProductResponse

interface ProductApiRepository {

    suspend fun getProducts(): List<ProductResponse>

}