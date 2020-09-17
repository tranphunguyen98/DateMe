package phu.nguyen.dateme.ui.chatting

import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import phu.nguyen.dateme.common.Result
import phu.nguyen.dateme.data.chat.ChatRepository
import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.data.chat.model.Message
import java.util.*
import javax.inject.Inject

class ChattingViewModel(
    private val chatRepository: ChatRepository
) : ViewModel() {
    private val uid = FirebaseAuth.getInstance().uid!!

    private var _chat: Chat? = null
    fun setChat(chat: Chat) {
        _chat = chat
    }
    val chat: Chat?
        get() = _chat

    var currentMessage: String = "test"

    private val _resultChat: MutableLiveData<Result<Chat>> = MutableLiveData()
    val resultChat: LiveData<Result<Chat>>
        get() = _resultChat

    fun saveMessage() {
        
        val message: Message = Message(
            timestamp = Date().time,
            content = currentMessage,
            contentType = Message.TYPE_TEXT,
            senderId = uid,
            status = Message.SEND_SUCCESS
        )

        currentMessage = ""

        viewModelScope.launch {
            try {
                chatRepository.saveChat(_chat!!.id, message)
            } catch (e: Exception) {
                _resultChat.value = Result.Error(e)
            }
        }


    }
}

class ChattingViewModelFactory @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChattingViewModel(chatRepository) as T
    }

}