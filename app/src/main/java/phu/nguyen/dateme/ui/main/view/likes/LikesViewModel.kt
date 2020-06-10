package phu.nguyen.dateme.ui.main.view.likes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LikesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is likes Fragment"
    }
    val text: LiveData<String> = _text
}