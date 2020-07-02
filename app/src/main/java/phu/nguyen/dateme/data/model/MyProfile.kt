package phu.nguyen.dateme.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyProfile(
    val uid: String = "",
    val name: String = "",
    val images: List<String> = emptyList(),
    val introduction: String = "",
    val school: String = "",
    val job: String = "",
    val organization: String = "",
    val hobby: String = "",
    val birthday: String = "",
    val gender: Int = 0,
    val notShowAge: Boolean = false,
    val notShowLocation: Boolean = false
) : Parcelable