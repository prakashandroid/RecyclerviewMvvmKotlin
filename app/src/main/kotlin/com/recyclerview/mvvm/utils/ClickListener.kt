package com.recyclerview.mvvm.utils

import android.view.View
import android.widget.Toast
import com.recyclerview.mvvm.model.CountryItem

interface CountryItemClickListener {
    fun onItemClick(view: View, item: CountryItem)
}

fun showToastShort(msg: String) {
    Toast.makeText(MyApplication.getMyApplication(), msg, Toast.LENGTH_SHORT).show()
}