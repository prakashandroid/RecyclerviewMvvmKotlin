package com.recyclerview.mvvm.model

import java.io.Serializable

class CountryItem(
    val name: String,
    val code: String,
    val states: List<StateItem>
) : Serializable {
    fun getCountryCodeStateCount(): String {
        var value = "Code : " + code
        if (states != null) {
            value += " & Total State : " + states.size
        }
        return value
    }
}