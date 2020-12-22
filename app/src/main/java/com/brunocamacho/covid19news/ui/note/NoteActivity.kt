package com.brunocamacho.covid19news.ui.note

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brunocamacho.covid19news.databinding.NoteActivityBinding

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: NoteActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = NoteActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webview.settings.javaScriptEnabled = true

        intent.extras?.let { extras ->
            extras.getString("url")?.let { url ->
                binding.webview.loadUrl(url)
            }
        }
    }
}