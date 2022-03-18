package com.example.kotlincrossplatformapp.android

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import com.example.kotlincrossplatformapp.Greeting
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener { pickImage() }
        val client = Http.newBuilder().build();

    }
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data
                val image = findViewById<ImageView>(R.id.imageView)
                image.setImageURI(uri)
            }
        }
    fun pickImage(){
        // Launches photo picker in single-select mode.
        // This means that the user can select one photo or video.
        val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        getResult.launch(intent)
    }
}
