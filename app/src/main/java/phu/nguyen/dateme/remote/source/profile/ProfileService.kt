package phu.nguyen.dateme.remote.source.profile

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import phu.nguyen.dateme.data.model.Interaction
import phu.nguyen.dateme.remote.model.NetworkInteraction
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
        val listMatching = getListInteraction(-1)

        for (document in snapshotProfiles.documents) {
//            Log.d("testCoroutine2", Thread.currentThread().name)

            val profile = document.toObject<NetworkProfile>()
//            Log.d("TestCoroutine", profile?.name ?: "null ne")

            profile?.let {
                if (it.uid != auth.uid && !isSwiped(listMatching, it.uid)) {
                    profiles.add(it)
                }
            }
        }
//        Log.d("TestCoroutine", profiles[0].images.size.toString())
        return@withContext profiles
    }

    suspend fun getInteractiveProfiles(interactiveType: Int): List<NetworkProfile> = withContext(Dispatchers.IO) {
        val profiles = mutableListOf<NetworkProfile>()

        val listInteraction = getListInteraction(interactiveType)

        listInteraction.forEach {
            val snapshotProfile = db.collection("users").document(it.uid).get().myAwait()
            val profile = snapshotProfile.toObject(NetworkProfile::class.java)

            if(profile != null) {
                profiles.add(profile)
            }
        }

        return@withContext profiles
    }

    private fun isSwiped(listInteraction: List<NetworkInteraction>, id: String): Boolean {
        listInteraction.forEach {
            if (it.uid == id &&
                (it.interactiveType == Interaction.LIKE || it.interactiveType == Interaction.DISLIKE)
            ) {
                return true
            }
        }
        return false
    }

    private suspend fun getListInteraction(interactiveType: Int): List<NetworkInteraction> =
        withContext(Dispatchers.IO) {
            val interactions = mutableListOf<NetworkInteraction>()
            val snapshotMatchings =
                db.collection("users").document(auth.uid!!).collection("interactions").get()
                    .myAwait()

            for (document in snapshotMatchings.documents) {
                val interaction = document.toObject(NetworkInteraction::class.java)
                if(interaction != null) {
                    if(interactiveType == -1) {
                        interactions.add(interaction)
                    } else
                    {
                        if (interaction.interactiveType == interactiveType) {
                            interactions.add(interaction)
                        }
                    }
                }


            }

            return@withContext interactions
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