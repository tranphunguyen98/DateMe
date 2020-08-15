package phu.nguyen.dateme.remote.source.matching

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import phu.nguyen.dateme.data.model.Interaction
import phu.nguyen.dateme.remote.model.NetworkInteraction
import timber.log.Timber
import javax.inject.Inject
import kotlinx.coroutines.tasks.await as myAwait

class MatchingService @Inject constructor() {
    init {
        Timber.d("MatchingService")
    }

    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()

    suspend fun getMatching(): List<NetworkInteraction> = withContext(Dispatchers.IO) {
        val matchings = mutableListOf<NetworkInteraction>()
        val snapshotMatchings =
            db.collection("users").document(auth.uid!!).collection("interactions").get().myAwait()

        for (document in snapshotMatchings.documents) {
            val matching = document.toObject<NetworkInteraction>()

            matching?.let {
                matchings.add(it)
            }
        }
        return@withContext matchings
    }

    private suspend fun markIsMatch(matching: NetworkInteraction, uidSource: String) {
        db.collection("users").document(uidSource)
            .collection("interactions").document(matching.uid)
            .set(
                matching.copy(match = true), SetOptions.merge()
            ).myAwait()
    }

    private suspend fun markIsLike(matching: NetworkInteraction, uidSource: String) {
        db.collection("users").document(uidSource)
            .collection("interactions").document(matching.uid)
            .set(
                matching, SetOptions.merge()
            ).myAwait()
    }

    suspend fun checkAndSaveMatching(uidSource: String): Boolean {

        //get matching of swiped user
        val snapshotMatching =
            db.collection("users").document(uidSource)
                .collection("interactions").document(auth.uid!!).get().myAwait()

        val matching = snapshotMatching.toObject<NetworkInteraction>()

        if (matching != null) {
            if (matching.interactiveType == Interaction.LIKE || matching.interactiveType == Interaction.SUPER_LIKE) {
                Timber.d("MATCH ne! $matching")
                markIsMatch(matching, uidSource)
                return true
            }
        } else {
            markIsLike(NetworkInteraction(auth.uid!!, Interaction.LIKE_YOU, match = false), uidSource)
        }

        Timber.d("FAIL MATCH roi ne! :(")
        return false
    }

    suspend fun saveMatching(matching: NetworkInteraction) {
        db.collection("users").document(auth.uid!!).collection("interactions").document(matching.uid)
            .set(
                matching, SetOptions.merge()
            ).myAwait()
    }
}