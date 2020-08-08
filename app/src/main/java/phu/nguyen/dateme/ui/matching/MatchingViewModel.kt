package phu.nguyen.dateme.ui.matching

import androidx.lifecycle.ViewModel
import phu.nguyen.dateme.data.model.UserBasicInfo

class MatchingViewModel() : ViewModel() {

    private var _matchingUser: UserBasicInfo = UserBasicInfo()
    val matchingUser: UserBasicInfo
        get() = _matchingUser

    private var _myUser: UserBasicInfo = UserBasicInfo()
    val myUser: UserBasicInfo
        get() = _myUser

    fun setMyUser(myUser: UserBasicInfo) {
        _myUser = myUser
    }

    fun setMatchingUser(matchingUser: UserBasicInfo) {
        _matchingUser = matchingUser
    }
}