package phu.nguyen.dateme.remote.source.chat

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import phu.nguyen.dateme.data.model.Interaction
import phu.nguyen.dateme.remote.model.NetworkChat
import phu.nguyen.dateme.remote.model.NetworkProfile
import javax.inject.Inject
import kotlinx.coroutines.tasks.await as myAwait

class ChatService @Inject constructor() {
    private val dbMessage = Firebase.firestore.collection("messages")
    private val dbUser = Firebase.firestore.collection("users")
    private val auth = FirebaseAuth.getInstance()

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

        dbMessage.document(idMessage).set(networkChat, SetOptions.merge())
    }

    suspend fun getChats(): List<NetworkChat> = withContext(Dispatchers.IO) {
        val chats = mutableListOf<NetworkChat>()

        val snapshotChats =
            dbMessage
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
            val idPartner = if (chats[i].ids[0] == auth.uid!!) chats[i].ids[1] else chats[i].ids[0]

            val userSnapshot = dbUser.document(idPartner).get().myAwait()

            val profile = userSnapshot.toObject(NetworkProfile::class.java)
            profile?.let {
                chats[i] = chats[i].copy(
                    matchingName = profile.name,
                    matchingAvatar = profile.images[0]
                )
            }
        }
//        val matchingIds = mutableListOf<String>()
//        val snapshotMatchings =
//            dbMessage.collection("users")
//                .document(auth.uid!!)
//                .collection("interactions")
//                .whereEqualTo("matching", true)
//                .get()
//                .myAwait()
//
//        snapshotMatchings.documents.forEach {
//            matchingIds.add(it.id)
//        }

        return@withContext chats
    }
}