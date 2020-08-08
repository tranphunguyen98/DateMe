package phu.nguyen.dateme.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserBasicInfo(
    val name: String = "",
    val age: String = "",
    val school: String = "",
    val avatarImage: String = ""
) : Parcelable {
    val nameAndAge: String
        get() = "$name, $age"
}