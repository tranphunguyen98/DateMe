package phu.nguyen.dateme.remote.model

data class NetworkInteraction(
    val uid: String = "",
    val interactiveType: Int = -1,
    val match: Boolean = false
)