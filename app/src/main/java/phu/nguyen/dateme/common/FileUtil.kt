package phu.nguyen.dateme.common

import android.content.Context
import java.io.File

fun deleteDir(dir: File?): Boolean {
    return if (dir != null && dir.isDirectory) {
        val children = dir.list()
        for (i in children.indices) {
            val success = deleteDir(File(dir, children[i]))
            if (!success) {
                return false
            }
        }
        dir.delete()
    } else if (dir != null && dir.isFile) {
        dir.delete()
    } else {
        false
    }
}

fun deleteCache(context: Context) {
    try {
        val dir: File = context.cacheDir
        deleteDir(dir)
    } catch (e: Exception) {
    }
}