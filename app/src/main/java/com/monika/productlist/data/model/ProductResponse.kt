package com.monika.productlist.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductResponse(
    @Json(name = "id") val id: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "price") val price: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "category") val category: String? = null,
    @Json(name = "image") val image: String? = null
) : Parcelable