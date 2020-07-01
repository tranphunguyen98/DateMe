package phu.nguyen.dateme.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Setting(
    val displayGenderObject: Int = 0,
    val displayRangeAgeMin: Float = 18f,
    val displayRangeAgeMax: Float = 22f,
    val displayRangeLocation: Float = 100f,
    val locations: Map<String, Boolean> = emptyMap(),
    val showGlobal: Boolean = true,
    val showMe: Boolean = true,
    val showNotification: Boolean = true
) : Parcelable {
    val rangeAgeMinAndMax: String
        get() {
            return if (displayRangeAgeMax.toInt() != 50) {
                "${displayRangeAgeMin.toInt()} - ${displayRangeAgeMax.toInt()}"
            } else {
                "${displayRangeAgeMin.toInt()} - 50+"
            }
        }

    val rangeLocationString: String
    get() = "${displayRangeLocation.toInt()} Km"
}