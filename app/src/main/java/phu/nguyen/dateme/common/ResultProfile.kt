package phu.nguyen.dateme.common

import phu.nguyen.dateme.data.model.Profile

sealed class ResultProfile{
    data class Success(val profiles : MutableList<Profile>) : ResultProfile()
    object Waiting : ResultProfile()
    data class Failure(val throwable: Throwable) : ResultProfile()
}