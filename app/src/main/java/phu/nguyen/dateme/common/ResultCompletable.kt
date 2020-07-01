package phu.nguyen.dateme.common

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class ResultCompletable {

    object Success : ResultCompletable()
    object Waiting : ResultCompletable()
    data class Error(val exception: Exception) : ResultCompletable()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success"
            is Error -> "Error[exception=$exception]"
            is Waiting -> "Waiting"
        }
    }
}