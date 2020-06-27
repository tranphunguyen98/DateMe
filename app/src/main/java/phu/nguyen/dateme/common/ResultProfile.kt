package phu.nguyen.dateme.common

import phu.nguyen.dateme.data.model.SwipeProfile

sealed class ResultProfile{
    data class Success(val swipeProfiles : MutableList<SwipeProfile>) : ResultProfile()
    object Waiting : ResultProfile()
    data class Failure(val throwable: Throwable) : ResultProfile()
}