package phu.nguyen.dateme.data.model

data class Matching(
    val uid: String = "",
    val typeSwipe: Int = -1,
    val wasMatched: Boolean = false
) {
    companion object {
        const val DISLIKE = 0
        const val LIKE = 1
        const val SUPER_LIKE = 2
    }
}