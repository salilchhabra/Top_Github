package com.example.topgithub.base

import com.example.topgithub.model.Items

sealed class Result<out T : Any> {

    class Success<out T : List<Items>>(val data: T) : Result<T>()

    class Error(val exception: String) : Result<Nothing>()
}