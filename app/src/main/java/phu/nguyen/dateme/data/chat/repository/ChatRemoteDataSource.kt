package phu.nguyen.dateme.data.chat.repository

import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.data.chat.model.Message
import phu.nguyen.dateme.data.model.Interaction


/**
 * Interface defining methods for the remoting of Matching. This is to be implemented by the
 * remote layer, using this interface as a way of communicating.
 */

interface ChatRemoteDataSource {
    suspend fun getChats() : List<Chat>
    suspend fun saveChat(idChat: String, message: Message)
    suspend fun getMoreChat(idChat: String): Chat
    suspend fun getChat(idChat: String): Chat
    suspend fun saveFirstChat(interaction: Interaction)
}