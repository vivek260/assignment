package com.assignment.domain.base

import com.assignment.domain.entities.Result
import kotlinx.coroutines.*

abstract class UseCase<T> {

    private var job: Job? = null
    abstract suspend fun makeRequest(): Result<T>

    fun execute(success: (T) -> Unit, failure: (Exception) -> Unit) {

        job = GlobalScope.launch {
            val result =
                try {
                    makeRequest()
                } catch (e: Exception) {
                    Result.error<T>(
                        if (e.message !== null) e else java.lang.Exception("Something went wrong at our end!")
                    )
                }
            withContext(Dispatchers.Main) {
                result.data?.let { success.invoke(it) }
            }
        }
    }

    fun cancel() = job?.cancel()

}