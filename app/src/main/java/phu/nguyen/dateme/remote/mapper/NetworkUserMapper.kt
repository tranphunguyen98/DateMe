package phu.nguyen.dateme.remote.mapper

import phu.nguyen.dateme.data.model.MyProfile
import phu.nguyen.dateme.data.model.Setting
import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.data.model.UserBasicInfo
import phu.nguyen.dateme.remote.model.NetworkUser
import javax.inject.Inject

class NetworkUserMapper @Inject constructor() : NetworkMapper<NetworkUser, User> {
    override fun mapFromRemote(type: NetworkUser): User {

        val setting = Setting(
            displayGenderObject = type.displayGenderObject,
            displayRangeLocation = type.displayRangeLocation.toFloat(),
            displayRangeAgeMax = type.displayRangeAgeMax.toFloat(),
            displayRangeAgeMin = type.displayRangeAgeMin.toFloat(),
            showGlobal = type.showGlobal,
            locations = type.locations,
            showMe = type.showMe,
            showNotification = type.showNotification
        )

        val userBasicInfo = UserBasicInfo(
            age = 22.toString(),
            avatarImage = if (type.images.isNotEmpty() ) type.images[0] else "",
            name = type.name,
            school = type.school
        )

        val myProfile = MyProfile(
            uid = type.uid,
            school = type.school,
            name = type.name,
            birthday = type.birthday,
            gender = type.gender,
            hobby = type.hobby,
            images = type.images,
            introduction = type.introduction,
            job = type.job,
            notShowAge = type.notShowAge,
            notShowLocation = type.notShowLocation,
            organization = type.organization
        )

        return User(myProfile,setting,userBasicInfo)
    }
}