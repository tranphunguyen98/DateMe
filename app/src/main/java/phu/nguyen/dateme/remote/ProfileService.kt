package phu.nguyen.dateme.remote

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import phu.nguyen.dateme.remote.model.NetworkProfile
import javax.inject.Inject
import kotlinx.coroutines.tasks.await as myAwait

class ProfileService @Inject constructor(){
    init {
        Log.d("testInit", "ProfileService")
    }

    private val db = Firebase.firestore

    suspend fun getTopProfiles(): List<NetworkProfile> = withContext(Dispatchers.IO){
        Log.d("testRemote", "a")

        Log.d("testCoroutine", Thread.currentThread().name)

        val profiles = mutableListOf<NetworkProfile>()
        val snapshot = db.collection("profiles").get().myAwait()
        for (document in snapshot.documents) {
            val profile = document.toObject<NetworkProfile>()
            profile?.let {
                profiles.add(it)
            }
        }
        return@withContext profiles
    }
}