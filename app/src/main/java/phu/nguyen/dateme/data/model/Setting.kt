package phu.nguyen.dateme.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Setting(
    val displayGenderObject: Int = 0,
    val displayRangeAgeMin: Int = 18,
    val displayRangeAgeMax: Int = 22,
    val displayRangeLocation: Int = 100,
    val locations : Map<String, Boolean> = emptyMap(),
    val isGlobal : Boolean = true,
    val showMe : Boolean = true,
    val showNotification: Boolean = true
): Parcelable