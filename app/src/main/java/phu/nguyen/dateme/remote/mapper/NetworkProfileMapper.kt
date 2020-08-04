package phu.nguyen.dateme.remote.mapper

import phu.nguyen.dateme.data.model.SwipeProfile
import phu.nguyen.dateme.remote.model.NetworkProfile
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NetworkProfileMapper @Inject constructor() : NetworkMapper<NetworkProfile, SwipeProfile> {
    override fun mapFromRemote(type: NetworkProfile): SwipeProfile = SwipeProfile(
        type.uid,
        type.name + ",",
        convertDateToAge(type.birthday),
        type.introduction,
        type.images,
        type.latitude,
        type.longitude
    )

    private fun convertDateToAge(date: String): String {
        try {
            val dateFormatter = SimpleDateFormat("dd/mm/yyyy")
            val date = dateFormatter.parse(date)
            return (Date().year - date?.year!!).toString()
        } catch (e: ParseException) {
            return ""
        }
    }
}