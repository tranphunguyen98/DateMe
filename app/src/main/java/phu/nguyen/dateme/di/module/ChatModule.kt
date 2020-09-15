package phu.nguyen.dateme.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import phu.nguyen.dateme.cache.ChatCacheDataSourceImpl
import phu.nguyen.dateme.data.chat.ChatRepository
import phu.nguyen.dateme.data.chat.ChatRepositoryImpl
import phu.nguyen.dateme.data.chat.repository.ChatCacheDataSource
import phu.nguyen.dateme.data.chat.repository.ChatRemoteDataSource
import phu.nguyen.dateme.remote.source.chat.ChatRemoteDataSourceImpl

@Module
@InstallIn(FragmentComponent::class)
abstract class ChatModule {

    @Binds
    abstract fun bindChatRemoteSource(
        chatRemoteDataSourceImpl: ChatRemoteDataSourceImpl
    ): ChatRemoteDataSource

    @Binds
    abstract fun bindChatCacheDataSource(
        chatCacheDataSourceImpl: ChatCacheDataSourceImpl
    ): ChatCacheDataSource

    @Binds
    abstract fun provideChatRepository(
        chatDataRepositoryImpl: ChatRepositoryImpl
    ): ChatRepository
}