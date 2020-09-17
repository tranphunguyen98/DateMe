package phu.nguyen.dateme.data.chat.repository

import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.data.chat.model.Message
import phu.nguyen.dateme.data.model.Interaction

/**
 * Interface defining methods for the data operations related to Matching.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */

interface ChatDataStore {
    suspend fun getChats() : List<Chat>
    suspend fun saveChat(idChat: String, message: Message)
    suspend fun getMoreChat(idChat: String): Chat
    suspend fun getChat(idChat: String): Chat
    suspend fun saveFirstChat(interaction: Interaction)
}