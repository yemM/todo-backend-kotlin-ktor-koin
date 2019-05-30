package com.todobackend

import java.util.*

data class Todo(
    val uid: String = UUID.randomUUID().toString(),
    val title: String,
    val order: Int,
    val completed: Boolean? = false
) {
    val url: String

    init {
        url = "${host}/${uid}"
    }

    companion object {
        var host: String = ""
    }
}