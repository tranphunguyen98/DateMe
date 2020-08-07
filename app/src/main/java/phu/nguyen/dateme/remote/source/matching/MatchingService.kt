package phu.nguyen.dateme.remote.source.matching

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import phu.nguyen.dateme.data.model.Matching
import phu.nguyen.dateme.remote.model.NetworkMatching
import timber.log.Timber
import javax.inject.Inject
import kotlinx.coroutines.tasks.await as myAwait

class MatchingService @Inject constructor() {
    init {
        Timber.d("MatchingService")
    }

    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()

    suspend fun getMatching(): List<NetworkMatching> = withContext(Dispatchers.IO) {
        val matchings = mutableListOf<NetworkMatching>()
        val snapshotMatchings =
            db.collection("users").document(auth.uid!!).collection("matchings").get().myAwait()

        for (document in snapshotMatchings.documents) {
            val matching = document.toObject<NetworkMatching>()

            matching?.let {
                matchings.add(it)
            }
        }
        return@withContext matchings
    }

    private suspend fun markIsMatch(matching: NetworkMatching, uidSource: String) {
        db.collection("users").document(uidSource)
            .collection("matchings").document(matching.uid)
            .set(
                matching.copy(isMatch = true), SetOptions.merge()
            ).myAwait()
    }

    suspend fun checkAndSaveMatching(uidSource: String): Boolean {
        val snapshotMatching =
            db.collection("users").document(uidSource)
                .collection("matchings").document(auth.uid!!).get().myAwait()

        val matching = snapshotMatching.toObject<NetworkMatching>()

        if (matching != null && (matching.typeSwipe == Matching.LIKE || matching.typeSwipe == Matching.SUPER_LIKE)) {
            Timber.d("MATCH ne! $matching")
            markIsMatch(matching,uidSource)
            return true
        }


        Timber.d("FAIL MATCH roi ne! :(")
        return false
    }

    suspend fun saveMatching(matching: NetworkMatching) {
        db.collection("users").document(auth.uid!!).collection("matchings").document(matching.uid)
            .set(
                matching, SetOptions.merge()
            ).myAwait()
    }
}