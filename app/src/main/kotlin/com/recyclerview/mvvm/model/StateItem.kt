package com.recyclerview.mvvm.model

import java.io.Serializable

class StateItem(
    val id: Int,
    val name: String,
    val state_code: String
): Serializable {
    fun getStateValue(): String {
        return "State Code : " + state_code
    }
}