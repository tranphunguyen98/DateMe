package phu.nguyen.dateme.data.chat.repository

import phu.nguyen.dateme.data.chat.model.Chat

/**
 * Interface defining methods for the caching of Profile. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface ChatCacheDataSource {
    suspend fun getChats() : List<Chat>
    suspend fun saveChat(chat: Chat)
    suspend fun getMoreChat(idChat: String): Chat
    suspend fun getChat(idChat: String): Chat
}