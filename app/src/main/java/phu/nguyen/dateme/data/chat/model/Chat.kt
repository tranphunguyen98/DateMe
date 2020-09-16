package phu.nguyen.dateme.data.chat.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
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
) : Parcelable {
    companion object {
        const val OFFLINE = 0
        const val ONLINE = 1
    }

    val matchingTimeString: String
        get() {
            val format = SimpleDateFormat("ss:mm dd/MM/yyyy", Locale.ENGLISH)
            val time = Date(matchingTime)

            return format.format(time)
        }
}