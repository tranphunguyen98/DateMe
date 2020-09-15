package phu.nguyen.dateme.data.chat.model

data class Message(
    val id: String = "",
    val timestamp: Long = 0,
    val status: Int = -1,
    val content: String = "",
    val contentType: String = "",
    val senderId: String = "",
    val mediaUrl: String = "",
    val mediaThumpUrl: String = ""
) {
    companion object {
        const val SEND_SUCCESS = 0
        const val RECEIVED = 1
        const val SEEN = 2
    }
}