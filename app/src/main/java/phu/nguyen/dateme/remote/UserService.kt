package phu.nguyen.dateme.remote

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import phu.nguyen.dateme.remote.model.NetworkUser
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.tasks.await as myAwait

class UserService @Inject constructor() {

    private val db = Firebase.firestore

    suspend fun getUser(uid: String): NetworkUser = withContext(Dispatchers.IO) {
        val snapshotUser = db.collection("users").document(uid).get().myAwait()

        val user = snapshotUser.toObject<NetworkUser>()

        Timber.d("isGlobal = ${user?.showGlobal}")

        user?.let {
            val images = getImagesById(uid)
            return@withContext user.copy(uid = uid,images = images)
        }

        throw IOException("Can't get User by id = $uid")
    }


    private suspend fun getImagesById(id: String): List<String> {
        val images = mutableListOf<String>()

        val snapshotImages =
            db.collection("users/$id/images").get().myAwait()

        for (document in snapshotImages.documents) {
            val image = document.getString("link")
            image?.let {
                images.add(image)
            }
        }
        return images
    }
}