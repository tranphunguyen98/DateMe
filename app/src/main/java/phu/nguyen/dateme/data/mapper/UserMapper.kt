package phu.nguyen.dateme.data.mapper

import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.remote.model.NetworkUser
import javax.inject.Inject

class UserMapper @Inject constructor() : UIMapper<User,NetworkUser> {
    override fun mapFromUI(type: User): NetworkUser {
        return NetworkUser(
            uid = type.myProfile.uid,
            name = type.myProfile.name,
            images = type.myProfile.images,
            introduction = type.myProfile.introduction,
            school = type.myProfile.school,
            job = type.myProfile.job,
            organization = type.myProfile.organization,
            hobby = type.myProfile.hobby,
            birthday = type.myProfile.birthday,
            gender = type.myProfile.gender,
            notShowAge = type.myProfile.notShowAge,
            notShowLocation = type.myProfile.notShowLocation,
            displayGenderObject = type.setting.displayGenderObject,
            displayRangeAgeMin = type.setting.displayRangeAgeMin.toInt(),
            displayRangeAgeMax = type.setting.displayRangeAgeMax.toInt(),
            displayRangeLocation = type.setting.displayRangeLocation.toInt(),
            locations = type.setting.locations,
            showGlobal = type.setting.showGlobal,
            showMe = type.setting.showMe,
            showNotification = type.setting.showNotification
        )
    }

}