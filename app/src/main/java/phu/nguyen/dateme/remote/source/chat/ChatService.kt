package phu.nguyen.dateme.remote.source.chat

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import phu.nguyen.dateme.data.model.Interaction
import phu.nguyen.dateme.remote.model.NetworkChat
import javax.inject.Inject

class ChatService @Inject constructor() {
    private val dbMessage = Firebase.firestore.collection("messages")
    private val auth = FirebaseAuth.getInstance()

    suspend fun saveFirstChat(interaction: Interaction) = withContext(Dispatchers.IO){
        val idMessage = if (auth.uid!! < interaction.uid) {
            auth.uid!! + '-' + interaction.uid
        } else {
            interaction.uid + '-' + auth.uid!!
        }

        val networkChat = NetworkChat(matchingTime = interaction.matchingTime, new = true)

        dbMessage.document(idMessage).set(networkChat, SetOptions.merge())
    }

    suspend fun getChat(): List<NetworkChat> = withContext(Dispatchers.IO) {
        val chats = mutableListOf<NetworkChat>()
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