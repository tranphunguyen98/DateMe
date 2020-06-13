package phu.nguyen.dateme.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profile(
    val id: String,
    val name: String,
    val age: Int,
    val introduction: String,
    val images : List<String>,
    val latitude: Double,
    val longitude: Double
): Parcelable