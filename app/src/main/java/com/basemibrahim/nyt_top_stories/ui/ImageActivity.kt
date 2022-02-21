package com.basemibrahim.nyt_top_stories.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import coil.load
import com.basemibrahim.nyt_top_stories.databinding.ActivityImageBinding
import com.basemibrahim.nyt_top_stories.utils.Constants.Companion.IMG
import com.igreenwood.loupe.Loupe


class ImageActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityImageBinding
    private lateinit var img: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityImageBinding.inflate(layoutInflater)
          hideSystemBars()

        setContentView(_binding.root)
        img = intent.getStringExtra(IMG)!!

        img.let {
            _binding.image.load(it)}

        handleImageViewer()
    }

    private fun handleImageViewer()
    {
         Loupe.create(_binding.image, _binding.container) { // imageView is your ImageView
            onViewTranslateListener = object : Loupe.OnViewTranslateListener {

                override fun onStart(view: ImageView) {
                    // called when the view starts moving
                }

                override fun onViewTranslate(view: ImageView, amount: Float) {
                    // called whenever the view position changed
                }

                override fun onRestore(view: ImageView) {
                    // called when the view drag gesture ended
                }

                override fun onDismiss(view: ImageView) {
                    // called when the view drag gesture ended
                    finish()
                }
            }
        }
    }

        private fun hideSystemBars() {
            val windowInsetsController =
                window?.let { ViewCompat.getWindowInsetsController(it.decorView) } ?: return
            // Configure the behavior of the hidden system bars
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            // Hide both the status bar and the navigation bar
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        }

    }
