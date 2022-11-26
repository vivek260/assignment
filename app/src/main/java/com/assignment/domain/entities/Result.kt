package com.assignment.domain.entities

data class Result<out T>(val data: T?, val exception: Exception? = null) {

    companion object {
        fun <T> success(data: T): Result<T> {
            return Result(data)
        }

        fun <T> error(exception: Exception): Result<T> {
            return Result(null, exception)
        }
    }
}