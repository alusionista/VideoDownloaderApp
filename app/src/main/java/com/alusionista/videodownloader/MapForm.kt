package com.alusionista.videodownloader

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import okhttp3.OkHttpClient
import okhttp3.Request


class DownloaderViewModel : ViewModel() {
    fun downloadFile(context: Context, url: String, fileName: String) {
        // Create a new video Uri in MediaStore
        val values = ContentValues().apply {
            put(MediaStore.Video.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
            put(MediaStore.Video.Media.IS_PENDING, 1)
        }

        val collection = MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        val videoUri = context.contentResolver.insert(collection, values)

        // Download the video from the URL to the new video Uri
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        client.newCall(request).execute().use { response ->
            val inputStream = response.body?.byteStream()
            context.contentResolver.openOutputStream(videoUri!!).use { outputStream ->
                if (outputStream != null) {
                    inputStream?.copyTo(outputStream)
                }
            }
        }

        // Update the video Uri to indicate that the download is no longer pending
        values.clear()
        values.put(MediaStore.Video.Media.IS_PENDING, 0)
        context.contentResolver.update(videoUri!!, values, null, null)
    }

    fun downloadVideos(context: Context, urls: Array<String>) {
        for (url in urls) {
            val fileName = url.substring(url.lastIndexOf('/') + 1)
            downloadFile(context, url, fileName)
        }
    }



}

@Composable
fun MapForm() {
    val list = remember { mutableListOf<String>() }
    var value = remember { mutableStateOf<String>("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(text = "Add a key-value pair to the map")
        BasicTextField(
            value = value,
            onValueChange = { value = it },
            placeholder = "URL"
        )
        Button(
            onClick = {
                list.add(value.value)
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Add")
        }
        Button(
            onClick = {
                list.clear()
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Remove All")
        }
        Button(
            onClick = {
                list.add(value.value)
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Download")
        }
    }
}

@Preview
@Composable
fun MapFormPreview() {
    MapForm()
}