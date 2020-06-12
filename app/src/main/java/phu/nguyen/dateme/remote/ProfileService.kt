package phu.nguyen.dateme.remote

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import phu.nguyen.dateme.remote.model.NetworkProfile
import javax.inject.Inject
import kotlinx.coroutines.tasks.await as myAwait

class ProfileService @Inject constructor() {
    init {
        Log.d("testInit", "ProfileService")
    }

    private val db = Firebase.firestore

    suspend fun getTopProfiles(): List<NetworkProfile> = withContext(Dispatchers.IO) {
        Log.d("testRemote", "a")
//        Log.d("testCoroutine", Thread.currentThread().name)

        val profiles = mutableListOf<NetworkProfile>()
        val snapshotProfiles = db.collection("profiles").get().myAwait()

        val job = launch {
//            Log.d("testCoroutine1", Thread.currentThread().name)

            for (document in snapshotProfiles.documents) {
//                Log.d("testCoroutine2", Thread.currentThread().name)

                val profile = document.toObject<NetworkProfile>()

                Log.d("TestCoroutine", profile?.name ?: "null ne")

                profile?.let {
                    profile.id = document.id
                    launch {
                        getImagesById(profile.id)
                        it.images = getImagesById(profile.id)
                        profiles.add(it)
                    }
                }
            }
        }
        job.join()
        Log.d("TestCoroutine", profiles[0].images.size.toString())
        return@withContext profiles
    }

    private suspend fun getImagesById(id: String) : List<String> {
        val images = mutableListOf<String>()

        val snapshotImages =
            db.collection("profiles/$id/images").get().myAwait()

        for (document in snapshotImages.documents) {
            val image = document.getString("link")
            image?.let {
                images.add(image)
            }
        }
        return images
    }
}