package phu.nguyen.dateme.remote.model

import phu.nguyen.dateme.data.chat.model.Message

data class NetworkChat(
    val id: String = "",
    val ids: List<String> = emptyList(),
    val new: Boolean = false,
    val matchingTime: Long = 0,
    val matchingAvatar: String = "",
    val matchingName: String = "",
    val onlineStatus: Int = 0,
    val timeOffline: Long = 0,
    val messages: List<Message> = emptyList()
)