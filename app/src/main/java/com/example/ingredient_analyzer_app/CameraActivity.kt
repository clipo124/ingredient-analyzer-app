package com.example.ingredient_analyzer_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ingredient_analyzer_app.databinding.ActivityCameraBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class CameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraBinding
    private lateinit var takePhotoButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_camera)
        takePhotoButton = binding.takePhotoButton
        takePhotoButton.setOnClickListener {
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }

    }
}