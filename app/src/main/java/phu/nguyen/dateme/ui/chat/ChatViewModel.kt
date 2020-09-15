package phu.nguyen.dateme.ui.chat

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import phu.nguyen.dateme.common.Result
import phu.nguyen.dateme.data.chat.ChatRepository
import phu.nguyen.dateme.data.chat.model.Chat
import javax.inject.Inject

class ChatViewModel(private val chatRepository: ChatRepository) : ViewModel() {
    private val _resultChat: MutableLiveData<Result<List<Chat>>> = MutableLiveData()
    val resultChat: LiveData<Result<List<Chat>>>
        get() = _resultChat

    init {
        getData()
    }

    private fun getData() {
        _resultChat.value = Result.Waiting
        viewModelScope.launch {
            try {
                _resultChat.value =
                    Result.Success(chatRepository.getChats())
            } catch (e: Exception) {
                _resultChat.value = Result.Error(e)
            }
        }

    }
}

class ChatViewModelFactory @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatViewModel(
            chatRepository
        ) as T
    }
}