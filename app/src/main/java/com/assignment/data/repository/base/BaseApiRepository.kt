package com.assignment.data.repository.base

import com.assignment.data.retrofit.ApiService
import com.assignment.data.retrofit.ApiServiceBuilder
import com.assignment.domain.entities.Result
import com.assignment.domain.exceptions.ApiException
import retrofit2.Response


open class BaseApiRepository {

    open var api: ApiService = ApiServiceBuilder.build()
    internal fun <T, R> parseResult(
        response: Response<T>,
        parser: (T) -> R
    ): Result<R> {
        return if (response.isSuccessful && response.body() !== null)
            Result.success(parser.invoke(response.body()!!))
        else
            Result.error(ApiException(response.errorBody()?.toString()))
    }
}

