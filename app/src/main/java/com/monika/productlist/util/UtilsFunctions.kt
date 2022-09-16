package com.monika.productlist.util

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun showToast(message:String,context:Context){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}

