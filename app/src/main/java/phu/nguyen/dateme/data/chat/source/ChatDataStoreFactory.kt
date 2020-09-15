package phu.nguyen.dateme.data.chat.source

import phu.nguyen.dateme.data.chat.repository.ChatDataStore
import javax.inject.Inject

/**
 * Create an instance of a ChatDataStore
 */
class ChatDataStoreFactory @Inject constructor(
    private val chatCacheDataStore: ChatCacheDataStore,
    private val chatRemoteDataStore: ChatRemoteDataStore
) {
    fun retrieveCacheDataStore(): ChatDataStore {
        return chatCacheDataStore
    }

    fun retrieveRemoteDataStore(): ChatDataStore {
        return chatRemoteDataStore
    }
}