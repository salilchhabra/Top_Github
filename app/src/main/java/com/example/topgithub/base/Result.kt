package com.example.topgithub.base

sealed class Result<out T : Any> {

    class Success<out T : Any>(val data: T) : Result<T>()

    class Error(val exception: String) : Result<Nothing>()
}