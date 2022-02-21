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
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Environment
import android.os.StrictMode
import androidx.core.content.FileProvider
import coil.ImageLoader
import coil.imageLoader
import coil.request.ImageRequest
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class ImageActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityImageBinding
    private lateinit var img: String
    private lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityImageBinding.inflate(layoutInflater)

        imageLoader = ImageLoader.Builder(this)
            .availableMemoryPercentage(0.25)
            .crossfade(true)
            .build()

        hideSystemBars()

        setContentView(_binding.root)
        img = intent.getStringExtra(IMG)!!

        img.let {
            _binding.image.load(it)
        }

        handleImageViewer()
        if (img.isNotEmpty()) {
            _binding.shareBtn.setOnClickListener { shareImage(img) }
        }
    }

    private fun handleImageViewer() {
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

    private fun shareImage(url: String?) {
        // for Image send ignore URI error
        val builder: StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val request = ImageRequest.Builder(this)
            .data(url)
            .allowHardware(false)
            .target(
                onStart = { placeholder ->
                    // Handle the placeholder drawable.
                },
                onSuccess = { result ->
                    // Handle the successful result.
                    val bitmap = (result as BitmapDrawable).bitmap
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "image/*"
                    intent.putExtra(Intent.EXTRA_STREAM, getBitmapFromView(bitmap))
                    startActivity(Intent.createChooser(intent, "Share Image"))
                },
                onError = { error ->
                    // Handle the error drawable.
                }
            )
            .build()
        imageLoader.enqueue(request)
    }


    fun getBitmapFromView(bmp: Bitmap?): Uri? {
        var bmpUri: Uri? = null
        try {
            // getExternalFilesDir() + "/Pictures" should match the declaration in fileprovider.xml paths
            val file = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "share_image_" + System.currentTimeMillis() + ".png"
            )

            val out = FileOutputStream(file)
            bmp?.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.close()
            // wrap File object into a content provider. NOTE: authority here should match authority in manifest declaration
            bmpUri = FileProvider.getUriForFile(this, "com.codepath.fileprovider", file)

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bmpUri
    }

}
