package com.brunocamacho.covid19news.ui.note

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brunocamacho.covid19news.databinding.NoteActivityBinding

class NoteActivity : AppCompatActivity() {

    private lateinit var binding:NoteActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = NoteActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.extras?.getString("url")

        binding.webview.settings.javaScriptEnabled = true

        if (url != null) {
            binding.webview.loadUrl(url)
        }

    }
}