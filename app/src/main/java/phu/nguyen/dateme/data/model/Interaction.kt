package phu.nguyen.dateme.data.model

data class Interaction(
    val uid: String = "",
    val typeSwipe: Int = -1,
    val match: Boolean = false
) {
    companion object {
        const val DISLIKE = 0
        const val LIKE = 1
        const val SUPER_LIKE = 2
        const val LIKE_YOU = 3
        const val VISIT_YOU = 4
    }
}