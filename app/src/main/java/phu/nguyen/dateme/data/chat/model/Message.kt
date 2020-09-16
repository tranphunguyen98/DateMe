package phu.nguyen.dateme.data.chat.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Message(
    val id: String = "",
    val timestamp: Long = 0,
    val status: Int = -1,
    val content: String = "",
    val contentType: String = "",
    val senderId: String = "",
    val mediaUrl: String = "",
    val mediaThumpUrl: String = ""
) : Parcelable {
    companion object {
        const val SEND_SUCCESS = 0
        const val RECEIVED = 1
        const val SEEN = 2
    }
}