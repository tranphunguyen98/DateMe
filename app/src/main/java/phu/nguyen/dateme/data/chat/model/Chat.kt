package phu.nguyen.dateme.data.chat.model

data class Chat(
    val id: String = "",
    val ids: List<String> = emptyList(),
    val new: Boolean = false,
    val matchingTime: Long = 0,
    val matchingAvatar: String = "",
    val matchingName: String = "",
    val onlineStatus: Int = 0,
    val timeOffline: Long = 0,
    val messages: List<Message> = emptyList()
) {
    companion object {
        const val OFFLINE = 0
        const val ONLINE = 1
    }
}