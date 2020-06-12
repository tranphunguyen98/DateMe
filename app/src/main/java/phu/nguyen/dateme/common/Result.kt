package phu.nguyen.dateme.common

sealed class Result{
    data class Success<T>(val value: T) : Result()
    object Waiting : Result()
    data class Failure(val throwable: Throwable) : Result()
}