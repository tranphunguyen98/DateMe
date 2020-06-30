package phu.nguyen.dateme.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val myProfile: MyProfile,
    val setting: Setting,
    val userBasicInfo: UserBasicInfo
): Parcelable