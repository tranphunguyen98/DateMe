package phu.nguyen.dateme.remote.source.profile

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import phu.nguyen.dateme.remote.model.NetworkProfile
import timber.log.Timber
import javax.inject.Inject
import kotlinx.coroutines.tasks.await as myAwait

class ProfileService @Inject constructor() {
    init {
        Timber.d("ProfileService")
    }

    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()

    suspend fun getTopProfiles(): List<NetworkProfile> = withContext(Dispatchers.IO) {
//        Log.d("testRemote", "a")
//        Log.d("testCoroutine", Thread.currentThread().name)

        val profiles = mutableListOf<NetworkProfile>()
        val snapshotProfiles = db.collection("users").get().myAwait()

//            Log.d("testCoroutine1", Thread.currentThread().name)
        val listMatching = getListProfileIdSwiped()

        for (document in snapshotProfiles.documents) {
//            Log.d("testCoroutine2", Thread.currentThread().name)

            val profile = document.toObject<NetworkProfile>()
//            Log.d("TestCoroutine", profile?.name ?: "null ne")

            profile?.let {
                if (it.uid != auth.uid && !listMatching.contains(it.uid)) {
                    profiles.add(it)
                }
            }
        }
//        Log.d("TestCoroutine", profiles[0].images.size.toString())
        return@withContext profiles
    }

    private suspend fun getListProfileIdSwiped(): List<String> = withContext(Dispatchers.IO) {
        val matchingIds = mutableListOf<String>()
        val snapshotMatchings =
            db.collection("users").document(auth.uid!!).collection("matchings").get().myAwait()

        for (document in snapshotMatchings.documents) {
            matchingIds.add(document.id)
        }

        return@withContext matchingIds
    }

//    private suspend fun getImagesById(id: String) : List<String> {
//        val images = mutableListOf<String>()
//
//        val snapshotImages =
//            db.collection("profiles/$id/images").get().myAwait()
//
//        for (document in snapshotImages.documents) {
//            val image = document.getString("link")
//            image?.let {
//                images.add(image)
//            }
//        }
//        return images
//    }
}