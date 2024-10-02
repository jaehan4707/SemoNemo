package com.semonemo.presentation.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Base64

fun encodeImageToBase64FromUri(
    context: Context,
    uri: Uri?,
): String? {
    if (uri == null) {
        return null
    }
    val filePath = uri.toAbsolutePath(context)
    val file = File(filePath)

    return try {
        val imageBytes = file.readBytes()
        Base64.getEncoder().encodeToString(imageBytes)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun decodeBase64ToImage(base64String: String): Uri? = Uri.parse("data:image/png;base64,$base64String")

fun saveBase64ParseImageToFile(
    context: Context,
    base64Uri: String,
): Uri? {
    val base64String = base64Uri.replace("data:image/png;base64,", "")
    val imageBytes = Base64.getDecoder().decode(base64String)
    val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    return if (bitmap != null) {
        saveBitmapToFile(context, bitmap, "image_${System.currentTimeMillis()}.png")
    } else {
        null
    }
}

fun saveBitmapToFile(
    context: Context,
    bitmap: Bitmap,
    fileName: String,
): Uri? {
    val file = File(context.cacheDir, fileName)
    return try {
        FileOutputStream(file).use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
        }
        Uri.fromFile(file)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}
