package phu.nguyen.dateme.remote.source.chat

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import phu.nguyen.dateme.data.model.Interaction
import phu.nguyen.dateme.remote.model.NetworkChat
import phu.nguyen.dateme.remote.model.NetworkMessage
import phu.nguyen.dateme.remote.model.NetworkProfile
import javax.inject.Inject
import kotlinx.coroutines.tasks.await as myAwait

class ChatService @Inject constructor() {
    private val dbChat = Firebase.firestore.collection("chats")
    private val dbUser = Firebase.firestore.collection("users")
    private val auth = FirebaseAuth.getInstance()

    private fun getIdPartner(chat: NetworkChat): String =
        if (chat.ids[0] == auth.uid!!) chat.ids[1] else chat.ids[0]

    suspend fun saveFirstChat(interaction: Interaction) = withContext(Dispatchers.IO) {
        val idMessage = if (auth.uid!! < interaction.uid) {
            auth.uid!! + '-' + interaction.uid
        } else {
            interaction.uid + '-' + auth.uid!!
        }

        val networkChat =
            NetworkChat(
                id = idMessage, matchingTime = interaction.matchingTime, new = true,
                ids = listOf(
                    auth.uid!!, interaction.uid
                )
            )

        dbChat.document(idMessage).set(networkChat, SetOptions.merge())
    }

    suspend fun saveChat(chatId: String, message: NetworkMessage) = withContext(Dispatchers.IO){
        val chatRef =
            dbChat
                .document(chatId)
                .collection("messages")
                .add(message)
                .myAwait()

    }

    suspend fun getChats(): List<NetworkChat> = withContext(Dispatchers.IO) {
        val chats = mutableListOf<NetworkChat>()

        val snapshotChats =
            dbChat
                .whereArrayContains("ids", auth.uid!!)
                .get()
                .myAwait()

        snapshotChats.documents.forEach { document ->
            val networkChat = document.toObject(NetworkChat::class.java)
            networkChat?.let {
                chats.add(it)
            }
        }

        for (i in chats.indices) {
            val idPartner = getIdPartner(chats[i])

            val userSnapshot = dbUser.document(idPartner).get().myAwait()

            val profile = userSnapshot.toObject(NetworkProfile::class.java)
            profile?.let {
                chats[i] = chats[i].copy(
                    matchingName = profile.name,
                    matchingAvatar = profile.images[0]
                )
            }
        }

        return@withContext chats
    }
}