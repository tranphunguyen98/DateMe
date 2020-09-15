package phu.nguyen.dateme.remote.model

data class NetworkMessage(
    val id: String = "",
    val timestamp: Long = 0,
    val status: Int = -1,
    val content: String = "",
    val contentType: String = "",
    val senderId: String = "",
    val mediaUrl: String = "",
    val mediaThumpUrl: String = ""
)