package phu.nguyen.dateme.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NetworkUser(
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
    val notShowLocation: Boolean = false,
    val displayGenderObject: Int = 0,
    val displayRangeAgeMin: Int = 18,
    val displayRangeAgeMax: Int = 22,
    val displayRangeLocation: Int = 100,
    val locations : Map<String, Boolean> = emptyMap(),
    val showGlobal : Boolean = true,
    val showMe : Boolean = true,
    val showNotification: Boolean = true
) : Parcelable