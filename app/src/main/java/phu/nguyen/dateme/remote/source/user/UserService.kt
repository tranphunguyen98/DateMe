package phu.nguyen.dateme.remote.source.user

import android.net.Uri
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import phu.nguyen.dateme.remote.model.NetworkUser
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.tasks.await as myAwait

@Singleton
class UserService @Inject constructor() {

    private val db = Firebase.firestore
    private val storage: FirebaseStorage by lazy {
        FirebaseStorage.getInstance()
    }

    init {
        Timber.d("init UserService")
    }

    suspend fun getUser(uid: String): NetworkUser = withContext(Dispatchers.IO) {
        val snapshotUser = db.collection("users").document(uid).get().myAwait()

        val user = snapshotUser.toObject<NetworkUser>()

        Timber.d("isGlobal = ${user?.showGlobal}")

        user?.let {
            //val images = getImagesById(uid)
            return@withContext user.copy(uid = uid)
        }

        throw IOException("Can't get User by id = $uid")
    }

    suspend fun saveUser(user: NetworkUser) {
        db.collection("users").document(user.uid).set(
            user, SetOptions.merge()
        ).myAwait()
    }

    suspend fun saveImage(path: String, uri: Uri): String = withContext(Dispatchers.IO) {
        val task =
            storage.reference.child(path)
                .putFile(uri).myAwait()
        return@withContext task.storage.downloadUrl.myAwait().toString()
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